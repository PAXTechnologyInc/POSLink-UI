package com.pax.pay.ui.def.utils;

import com.pax.pay.ui.def.R;
import com.pax.us.pay.ui.constant.entry.enumeration.AccountType;
import com.pax.us.pay.ui.constant.entry.enumeration.BatchType;
import com.pax.us.pay.ui.constant.entry.enumeration.BypassReason;
import com.pax.us.pay.ui.constant.entry.enumeration.CardType;
import com.pax.us.pay.ui.constant.entry.enumeration.CashoutType;
import com.pax.us.pay.ui.constant.entry.enumeration.DuplicateType;
import com.pax.us.pay.ui.constant.entry.enumeration.EBTType;
import com.pax.us.pay.ui.constant.entry.enumeration.EDCGroup;
import com.pax.us.pay.ui.constant.entry.enumeration.EDCType;
import com.pax.us.pay.ui.constant.entry.enumeration.MOTOType;
import com.pax.us.pay.ui.constant.entry.enumeration.RefundReason;
import com.pax.us.pay.ui.constant.entry.enumeration.ReportType;
import com.pax.us.pay.ui.constant.entry.enumeration.SearchCriteria;
import com.pax.us.pay.ui.constant.entry.enumeration.SubTransType;
import com.pax.us.pay.ui.constant.entry.enumeration.TaxReason;
import com.pax.us.pay.ui.constant.entry.enumeration.TransType;

import java.util.HashMap;
import java.util.Map;

public class SelectOptionContent {
    public static final Map<String, Integer> SELECT_OPTION_MAP = new HashMap<>();

    static {
        SELECT_OPTION_MAP.put(EBTType.FOOD_STAMP, R.string.food_stamp);
        SELECT_OPTION_MAP.put(EBTType.CASH_BENEFIT, R.string.cash_benefit);
        SELECT_OPTION_MAP.put(EBTType.VOUCHER, R.string.voucher);
        SELECT_OPTION_MAP.put(EBTType.E_WIC, R.string.e_wic);
        SELECT_OPTION_MAP.put(EBTType.BOTH, R.string.both);
        SELECT_OPTION_MAP.put(EBTType.E_WIC_VOUCHER, R.string.e_wic_voucher);

        SELECT_OPTION_MAP.put(BypassReason.WANT_TO_BYPASS, R.string.want_to_bypass);
        SELECT_OPTION_MAP.put(BypassReason.CANNOT_READ, R.string.cannot_read);
        SELECT_OPTION_MAP.put(BypassReason.DOES_NOT_EXIST, R.string.does_not_exist);

        SELECT_OPTION_MAP.put(SubTransType.BY_CARDNUM, R.string.option_by_cardnum);
        SELECT_OPTION_MAP.put(SubTransType.BY_REFNO, R.string.by_refno);

        SELECT_OPTION_MAP.put(CashoutType.CASHOUT_ACTIVE, R.string.cashout_active);
        SELECT_OPTION_MAP.put(CashoutType.CASHOUT, R.string.cashout);

        SELECT_OPTION_MAP.put(RefundReason.RETURN, R.string.option_return);
        SELECT_OPTION_MAP.put(RefundReason.ADJUSTMENT, R.string.adjustment);

        SELECT_OPTION_MAP.put(MOTOType.MAIL, R.string.mail_order);
        SELECT_OPTION_MAP.put(MOTOType.PHONE, R.string.phone_order);

        SELECT_OPTION_MAP.put(TaxReason.TAX_NOT_USED, R.string.tax_not_used);
        SELECT_OPTION_MAP.put(TaxReason.TAX_EXEMPT, R.string.tax_exempt);
        SELECT_OPTION_MAP.put(TaxReason.ENTER_TAX_AGAIN, R.string.enter_tax_amount_again);

        SELECT_OPTION_MAP.put(DuplicateType.OVERRIDE, R.string.override);
        SELECT_OPTION_MAP.put(DuplicateType.GET_ORIG_RESP, R.string.get_original_response_data);

        SELECT_OPTION_MAP.put(CardType.VISA, R.string.visa);
        SELECT_OPTION_MAP.put(CardType.MASTER_CARD, R.string.mastercard);
        SELECT_OPTION_MAP.put(CardType.AMEX, R.string.amex);
        SELECT_OPTION_MAP.put(CardType.DINERS, R.string.diners);
        SELECT_OPTION_MAP.put(CardType.DISCOVER, R.string.discover);
        SELECT_OPTION_MAP.put(CardType.CUP, R.string.unionpay);
        SELECT_OPTION_MAP.put(CardType.JCB, R.string.jcb);
        SELECT_OPTION_MAP.put(CardType.ENROUTE, R.string.enroute);
        SELECT_OPTION_MAP.put(CardType.EXTENDED, R.string.extended);
        SELECT_OPTION_MAP.put(CardType.VISA_FLEET, R.string.visa_fleet);
        SELECT_OPTION_MAP.put(CardType.MASTERCARD_FLEET, R.string.mastercard_fleet);
        SELECT_OPTION_MAP.put(CardType.FLEET_ONE, R.string.fleet_one);
        SELECT_OPTION_MAP.put(CardType.FLEET_WIDE, R.string.fleet_wide);
        SELECT_OPTION_MAP.put(CardType.FUELMAN, R.string.fuelman);
        SELECT_OPTION_MAP.put(CardType.GASCARD, R.string.gascard);
        SELECT_OPTION_MAP.put(CardType.VOYAGER, R.string.voyager);
        SELECT_OPTION_MAP.put(CardType.WRIGHT_EXPRESS, R.string.wrightexpress);

        SELECT_OPTION_MAP.put(TransType.SALE, R.string.sale);
        SELECT_OPTION_MAP.put(TransType.REDEEM, R.string.redeem); //For loyalty
        SELECT_OPTION_MAP.put(TransType.RETURN, R.string.trans_return);
        SELECT_OPTION_MAP.put(TransType.BALANCE, R.string.balance);
        SELECT_OPTION_MAP.put(TransType.FORCED, R.string.forced);
        SELECT_OPTION_MAP.put(TransType.VFRCD, R.string.vfrcd);
        SELECT_OPTION_MAP.put(TransType.VRTRN, R.string.vrtrn);
        SELECT_OPTION_MAP.put(TransType.AUTH, R.string.auth);
        SELECT_OPTION_MAP.put(TransType.POSTAUTH, R.string.postauth);
        SELECT_OPTION_MAP.put(TransType.INCREMENTAL_AUTH, R.string.incremental_auth);
        SELECT_OPTION_MAP.put(TransType.BALANCE_WITH_LOCK, R.string.balance_lock);
        SELECT_OPTION_MAP.put(TransType.REDEMPTION_WITH_UNLOCK, R.string.redemption_unlock);
        SELECT_OPTION_MAP.put(TransType.VAUTH, R.string.vauth);
        SELECT_OPTION_MAP.put(TransType.VERIFY, R.string.verify);
        SELECT_OPTION_MAP.put(TransType.TOKENIZE, R.string.tokenize);
        SELECT_OPTION_MAP.put(TransType.WITHDRAWAL, R.string.withdrawal);
        SELECT_OPTION_MAP.put(TransType.ADD, R.string.add);
        SELECT_OPTION_MAP.put(TransType.ADDTIP, R.string.addtip);
        SELECT_OPTION_MAP.put(TransType.ACTIVATE, R.string.activate);
        SELECT_OPTION_MAP.put(TransType.UNLOAD, R.string.unload);
        SELECT_OPTION_MAP.put(TransType.VOID, R.string.trans_void);
        SELECT_OPTION_MAP.put(TransType.ISSUE, R.string.issue);
        SELECT_OPTION_MAP.put(TransType.CASHOUT, R.string.trans_cashout);
        SELECT_OPTION_MAP.put(TransType.VSALE, R.string.vsale);
        SELECT_OPTION_MAP.put(TransType.DEACT, R.string.deact);
        SELECT_OPTION_MAP.put(TransType.VPOST, R.string.vpost);
        SELECT_OPTION_MAP.put(TransType.VWITHDRAW, R.string.vwithdraw);
        SELECT_OPTION_MAP.put(TransType.REPLACE, R.string.replace);
        SELECT_OPTION_MAP.put(TransType.MERGE, R.string.merge);
        SELECT_OPTION_MAP.put(TransType.TRANSFER, R.string.transfer);
        SELECT_OPTION_MAP.put(TransType.REVERSAL, R.string.reversal);
        SELECT_OPTION_MAP.put(TransType.ADJUST, R.string.adjust);
        SELECT_OPTION_MAP.put(TransType.UPLOAD, R.string.upload);
        SELECT_OPTION_MAP.put(TransType.BATCH_CLOSE, R.string.batch_close);

        SELECT_OPTION_MAP.put(EDCType.CREDIT, R.string.credit);
        SELECT_OPTION_MAP.put(EDCType.DEBIT, R.string.debit);
        SELECT_OPTION_MAP.put(EDCType.EBT, R.string.ebt);
        SELECT_OPTION_MAP.put(EDCType.CASH, R.string.cash);
        SELECT_OPTION_MAP.put(EDCType.GIFT, R.string.gift);
        SELECT_OPTION_MAP.put(EDCType.LOYALTY, R.string.loyalty);
        SELECT_OPTION_MAP.put(EDCType.SUBGIFT, R.string.subgift); // Sub Services
        SELECT_OPTION_MAP.put(EDCType.SUBLOYALTY, R.string.subloyalty);
        SELECT_OPTION_MAP.put(EDCType.CHECK, R.string.check);

        SELECT_OPTION_MAP.put(SearchCriteria.BY_TRANS_NUMBER, R.string.by_trans_number);
        SELECT_OPTION_MAP.put(SearchCriteria.BY_LAST_4_DIGITS, R.string.by_last_4_digits);
        SELECT_OPTION_MAP.put(SearchCriteria.BY_INVOICE_NUMBER, R.string.by_invoice_number);
        SELECT_OPTION_MAP.put(SearchCriteria.BY_CARD_TYPE, R.string.by_card_type);
        SELECT_OPTION_MAP.put(SearchCriteria.BY_CLERK_SERVER_ID, R.string.by_clerk_server_id);
        SELECT_OPTION_MAP.put(SearchCriteria.BY_UNTIPPED, R.string.by_untipped);
        SELECT_OPTION_MAP.put(SearchCriteria.BY_REFERENCE_NUMBER, R.string.by_reference_number);
        SELECT_OPTION_MAP.put(SearchCriteria.BY_STORE_FORWARD, R.string.by_store_forward);
        SELECT_OPTION_MAP.put(SearchCriteria.BY_UPLOAD_TRANS, R.string.by_upload_trans);
        SELECT_OPTION_MAP.put(SearchCriteria.BY_RESENT_FAILED, R.string.by_resent_failed);

        SELECT_OPTION_MAP.put(BatchType.BATCH_CLOSE, R.string.batch_type_batch_close);
        SELECT_OPTION_MAP.put(BatchType.PURGE_BATCH, R.string.batch_type_purge_batch);
        SELECT_OPTION_MAP.put(BatchType.HOST_TOTALS, R.string.batch_type_host_totals);

//        SELECT_OPTION_MAP.put(BatchType.BATCH_CLOSE_NEW, R.string.batch_type_batch_close);
//        SELECT_OPTION_MAP.put(BatchType.PURGE_BATCH_NEW, R.string.batch_type_purge_batch);
//        SELECT_OPTION_MAP.put(BatchType.HOST_TOTALS_NEW, R.string.batch_type_host_totals);
        SELECT_OPTION_MAP.put(BatchType.FORCE_BATCH_CLOSE, R.string.batch_type_force_batch_close);


        SELECT_OPTION_MAP.put(EDCGroup.CREDIT_DEBIT_EBT, R.string.edc_group_credit_debit_ebt);

        SELECT_OPTION_MAP.put(ReportType.CURRENT_BATCH, R.string.report_type_current_batch);
        SELECT_OPTION_MAP.put(ReportType.PREVIOUS_BATCH, R.string.report_type_previous_batch);

//        SELECT_OPTION_MAP.put(ReportType.CURRENT_BATCH_NEW, R.string.report_type_current_batch);
//        SELECT_OPTION_MAP.put(ReportType.PREVIOUS_BATCH_NEW, R.string.report_type_previous_batch);

        SELECT_OPTION_MAP.put(AccountType.SAVING, R.string.acc_type_saving);
        SELECT_OPTION_MAP.put(AccountType.CHECKING, R.string.acc_type_checking);

    }

}
