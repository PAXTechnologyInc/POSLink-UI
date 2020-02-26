# History
## Version :  0.4
    Add "SELECT_BATCH_TYPE,SELECT_EDC_GROUP, SELECT_REPORT_TYPE" define for option action;
    Add "CONFIRM_CARD_PROCESS_RESULT" define for confirmation action
    Add "TRANS_REVERSAL_STARTED","TRANS_REVERSAL_FINISHED" define for information status
    Add "UnitType" for tip action and "InputType" for zipcode action

## Version :  1.00.00
   The first release

## Version :  1.00.01
   1. Add "enableContactlessLight" parameter for INPUT_ACTION;
   2. Add "CONFIRM_RECEIPT_SIGNATURE" action
   3. Add "Interac" for CardType
   4. Add "Prompts" for "See Phone" action
   5. Add "PARAM_ENABLE_CONTACTLESS_LIGHT" for input account action
   6. Fixed error TransType name "VOID VERIFY" to "VERIFY"
   7. Add “ENTER_CS_PHONE”, “ENTER_MERCHANT_TAX_ID”,"CONFIRM_RECEIPT_SIGNATURE",“CONFIRM_ADDITIONAL_FEE” actions
   8. Add "fleetCustomerDataPattern,fleetDepartmentNumberPattern,fleetUserIDPattern,fleetVehicleIDPattern,
          fleetVehicleNumberPattern,fleetJobNumberPattern,fleetFleetOdometerPattern,fleetDriverIDPattern,
          fleetLicenseNumberPattern" for "ENTER_FLEET_DATA" action
   9. Add "enableCancel" for "GET_SIGNATURE" action
   10. Add "enableApplePay","enableGooglePay","enableSumsungPay","enableNFCPay" parameters for "INPUT_ACCOUNT" action
   11. Add "SELECT_ACCOUNT_TYPE", "SELECT_TIP_AMOUNT", "SELECT_CASHBACK_AMOUNT" actions
   12. Add "amountUnit" for "ENTER_TIP" action
   13. ADD "valuePattern" for "ENTER_AMOUNT,ENTER_TIP, ENTER_ZIP_CODE,ENTER_TRANS_NUMBER,ENTER_CASH_BACK,ENTER_AUTH_CODE,
        ENTER_REFERENCE_NUMBER, ENTER_INVOICE_NUMBER, ENTER_PHONE_NUMBER,ENTER_CS_PHONE_NUMBER,ENTER_MERCHANT_TAX_ID" actions;
   14. Add "fontSize" for "ACTION_INPUT_ACCOUNT, ACTION_ENTER_VCODE,ACTION_ENTER_CARD_LAST_4_DIGITS,
           ACTION_ENTER_CARD_ALL_DIGITS" actions.
