package com.pax.pay.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.pax.pay.ui.R;


public class ProcessDialog extends Dialog {

    //    private String mTitle;
    private String mContent;
    //    private TextView mTitleTextView;
    private TextView mContentTextView;

    private ProgressHelper mProgressHelper; //Not Used?

    public ProcessDialog(Context context, String content) {
        super(context, R.style.AlertDialog);

        setCancelable(false);
        setCanceledOnTouchOutside(false);

//        mTitle = title;
        mContent = content;
        mProgressHelper = new ProgressHelper(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_processing_dialog);
        Window window = getWindow();
        if (window == null)
            return;
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

//        mTitleTextView = findViewById(R.id.title_text);
        mContentTextView = findViewById(R.id.content_text);

        mProgressHelper.setProgressWheel((ProgressWheel) findViewById(R.id.progressWheel));

//        setTitle(mTitle);
        setContent(mContent);
    }


    public void setContent(String content) {
        this.mContent = content;
        if (mContentTextView != null) {
            mContentTextView.setText(content);
            mContentTextView.setVisibility(mContentTextView.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
        }
    }

}
