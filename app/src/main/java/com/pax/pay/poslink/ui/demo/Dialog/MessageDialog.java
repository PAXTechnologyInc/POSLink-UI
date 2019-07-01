package com.pax.pay.poslink.ui.demo.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.pax.pay.poslink.ui.demo.R;

public class MessageDialog extends Dialog {

    private TextView mContentTextView;
    private String mContentText;

    public MessageDialog(Context context) {
        super(context);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_message_dialog);
        Window window = getWindow();
        if (window == null)
            return;
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mContentTextView = findViewById(R.id.content_text);
        setContent(mContentText);
    }

    public void setContent(String content) {
        mContentText = content;
        if (mContentTextView != null) {
            mContentTextView.setText(content);
            mContentTextView.setVisibility(mContentTextView.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
        }
    }

}
