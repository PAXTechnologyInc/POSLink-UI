package com.pax.pay.ui.def.constant;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Kim.L 2019/8/12
 * <b>!!!
 * ANY HOST SPECIAL FIELD IS NOT ALLOWED TO ADD HERE
 * for those fields, please extend this to your lib-host, and extend their related controller, action in your lib-host
 * !!!
 * naming rule:
 * normal key: "xxx_xxx_xxx"
 * primary/foreign key: "_xxx_xxx_id"
 * </b>
 */
@SuppressWarnings("unused")
public final class EdcTransContract {

    /**
     * A query parameter that limits the number of results returned for supported URIs. The
     * parameter value should be an integer.
     *
     * <p>In order to limit the number of rows returned by a non-supported URI, you can implement a
     * {@link android.database.CursorWrapper} and override the {@link android.database.CursorWrapper#getCount()} methods.
     */
    public static final String LIMIT_PARAM_KEY = "limit";
    /**
     * The authority for the contacts provider, READ ONLY!!!
     */
    private static final String AUTHORITY_SUFFIX = ".internal";
    private static final String SECURE_PARAM_PREFIX = "s_";
    private static final String EMV_PARAM_PREFIX = "emv_";

    private EdcTransContract() {
    }

    /**
     * A content:// style uri to the authority for the contacts provider
     */
    public static Uri getBaseUri(Context context) {
        return Uri.parse("content://" + context.getPackageName() + AUTHORITY_SUFFIX);
    }

//    public static Uri insert(Context context, Uri uri, Map<String, Object> data) {
//        ContentResolver contentResolver = context.getContentResolver();
//        return contentResolver.insert(uri, ContractUtils.convertFromMap(data));
//    }

    protected interface UnGroupedColumns {
        /**
         * <P>Type: Text</P>
         */
        String INVOICE = "invoice";
        /**
         * <P>Type: Text</P>
         */
        String CLERK_ID = "clerk_id";
        /**
         * <P>Type: Text</P>
         */
        String CLERK_NAME = "clerk_name";
    }

    protected interface IdColumns {
        /**
         * The unique ID for a row.
         * <P>Type: Integer</P>
         */
        String _ID = "_id";
    }

    protected interface ForeignIdColumns {
        /**
         * Foreign key, for querying sub table
         * <P>Type: Integer</P>
         */
        String _FOREIGN_ID = "_foreign_id";
    }

    protected interface CommonColumns {
        /**
         * <P>Type: Long</P>
         */
        String TRANS_NUMBER = "trans_no";

        /**
         * <P>Type: String</P>
         */
        String GLOBAL_UID = "global_uid";

        /**
         * <P>Type: Text</P>
         */
        String BATCH_NUMBER = "batch_no";

        /**
         * <P>Type: Integer</P>
         */
        String BATCH_TYPE = "batch_type";

        /**
         * The time that this transaction was created on its originating client.
         * <P>Type: TEXT (format: yyyyMMdd)</P>
         */
        String TRANS_DATE = "created_date";

        /**
         * The time that this transaction was created on its originating client.
         * <P>Type: TEXT (format: HHmmss)</P>
         */
        String TRANS_TIME = "created_time";

        /**
         * <P>Type: Text</P>
         *
         * @see com.paxus.data.type.EdcContext.EdcType
         */
        String EDC_TYPE = "edc";

        /**
         * <P>Type: Text</P>
         *
         * @see com.paxus.data.type.TransContext.TransType
         */
        String TRANS_TYPE = "trans_type";

        /**
         * <P>Type: Integer</P>
         *
         * @see com.paxus.data.type.TransContext.SubTransType
         */
        String SUB_TRANS_TYPE = "sub_trans_type";
    }

    protected interface StatusColumns {
        /**
         * <P>Type: Boolean</P>
         */
        String IS_FPS_TRANS = "fps";
        /**
         * <P>Type: Integer</P>
         *
         * @see StatusType
         */
        String LIFE_CYCLE_STATUS = "status";

        /**
         * <P>Type: Text</P>
         *
         * @see com.pax.pay.ui.def.constant.TransContext.ReqStatus
         */
        String REQ_STATUS = "trans_status";

        /**
         * <P>Type: Boolean</P>
         */
        String IS_DEMO = "demo";
        /**
         * <P>Type: Boolean</P>
         */
        String IS_ONLINE = "online";
        /**
         * <P>Type: Boolean</P>
         */
        String IS_SF_OFFLINE = "sf_offline";
        /**
         * <P>Type: Boolean</P>
         */
        String IS_PINLESS_DEBIT = "pinless_debit";
        /**
         * <P>Type: Boolean</P>
         */
        String IS_SIGN_FREE = "sign_free";
        /**
         * <P>Type: Boolean</P>
         */
        String IS_SPLIT_TENDER = "split_tender";
        /**
         * <P>Type: Boolean</P>
         */
        String IS_FSA_TRANS = "fsa";

        @IntDef({StatusType.PENDING, StatusType.COMPLETED, StatusType.BATCHED})
        @Retention(RetentionPolicy.SOURCE)
        @interface StatusType {
            /**
             * new generated record, invalid record, should be remove.
             */
            int INIT = 0;
            /**
             * record was interrupted accidentally during processing, should be reversed.
             */
            int PENDING = 1;
            /**
             * completed record in the current batch
             */
            int COMPLETED = 2;
            /**
             * batched record, keep in database for report,
             * records in other tables related may be able to deleted?
             */
            int BATCHED = 3;
        }

    }

    protected interface BaseAmountColumns {
        /**
         * <P>Type: Text</P>
         *
         * @see com.paxus.data.type.Currency
         * update after EnterAmount
         */
        String CURRENCY = "currency";
        /**
         * <P>Type: Long</P>
         */
        String BASE_AMOUNT = "base_amount";
        /**
         * <P>Type: Long</P>
         * Total Amount
         */
        String AMOUNT = "amount";
        /**
         * <P>Type: Long</P>
         */
        String TIP0 = "tip0";
        /**
         * <P>Type: Long</P>
         */
        String TIP1 = "tip1";
        /**
         * <P>Type: Long</P>
         */
        String TIP2 = "tip2";
        /**
         * <P>Type: Long</P>
         */
        String CASH_BACK = "cash_back";
        /**
         * <P>Type: Long</P>
         */
        String TAX = "tax";
        /**
         * <P>Type: Long</P>
         */
        String AUTH_AMOUNT = "auth_amount";
        /**
         * <P>Type: Long</P>
         */
        String MERCHANT_FEE = "merchant_fee"; // for Debit card, not transaction itself
        /**
         * <P>Type: Long</P>
         */
        String SURCHARGE_FEE = "surcharge_fee"; //for Credit card, not transaction itself
        /**
         * <P>Type: Long</P>
         */
        String ADDITIONAL_FEE = "additional_fee";

        /**
         * <P>Type: Long</P>
         */
        String CASH_DISCOUNT = "cash_discount";

        /**
         * <P>Type: Long</P>
         */
        String TIP_DISCOUNT = "tip_discount";
    }

    protected interface CardColumns {
        /**
         * Raw PAN
         * <P>Type: Text</P>
         */
        String PAN = SECURE_PARAM_PREFIX + "pan";
        /**
         * <P>Type: Text</P>
         *
         * @see com.paxus.data.type.CardContext.CardType
         */
        String CARD_TYPE = "card_type";
        /**
         * Raw Card Holder Name
         * <P>Type: Text</P>
         */
        String HOLDER_NAME = SECURE_PARAM_PREFIX + "holder_name";
        /**
         * Raw Expiry
         * <P>Type: TEXT (format:MMYY)</P>
         */
        String EXPIRY = SECURE_PARAM_PREFIX + "expiry";
        /**
         * Raw S&F Offline Manual Credit Sale need this field
         * <P>Type: TEXT</P>
         */
        String CVV = SECURE_PARAM_PREFIX + "cvv";
        /**
         * Raw Track1
         * <P>Type: TEXT</P>
         */
        String TRACK1 = SECURE_PARAM_PREFIX + "track1";
        /**
         * Raw Track2
         * <P>Type: TEXT</P>
         */
        String TRACK2 = SECURE_PARAM_PREFIX + "track2";
        /**
         * Raw Track3
         * <P>Type: TEXT</P>
         */
        String TRACK3 = SECURE_PARAM_PREFIX + "track3";
    }

    protected interface AvsColumns {
        /**
         * Raw Zip
         * <P>Type: TEXT</P>
         */
        String ZIP = SECURE_PARAM_PREFIX + "zip";
        /**
         * Raw Address
         * <P>Type: TEXT</P>
         */
        String ADDRESS = SECURE_PARAM_PREFIX + "address";
    }

    protected interface AccountColumns extends CardColumns, AvsColumns {

        /**
         * <P>Type: Text</P>
         *
         * @see com.paxus.data.type.NoCVVReason
         */
        String NO_CVV_REASON = "no_cvv_reason";
        /**
         * Entry mode, may be combined with Fallback flag if it's {@link com.paxus.data.type.EntryMode#SWIPE}
         * <P>Type: Integer</P>
         *
         * @see com.paxus.data.type.EntryMode
         */
        String ENTRY_MODE = "entry_mode";
        /**
         * <P>Type: Text</P>
         * TransArmor
         */
        String TOKEN = "token";
        /**
         * <P>Type: Boolean</P>
         */
        String CARD_PRESENT = "card_present";
        /**
         * <P>Type: Text</P>
         */
        String PIN_BLOCK = SECURE_PARAM_PREFIX + "pin_block";
        /**
         * <P>Type: Text</P>
         */
        String KSN = SECURE_PARAM_PREFIX + "ksn";
        /**
         * <P>Type: Integer</P>
         */
        String PIN_RETRY_TIME = "pin_retry_time";
        /**
         * <P>Type: blob(short array)</P>
         */
        String SIGN_DATA = "sign_data";

        /**
         * <P>Type: Text</P>
         *
         * @see com.paxus.data.type.SignStatus
         */
        String SIGN_STATUS = "sign_status";
    }

    protected interface RestaurantColumns {
        /**
         * <P>Type: Text</P>
         */
        String TABLE_NUMBER = "table_no";
        /**
         * <P>Type: Text</P>
         */
        String GUEST_NUMBER = "guest_no";
    }

    protected interface BalanceColumns {
        //-------------- Balance, Unit: Amount --> HAB balance0 --------------
        /**
         * <P>Type: Text</P>
         */
        String BALANCE0 = "balance0";
        //-------------- Balance, Unit: Point --> HAB balance1 --------------
        /**
         * <P>Type: Text</P>
         */
        String BALANCE1 = "balance1";
    }

    protected interface CommercialColumns {

        /**
         * <P>Type: Boolean</P>
         */
        String HAS_COMMERCIAL_DATA = "commercial";

        /**
         * <P>Type: Text</P>
         */
        String CUSTOMER_CODE = "customer_code";
        /**
         * <P>Type: Text</P>
         *
         * @see com.paxus.data.type.TaxReason
         */
        String TAX_REASON = "tax_reason";
        /**
         * <P>Type: Text</P>
         */
        String TAX_EXEMPT_ID = "tax_exempt_id";
        /**
         * <P>Type: Text</P>
         */
        String PO_NUMBER = "po_number";
        /**
         * <P>Type: Text</P>
         */
        String MERCHANT_TAX_ID = "merchant_tax_id"; //for commercial Card
        /**
         * <P>Type: Text</P>
         */
        String DEST_ZIP_CODE = "dest_zip_code"; //for commercial Card
        /**
         * <P>Type: Text</P>
         */
        String PROD_DESC = "prod_desc"; //for commercial Card

        /**
         * <P>Type: Text</P>
         *
         * @see com.paxus.data.type.BusinessType
         */
        String BUSINESS_TYPE = "business_type";
    }

    protected interface OriginalColumns {
        /**
         * The time that this transaction was created on its originating client.
         * <P>Type: TEXT (format: yyyyMMdd)</P>
         */
        String ORIG_TRANS_DATE = "orig_created_date";

        /**
         * The time that this transaction was created on its originating client.
         * <P>Type: TEXT (format: HHmmss)</P>
         */
        String ORIG_TRANS_TIME = "orig_created_time";

        /**
         * <P>Type: Long</P>
         */
        String ORIG_TRANS_NUMBER = "orig_trans_no";

        /**
         * <P>Type: Text</P>
         *
         * @see com.paxus.data.type.TransContext.TransType
         */
        String ORIG_TRANS_TYPE = "orig_trans_type";

        /**
         * <P>Type: Text</P>
         *
         * @see com.paxus.data.type.TransContext.SubTransType
         */
        String ORIG_SUB_TRANS_TYPE = "orig_sub_trans_type";

        /**
         * <P>Type: Text</P>
         */
        String ORIG_AUTH_AMOUNT = "orig_auth_amount";

        /**
         * <P>Type: Text</P>
         */
        String ORIG_REF_NUMBER = "orig_ref_no";
    }

    protected interface FpsColumns {

        /**
         * <P>Type: Integer</P>
         *
         * @see com.paxus.data.type.ReceiptContext.FpsPrintMode
         */
        String FPS_PRINT_MODE = "fps_print_mode";

        /**
         * <P>Type: Integer</P>
         *
         * @see com.paxus.data.type.ReceiptContext.FpsPrintRule
         */
        String FPS_SIGN_TYPE = "fps_sign_type";

        /**
         * <P>Type: Boolean</P>
         */
        String FPS_PRINT_FLAG = "fps_print_flag";
    }

    /**
     * for gift & loyalty only, so don't need security prefix
     */
    protected interface NewAccountColumns {
        /**
         * <P>Type: Text</P>
         */
        String NEW_ACCOUNT = "new_account";
        /**
         * <P>Type: Text</P>
         */
        String NEW_TRACK1 = "new_track1";
        /**
         * <P>Type: Text</P>
         */
        String NEW_TRACK2 = "new_track2";
        /**
         * <P>Type: Text</P>
         */
        String NEW_ENTRY_MODE = "new_entry_mode";

    }

    protected interface StoreForwardColumns {
        /**
         * <P>Type: Integer</P>
         *
         * @see com.paxus.data.type.StoreForwardContext.StoreForwardUploadStatus
         */
        String SF_UPLOAD_STATUS = "sf_upload_status";
    }


    protected interface EWICBalanceInfoColumns {
        /**
         * <P>Type: Text</P>
         */
        String PRODUCT_CODE = "product_code";
        /**
         * <P>Type: Text</P>
         */
        String SUB_CODE = "sub_code";
        /**
         * <P>Type: Text</P>
         */
        String E_WIC_BALANCE = "e_wic_balance";
    }

    protected interface EWICDetailDataColumns {

        /**
         * <P>Type: Text</P>
         *
         * @see com.paxus.data.type.UPCPLUInd
         */
        String UPCPLU_TYPE = "upc_plu_type"; //This field identifies whether the group contains UPC or PLU data. 0: UPC data 1: PLU data
        /**
         * <P>Type: Text</P>
         */
        String UPCPLU_DATA = "upc_plu_data";
        /**
         * <P>Type: Long</P>
         */
        String UPC_PRICE = "upc_price";
        /**
         * <P>Type: Long</P>
         */
        String UPC_QTY = "upc_qty";
        //++++++++++++For Response++++++++++++++++
        /**
         * <P>Type: Text</P>
         */
        String ACTION_CODE = "action_code";
        /**
         * <P>Type: Long</P>
         */
        String ORIG_PRICE = "orig_price";
        /**
         * <P>Type: Long</P>
         */
        String ORIG_QTY = "orig_qty";
    }


    protected interface EbtColumns {

        /**
         * <P>Type: Integer</P>
         *
         * @see com.paxus.data.type.EdcContext.EbtType
         */
        String EBT_TYPE = "ebt_type";
        /**
         * <P>Type: Text</P>
         */
        String VOUCHER_NUMBER = "voucher_no";
        /**
         * <P>Type: Long</P>
         */
        String CASH_BENEFIT_BALANCE = "cash_benefit_balance";
        /**
         * <P>Type: Long</P>
         */
        String FOOD_STAMP_BALANCE = "food_stamp_balance";
        /**
         * <P>Type: Text</P>
         */
        String RSP_EWIC_EXPIRY = "rsp_ewic_expiry";
    }

    protected interface EmvColumns {

        /**
         * <P>Type: Text</P>
         */
        String ICC_DATA = EMV_PARAM_PREFIX + "icc_data";
        /**
         * <P>Type: Text</P>
         */
        String RECV_ICC_DATA = EMV_PARAM_PREFIX + "recv_icc_data";

        /**
         * <P>Type: Text</P>
         * TAG 5F34
         */
        String EMV_CARD_SRQ_NUM = EMV_PARAM_PREFIX + "card_seq_num";
        /**
         * <P>Type: Text</P>
         * TAG 9F6E
         */
        String CLSS_CAP = EMV_PARAM_PREFIX + "clss_cap";

        /**
         * <P>Type: Text</P>
         * tag 95
         */
        String EMV_TVR = EMV_PARAM_PREFIX + "tvr";
        /**
         * <P>Type: Text</P>
         * tag 9B
         */
        String EMV_TSI = EMV_PARAM_PREFIX + "tsi";
        /**
         * <P>Type: Text</P>
         * tag 9F36
         */
        String EMV_ATC = EMV_PARAM_PREFIX + "atc";

        /**
         * <P>Type: Text</P>
         * tag 4F / 9F06
         */
        String EMV_AID = EMV_PARAM_PREFIX + "aid";
        /**
         * <P>Type: Text</P>
         * tag 50
         */
        String EMV_APP_LABEL = EMV_PARAM_PREFIX + "emv_app_label";
        /**
         * <P>Type: Text</P>
         * tag 9F26 (BIN)
         */
        String EMV_TC = EMV_PARAM_PREFIX + "tc";
        /**
         * <P>Type: Text</P>
         * tag 9F12
         */
        String EMV_APP_NAME = EMV_PARAM_PREFIX + "emv_app_name";
        /**
         * <P>Type: Text</P>
         * tag 8A
         */
        String EMV_ARC = EMV_PARAM_PREFIX + "arc";
        /**
         * <P>Type: Text</P>
         * tag 9F10
         */
        String EMV_IAD = EMV_PARAM_PREFIX + "iad";

        /**
         * <P>Type: Text</P>
         * tag 9F27, for Quick Chip, First CID
         */
        String EMV_CID = EMV_PARAM_PREFIX + "cid";

        /**
         * <P>Type: Text</P>
         * tag 82 AIP
         */
        String EMV_AIP = EMV_PARAM_PREFIX + "aip";

        /**
         * <P>Type: Text</P>
         * tag 89 Authorisation Code
         */
        String EMV_AUTHORISATION_CODE = EMV_PARAM_PREFIX + "auth_code";

        /**
         * <P>Type: Text</P>
         * tag 9F08 Application Version Number
         */
        String EMV_APP_VER_NUM = EMV_PARAM_PREFIX + "app_ver_num";

        /**
         * <P>Type: Text</P>
         * tag 91 Issuer Authentication Data
         */
        String EMV_ISSUER_AUTH_DATA = EMV_PARAM_PREFIX + "issuer_auth_data";

        /**
         * <P>Type: Text</P>
         * tag 8D CDOL2
         */
        String EMV_CDOL2 = EMV_PARAM_PREFIX + "cdol2";

        /**
         * <P>Type: Integer</P>
         * tag 9F34 CVM Result
         */
        String EMV_CVM_RESULT = EMV_PARAM_PREFIX + "cvmr";
    }

    protected interface FSAColumns {

        /**
         * <P>Type: Integer</P>
         *
         * @see com.paxus.data.type.FsaContext.FsaType
         */
        String FSA_TYPE = "fsa_type";
        /**
         * <P>Type: Integer</P>
         */
        String FSA_AMOUNT = "fsa_amount"; //For both HealthCare / Transit
        /**
         * <P>Type: Integer</P>
         */
        String HEALTH_AMOUNT = "health_amount";
        /**
         * <P>Type: Integer</P>
         */
        String TRANSIT_AMOUNT = "transit_amount";
        /**
         * <P>Type: Integer</P>
         */
        String RX_AMOUNT = "rx_amount";//HSA
        /**
         * <P>Type: Integer</P>
         */
        String VISION_AMOUNT = "vision_amount";//HSA
        /**
         * <P>Type: Integer</P>
         */
        String CLINICAL_AMOUNT = "clinical_amount";//HSA
        /**
         * <P>Type: Integer</P>
         */
        String COPAY_AMOUNT = "copay_amount";//HSA
        /**
         * <P>Type: Integer</P>
         */
        String DENTAL_AMOUNT = "dental_amount";//HSA
    }

    protected interface MOTOColumns {
        /**
         * <P>Type: Text</P>
         *
         * @see com.paxus.data.type.MOTOType
         */
        String MOTO_TYPE = "moto_type";

        /**
         * Customer Service Phone Number
         * <P>Type: Text</P>
         */
        String CS_PHONE_NUMBER = "cs_phone_no";
        /**
         * <P>Type: Text</P>
         * Yanina: Order Number is not used by MOTO only.
         */
        String ORDER_NUMBER = "order_no";

    }

    protected interface PCL3Columns {
        /**
         * <P>Type: Integer</P>
         */
        String ITEM_SEQ_NUM = "item_seq_num"; //L3ItemSeqNum
        /**
         * <P>Type: Text</P>
         */
        String ITEM_CODE = "item_code"; //L3ItemCode
        /**
         * <P>Type: Text</P>
         */
        String ITEM_DESC = "item_desc"; //L3ItemDesc
        /**
         * <P>Type: Long</P>
         */
        String ITEM_QUANTITY = "item_qty"; //L3Qty
        /**
         * <P>Type: Integer</P>
         */
        String ITEM_QUANTITY_EXPONENT = "item_qty_exponent"; //only for MasterCard BatchSettleL3 transactions.
        /**
         * <P>Type: Text</P>
         */
        String UNIT_OF_MEASURE = "unit_of_measure"; //L3UnitOfMeasure
        /**
         * <P>Type: Long</P>
         */
        String UNIT_COST = "unit_cost"; //L3UnitCost
        /**
         * <P>Type: Integer</P>
         */
        String UNIT_COST_EXPONENT = "unit_cost_exponent"; //only for MasterCard BatchSettleL3 transactions.
        /**
         * <P>Type: Long</P>
         */
        String LINE_ITEM_TOTAL = "line_item_total"; //L3ItemTot
        /**
         * <P>Type: Long</P>
         */
        String DISCOUNT_AMT = "discount_amt"; //L3DiscountAmt
        /**
         * <P>Type: Long</P>
         */
        String DISCOUNT_RATE = "discount_rate"; //L3DiscountRate
        /**
         * <P>Type: Long</P>
         */
        String TAX_AMT = "tax_amt"; //L3TaxAmt
        /**
         * <P>Type: Integer</P>
         */
        String TAX_RATE = "tax_rate"; //L3TaxRt
        /**
         * <P>Type: Text</P>
         */
        String COMMODITY_CODE = "commodity_code"; //only applicable to Visa BatchSettleL3 transactions.
    }

    protected interface TaxDetailColumns {
        /**
         * <P>Type: Integer</P>
         */
        String TAX_TYPE = "tax_type";
        /**
         * <P>Type: Long</P>
         */
        String TAX_AMOUNT = "tax_amount";
        /**
         * <P>Type: Integer</P>
         */
        String TAX_RATE = "tax_rate";
        /**
         * <P>Type: Text</P>
         */
        String MERCHANT_TAX_ID = "merchant_tax_id";
        /**
         * <P>Type: Text</P>
         */
        String CUSTOMER_TAX_ID = "customer_tax_id";
        /**
         * <P>Type: Text</P>
         */
        String VAT_INVOICE_NUMBER = "vat_invoice_number";
    }

    protected interface OnlineResponseColumns {

        /**
         * <P>Type: Text</P>
         *
         * @see com.paxus.data.type.TransContext.OnlineRespStatus
         */
        String RESP_STATUS = "resp_status";

        /**
         * <P>Type: Text</P>
         */
        String RESP_CODE = "resp_code";

        /**
         * <P>Type: Text</P>
         */
        String RESP_MSG = "resp_msg";

        /**
         * <P>Type: Text</P>
         */
        String AUTH_CODE = "auth_code";

        /**
         * <P>Type: Text</P>
         */
        String REF_NUMBER = "ref_no";

        /**
         * AKA SEQ ID or STAN
         * <P>Type: Text</P>
         */
        String TRACE_NUMBER = "trace_no";

        /**
         * CVV Response Code
         * <P>Type: Text</P>
         */
        String CVV_RESULT = "cvv_result";

        /**
         * CVV Response Message
         * <P>Type: Text</P>
         */
        String CVV_RESP = "cvv_resp";

        /**
         * <P>Type: Text</P>
         */
        String AVS_RESULT = "avs_result";

        /**
         * <P>Type: Text</P>
         */
        String AVS_RESP = "avs_resp";

        /**
         * <P>Type: Text</P>
         */
        String GATEWAY_TXN_ID = "gateway_tnx_id";

        /**
         * <P>Type: Text</P>
         */
        String ADDITION_FEE = "addition_fee";
    }

    protected interface FleetProductColumns {
        /**
         * <P>Type: Text</P>
         */
        String FLEET_PRODUCT_CODE = "fleet_product_code";
        /**
         * <P>Type: Long</P>
         */
        String FLEET_PRODUCT_AMOUNT = "fleet_product_amount";
        /**
         * <P>Type: Long</P>
         */
        String FLEET_UNIT_PRICE = "fleet_unit_price";
        /**
         * <P>Type: Long</P>
         */
        String FLEET_QUANTITY = "fleet_quantity";
        /**
         * <P>Type: Text</P>
         */
        String FLEET_UNIT_OF_MEASURE = "fleet_unit_of_measure";
    }

    protected interface FleetColumns {
        /**
         * <P>Type: Long</P>
         */
        String FLEET_FUEL_AMOUNT = "fleet_fuel_amount";
        /**
         * <P>Type: Long</P>
         */
        String FLEET_ODOMETER = "fleet_odometer";
        /**
         * For Wex, this field is embossed on the front of the card as Vehicle Card No.
         * <P>Type: Text</P>
         */
        String FLEET_VEHICLE_NUMBER = "fleet_vehicle_no";
        /**
         * Only for Wex, Same as Job ID, Job # Attribute from Alpha_Numeric to Numeric and length from 12 to 9 on May 4,2015;
         * <P>Type: Text</P>
         */
        String FLEET_JOB_NUMBER = "fleet_job_no";
        /**
         * <P>Type: Text</P>
         */
        String FLEET_DRIVER_ID = "fleet_driver_no";
        /**
         * <P>Type: Text</P>
         */
        String FLEET_EMPLOYEE_ID = "fleet_employee_id";
        /**
         * <P>Type: Text</P>
         */
        String FLEET_LICENSE_NUMBER = "fleet_license_no";
        /**
         * <P>Type: Text</P>
         */
        String FLEET_DEPARTMENT_NUMBER = "fleet_department_no";
        /**
         * <P>Type: Text</P>
         */
        String FLEET_CUSTOMER_DATA = "fleet_customer_data";
        /**
         * <P>Type: Text</P>
         */
        String FLEET_USER_ID = "fleet_user_id";
        /**
         * <P>Type: Text</P>
         */
        String FLEET_VEHICLE_ID = "fleet_vehicle_id";
        /**
         * <P>Type: Text</P>
         */
        String FLEET_SERVER_RES_CODE = "fleet_server_res_code";
    }

    public static final class EWICBalanceInfoData implements IdColumns, ForeignIdColumns, EWICBalanceInfoColumns {

        private EWICBalanceInfoData() {
        }
    }

    public static final class EWICDetailData implements IdColumns, ForeignIdColumns, EWICDetailDataColumns {

        private EWICDetailData() {
        }
    }

    public static final class PCL3Data implements IdColumns, ForeignIdColumns, PCL3Columns {
        private PCL3Data() {
        }
    }

    public static final class TaxDetailData implements IdColumns, TaxDetailColumns {
        /**
         * Foreign key
         * <P>Type: Integer</P>
         */
        public static final String _TRANS_ID = "_trans_id";

        private TaxDetailData() {
        }
    }

    public static final class FleetProductData implements IdColumns, ForeignIdColumns, FleetProductColumns {

        private FleetProductData() {
        }
    }

    /**
     * 1. Columns implemented means one-one relation
     * 2. Columns not implemented means one-many relation, requires function call like getXXXUri
     */
    public abstract static class CommonTrans implements IdColumns,
            CommonColumns, BaseAmountColumns, AccountColumns, StatusColumns,
            RestaurantColumns, BalanceColumns, CommercialColumns, EmvColumns, FleetColumns,
            FSAColumns, FpsColumns, MOTOColumns, OnlineResponseColumns,
            StoreForwardColumns, UnGroupedColumns {

        /**
         * <p>Type: List[Map<String, Object>]</p>
         *
         * @see FleetProductData
         */
        public static final String FLEET_PRODUCT = "fleet_product_list";
        /**
         * <p>Type: List[Map<String, Object>]</p>
         *
         * @see PCL3Data
         */
        public static final String PCL3 = "pcl3_list";

        private CommonTrans() {
        }

        public static Uri getTransUri(Context context) {
            return Uri.withAppendedPath(getBaseUri(context), "transactions");
        }

        /**
         * @see FleetProductData
         */
        public static Uri getFleetProductUri(Context context) {
            return Uri.withAppendedPath(getTransUri(context), "fleet_products");
        }

        /**
         * @see PCL3Data
         */
        public static Uri getPcl3Uri(Context context) {
            return Uri.withAppendedPath(getTransUri(context), "pcl3");
        }

        /**
         * @see TaxDetailData
         */
        public static Uri getTaxDetailUri(Context context) {
            return Uri.withAppendedPath(getTransUri(context), "tax_details");
        }

        public static String sumProjection(String field) {
            return "SUM(" + field + ")";
        }

//        public static void clearFSAData(Map data) {
//            ContractUtils.clearColumns(data, FSAColumns.class);
//        }
//
//        public static void clearFleetData(Map data) {
//            ContractUtils.clearColumns(data, FleetColumns.class);
//        }
//
//        public static boolean isContainCommercialData(Map data) {
//            return ContractUtils.containColumns(data, CommercialColumns.class);
//        }

        public static String countProjection(String field) {
            return "COUNT(" + field + ")";
        }
    }

    /**
     * abstract + private in case we need to extends from this
     */
    public abstract static class ManageTrans implements IdColumns, OriginalColumns, ForeignIdColumns {

        /**
         * Need Print it on Receipt
         * <P>Type: Text</P>
         */
        public static final String REFUND_REASON = "refund_reason";

        private ManageTrans() {
        }

        public static Uri getTransUri(Context context) {
            return Uri.withAppendedPath(getBaseUri(context), "transactions/manager");
        }
    }

    public static final class GiftLoyaltyTrans implements IdColumns, NewAccountColumns, ForeignIdColumns {

        private GiftLoyaltyTrans() {
        }

        public static Uri getTransUri(Context context) {
            return Uri.withAppendedPath(getBaseUri(context), "transactions/gift_loyalty");
        }
    }

    public static final class EbtTrans implements IdColumns, EbtColumns, ForeignIdColumns {

        /**
         * <p>Type: List[Map<String, Object>]</p>
         *
         * @see EWICDetailData
         */
        public static final String E_WIC_DETAIL = "e_wic_detail_list";
        /**
         * <p>Type: List[Map<String, Object>]</p>
         *
         * @see EWICDetailData
         */
        public static final String RSP_E_WIC_DETAIL = "rsp_e_wic_detail_list";
        /**
         * <p>Type: List[Map<String, Object>]</p>
         *
         * @see EWICBalanceInfoData
         */
        public static final String RSP_E_WIC_BALANCE = "rsp_e_wic_balance_list";

        private EbtTrans() {
        }

        public static Uri getTransUri(Context context) {
            return Uri.withAppendedPath(getBaseUri(context), "transactions/ebt");
        }

        /**
         * @see EWICDetailData
         */
        public static Uri getEWicDetailUri(Context context) {
            return Uri.withAppendedPath(getTransUri(context), "e_wic_details");
        }

        /**
         * @see EWICDetailData
         */
        public static Uri getRspEWicDetailUri(Context context) {
            return Uri.withAppendedPath(getTransUri(context), "rsp_e_wic_details");
        }

        /**
         * @see EWICBalanceInfoData
         */
        public static Uri getRspEWicBalanceUri(Context context) {
            return Uri.withAppendedPath(getTransUri(context), "rsp_e_wic_balances");
        }
    }
}