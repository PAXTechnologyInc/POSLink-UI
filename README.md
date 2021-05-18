# POSLink-UI
## lib-ui-constant:  
   Define customer UI's constant and interface API  
   > ../entry/                     : Customer action UI's interface and input/output parameters  
   >> ../entry/ExtryExtraData.java  : UI Action's input parameters, customer UI according those parameters to display different contents;  
   >> ../entry/ExtryRequest.java    : UI Action's output parameters, customer UI return action's result data;  
   >> ../entry/ExtryResponse.java   : UI Action's result input parameters, customer UI received the action's result, then do the relevant actions;  
   >> ../entry/xxx.java             : Customer action's interface.  
   >> ../entry/enumeration          : Constant enmu define of parameters  
   
   > ../status/                    : Transaction status prompt, customer UI according those status to display relevant informations or do actions. involve card's status, contactless light status, batch transaction status, prompt message during transaction, etc.     
   
### ui-demo(deprecated) :
    Demo code to show how to parse the UI action's input datas, and display relevant contents, Customer UI get operator data, and pack into protocol datas, then send request package and according action's result to finish action UI or continue re-enter. that is a low layer encapsulation, all datas processing in one .java class.     
   
## lib-ui-core :  
   Customer UI protocol layer implement  
   > ../api    :  Callback interface define, which will be implement in Customer;  
   > ../helper :  protocol layer data pack and unpack parsing.     

## app   :  
   Demo code to show how to use lib-ui-core to dispaly action UI and send/received action datas 
   
## ui-component   :  
   Components for app, common components be used by app
   
## ui-message   :  
   Display message for ConfirmUnifiedDialogActivity.java of app, some actions use same UI to show message
   
   
