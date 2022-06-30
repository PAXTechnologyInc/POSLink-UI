/*
 * ============================================================================
 * = COPYRIGHT
 *          PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or nondisclosure
 *   agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *   disclosed except in accordance with the terms in that agreement.
 *     Copyright (C) 2018-? PAX Computer Technology(Shenzhen) CO., LTD All rights reserved.

 * Module Date: 2019-3-7
 * Module Auth: Justin.Z
 * Description:

 * Revision History:
 * Date                   Author                       Action
 * 2019-3-7               Justin.Z                      Create
 * ============================================================================
 */
package com.pax.pay.ui.def;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.pax.pay.ui.def.eventbus.ActivityEndEvent;
import com.pax.pay.ui.def.eventbus.CardEvent;
import com.pax.pay.ui.def.eventbus.ClssLightEvent;
import com.pax.pay.ui.def.eventbus.ConfirmDialogEndEvent;
import com.pax.pay.ui.def.eventbus.EventBusUtil;
import com.pax.pay.ui.def.eventbus.TransparentActivityEndEvent;
import com.pax.pay.ui.def.receiver.IStatusListener;
import com.pax.pay.ui.def.receiver.StatusListenerHelper;
import com.pax.pay.ui.def.utils.UIControl;
import com.pax.pay.ui.def.view.ProcessDialogListener;
import com.pax.pay.ui.def.view.ProcessDialogListenerImpl;
import com.pax.us.pay.ui.component.utils.UIData;
import com.pax.us.pay.ui.constant.entry.enumeration.SFType;
import com.pax.us.pay.ui.constant.status.BatchStatus;
import com.pax.us.pay.ui.constant.status.CardStatus;
import com.pax.us.pay.ui.constant.status.ClssLightStatus;
import com.pax.us.pay.ui.constant.status.InformationStatus;
import com.pax.us.pay.ui.constant.status.StatusData;
import com.pax.us.pay.ui.constant.status.Uncategory;
import com.paxus.utils.LocaleUtils;
import com.paxus.utils.log.Logger;
import com.paxus.view.BaseAppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class DialogActivity extends BaseAppCompatActivity implements IStatusListener {
    private static final Map<String, String> ACTION_MAP = new HashMap<>();
    private static final String KILL_OLD_DIALOG_ACTION = "local://kill.old.dialog";
    //    private static final String ORDER_BROAST = "ORDER_BROAST";
//    private static UpdateOrderReceiver updateOrderReceiver;
//    private static NewOrderReceiver newOrderReceiver;
    private static String currAction;

    static {
        ACTION_MAP.put(CardStatus.CARD_REMOVAL_REQUIRED, CardStatus.CARD_REMOVED);
        ACTION_MAP.put(CardStatus.CARD_QUICK_REMOVAL_REQUIRED, CardStatus.CARD_REMOVED);
        ACTION_MAP.put(CardStatus.CARD_PROCESS_STARTED, CardStatus.CARD_PROCESS_COMPLETED);
        ACTION_MAP.put(BatchStatus.BATCH_CLOSE_STARTED, BatchStatus.BATCH_CLOSE_COMPLETED);
        ACTION_MAP.put(BatchStatus.BATCH_CLOSE_UPLOADING, BatchStatus.BATCH_CLOSE_COMPLETED);
        ACTION_MAP.put(BatchStatus.BATCH_SF_STARTED, BatchStatus.BATCH_SF_COMPLETED);
        ACTION_MAP.put(BatchStatus.BATCH_SF_UPLOADING, BatchStatus.BATCH_SF_COMPLETED);
        ACTION_MAP.put(InformationStatus.TRANS_ONLINE_STARTED, InformationStatus.TRANS_ONLINE_FINISHED);
        ACTION_MAP.put(InformationStatus.TRANS_REVERSAL_STARTED, InformationStatus.TRANS_REVERSAL_FINISHED);
        ACTION_MAP.put(InformationStatus.PINPAD_CONNECTION_STARTED, InformationStatus.PINPAD_CONNECTION_FINISHED);
        ACTION_MAP.put(InformationStatus.EMV_TRANS_ONLINE_STARTED, InformationStatus.EMV_TRANS_ONLINE_FINISHED);
        ACTION_MAP.put(InformationStatus.DCC_ONLINE_STARTED, InformationStatus.DCC_ONLINE_FINISHED);
        ACTION_MAP.put(InformationStatus.RKI_STARTED, InformationStatus.RKI_FINISHED);

        ACTION_MAP.put(Uncategory.ACTIVATE_STARTED, Uncategory.ACTIVATE_COMPLETED);
        ACTION_MAP.put(Uncategory.CAPK_UPDATE_STARTED, Uncategory.CAPK_UPDATE_COMPLETED);
        ACTION_MAP.put(Uncategory.PRINT_STARTED, Uncategory.PRINT_COMPLETED);
        ACTION_MAP.put(Uncategory.FILE_UPDATE_STARTED, Uncategory.FILE_UPDATE_COMPLETED);
        ACTION_MAP.put(Uncategory.LOG_UPLOAD_STARTED, Uncategory.LOG_UPLOAD_COMPLETED);
        ACTION_MAP.put(Uncategory.LOG_UPLOAD_CONNECTED, Uncategory.LOG_UPLOAD_COMPLETED);
        ACTION_MAP.put(Uncategory.LOG_UPLOAD_UPLOADING, Uncategory.LOG_UPLOAD_COMPLETED);
        ACTION_MAP.put(Uncategory.FCP_FILE_UPDATE_STARTED, Uncategory.FCP_FILE_UPDATE_COMPLETED);
    }

    public static final long FAILED_DIALOG_SHOW_TIME = 5000L;
    public static final long SUCCESS_DIALOG_SHOW_TIME = 2000L;
    public static final int WARN_DIALOG_SHOW_TIME = 2;

    boolean isNeedDisplayMessage;

    private ProcessDialogListener processDialogListener;
    private boolean registered = true;
    private boolean isOnStop;
    private Handler dismissHandler = null;
    private boolean dialogCloseAll = false;

    private final BroadcastReceiver killSelfReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (registered) {
                LocalBroadcastManager.getInstance(context).unregisterReceiver(this);
                Logger.d("Clear Listener ");
                //lastAction = null;
                StatusListenerHelper.getInstance().setListener(null);
                if (processDialogListener != null) {
                    Logger.d("processDialogListener onHideProgress");
                    processDialogListener.onHideProgress();
                    processDialogListener = null;
                }
                currAction = null;
                finish();
            }
            registered = false;
        }
    };
    private final Runnable dismissRunable = new Runnable() {
        @Override
        public void run() {
            if (processDialogListener != null) {
                Logger.d("processDialogListener onHideProgress ");
                processDialogListener.onHideProgress();
                processDialogListener = null;
                LocalBroadcastManager.getInstance(DialogActivity.this).sendBroadcast(new Intent(KILL_OLD_DIALOG_ACTION));
            } else {
                Logger.d("hideDialog dismissListener close ");
                dismissListener.close(dialogCloseAll);
            }
        }
    };

    private final DismissListener dismissListener = new DismissListener();

//    private static String lastAction = null;
//    another method
//    public static void sendBroadcast(Context context, Intent intent){
//        if(lastAction != null){
//            Intent intent1 = new Intent();
//            intent1.setAction(ORDER_BROAST);
//            Bundle bundle = new Bundle();
//            bundle.putString("Action", intent.getAction());
//            bundle.putBundle("Bundle", intent.getExtras());
//            context.sendOrderedBroadcast(intent1, null, null, null, 1,
//                    null, bundle);
//        }else{
//            start(context, intent);
//        }
//        lastAction = intent.getAction();
//    }

    public static void start(Context context, Intent intent) {
        Intent starter = new Intent(context, DialogActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        starter.setAction(intent.getAction());
        if (intent.getExtras() != null) {
            starter.putExtras(intent.getExtras());
        }
        context.startActivity(starter);
    }

    public static void stop(Context context, Intent intent) {
        //no Action need to close
        if (TextUtils.isEmpty(currAction)) {
            Logger.d( "currAction is empty ");
            return;
        }
        //For Broadcast action:
        // only current action is xxx_Started Action which is need to close,
        // other action will be closed by TRANS_COMPLETED or cover by other action.
        for (Map.Entry entry : ACTION_MAP.entrySet()) {
            if (intent.getAction() != null && intent.getAction().equals(entry.getValue()) && currAction.equals(entry.getKey())) {
                Logger.d( "stop : " + intent.getAction());
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(KILL_OLD_DIALOG_ACTION));
            }
        }
        Logger.d("current action : " + currAction + "Action : " + intent.getAction() + "Already closeed!");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
//        updateOrderReceiver = new UpdateOrderReceiver();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.setPriority(200);
//        intentFilter.addAction(ORDER_BROAST);
//        registerReceiver(updateOrderReceiver, intentFilter);
//        updateOrderReceiver.setBRInteractionListener(this);
//
//        IntentFilter newFilter = new IntentFilter();
//        newFilter.setPriority(-200);
//        newFilter.addAction(ORDER_BROAST);
//        registerReceiver(newOrderReceiver, newFilter);

        Logger.d("onCreated! and setListener for :" + getIntent().getAction());
        StatusListenerHelper.getInstance().setListener(this);

        //Remove confirm dialog, if confirm dialog not dismissing
        EventBusUtil.doEvent(new ConfirmDialogEndEvent());

        LocalBroadcastManager.getInstance(this).registerReceiver(killSelfReceiver, new IntentFilter(KILL_OLD_DIALOG_ACTION));
        registered = true;

        if (processDialogListener == null) {
            processDialogListener = new ProcessDialogListenerImpl(this);
        }
        handleIntent(getIntent());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        isOnStop = false;
        super.onStart();
    }


    @Override
    protected void onStop() {
        //DialogActivity cover by other activity, then close it
        isOnStop = true;
        super.onStop();
    }

    @Override
    public void finish() {
        super.finish();
        //remove activity animation
        overridePendingTransition(0, 0);
    }

    @Override
    public synchronized void setIntent(Context context, Intent intent) {
        if (processDialogListener == null) {
            Logger.d("processDialogListener is Empty");
            throw new RuntimeException("Dialog is empty");
        }

        if (isOnStop) {
            Logger.d("currant DialogActivity is onStoped! ");
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(KILL_OLD_DIALOG_ACTION));
            start(context, intent);
        } else {
            //Logger.d("handleIntent start");
            handleIntent(intent);
            //Logger.d("handleIntent finished");
        }

    }

    private void handleIntent(Intent intent) {

        //only manager is on front ground, it need to show dialog, otherwise close all manager UI
        isNeedDisplayMessage = getIntent().getBooleanExtra("isNeedReceiveMessage", false);
        Logger.d("isNeedDisplayMessage :" + isNeedDisplayMessage);


        String action = intent.getAction();
        if (action == null || action.isEmpty()) {
            Logger.d("action empty DialogActivity is Killed! ");
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(KILL_OLD_DIALOG_ACTION));
            return;
        }

        currAction = action;
        Logger.d("handleIntent action :" + action);

        Context wrapContext = this;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            wrapContext = LocaleUtils.wrapContext(this);
        }
        switch (action) {
//            case InformationStatus.TRANS_START:
//                UIControl.getInstance().setTransStarted(true);
//                //this DialogActivity will be cover by other activity, so remove it
//                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(KILL_OLD_DIALOG_ACTION));
//                break;
            case InformationStatus.EMV_TRANS_ONLINE_STARTED:
                showProcessDialog(wrapContext.getString(R.string.emv_trans_online));
                break;
            case InformationStatus.TRANS_ONLINE_STARTED:
                showProcessDialog(wrapContext.getString(R.string.def_ui_processing));
                break;
            case InformationStatus.TRANS_REVERSAL_STARTED:
                showProcessDialog(wrapContext.getString(R.string.reversal_process));
                break;
            case InformationStatus.PINPAD_CONNECTION_STARTED:
                showProcessDialog(wrapContext.getString(R.string.pinpad_connect));
                break;
            case CardStatus.CARD_PROCESS_STARTED:
                showProcessDialog(wrapContext.getString(R.string.emv_process_start));
                break;
            case CardStatus.CARD_PROCESS_ERROR:
                showWarnDialog(wrapContext.getString(R.string.card_error), true);
                break;
            case CardStatus.CARD_REMOVAL_REQUIRED:
                showWarnDialog(wrapContext.getString(R.string.please_remove_card), false);
                break;
            case CardStatus.CARD_QUICK_REMOVAL_REQUIRED:
                showWarnDialog(wrapContext.getString(R.string.please_remove_card_quickly), false);
                break;
            case CardStatus.CARD_INSERT_REQUIRED:
                EventBusUtil.doEvent(new ClssLightEvent(ClssLightStatus.CLSS_LIGHT_NOT_READY));
                EventBusUtil.doEvent(new CardEvent(CardStatus.CARD_INSERT_REQUIRED));
                showWarnDialog(wrapContext.getString(R.string.please_insert_chip_card), true);
                break;
            case CardStatus.CARD_SWIPE_REQUIRED:
                EventBusUtil.doEvent(new ClssLightEvent(ClssLightStatus.CLSS_LIGHT_NOT_READY));
                EventBusUtil.doEvent(new CardEvent(CardStatus.CARD_SWIPE_REQUIRED));
                showWarnDialog(wrapContext.getString(R.string.please_swipe_card), true);
                break;
            case CardStatus.CARD_TAP_REQUIRED:
                EventBusUtil.doEvent(new ClssLightEvent(ClssLightStatus.CLSS_LIGHT_READY_FOR_TXN));
                EventBusUtil.doEvent(new CardEvent(CardStatus.CARD_TAP_REQUIRED));
                showWarnDialog(wrapContext.getString(R.string.please_tap_card), true);
                break;
            case CardStatus.SEE_PHONE:
                String prompts = intent.getStringExtra(StatusData.PARAM_PROMPTS);
                if (TextUtils.isEmpty(prompts))
                    showWarnDialog(wrapContext.getString(R.string.please_see_phone), true); //Fix ANBP-385
                else
                    showWarnDialog(prompts, true);
                break;
            case Uncategory.ACTIVATE_STARTED:
                showProcessDialog(wrapContext.getString(R.string.trans_online));
                break;
            case Uncategory.CAPK_UPDATE_STARTED:
                showProcessDialog(wrapContext.getString(R.string.download_emv_capk)); //Fix ANFDRC-922
                break;
            case Uncategory.PRINT_STARTED:
                showProcessDialog(wrapContext.getString(R.string.print_process));
                break;
            case Uncategory.FILE_UPDATE_STARTED:
                showProcessDialog(wrapContext.getString(R.string.update_process)); //Fix ADJ-118
                break;
            case BatchStatus.BATCH_CLOSE_STARTED:
                showProcessDialog(wrapContext.getString(R.string.batch_close_start));
                break;
            case BatchStatus.BATCH_CLOSE_UPLOADING:
                String edcType = intent.getStringExtra(StatusData.PARAM_EDC_TYPE);
                long currentCount = intent.getLongExtra(StatusData.PARAM_UPLOAD_CURRENT_COUNT, 0);
                long totalCount = intent.getLongExtra(StatusData.PARAM_UPLOAD_TOTAL_COUNT, 0);
                String uploadMessage = wrapContext.getString(R.string.uploading_trans) +" "+edcType+"\n"+currentCount+"/"+totalCount;
                showProcessDialog(uploadMessage);
                break;
            case BatchStatus.BATCH_SF_UPLOADING:
                String sfType = intent.getStringExtra(StatusData.PARAM_SF_TYPE);
                long sfCurrentCount = intent.getLongExtra(StatusData.PARAM_SF_CURRENT_COUNT, 0);
                long sfTotalCount = intent.getLongExtra(StatusData.PARAM_SF_TOTAL_COUNT, 0);
                String message = SFType.FAILED.equals(sfType) ? wrapContext.getString(R.string.uploading_failed_trans) : wrapContext.getString(R.string.uploading_sf_trans) +
                        wrapContext.getString(R.string.total_count) + sfTotalCount + wrapContext.getString(R.string.current_count) + sfCurrentCount;
                showProcessDialog(message);
                break;
            case BatchStatus.BATCH_SF_STARTED:
                showProcessDialog(wrapContext.getString(R.string.uploading_start));
                break;
            case Uncategory.LOG_UPLOAD_STARTED:
                showProcessDialog(wrapContext.getString(R.string.log_uploading_start));
                break;
            case Uncategory.LOG_UPLOAD_CONNECTED:
                long uploadPercent = intent.getLongExtra(StatusData.PARAM_UPLOAD_CURRENT_PERCENT, 0);
                String logMessage = wrapContext.getString(R.string.log_connected) + " (" + uploadPercent + "%)";
                showProcessDialog(logMessage);
                break;
            case Uncategory.LOG_UPLOAD_UPLOADING:
                long logUploadCount = intent.getLongExtra(StatusData.PARAM_UPLOAD_CURRENT_COUNT, 0);
                long logTotalCount = intent.getLongExtra(StatusData.PARAM_UPLOAD_TOTAL_COUNT, 0);
                long logUploadPercent = intent.getLongExtra(StatusData.PARAM_UPLOAD_CURRENT_PERCENT, 0);
                String logUploadMessage = wrapContext.getString(R.string.update_process) + " " + logUploadCount + "/" + logTotalCount + "("
                        + logUploadPercent + "%)";
                showProcessDialog(logUploadMessage);
                break;
            case Uncategory.FCP_FILE_UPDATE_STARTED:
                showProcessDialog(wrapContext.getString(R.string.check_for_update_start));
                break;
            case InformationStatus.DCC_ONLINE_STARTED:
                showProcessDialog(wrapContext.getString(R.string.dcc_online_start));
                break;
            case InformationStatus.RKI_STARTED:
                showProcessDialog(wrapContext.getString(R.string.rki_start));
                break;
            case InformationStatus.ERROR:
                long errCode = intent.getLongExtra(StatusData.PARAM_CODE, 0);
                String errMessage = intent.getStringExtra(StatusData.PARAM_MSG);
                if (TextUtils.isEmpty(errMessage)) {
                    errMessage = wrapContext.getString(R.string.transaction_failed);
                }
                //ANBP-469 don't prompt error code
                //errMessage += "\n Error Code:" + errCode;
                showResultDialog(false, errMessage);
                int errTimeOut = (int)(FAILED_DIALOG_SHOW_TIME/1000);
                hideDialog(errTimeOut, false);
                break;
            case InformationStatus.TRANS_COMPLETED:
                UIControl.getInstance().setTransStarted(false);
                UIData.clearData(wrapContext.getApplicationContext());
                EventBusUtil.doEvent(new ConfirmDialogEndEvent());
                //EventBusUtil.doEvent(new TransEndEvent());
                EventBusUtil.doEvent(new TransparentActivityEndEvent()); // To avoid black screen
                //ContentResolver.getInstance().unregister(this);
                long resultCode = intent.getLongExtra(StatusData.PARAM_CODE, 0);
                String resultMessage = intent.getStringExtra(StatusData.PARAM_MSG);
                boolean result;
                int timeOut;
                if (resultCode == 0) {
                    result = true;
                    if (TextUtils.isEmpty(resultMessage)) {
                        //Yanina: if the message is empty, do not prompt Dialog
                        //For semi-integration, sometimes do not need prompt Message (like AUTO-EDC)
//                        resultMessage = getString(R.string.transaction_successful);
                        dismissListener.close(true);
                        break;
                    }
                    timeOut = (int)(intent.getLongExtra(StatusData.PARAM_HOST_RESP_TIMEOUT, SUCCESS_DIALOG_SHOW_TIME)/1000);
                } else if (resultCode == -3) {
                    LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(KILL_OLD_DIALOG_ACTION));
                    //ANBP-661 To close other screen firstly, and avoid black screen
                    new Handler().postDelayed(() -> {
                        EventBusUtil.doEvent(new ActivityEndEvent());
                    }, 200);
                    break;
                } else {
                    result = false;
                    if (TextUtils.isEmpty(resultMessage)) {
                        //Yanina: if the message is empty, do not prompt Dialog
                        //For semi-integration, sometimes do not need prompt Error Message
                        dismissListener.close(true);

                        //resultMessage = "Transaction Failed!";
                        break;
                    }
                    //ANBP-469 don't prompt error code
                    //resultMessage += "\nError Code : " + resultCode;
                    timeOut = (int)(intent.getLongExtra(StatusData.PARAM_HOST_RESP_TIMEOUT, FAILED_DIALOG_SHOW_TIME)/1000);
                    //timeOut = FAILED_DIALOG_SHOW_TIME;
                }
                //if (UIConfiguration.getInstance().isNeedReceiveMessage())
                boolean displayVisaInstallmentEnd = "Y".equals(intent.getStringExtra(StatusData.PARAM_DISPLAY_VISA_INSTALLMENT_APPROVAL));
                if (isNeedDisplayMessage && !displayVisaInstallmentEnd){
                    showResultDialog(result, resultMessage);
                } else {
                    if (displayVisaInstallmentEnd){
                        Intent newIntent = new Intent(DialogActivity.this, VisaInstallmentTransEndActivity.class);
                        startActivity(newIntent);
                    }
                    if (processDialogListener != null) {
                        processDialogListener.onHideProgress();
                        processDialogListener = null;
                    }
                    timeOut = 0;
                }
                hideDialog(timeOut, true);
                break;
            default:
                Logger.d("unKnown Action DialogActivity is Killed! : " + action);
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(KILL_OLD_DIALOG_ACTION));
                break;
        }
    }

    @Override
    protected void onDestroy() {

        // if registered is already false, then it don't need kill old dialog again, otherwise maybe it will kill the current action.
        if (registered) {
            Logger.d("onDestroy Action DialogActivity is Killed!");
            LocalBroadcastManager.getInstance(DialogActivity.this).sendBroadcast(new Intent(KILL_OLD_DIALOG_ACTION));
            registered = false;
        }
        super.onDestroy();
    }

    private void showProcessDialog(String message) {
        //if the previous dialog is delay to close, then don't need to close,which will be replace by current dialog.
        if (dismissHandler != null) {
            dismissHandler.removeCallbacks(dismissRunable);
            dismissHandler = null;
        }
        if (isNeedDisplayMessage)
            processDialogListener.onShowProgress(message);
    }

    private void showResultDialog(boolean isSuccess, String message) {
        //if the previous dialog is delay to close, then don't need to close,which will be replace by current dialog.
        if (dismissHandler != null) {
            dismissHandler.removeCallbacks(dismissRunable);
            dismissHandler = null;
        }
        if (isNeedDisplayMessage)
            processDialogListener.onShowResult(isSuccess, message, dismissListener);
    }

    private void showWarnDialog(String message, boolean close) {
        //if the previous dialog is delay to close, then don't need to close,which will be replace by current dialog.
        if (dismissHandler != null) {
            dismissHandler.removeCallbacks(dismissRunable);
            dismissHandler = null;
        }
        if (isNeedDisplayMessage) {
            processDialogListener.onShowWarn(message);
            if (close) {
                hideDialog(WARN_DIALOG_SHOW_TIME, false);
            }
        }
    }

    private void updateMessage(String message) {
        processDialogListener.onUpdateMessage(message);
    }

    private void hideDialog(int timeout, boolean closeAll) {
        dismissListener.closeAll = closeAll;
        dialogCloseAll = closeAll;
        dismissHandler = new Handler();
        dismissHandler.postDelayed(dismissRunable, timeout * 1000);

    }

    private class DismissListener implements DialogInterface.OnDismissListener {
        private boolean closeAll = false;

        public void close(boolean closeAll) {
            this.closeAll = closeAll;
            onDismiss(null);
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            LocalBroadcastManager.getInstance(DialogActivity.this).sendBroadcast(new Intent(KILL_OLD_DIALOG_ACTION));
            if (closeAll) {
                //avoid black screen
                new Handler().postDelayed(() -> {
                    EventBusUtil.doEvent(new ActivityEndEvent());
                }, 200);
            }
            if (dismissHandler != null) {
                dismissHandler.removeCallbacks(dismissRunable);
                dismissHandler = null;
            }

        }
    }

}