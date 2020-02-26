# History
## Version :  1.00.00
   The first release

## Version :  1.00.01
   1. Add "onShowLight" interface for ICardListener;
   2. Modify SearchCardHelper to support onShowLight interface
   3. Add Support ExpandScreen function
   4. Add “ENTER_CS_PHONE”, “ENTER_MERCHANT_TAX_ID”,"CONFIRM_RECEIPT_SIGNATURE",“CONFIRM_ADDITIONAL_FEE” actions
   5. Add "fleetCustomerDataPattern,fleetDepartmentNumberPattern,fleetUserIDPattern,fleetVehicleIDPattern,
          fleetVehicleNumberPattern,fleetJobNumberPattern,fleetFleetOdometerPattern,fleetDriverIDPattern,
          fleetLicenseNumberPattern" for "ENTER_FLEET_DATA" action
   6. Add "enableCancel" for "GET_SIGNATURE" action
   7. Add "enableApplePay","enableGooglePay","enableSumsungPay","enableNFCPay" parameters for "INPUT_ACCOUNT" action
   8. Add "SELECT_ACCOUNT_TYPE", "SELECT_TIP_AMOUNT", "SELECT_CASHBACK_AMOUNT" actions
   9. Add "amountUnit" for "ENTER_TIP" action
   10. ADD "valuePattern" for "ENTER_AMOUNT,ENTER_TIP, ENTER_ZIP_CODE,ENTER_TRANS_NUMBER,ENTER_CASH_BACK,ENTER_AUTH_CODE,
        ENTER_REFERENCE_NUMBER, ENTER_INVOICE_NUMBER, ENTER_PHONE_NUMBER,ENTER_CS_PHONE_NUMBER,ENTER_MERCHANT_TAX_ID" actions;
   11. Add "fontSize" for SecurityHelper called by "ACTION_INPUT_ACCOUNT, ACTION_ENTER_VCODE,ACTION_ENTER_CARD_LAST_4_DIGITS,
           ACTION_ENTER_CARD_ALL_DIGITS, ACTION_ENTER_ADMINISTRATION_PASSWORD" actions.

