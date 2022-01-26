package com.paxus.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import com.paxus.view.R;

/**
 * Created by Yanina.Yang on 12/17/2020.
 */
public class CustomTextContentDialog extends Dialog {
    private TextView txtContent;
    private TextView txtTitle;
    private String title;

    private Button btnOk;
    private boolean okClickable = true;

    public CustomTextContentDialog(@NonNull Context context) {
        super(context, R.style.PaxTheme_CustomDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cust_round_corner_dialog);
        setCanceledOnTouchOutside(false);
        txtContent = findViewById(R.id.txt_content);
        txtTitle = findViewById(R.id.txt_title);
        if (!TextUtils.isEmpty(title)) {
            txtTitle.setText(title);
        } else {
            title = txtTitle.getText().toString();
        }
        btnOk = findViewById(R.id.btn_ok);
        btnOk.setClickable(true);
        btnOk.setOnClickListener(v -> dismiss());
        btnOk.setEnabled(okClickable);
    }

    public void setButtonEnabled(boolean enabled) {
        this.okClickable = enabled;
        if (btnOk == null) {
            return;
        }
        btnOk.setEnabled(enabled);
        setCancelable(enabled);
    }

    public void setContent(String content) {
        if (txtContent != null && content != null) {
            txtContent.setText(content);
        }
    }

    public void appendContentLine(String content) {
        if (txtContent != null && content != null) {
            if (TextUtils.isEmpty(txtContent.getText())) {
                txtContent.setText(content);
            } else {
                String newContent = txtContent.getText() + "\n" + content;
                txtContent.setText(newContent);
            }
        }
    }

    public void setTitle(String title) {
        this.title = title;
        if (txtTitle != null && title != null) {
            txtTitle.setText(title);
        }
    }
}
