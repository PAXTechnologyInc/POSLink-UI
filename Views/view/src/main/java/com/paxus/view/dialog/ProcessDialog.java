package com.paxus.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.paxus.view.R;

public class ProcessDialog extends Dialog {

    private final OnCancelListener cancellable;
    //    private String mTitle;
    private String mContent;
    //    private TextView mTitleTextView;
    private TextView mContentTextView;
    private ProgressHelper mProgressHelper; //Not Used?

    public ProcessDialog(Context context, String content) {
        this(context, content, null);
    }

    public ProcessDialog(Context context, String content, OnCancelListener cancellable) {
        super(context, R.style.PaxTheme_AlertDialog);

        this.cancellable = cancellable;

        setCancelable(cancellable != null);
        if (cancellable != null) {
            setOnCancelListener(cancellable);
        }
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
        Button mCancelBtn = findViewById(R.id.cancel_button);
        mProgressHelper.setProgressWheel(findViewById(R.id.progressWheel));

        if (cancellable != null) {
            mCancelBtn.setVisibility(View.VISIBLE);
            mCancelBtn.setOnClickListener((v) -> {
                cancel();
            });
        }

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
