package com.pax.us.pay.ui.constant.entry;

/**
 * define Activity for POSLink
 */
public final class PoslinkEntry {
    private PoslinkEntry(){

    }
    /**
     * Activity Category: POSLINK
     */
    public static final String CATEGORY = "com.pax.us.pay.ui.category.POSLINK";

    /**
     * Activity Action: Show Dialog
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TITLE}  <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_OPTIONS} <br>
     *     Type:String[]<br>
     *     Example: {"Button1","Button2","Button3","Button4"}
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_CONTINUE_SCREEN} <br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INDEX} <br>
     *     Type: Integer<br>
     *     Example: <br>
     *     if button1 is clicked, return "0",<br>
     *     if button4 is clicked, return "3"<br>
     * </p>
     */
    public static final String ACTION_SHOW_DIALOG = "com.pax.us.pay.action.SHOW_DIALOG";


    /**
     * Activity Action:  Show Thank You
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TITLE}  </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_MESSAGE_1} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_MESSAGE_2} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} <br>
     *     Type: String
     * </p>
     * <p>
     *     Output: nothing. </p>
     */
    public static final String ACTION_SHOW_THANK_YOU = "com.pax.us.pay.action.SHOW_THANK_YOU";

    /**
     * Activity Action:  Input text
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TITLE} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INPUT_TYPE} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_MIN_LENGTH} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_MAX_LENGTH} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_DEFAULT_VALUE} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_CONTINUE_SCREEN} <br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INPUT_VALUE} <br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_INPUT_TEXT = "com.pax.us.pay.action.INPUT_TEXT";

    /**
     * Activity Action:  Show Dialog Form
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TITLE} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_LABELS} <br>
     *     Type: String[]
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_LABELS_PROPERTY} <br>
     *     Type: String[]
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_BUTTON_TYPE} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_CONTINUE_SCREEN} <br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_LABEL_SELECTED} <br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_SHOW_DIALOG_FORM = "com.pax.us.pay.action.SHOW_DIALOG_FORM";


    /**
     * Activity Action:  Show Message
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TITLE}</p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TAX_LINE} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TOTAL_LINE} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_IMAGE_URL} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_IMAGE_DESC} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_MESSAGE_LIST} <br>
     *     Type: String<br>
     *     It is a json list. Example code for read it:
     * </p>
     * <pre>
     *     InputStream inputStream = new ByteArrayInputStream(bundle.getString(EntryExtraData.PARAM_MESSAGE_LIST).getBytes());
     *         showMsgList = (List&lt;MsgInfoWrapper&gt;) JsonUtils.readListFromJSON(inputStream, MsgInfoWrapper.class);
     *     //MsgInfoWrapper definition
     *     &#64;RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
     *     public class MsgInfoWrapper {
     *     &#64;JsonProperty(value = "index")
     *     private String index;
     *     &#64;JsonProperty(value = "MsgInfo")
     *     private MsgInfo msgInfo;
     *
     *     public MsgInfoWrapper() {
     *     }
     *
     *     public MsgInfoWrapper(String index, MsgInfo msgInfo) {
     *         this.index = index;
     *         this.msgInfo = msgInfo;
     *     }
     *
     *     public String getIndex() {
     *         return index;
     *     }
     *
     *     public void setIndex(String index) {
     *         this.index = index;
     *     }
     *
     *     public MsgInfo getMsgInfo() {
     *         return msgInfo;
     *     }
     *
     *     public void setMsgInfo(MsgInfo msgInfo) {
     *         this.msgInfo = msgInfo;
     *     }
     *     }
     * </pre>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_CONTINUE_SCREEN} <br>
     *     Type: String<br>
     *     Value is always {@link com.pax.us.pay.ui.constant.entry.enumeration.ManageUIConst.ContinuousScreen#DO_NOT_GO_TO_IDLE}
     * </p>
     */
    public static final String ACTION_SHOW_MESSAGE = "com.pax.us.pay.action.SHOW_MESSAGE";


    /**
     * Activity Action:  Show Item
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TITLE} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TAX_LINE} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TOTAL_LINE} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_CURRENCY_SYMBOL} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_MESSAGE_LIST} 
     * </p>
     */
    public static final String ACTION_SHOW_ITEM = "com.pax.us.pay.action.SHOW_ITEM";


    /**
     * Activity Action:  Show Text Box
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TITLE} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TEXT} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_BUTTON_1_NAME} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_BUTTON_1_COLOR} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_BUTTON_1_KEY} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_BUTTON_2_NAME} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_BUTTON_2_COLOR} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_BUTTON_2_KEY} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_BUTTON_3_NAME} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_BUTTON_3_COLOR} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_BUTTON_3_KEY} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_ENABLE_HARD_KEY} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_HARD_KEY_LIST} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_CONTINUE_SCREEN} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_BARCODE_TYPE} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_BARCODE_DATA}  <br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_BUTTON_NUMBER} <br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_SHOW_TEXT_BOX = "com.pax.us.pay.action.SHOW_TEXT_BOX";

    /**
     * Activity Action:  Show signature box
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TITLE} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TEXT} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_SIGN_BOX} <br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_SIGN_STATUS} <br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_SIGNATURE} <br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_SHOW_SIGNATURE_BOX = "com.pax.us.pay.action.SHOW_SIGNATURE_BOX";

    /**
     * Activity Action:  Show Input Text Box
     * <p>
     *     Input: {@link EntryExtraData#PARAM_PACKAGE} is the package name of caller.<br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TRANS_MODE} is transaction mode.<br>
     *     Type: String<br>
     *     See {@link com.pax.us.pay.ui.constant.entry.enumeration.TransMode} for details
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TITLE} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TEXT} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_TIMEOUT} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_CONTINUE_SCREEN} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_INPUT_TYPE} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_MIN_LENGTH} <br>
     *     Type: String
     * </p>
     * <p>
     *     Input: {@link EntryExtraData#PARAM_MAX_LENGTH} <br>
     *     Type: String
     * </p>
     * <p>
     *     Output: {@link EntryRequest#PARAM_INPUT_VALUE} <br>
     *     Type: String
     * </p>
     */
    public static final String ACTION_SHOW_INPUT_TEXT_BOX = "com.pax.us.pay.action.SHOW_INPUT_TEXT_BOX";

} 