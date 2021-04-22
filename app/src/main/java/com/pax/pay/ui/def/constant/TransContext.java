package com.pax.pay.ui.def.constant;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class TransContext {

    public static final String TYPE_ALL = "ALL";
    public static final String TYPE_SALE = "SALE";
    public static final String TYPE_REDEEM = "REDEEM"; //For loyalty
    public static final String TYPE_RETURN = "RETURN";
    public static final String TYPE_BALANCE = "BALANCE";
    public static final String TYPE_FORCED = "FORCED";
    public static final String TYPE_VOID_FORCED = "V_FORCED";
    public static final String TYPE_VOID_RETURN = "V_RETURN";
    public static final String TYPE_AUTH = "AUTH";
    public static final String TYPE_POST_AUTH = "POST_AUTH";
    public static final String TYPE_VOID_AUTH = "V_AUTH";
    public static final String TYPE_VERIFY = "VERIFY";
    public static final String TYPE_TOKENIZE = "TOKENIZE";
    public static final String TYPE_WITHDRAWAL = "WITHDRAWAL";
    public static final String TYPE_ADD = "ADD";
    public static final String TYPE_ADD_TIP = "ADD_TIP";
    public static final String TYPE_ACTIVATE = "ACTIVATE";
    public static final String TYPE_REACTIVATE = "REACTIVATE";
    public static final String TYPE_UNLOAD = "UNLOAD";
    public static final String TYPE_VOID = "VOID";
    public static final String TYPE_ISSUE = "ISSUE";
    public static final String TYPE_CASH_OUT = "CASH_OUT";
    public static final String TYPE_VOID_SALE = "V_SALE";
    public static final String TYPE_DEACTIVATE = "DEACTIVATE";
    public static final String TYPE_VOID_POST_AUTH = "V_POST_AUTH";
    public static final String TYPE_VOID_WITHDRAW = "V_WITHDRAW";
    public static final String TYPE_REPLACE = "REPLACE";
    public static final String TYPE_MERGE = "MERGE";
    public static final String TYPE_TRANSFER = "TRANSFER";
    public static final String TYPE_REVERSAL = "REVERSAL";
    public static final String TYPE_ADJUST = "ADJUST";
    public static final String TYPE_UPLOAD = "UPLOAD";
    public static final String TYPE_BATCH_CLOSE = "BATCH";
    public static final String TYPE_REPORT_LOST = "REPORT_LOST";
    public static final String TYPE_FORCE_ADD = "FORCE_ADD";
    public static final String TYPE_FORCE_ISSUE = "FORCE_ISSUE";
    public static final String TYPE_RENEW = "RENEW";
    public static final String TYPE_GET_CONVERT_DETAIL = "GET_CONVERT_DETAIL";
    public static final String TYPE_CONVERT = "CONVERT";
    public static final String TYPE_INCREMENTAL_AUTH = "INCREMENTAL_AUTH";
    public static final String TYPE_BALANCE_WITH_LOCK = "BALANCE_WITH_LOCK";
    public static final String TYPE_REDEMPTION_WITH_UNLOCK = "REDEMPTION_WITH_UNLOCK";
    public static final String TYPE_REWARDS = "REWARDS";
    public static final String TYPE_REENTER = "RE_ENTER";
    public static final String TYPE_RELOAD = "RELOAD";
    //sub type
    public static final int SUB_TYPE_NONE = 0;
    public static final int SUB_TYPE_BY_CARD_NUM = 1;
    public static final int SUB_TYPE_BY_REF_NUM = 2;
    public static final int SUB_TYPE_CASH_OUT_ACTIVE = 3;
    public static final int SUB_TYPE_CASH_OUT = 4;
    //req status
    public static final String REQ_STATUS_UPLOADED = "Uploaded"; // clear transaction.
    public static final String REQ_STATUS_VOIDED = "Voided"; // voided.
    public static final String REQ_STATUS_COMPLETED = "Completed"; // completed
    public static final String REQ_STATUS_ADJUSTED = "Adjusted"; // tip adjusted.
    public static final String REQ_STATUS_APPENDED = "Appended"; // appended commercial card info.
    public static final String REQ_STATUS_QUICK_SALE = "QuickSale"; // quick sale.
    public static final String REQ_STATUS_STORE_OFFLINE = "StoredOffline"; // stored in offline mode.
    //resp status
    public static final String OL_RESP_STATUS_APPROVED = "Approved";
    public static final String OL_RESP_STATUS_PARTIALLY_APPROVED = "PartiallyApproved";
    public static final String OL_RESP_STATUS_DECLINED = "Declined";
    public static final String OL_RESP_STATUS_CONN_ERR = "ConnectError";
    public static final String OL_RESP_STATUS_NO_RESP = "NoResp"; // no response from server
    public static final String OL_RESP_STATUS_DUP = "Duplicate"; // duplicate transaction.
    public static final String OL_RESP_STATUS_ERROR = "ERROR"; // general error.
    //partial approve type
    public static final int PARTIAL_APPROVE_NORMAL = 1;
    public static final int PARTIAL_APPROVE_REVERSER = 2;
    public static final int PARTIAL_APPROVE_NEW_TRANS = 3;

    private TransContext() {
    }

    public static String getTransTypeDesc(Context context, @TransType String t) {
        if (TYPE_ALL.equals(t)) {
            return "ALL";
        } else if (TYPE_SALE.equals(t)) {
            return "SALE";
        } else if (TYPE_REDEEM.equals(t)) {
            return "REDEEM";
        } else if (TYPE_RETURN.equals(t)) {
            return "RETURN";
        } else if (TYPE_BALANCE.equals(t)) {
            return "BALANCE";
        } else if (TYPE_FORCED.equals(t)) {
            return "FORCED";
        } else if (TYPE_VOID_FORCED.equals(t)) {
            return "V/FRCD";
        } else if (TYPE_VOID_RETURN.equals(t)) {
            return "V/RTRN";
        } else if (TYPE_AUTH.equals(t)) {
            return "AUTH";
        } else if (TYPE_POST_AUTH.equals(t)) {
            return "POSTAUTH";
        } else if (TYPE_VOID_AUTH.equals(t)) {
            return "V/AUTH";
        } else if (TYPE_VERIFY.equals(t)) {
            return "VERIFY";
        } else if (TYPE_TOKENIZE.equals(t)) {
            return "TOKENIZE";
        } else if (TYPE_WITHDRAWAL.equals(t)) {
            return "WITHDRAWAL";
        } else if (TYPE_ADD.equals(t)) {
            return "ADD";
        } else if (TYPE_ADD_TIP.equals(t)) {
            return "ADDTIP";
        } else if (TYPE_ACTIVATE.equals(t)) {
            return "ACTIVATE";
        } else if (TYPE_REACTIVATE.equals(t)) {
            return "REACTIVATE";
        } else if (TYPE_UNLOAD.equals(t)) {
            return "UNLOAD";
        } else if (TYPE_VOID.equals(t)) {
            return "VOID";
        } else if (TYPE_ISSUE.equals(t)) {
            return "ISSUE";
        } else if (TYPE_CASH_OUT.equals(t)) {
            return "CASHOUT";
        } else if (TYPE_VOID_SALE.equals(t)) {
            return "V/SALE";
        } else if (TYPE_DEACTIVATE.equals(t)) {
            return "DEACT";
        } else if (TYPE_VOID_POST_AUTH.equals(t)) {
            return "V/POST";
        } else if (TYPE_VOID_WITHDRAW.equals(t)) {
            return "V/WITHDRAW";
        } else if (TYPE_REPLACE.equals(t)) {
            return "REPLACE";
        } else if (TYPE_MERGE.equals(t)) {
            return "MERGE";
        } else if (TYPE_TRANSFER.equals(t)) {
            return "TRANSFER";
        } else if (TYPE_REVERSAL.equals(t)) {
            return "REVERSAL";
        } else if (TYPE_ADJUST.equals(t)) {
            return "ADJUST";
        } else if (TYPE_UPLOAD.equals(t)) {
            return "UPLOAD";
        } else if (TYPE_BATCH_CLOSE.equals(t)) {
            return "BATCH";
        } else if (TYPE_REPORT_LOST.equals(t)) {
            return "REPORT LOST";
        } else if (TYPE_FORCE_ADD.equals(t)) {
            return "FORCE ADD";
        } else if (TYPE_FORCE_ISSUE.equals(t)) {
            return "FORCE ISSUE";
        } else if (TYPE_RENEW.equals(t)) {
            return "RENEW";
        } else if (TYPE_GET_CONVERT_DETAIL.equals(t)) {
            return "CONVERT DETAIL";
        } else if (TYPE_CONVERT.equals(t)) {
            return "CONVERT";
        } else if (TYPE_INCREMENTAL_AUTH.equals(t)) {
            return "INCREMENTAL AUTH";
        } else if (TYPE_BALANCE_WITH_LOCK.equals(t)) {
            return "BALANCE LOCK";
        } else if (TYPE_REDEMPTION_WITH_UNLOCK.equals(t)) {
            return "REDEMPTION UNLOCK";
        } else if (TYPE_REWARDS.equals(t)) {
            return "REWARDS";
        } else if (TYPE_REENTER.equals(t)) {
            return "REENTER";
        } else if (TYPE_RELOAD.equals(t)) {
            return "RELOAD";
        } else {
            return "";
        }
    }

    public static boolean isVoidTransType(@TransType String t) {
        return (TYPE_VOID.equals(t) ||
                TYPE_VOID_AUTH.equals(t) ||
                TYPE_VOID_FORCED.equals(t) ||
                TYPE_VOID_POST_AUTH.equals(t) ||
                TYPE_VOID_RETURN.equals(t) ||
                TYPE_VOID_SALE.equals(t) ||
                TYPE_VOID_WITHDRAW.equals(t));
    }

    @TransType
    public static String getVoidTransType(@TransType String t) {
        if (TYPE_SALE.equals(t)) {
            return TYPE_VOID_SALE;
        } else if (TYPE_RETURN.equals(t)) {
            return TYPE_VOID_RETURN;
        } else if (TYPE_AUTH.equals(t)) {
            return TYPE_VOID_AUTH;
        } else if (TYPE_POST_AUTH.equals(t)) {
            return TYPE_VOID_POST_AUTH;
        } else if (TYPE_FORCED.equals(t)) {
            return TYPE_VOID_FORCED;
        } else if (TYPE_WITHDRAWAL.equals(t)) {
            return TYPE_VOID_WITHDRAW;
        }
        return null;
    }

    public static String getSubTypeDesc(Context context, @SubTransType int t) {
        if (t == SUB_TYPE_NONE) {
            return "None";
        } else if (t == SUB_TYPE_BY_CARD_NUM) {
            return "By CardNum";
        } else if (t == SUB_TYPE_BY_REF_NUM) {
            return "By RefNo.";
        } else if (t == SUB_TYPE_CASH_OUT_ACTIVE) {
            return "Cashout Active";
        } else if (t == SUB_TYPE_CASH_OUT) {
            return "Cashout";
        } else {
            return null;
        }
    }

    //Sub Type
    @IntDef({
            SUB_TYPE_NONE,
            SUB_TYPE_BY_CARD_NUM,
            SUB_TYPE_BY_REF_NUM,
            SUB_TYPE_CASH_OUT_ACTIVE,
            SUB_TYPE_CASH_OUT,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface SubTransType {
    }

    @StringDef({
            REQ_STATUS_UPLOADED,
            REQ_STATUS_VOIDED,
            REQ_STATUS_COMPLETED,
            REQ_STATUS_ADJUSTED,
            REQ_STATUS_APPENDED,
            REQ_STATUS_QUICK_SALE,
            REQ_STATUS_STORE_OFFLINE,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface ReqStatus {
    }

    @StringDef({
            OL_RESP_STATUS_APPROVED,
            OL_RESP_STATUS_PARTIALLY_APPROVED,
            OL_RESP_STATUS_DECLINED,
            OL_RESP_STATUS_CONN_ERR,
            OL_RESP_STATUS_NO_RESP,
            OL_RESP_STATUS_DUP,
            OL_RESP_STATUS_ERROR,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface OnlineRespStatus {
    }


    @StringDef({
            TYPE_ALL,
            TYPE_SALE,
            TYPE_REDEEM,
            TYPE_RETURN,
            TYPE_BALANCE,
            TYPE_FORCED,
            TYPE_VOID_FORCED,
            TYPE_VOID_RETURN,
            TYPE_AUTH,
            TYPE_POST_AUTH,
            TYPE_VOID_AUTH,
            TYPE_VERIFY,
            TYPE_TOKENIZE,
            TYPE_WITHDRAWAL,
            TYPE_ADD,
            TYPE_ADD_TIP,
            TYPE_ACTIVATE,
            TYPE_REACTIVATE,
            TYPE_UNLOAD,
            TYPE_VOID,
            TYPE_ISSUE,
            TYPE_CASH_OUT,
            TYPE_VOID_SALE,
            TYPE_DEACTIVATE,
            TYPE_VOID_POST_AUTH,
            TYPE_VOID_WITHDRAW,
            TYPE_REPLACE,
            TYPE_MERGE,
            TYPE_TRANSFER,
            TYPE_REVERSAL,
            TYPE_ADJUST,
            TYPE_UPLOAD,
            TYPE_BATCH_CLOSE,
            TYPE_REPORT_LOST,
            TYPE_FORCE_ADD,
            TYPE_FORCE_ISSUE,
            TYPE_RENEW,
            TYPE_GET_CONVERT_DETAIL,
            TYPE_CONVERT,
            TYPE_INCREMENTAL_AUTH,
            TYPE_BALANCE_WITH_LOCK,
            TYPE_REDEMPTION_WITH_UNLOCK,
            TYPE_REWARDS,
            TYPE_REENTER,
            TYPE_RELOAD,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface TransType {
    }


    @IntDef({
            PARTIAL_APPROVE_NORMAL,
            PARTIAL_APPROVE_REVERSER,
            PARTIAL_APPROVE_NEW_TRANS
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface PartialApproveType {
    }
}

