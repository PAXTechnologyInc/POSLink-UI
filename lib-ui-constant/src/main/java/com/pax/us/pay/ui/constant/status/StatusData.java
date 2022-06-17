package com.pax.us.pay.ui.constant.status;

/**
 * define extra data for status Broadcast
 */
public final class StatusData {
 private StatusData(){

 }
 /**
  * Trans Result Code
  * <p>Type: Long.</p>
  * <p>value 0 means Transaction Approved.</p>
  * <p>value -3 means Transaction Canceled.</p>
  * <p>other value means Transaction Error.</p>
  * <p>Used in {@link InformationStatus#TRANS_COMPLETED}</p>
  */
 public static final String PARAM_CODE = "resultCode";

 /**
  * Trans Result Message
  * <p>Type: String.</p>
  * <p>Used in {@link InformationStatus#TRANS_COMPLETED} and {@link InformationStatus#ERROR}</p>
  */
 public static final String PARAM_MSG = "resultMessage";
 /**
  * Message to be shown on external PIN PAD (BroadPOS use only)
  * <p>Type: String.</p>
  * <p>Used in {@link InformationStatus#TRANS_COMPLETED}</p>
  */
 public static final String PARAM_EXT_MSG = "externalResultMessage";

 /**
  * S&amp;F Type
  * <p>Type: String. see {@link com.pax.us.pay.ui.constant.entry.enumeration.SFType}</p>
  * <p>Used in {@link BatchStatus#BATCH_SF_UPLOADING}</p>
  */
 public static final String PARAM_SF_TYPE = "sfType";

 /**
  * S&amp;F Uploaded Count
  * <p>Type: Long. Default: 0</p>
  * <p>Used in {@link BatchStatus#BATCH_SF_UPLOADING}</p>
  */
 public static final String PARAM_SF_CURRENT_COUNT = "sfCurrent";

 /**
  * S&amp;F Total Count to upload
  * <p>Type: Long. Default: 0</p>
  * <p>Used in {@link BatchStatus#BATCH_SF_UPLOADING}</p>
  */
 public static final String PARAM_SF_TOTAL_COUNT = "sfTotal";

 /**
  * EDC Type
  * <p>
  *     Type: String.<br>
  *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.EDCType}
  * </p>
  */
 public static final String PARAM_EDC_TYPE = "edcType";

 /**
  * Uploaded Count
  * <p>Type: Long. Default: 0</p>
  * <p>Used in {@link Uncategory#LOG_UPLOAD_UPLOADING}</p>
  */
 public static final String PARAM_UPLOAD_CURRENT_COUNT = "uploadCurrentCount";

 /**
  * Total Count to Upload
  * <p>Type: Long. Default: 0</p>
  * <p>Used in {@link Uncategory#LOG_UPLOAD_UPLOADING}</p>
  */
 public static final String PARAM_UPLOAD_TOTAL_COUNT = "uploadTotalCount";

 /**
  * Total Amount
  * <p>Type: Long. Default: 0</p>
  * <p>Used in {@link InformationStatus#TRANS_AMOUNT_CHANGED_IN_CARD_PROCESSING}</p>
  */
 public static final String PARAM_TOTAL_AMOUNT = "totalAmount";

 /**
  * Timeout for trans result dialog
  * <p>Type: Long. Default: 2000</p>
  * <p>Used in {@link InformationStatus#TRANS_COMPLETED}</p>
  */
 public static final String PARAM_HOST_RESP_TIMEOUT = "hostRespTimeout";

 /**
  * Uploaded Percent
  * <p>Type: Long. Default: 0</p>
  * <p>Used in {@link Uncategory#LOG_UPLOAD_CONNECTED},{@link Uncategory#LOG_UPLOAD_UPLOADING}</p>
  */
 public static final String PARAM_UPLOAD_CURRENT_PERCENT = "uploadCurrentPercent";

 /**
  * Language Code of Locale.
  * <p>Type: String. See java.util.Locale for details.</p>
  * <p>Used in {@link LanguageStatus#SET_LANGUAGE}</p>
  */
 public static final String PARAM_LANGUAGE = "language";

 /**
  * Country Code of Locale.
  * <p>Type: String. See java.util.Locale for details.</p>
  * <p>Used in {@link LanguageStatus#SET_LANGUAGE}</p>
  */
 public static final String PARAM_COUNTRY = "country";

 /**
  * Prompt Message
  * <p>Type: String</p>
  * Used by {@link CardStatus#SEE_PHONE}.
  */
 public static final String PARAM_PROMPTS = "prompts";

 /**
  * Enable Swipe
  * <p>Type: Boolean</p>
  */
    public static final String PARAM_ENABLE_SWIPE = "enableSwipe";

 /**
  * Enable Insert
  * <p>Type: Boolean</p>
  */
    public static final String PARAM_ENABLE_INSERT = "enableInsert";

 /**
  * Enable Tap
  * <p>Type: Boolean</p>
  */
    public static final String PARAM_ENABLE_TAP = "enableTap";

 /**
  * Enable Manual Entry
  * <p>Type: Boolean</p>
  */
    public static final String PARAM_ENABLE_MANUAL = "enableManualEntry";

    /**
     * Display Visa Installment (For Visa Installment use only)
     * <p>Type: String</p>
     * Used by {@link InformationStatus#TRANS_COMPLETED}.<br>
     * When it is set to "Y", the terminal should display Visa Installment Approve UI message<br>
     * Otherwise the terminal should show the original credit sale success dialog
     */
    public static final String PARAM_DISPLAY_VISA_INSTALLMENT_APPROVAL = "displayVisaInstallmentApproval";

    /**
     * X coordinate
     * <p>Type: Integer</p>
     * <p>Unit: pixel</p>
     */
    public static final String PARAM_X = "x";
    /**
     * Y coordinate
     * <p>Type: Integer</p>
     * <p>Unit: pixel</p>
     */
    public static final String PARAM_Y = "y";

    /**
     * Width
     * <p>Type: Integer</p>
     * <p>Unit: pixel</p>
     */
    public static final String PARAM_WIDTH = "width";

    /**
     * Height
     * <p>Type: Integer</p>
     * <p>Unit: pixel</p>
     */
    public static final String PARAM_HEIGHT = "height";



}
