package com.pax.pay.ui.def.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.text.Html;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.pax.pay.ui.def.R;

public class HtmlAlertDialog  extends Dialog implements View.OnClickListener{
    private TextView mContentTextView;
    private Button mConfirmButton;
    private Button mCancelButton;

    private String mContentText;
    private OnCustomClickListener mCancelClickListener;
    private OnCustomClickListener mConfirmClickListener;
    private OnCustomClickListener mKeycodeBackClickListener;


    public HtmlAlertDialog(Context context) {
        super(context, com.paxus.view.R.style.PaxTheme_AlertDialog);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_html_dialog);
        Window window = getWindow();
        if (window == null)
            return;
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mContentTextView = findViewById(R.id.content);

        mConfirmButton = findViewById(R.id.confirm_btn);
        mCancelButton = findViewById(R.id.cancel_btn);


        mConfirmButton.setOnClickListener(this);
        mConfirmButton.setFocusable(false);
        mCancelButton.setOnClickListener(this);
        mCancelButton.setFocusable(false);

        setContent(mContentText);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cancel_btn) {
            cancelClick();
        } else if (v.getId() == R.id.confirm_btn) {
            confirmClick();
        }
    }

    private void confirmClick() {
        if (mConfirmClickListener != null) {
            mConfirmClickListener.onClick(this);
        } else {
            dismiss();
        }
    }

    private void cancelClick() {
        if (mCancelClickListener != null) {
            mCancelClickListener.onClick(this);
        } else {
            dismiss();
        }
    }

    private void keycodeBackClick() {
        if (mKeycodeBackClickListener != null) {
            mKeycodeBackClickListener.onClick(this);
        } else {
            cancelClick();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            keycodeBackClick();
        } else if (keyCode == KeyEvent.KEYCODE_ENTER) {
            confirmClick();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setContent(String content) {
        mContentText = content;
        if (mContentTextView != null) {
            if (!TextUtils.isEmpty(content))
                mContentTextView.setText(Html.fromHtml(content));
            mContentTextView.setVisibility(mContentTextView.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
        }
    }

    public HtmlAlertDialog setmCancelClickListener(OnCustomClickListener mCancelClickListener) {
        this.mCancelClickListener = mCancelClickListener;
        return this;
    }

    public HtmlAlertDialog setmConfirmClickListener(OnCustomClickListener mConfirmClickListener) {
        this.mConfirmClickListener = mConfirmClickListener;
        return this;
    }

    public HtmlAlertDialog setmKeycodeBackClickListener(OnCustomClickListener mKeycodeBackClickListener) {
        this.mKeycodeBackClickListener = mKeycodeBackClickListener;
        return this;
    }

    public interface OnCustomClickListener {
        void onClick(HtmlAlertDialog alertDialog);
    }
}