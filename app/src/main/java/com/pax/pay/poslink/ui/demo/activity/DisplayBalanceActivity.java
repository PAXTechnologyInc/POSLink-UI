package com.pax.pay.poslink.ui.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.poslink.ui.demo.R;
import com.pax.pay.poslink.ui.demo.base.RespStatusImpl;
import com.pax.pay.poslink.ui.demo.view.DisplayInfoContent;
import com.pax.us.pay.ui.core.helper.ConfirmHelper;

public class DisplayBalanceActivity extends AppCompatActivity implements View.OnClickListener, ConfirmHelper.IConfirmListener {

    TextView promptTv;
    Button btnConfirm;
    Button btnCancel;
    LinearLayout llDetailContainer;


    private ConfirmHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_information);

        promptTv = findViewById(R.id.title_id);
        btnCancel = (Button) findViewById(R.id.cancel_btn);
        btnCancel.setOnClickListener(this);
        btnConfirm = (Button) findViewById(R.id.confirm_btn);
        btnConfirm.setOnClickListener(this);
        promptTv.setText("EBT Balance : ");
        llDetailContainer = (LinearLayout) findViewById(R.id.detail_layout);

        helper = new ConfirmHelper(this, new RespStatusImpl(this));
        helper.start(this, getIntent());
        ActivityLocalManager.getInstance().addActivity(this);
    }


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.cancel_btn) {
            helper.sendAbort();
        } else if (i == R.id.confirm_btn) {
            helper.sendNext();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            helper.sendAbort();
        } else if (keyCode == KeyEvent.KEYCODE_ENTER) {
            btnConfirm.performClick();
        }
        return false;
    }

    @Override
    protected void onStop() {
        moveTaskToBack(true);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message) {

    }

    @Override
    public void onShowInformation(String[] key, String[] value) {
        String leftColum = "", rightColum = "";

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 15;
        if (key.length != value.length)
            return;
        for (int i = 0; i < key.length; i++) {
            if (!TextUtils.isEmpty(key[i])) {
                if (DisplayInfoContent.BALANCE_MAP.get(key[i]) != null) {
                    leftColum = DisplayInfoContent.BALANCE_MAP.get(key[i]);
                } else
                    leftColum = key[i];

            } else {
                leftColum = "";
            }
            if (!TextUtils.isEmpty(value[i]))
                rightColum = value[i];
            else
                rightColum = "";

            View view = genSingleLineLayout(leftColum, rightColum);
            llDetailContainer.addView(view, params);
        }
    }

    /**
     * 生成每一行记录
     */
    private View genSingleLineLayout(String title, String value) {

        View view = LayoutInflater.from(llDetailContainer.getContext()).inflate(R.layout.layout_keyvalue, null);

        TextView titleTv = view.findViewById(R.id.item_title);
        titleTv.setText(title);

        TextView valueTv = view.findViewById(R.id.item_value);
        valueTv.setText(value);

        return view;
    }


}
