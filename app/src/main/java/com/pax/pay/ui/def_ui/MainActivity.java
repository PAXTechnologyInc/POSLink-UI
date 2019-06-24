package com.pax.pay.ui.def_ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        moveTaskToBack(true);
        //Intent intent = new Intent(this, EnterAmountActivity.class);
//        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setAction("com.pax.pay.poslink_ui_test.TEST");
        //startActivity(intent);
        //Use for test
//        Intent intent = new Intent();
//        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setAction("com.pax.pay.poslink_ui_test.TEST");
//        startActivity(intent);

    }

}
