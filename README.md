# POSLink-UI
## lib-ui-constant:  
Define customer UI's constant and interface API
   > ../entry/                     : Customer action UI's interface and input/output parameters  
   >> ../entry/ExtryExtraData.java  : UI Action's input parameters, customer UI according those parameters to display different contents;  
   >> ../entry/ExtryRequest.java    : UI Action's output parameters, customer UI return action's result data;  
   >> ../entry/ExtryResponse.java   : UI Action's result input parameters, customer UI received the action's result, then do the relevant actions;  
   >> ../entry/xxx.java             : Customer action's interface.  
   >> ../entry/enumeration          : Constant enumeration define of parameters
   
   > ../status/                    : Transaction status prompt, customer UI according those status to display relevant information or do actions. involve card's status, contactless light status, batch transaction status, prompt message during transaction, etc.


## POSLink-UI Demo
Our latest simple demo for POSLink-UI.
See [POSLinkUI-Demo](https://github.com/PAXTechnologyInc/POSLinkUI-Demo) for details.

The old demo app is deprecated. See [deprecated-demo branch](https://github.com/PAXTechnologyInc/POSLink-UI/tree/deprecated-demo) if you need.
