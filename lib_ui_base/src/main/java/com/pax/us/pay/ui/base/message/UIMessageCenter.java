package com.pax.us.pay.ui.base.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.pax.us.pay.ui.base.constant.ActionCategory;
import com.pax.us.pay.ui.base.constant.InnerBroadcast;
import com.pax.us.pay.ui.base.constant.entry.Confirmation;
import com.pax.us.pay.ui.base.constant.entry.Option;
import com.pax.us.pay.ui.base.constant.entry.Security;
import com.pax.us.pay.ui.base.constant.entry.Signature;
import com.pax.us.pay.ui.base.constant.entry.Text;
import com.pax.us.pay.ui.base.constant.status.Information;
import com.pax.us.pay.ui.base.message.api.IAmountListener;
import com.pax.us.pay.ui.base.message.api.IAmountOptionListener;
import com.pax.us.pay.ui.base.message.api.ICardListener;
import com.pax.us.pay.ui.base.message.api.ICurrencyListener;
import com.pax.us.pay.ui.base.message.api.IInformationListener;
import com.pax.us.pay.ui.base.message.api.IMessageListener;
import com.pax.us.pay.ui.base.message.api.IOptionListener;
import com.pax.us.pay.ui.base.message.api.IRespStatus;
import com.pax.us.pay.ui.base.message.api.ITitleListener;
import com.pax.us.pay.ui.base.message.api.IUIListener;
import com.pax.us.pay.ui.base.message.helper.BaseHelper;
import com.pax.us.pay.ui.constant.parameter.Common;
import com.pax.us.pay.ui.constant.parameter.InputPar;
import com.pax.us.pay.ui.constant.parameter.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

class UIMessageCenter implements ICommModel, Observer {
    private final static Map<String, Class> valueTypeMap = new HashMap<>();

    static {
        valueTypeMap.put(Text.ACTION_ENTER_ADDRESS, String.class);
        valueTypeMap.put(Text.ACTION_ENTER_AUTH, String.class);
        valueTypeMap.put(Text.ACTION_ENTER_EXPIRY_DATE, String.class);
        valueTypeMap.put(Text.ACTION_ENTER_ZIPCODE, String.class);
        valueTypeMap.put(Text.ACTION_ENTER_VOUCHER_DATA, String.class);
        valueTypeMap.put(Text.ACTION_ENTER_AVS_DATA, String.class);
        valueTypeMap.put(Text.ACTION_ENTER_REFERENCE_NUMBER, String.class);
        valueTypeMap.put(Text.ACTION_ENTER_INVOICE_NUMBER, String.class);
        valueTypeMap.put(Text.ACTION_ENTER_CLERK_ID, String.class);

        valueTypeMap.put(Text.ACTION_ENTER_TRANS_NO, Integer.class);
        valueTypeMap.put(Option.ACTION_SELECT_EBT_TYPE, Integer.class);
        valueTypeMap.put(Option.ACTION_SELECT_BY_PASS, Integer.class);
        valueTypeMap.put(Option.ACTION_SELECT_CARD_PRESENT, Integer.class);
        valueTypeMap.put(Option.ACTION_SELECT_SUB_TRANS_TYPE, Integer.class);
        valueTypeMap.put(Option.ACTION_SELECT_AID, Integer.class);
        valueTypeMap.put(Option.ACTION_REVERSE_PARTIAL_APPROVAL, Integer.class);
        valueTypeMap.put(Option.ACTION_SUPPLEMENT_PARTIAL_APPROVAL, Integer.class);

        valueTypeMap.put(Security.ACTION_ENTER_VCODE, Integer.class);
        valueTypeMap.put(Security.ACTION_ENTER_PIN, Integer.class);
        valueTypeMap.put(Security.ACTION_INPUT_ACCOUNT, Integer.class);
        valueTypeMap.put(Security.ACTION_ENTER_CARD_LAST_4_DIGITS, Integer.class);
        valueTypeMap.put(Security.ACTION_ENTER_CARD_ALL_DIGITS, Integer.class);

        valueTypeMap.put(Text.ACTION_ENTER_AMOUNT, Long.class);
        valueTypeMap.put(Text.ACTION_ENTER_TIP, Long.class);
        valueTypeMap.put(Text.ACTION_ENTER_FSA_AMOUNT, Long.class);
        valueTypeMap.put(Text.ACTION_ENTER_CAHSBACK, Long.class);

        valueTypeMap.put(Signature.ACTION_SIGNATURE, Short[].class);
    }

    private Context context;
    private List<Intent> receiverDataCacheList = new LinkedList<>();
    private BroadcastReceiver receiver;
    private BroadcastSender sender;

    private ICommModel.RespResult<RespMessage> result;

    private TransactionFinishListener transFinishListener;

    private String currentAction;
    private ReqMessage reqMessage;
    private IRespStatus resp;

    private static UIMessageCenter instance;

    public static UIMessageCenter getInstance(Context context) {
        if (instance == null) {
            instance = new UIMessageCenter(context.getApplicationContext());
        }
        return instance;
    }

    private UIMessageCenter(Context context) {
        this.context = context.getApplicationContext();
    }

    public void registerUICenter(Context context, IUIListener uiListener, BaseHelper helper, Intent intent, IRespStatus respStatus) {
        this.context = context;
        this.resp = respStatus;
        this.currentAction = intent.getAction();
        reqMessage = new ReqMessage();
        setReqMessage(intent);
        registerUIDesignReceiver();
        showUI(uiListener);
        helper.addObserver(UIMessageCenter.this);
        Log.i("Obsever", "helper.addObserver : " + currentAction);

    }

    public void unregisterUICenter(BaseHelper helper) {
        helper.deleteObserver(UIMessageCenter.this);
        Log.i("Obsever", "helper.deleteObserver : " + currentAction);
    }

    @Override
    public void update(Observable observable, Object o) {
        //Log.i("Obsever", "update !");
        if (observable != null) {
            getSendHandler(new ICommModel.RespResult() {
                @Override
                public void onSuccess() {
                    resp.respAccept();
                }

                @Override
                public void onFailure(RespMessage respMessage) {
                    resp.respDecline(respMessage);
                }

                @Override
                public void onComplete() {
                    resp.respComplete();
                }
            });
            if (o instanceof String) {
                if (o.equals("Abort")) {
                    sendObjAbort();
                } else if (o.equals("Prev")) {
                    sendObjPrev();
                } else
                    sendObjNext(o);
            } else
                sendObjNext(o);
        }
    }

    /**
     * This should be called in your Application onCreate. This initialize the BroadcastReceiver.
     */
    public void registerUIDesignReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(InnerBroadcast.respAction.BROADCAST_ACTION_ACCEPTED);
        intentFilter.addAction(InnerBroadcast.respAction.BROADCAST_ACTION_DECLINED);
        intentFilter.addAction(InnerBroadcast.reqAction.AREA);
        intentFilter.addAction(Information.TRANS_COMPLETED);
        registerUIDesignReceiver(intentFilter);
    }

    private void registerUIDesignReceiver(IntentFilter intentFilter) {
        //final IBroadcastMessage broadcastMessage = null;
        receiverDataCacheList.clear();
        sender = new BroadcastSender(context);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                RespMessage respMessage = new RespMessage();

                if (TextUtils.isEmpty(action))
                    return;
                Log.i("UIDesignReceiver", "onReceive action : " + action);
                switch (action) {
                    case InnerBroadcast.respAction.BROADCAST_ACTION_ACCEPTED:
                        //notifyUI(respMessage);
                        Log.i("BroadcastReceiver", "BROADCAST_ACTION_ACCEPTED, receiver need unregisterReceiver ");
                        result.onSuccess();
                        if (receiver != null) {
                            context.unregisterReceiver(receiver);
                            receiver = null;
                            Log.i("BroadcastReceiver", "BROADCAST_ACTION_ACCEPTED receiver unregisterReceiver ");
                        }
                        break;
                    case InnerBroadcast.respAction.BROADCAST_ACTION_DECLINED:
                        respMessage.setResultCode(intent.getStringExtra(Response.RESULT_CODE));
                        respMessage.setResultMsg(intent.getStringExtra(Response.RESULT_MSG));
                        result.onFailure(respMessage);
                        break;
                    case Information.TRANS_COMPLETED:
                        //notifyUI(respMessage);
                        Log.i("BroadcastReceiver", "TRANS_COMPLETED, receiver need unregisterReceiver ");
                        result.onComplete();
                        if (receiver != null) {
                            context.unregisterReceiver(receiver);
                            receiver = null;
                            Log.i("BroadcastReceiver", "TRANS_COMPLETED receiver unregisterReceiver! ");
                        }
                        receiverDataCacheList.clear();
                        if (transFinishListener != null) {
                            transFinishListener.onFinish();
                        } else {
                            receiverDataCacheList.add(intent);
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        context.registerReceiver(receiver, intentFilter);
    }

    public void registerTransactionFinishListener(TransactionFinishListener transFinishListener) {
        this.transFinishListener = transFinishListener;
        Intent cacheIntent = detectCacheAction(Information.TRANS_COMPLETED);
        if (cacheIntent != null) {
            transFinishListener.onFinish();
            receiverDataCacheList.remove(cacheIntent);
        }
    }

    public void unregisterTransactionFinishListener() {
        transFinishListener = null;
        receiverDataCacheList.clear();

    }

    public void init() {
        sender = null;
        transFinishListener = null;
        receiverDataCacheList.clear();
    }

    private Intent detectCacheAction(String action) {
        for (Intent intent : receiverDataCacheList) {
            String curAction = intent.getAction();
            if (action.equals(curAction)) {
                return intent;
            }
        }
        return null;
    }

    @Override
    public void getSendHandler(RespResult result) {
        this.result = result;
    }


    public interface TransactionFinishListener {
        void onFinish();
    }

//    /**
//     * When you need to terminate the transaction, you can call this. <br>
//     *
//     * @param senderPackage The package name of the transaction service.
//     */
//    public void sendAbort(String senderPackage) {
//        Intent intent = new Intent();
//        sender.send(senderPackage, InnerBroadcast.reqAction.ABORT, intent);
//    }


    public void sendObjAbort() {
        Log.i("BroadcastReceiver", reqMessage.getReqAction() + "sendObjAbort ");
        Intent intent = new Intent();
        intent.setPackage(reqMessage.getReqPackage());
        intent.setAction(InnerBroadcast.reqAction.ABORT);
        sender.send(intent);
    }

    public void sendObjPrev() {
        Intent intent = new Intent();
        intent.setPackage(reqMessage.getReqPackage());
        intent.setAction(InnerBroadcast.reqAction.PREV);
        sender.send(intent);
    }

    private void setReqMessage(Intent intent) {
        List<String> options = null;
        currentAction = intent.getAction();
        reqMessage.setMessage(intent.getStringExtra(Common.PARA_MESSAGE));
        reqMessage.setReqPackage(intent.getStringExtra(Common.PARA_PACKAGE));
        reqMessage.setReqAction(intent.getAction());
        if (intent.getCategories().contains(ActionCategory.UI.CATEGORY_SECURITY))
            reqMessage.setReqCategory(ActionCategory.UI.CATEGORY_SECURITY);
        else if (intent.getCategories().contains(ActionCategory.UI.CATEGORY_TEXT))
            reqMessage.setReqCategory(ActionCategory.UI.CATEGORY_TEXT);
        else if (intent.getCategories().contains(ActionCategory.UI.CATEGORY_SELECT_OPTION))
            reqMessage.setReqCategory(ActionCategory.UI.CATEGORY_SELECT_OPTION);
        else if (intent.getCategories().contains(ActionCategory.UI.CATEGORY_SIGNATURE))
            reqMessage.setReqCategory(ActionCategory.UI.CATEGORY_SIGNATURE);
        else if (intent.getCategories().contains(ActionCategory.UI.CATEGORY_CONFIRMATION))
            reqMessage.setReqCategory(ActionCategory.UI.CATEGORY_CONFIRMATION);
        else
            reqMessage.setReqCategory("");

        String[] array = intent.getStringArrayExtra(Common.PARA_OPTIONS);
        if (array != null)
            options = Arrays.asList(array);
        reqMessage.setReqOption(options);
        switch (currentAction) {
            case Text.ACTION_ENTER_TIP:
                reqMessage.setCurrency(intent.getStringExtra(InputPar.Amount.PARA_CURRENCY));
                String[] tipArray = intent.getStringArrayExtra(InputPar.Amount.PARA_TIP_OPTIONS);
                if (tipArray != null)
                    reqMessage.setAmountOption(Arrays.asList(tipArray));
                break;
            case Text.ACTION_ENTER_AMOUNT:
                reqMessage.setCurrency(intent.getStringExtra(InputPar.Amount.PARA_CURRENCY));
                break;
            case Signature.ACTION_SIGNATURE:
            case Text.ACTION_ENTER_FSA_AMOUNT:
            case Option.ACTION_REVERSE_PARTIAL_APPROVAL:
            case Option.ACTION_SUPPLEMENT_PARTIAL_APPROVAL:
                reqMessage.setCurrency(intent.getStringExtra(InputPar.Amount.PARA_CURRENCY));
                reqMessage.setAmount(intent.getLongExtra(InputPar.Amount.PARA_DISP_AMOUNT, 0));
                break;
            case Security.ACTION_INPUT_ACCOUNT:
                reqMessage.setCurrency(intent.getStringExtra(InputPar.Amount.PARA_CURRENCY));
                reqMessage.setAmount(intent.getLongExtra(InputPar.Amount.PARA_DISP_AMOUNT, 0));
                reqMessage.setEnableEntryManual(intent.getBooleanExtra(InputPar.Account.PARA_ENABLE_MANUAL, true));
                reqMessage.setEnableSwipe(intent.getBooleanExtra(InputPar.Account.PARA_ENABLE_SWIPE, true));
                reqMessage.setEnableInsert(intent.getBooleanExtra(InputPar.Account.PARA_ENABLE_INSERT, true));
                reqMessage.setEnableTap(intent.getBooleanExtra(InputPar.Account.PARA_ENABLE_TAP, true));
                break;
            case Text.ACTION_ENTER_CAHSBACK:
                reqMessage.setCurrency(intent.getStringExtra(InputPar.Amount.PARA_CURRENCY));
                String[] amountArray = intent.getStringArrayExtra(InputPar.Amount.PARA_CASHBACK_OPTIONS);
                if (amountArray != null)
                    reqMessage.setAmountOption(Arrays.asList(amountArray));
                break;
            case Confirmation.ACTION_DISPLAY_TRANS_INFORMATION:
                reqMessage.setTitle(intent.getStringExtra(InputPar.Information.PARA_TITLE));
                Bundle extras = intent.getExtras();
                Set<String> keySet = extras.keySet();
                Map<String, String> map = new LinkedHashMap<>();
                for (String key : keySet) {
                    if (key.equals(InputPar.Information.PARA_TITLE))
                        continue;
                    else
                        map.put(key, (String) extras.get(key));
                }
                if ((map != null) && (map.size() > 0))
                    reqMessage.setInformations(map);
                break;
            default:
                break;
        }
    }

    private void showUI(IUIListener uiListener) {
        int showFlag = 0;
        if (uiListener instanceof IMessageListener) {
            IMessageListener messageListener = (IMessageListener) uiListener;
            messageListener.onShowMessage(reqMessage.getMessage());
            showFlag++;
        }
        if (uiListener instanceof ICurrencyListener) {
            ICurrencyListener currencyListener = (ICurrencyListener) uiListener;
            currencyListener.onShowCurrency(reqMessage.getCurrency());
            showFlag++;
        }

        if (uiListener instanceof IAmountListener) {
            IAmountListener amountListener = (IAmountListener) uiListener;
            amountListener.onShowAmount(reqMessage.getAmount());
            showFlag++;
        }

        if (uiListener instanceof IOptionListener) {
            IOptionListener optionListener = (IOptionListener) uiListener;
            optionListener.onShowOptions(reqMessage.getReqOption());
            showFlag++;
        }

        if (uiListener instanceof ICardListener) {
            ICardListener cardListener = (ICardListener) uiListener;
            cardListener.onShowCard(reqMessage.getEnableEntryManual(), reqMessage.getEnableSwipe(),
                    reqMessage.getEnableInsert(), reqMessage.getEnableTap());
            showFlag++;
        }

        if (uiListener instanceof IAmountOptionListener) {
            IAmountOptionListener amountOptionListener = (IAmountOptionListener) uiListener;
            amountOptionListener.onShowAmountOptions(reqMessage.getAmountOption());
            showFlag++;
        }

        if (uiListener instanceof ITitleListener) {
            ITitleListener titleListener = (ITitleListener) uiListener;
            titleListener.onShowTitle(reqMessage.getTitle());
            showFlag++;
        }

        if (uiListener instanceof IInformationListener) {
            IInformationListener informationListener = (IInformationListener) uiListener;
            informationListener.onShowInformation(reqMessage.getInformations());
            showFlag++;
        }

        if (showFlag == 0)
            throw new RuntimeException("Listener Set Class Not Correct!");
    }


    public void sendObjNext(Object obj) {
        Intent intent = new Intent();
        if ((obj != null) && (obj instanceof Map)) {
            intent.setPackage(reqMessage.getReqPackage());
            if (reqMessage.getReqCategory().equals(ActionCategory.UI.CATEGORY_SECURITY))
                intent.setAction(InnerBroadcast.reqAction.AREA);
            else
                intent.setAction(InnerBroadcast.reqAction.NEXT);

            String reqAction = reqMessage.getReqAction();
            Log.i("BroadcastReceiver", reqAction + " sendObjNext ");

            Class type = valueTypeMap.get(reqAction);
            if (type == null)
                throw new RuntimeException("Action Not Found");

            if (type.equals(String.class)) {
                Map<String, Class> stringMap = (Map<String, Class>) obj;
                for (Map.Entry<String, Class> entry : stringMap.entrySet()) {
                    intent.putExtra(entry.getKey(), entry.getValue());
                }
            } else if (type.equals(Long.class)) {
                Map<String, Long> longMap = (Map<String, Long>) obj;
                for (Map.Entry<String, Long> entry : longMap.entrySet()) {
                    intent.putExtra(entry.getKey(), entry.getValue());
                }
            } else if (type.equals(Integer.class)) {
                Map<String, Long> longMap = (Map<String, Long>) obj;
                for (Map.Entry<String, Long> entry : longMap.entrySet()) {
                    intent.putExtra(entry.getKey(), entry.getValue());
                }
            } else if (type.equals(Short[].class)) {
                Map<String, short[]> map = (Map<String, short[]>) obj;
                for (Map.Entry<String, short[]> entry : map.entrySet()) {
                    short[] array = entry.getValue();
                    intent.putExtra(entry.getKey(), array);
                }
            } else {
                intent.setPackage(reqMessage.getReqPackage());
                intent.setAction(InnerBroadcast.reqAction.NEXT);
            }
        } else {
            intent.setPackage(reqMessage.getReqPackage());
            intent.setAction(InnerBroadcast.reqAction.NEXT);

        }
        sender.send(intent);
    }

}
