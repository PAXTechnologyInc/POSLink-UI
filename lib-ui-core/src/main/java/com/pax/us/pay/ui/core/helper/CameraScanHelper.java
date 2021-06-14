package com.pax.us.pay.ui.core.helper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.EntryRequest;
import com.pax.us.pay.ui.constant.entry.EntryResponse;
import com.pax.us.pay.ui.core.BaseActionHelper;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.core.api.IRespStatus;
import com.pax.us.pay.ui.core.api.IUIListener;
import com.pax.api.scanner.ScanResult;
import com.pax.api.scanner.ScannerListener;
import com.pax.api.scanner.ScannerManager;

public class CameraScanHelper extends BaseActionHelper {

    private static CameraScanHelper helper;

    private ScannerManager manager;

    private FinishListener finishListener;

    private boolean scanCancel = false, scanOnRead = false;

    public CameraScanHelper(@Nullable ICameraScanHelper listener, @Nullable IRespStatus respStatus, Context context) {
        super(listener, respStatus);
        manager = ScannerManager.getInstance(context);
    }

//    public static ScannerHelper getInstance(Context context) {
//        if (helper == null) {
//            helper = new ScannerHelper(context);
//        }
//        return helper;
//    }

    public void doScan(Boolean front) {
        manager.setUseFrontCcd(front);
        manager.scanOpen();
        manager.scanStart(new ScannerListener() {
            public void scanOnRead(ScanResult result) {
                // Here we get the scanning result
                String text = result.getContent();
                scanOnRead = true;
                if (result.getFormat().equals("QR CODE")) {
                    finishListener.ScannerFinish(text, 0);
                } else {
                    finishListener.ScannerFinish(text, 1);
                }

            }

            public void scanOnComplete() {
                if (!scanOnRead && !scanCancel) {
                    finishListener.cancel();
                }
                manager.scanStop();
            }

            @Override
            public void scanOnCancel() {
                scanCancel = true;
                finishListener.cancel();
                manager.scanStop();
            }

        });

    }

    public void sendNext(String text, int type) {
        Bundle bundle = new Bundle();
        bundle.putString(EntryRequest.PARAM_BARCODE_DATA, text);
        bundle.putString(EntryRequest.PARAM_BARCODE_TYPE, String.valueOf(type));
        super.sendNext(bundle);
    }

    /**
     * close scan when destroy activity
     */
    public void closeScan() {
        manager.scanStop();
        manager.scanClose();
        helper = null;
    }

    public void setFinishListener(FinishListener listener) {
        finishListener = listener;
    }

    public FinishListener getFinishListener() {
        return finishListener;
    }

    public interface FinishListener {
        void ScannerFinish(String text, int type);

        void cancel();
    }

    public interface ICameraScanHelper extends IMessageListener {
    }
}
