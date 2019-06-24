package com.pax.us.pay.ui.core.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.pax.us.pay.ui.constant.parameter.InputParameter;
import com.pax.us.pay.ui.constant.parameter.Response;
import com.pax.us.pay.ui.core.constant.ActionCategory;
import com.pax.us.pay.ui.core.constant.InnerBroadcast;
import com.pax.us.pay.ui.core.constant.entry.Confirmation;
import com.pax.us.pay.ui.core.constant.entry.Option;
import com.pax.us.pay.ui.core.constant.entry.Security;
import com.pax.us.pay.ui.core.constant.entry.Signature;
import com.pax.us.pay.ui.core.constant.entry.Text;
import com.pax.us.pay.ui.core.message.api.IAmountListener;
import com.pax.us.pay.ui.core.message.api.IAmountOptionListener;
import com.pax.us.pay.ui.core.message.api.ICardListener;
import com.pax.us.pay.ui.core.message.api.ICurrencyListener;
import com.pax.us.pay.ui.core.message.api.IInformationListener;
import com.pax.us.pay.ui.core.message.api.IMessageListener;
import com.pax.us.pay.ui.core.message.api.IOptionListener;
import com.pax.us.pay.ui.core.message.api.IRespStatus;
import com.pax.us.pay.ui.core.message.api.ITitleListener;
import com.pax.us.pay.ui.core.message.api.IUIListener;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 */
class UIMessageHandler implements IActionHandler {

    private static UIMessageHandler instance;
    @Nullable
    private BroadcastSender sender = null;
    @Nullable
    private BroadcastReceiver receiver = null;
    @Nullable
    private BaseActionHelper currHelper = null;

    private String currentAction;
    private ReqMessage reqMessage;
    @Nullable
    private IRespStatus resp;

    private UIMessageHandler() {
    }

    static UIMessageHandler getInstance() {
        if (instance == null) {
            instance = new UIMessageHandler();
        }
        return instance;
    }

    synchronized void setCurrentUI(Context context, @Nullable IUIListener uiListener, BaseActionHelper helper, Intent intent, @Nullable IRespStatus respStatus) {
        this.resp = respStatus;
        this.reqMessage = new ReqMessage();
        setReqMessage(intent);
        registerUIDesignReceiver(context);

        helper.setHandler(this);
        currHelper = helper;

        showUI(uiListener);
        Log.i("Observer", "helper.addObserver : " + currentAction);
    }

    /**
     * This should be called in your Application onCreate. This initialize the BroadcastReceiver.
     */
    private void registerUIDesignReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(InnerBroadcast.RespAction.ACCEPTED);
        intentFilter.addAction(InnerBroadcast.RespAction.DECLINED);

        sender = new BroadcastSender(context);
        if (receiver != null) {
            context.unregisterReceiver(receiver);
            receiver = new RespReceiver();
        }
        context.registerReceiver(receiver, intentFilter);
    }

    @Override
    public void sendNext(@Nullable Bundle bundle) {
        Intent intent = new Intent();
        intent.setPackage(reqMessage.getReqPackage());
        intent.setAction(InnerBroadcast.ReqAction.NEXT);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        sender.send(intent);
    }

    @Override
    public void setSecurityArea(@NonNull Bundle bundle) {
        if (sender != null && reqMessage != null) {
            Intent intent = new Intent();
            intent.setPackage(reqMessage.getReqPackage());
            if (reqMessage.getReqCategory().equals(ActionCategory.UI.CATEGORY_SECURITY)) {
                intent.setAction(InnerBroadcast.ReqAction.AREA);
                intent.putExtras(bundle);
                sender.send(intent);
            }
        }
    }

    @Override
    public void sendAbort() {
        if (sender != null && reqMessage != null) {
            Log.i("BroadcastReceiver", reqMessage.getReqAction() + "sendObjAbort ");
            Intent intent = new Intent();
            intent.setPackage(reqMessage.getReqPackage());
            intent.setAction(InnerBroadcast.ReqAction.ABORT);
            sender.send(intent);
        }
    }

    @Override
    public void sendPrev() {
        if (sender != null && reqMessage != null) {
            Intent intent = new Intent();
            intent.setPackage(reqMessage.getReqPackage());
            intent.setAction(InnerBroadcast.ReqAction.PREV);
            sender.send(intent);
        }
    }

    private void setReqMessage(Intent intent) {
        currentAction = intent.getAction();
        reqMessage.setMessage(intent.getStringExtra(InputParameter.Common.PARA_MESSAGE));
        reqMessage.setReqPackage(intent.getStringExtra(InputParameter.Common.PARA_PACKAGE));
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

        String[] array = intent.getStringArrayExtra(InputParameter.Common.PARA_OPTIONS);
        reqMessage.setReqOption(array);
        switch (currentAction) {
            case Text.ACTION_ENTER_TIP:
                reqMessage.setCurrency(intent.getStringExtra(InputParameter.Amount.PARA_CURRENCY));
                String[] tipArray = intent.getStringArrayExtra(InputParameter.Amount.PARA_TIP_OPTIONS);
                if (tipArray != null)
                    reqMessage.setAmountOption(Arrays.asList(tipArray));
                break;
            case Text.ACTION_ENTER_AMOUNT:
                reqMessage.setCurrency(intent.getStringExtra(InputParameter.Amount.PARA_CURRENCY));
                break;
            case Signature.ACTION_SIGNATURE:
            case Text.ACTION_ENTER_FSA_AMOUNT:
            case Option.ACTION_REVERSE_PARTIAL_APPROVAL:
            case Option.ACTION_SUPPLEMENT_PARTIAL_APPROVAL:
                reqMessage.setCurrency(intent.getStringExtra(InputParameter.Amount.PARA_CURRENCY));
                reqMessage.setAmount(intent.getLongExtra(InputParameter.Amount.PARA_DISP_AMOUNT, 0));
                break;
            case Security.ACTION_INPUT_ACCOUNT:
                reqMessage.setCurrency(intent.getStringExtra(InputParameter.Amount.PARA_CURRENCY));
                reqMessage.setAmount(intent.getLongExtra(InputParameter.Amount.PARA_DISP_AMOUNT, 0));
                reqMessage.setEnableEntryManual(intent.getBooleanExtra(InputParameter.Account.PARA_ENABLE_MANUAL, true));
                reqMessage.setEnableSwipe(intent.getBooleanExtra(InputParameter.Account.PARA_ENABLE_SWIPE, true));
                reqMessage.setEnableInsert(intent.getBooleanExtra(InputParameter.Account.PARA_ENABLE_INSERT, true));
                reqMessage.setEnableTap(intent.getBooleanExtra(InputParameter.Account.PARA_ENABLE_TAP, true));
                break;
            case Text.ACTION_ENTER_CASH_BACK:
                reqMessage.setCurrency(intent.getStringExtra(InputParameter.Amount.PARA_CURRENCY));
                String[] amountArray = intent.getStringArrayExtra(InputParameter.Amount.PARA_CASHBACK_OPTIONS);
                if (amountArray != null)
                    reqMessage.setAmountOption(Arrays.asList(amountArray));
                break;
            case Confirmation.ACTION_DISPLAY_TRANS_INFORMATION:
                reqMessage.setTitle(intent.getStringExtra(InputParameter.Information.PARA_TITLE));
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    Set<String> keySet = extras.keySet();
                    Map<String, String> map = new LinkedHashMap<>();
                    for (String key : keySet) {
                        if (!key.equals(InputParameter.Information.PARA_TITLE)) {
                            map.put(key, (String) extras.get(key));
                        }
                    }
                    if (map.size() > 0) {
                        reqMessage.setInformations(map);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void showUI(@Nullable IUIListener uiListener) {
        if (uiListener instanceof IMessageListener) {
            IMessageListener messageListener = (IMessageListener) uiListener;
            messageListener.onShowMessage(reqMessage.getMessage());
        }
        if (uiListener instanceof ICurrencyListener) {
            ICurrencyListener currencyListener = (ICurrencyListener) uiListener;
            currencyListener.onShowCurrency(reqMessage.getCurrency());
        }

        if (uiListener instanceof IAmountListener) {
            IAmountListener amountListener = (IAmountListener) uiListener;
            amountListener.onShowAmount(reqMessage.getAmount());
        }

        if (uiListener instanceof IOptionListener) {
            IOptionListener optionListener = (IOptionListener) uiListener;
            optionListener.onShowOptions(reqMessage.getReqOption());
        }

        if (uiListener instanceof ICardListener) {
            ICardListener cardListener = (ICardListener) uiListener;
            cardListener.onShowCard(reqMessage.getEnableEntryManual(), reqMessage.getEnableSwipe(),
                    reqMessage.getEnableInsert(), reqMessage.getEnableTap());
        }

        if (uiListener instanceof IAmountOptionListener) {
            IAmountOptionListener amountOptionListener = (IAmountOptionListener) uiListener;
            amountOptionListener.onShowAmountOptions(reqMessage.getAmountOption());
        }

        if (uiListener instanceof ITitleListener) {
            ITitleListener titleListener = (ITitleListener) uiListener;
            titleListener.onShowTitle(reqMessage.getTitle());
        }

        if (uiListener instanceof IInformationListener) {
            IInformationListener informationListener = (IInformationListener) uiListener;
            informationListener.onShowInformation(reqMessage.getInformations());
        }
    }

    private class RespReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            RespMessage respMessage = new RespMessage();

            if (TextUtils.isEmpty(action))
                return;
            Log.i("UIDesignReceiver", "onReceive action : " + action);
            switch (action) {
                case InnerBroadcast.RespAction.ACCEPTED:
                    //notifyUI(respMessage);
                    Log.i("BroadcastReceiver", "ACCEPTED, receiver need unregisterReceiver ");
                    resp.onAccepted();
                    if (receiver != null) {
                        context.unregisterReceiver(receiver);
                        receiver = null;
                        Log.i("BroadcastReceiver", "ACCEPTED receiver unregisterReceiver ");
                    }
                    break;
                case InnerBroadcast.RespAction.DECLINED:
                    respMessage.setResultCode(intent.getStringExtra(Response.RESULT_CODE));
                    respMessage.setResultMsg(intent.getStringExtra(Response.RESULT_MSG));
                    resp.onDeclined(respMessage);
                    break;
                default:
                    break;
            }
        }
    }

}
