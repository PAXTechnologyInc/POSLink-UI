package com.paxus.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.paxus.view.R;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class CustomAlertDialog extends Dialog implements View.OnClickListener {

    public static final int NORMAL_TYPE = 0;
    public static final int ERROR_TYPE = 1;
    public static final int CUSTOM_TYPE = 2;
    @DialogType
    private final int mAlertType;
    private TextView mTitleTextView;
    private TextView mContentTextView;
    private Button mConfirmButton;
    private Button mCancelButton;
    private boolean mShowCancel;
    private boolean mShowConfirm;
    private String mConfirmText;
    private String mCancelText;
    private OnCustomClickListener mCancelClickListener;
    private OnCustomClickListener mConfirmClickListener;
    private OnCustomClickListener mKeycodeBackClickListener;
    private String mTitle;
    private String mContent;

    public CustomAlertDialog(Context context, @DialogType int alertType) {
        super(context, R.style.PaxTheme_AlertDialog);
        this.mAlertType = alertType;
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cust_alert_dialog);
        Window window = getWindow();
        if (window == null)
            return;
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        View view = findViewById(R.id.error_frame);
        view.setVisibility(mAlertType == ERROR_TYPE ?
                View.VISIBLE :
                View.GONE);

        view = findViewById(R.id.custom_layout);
        if (mAlertType == CUSTOM_TYPE) {
            view.setVisibility(View.VISIBLE);
        }

        mConfirmButton = findViewById(R.id.confirm_button);
        mCancelButton = findViewById(R.id.cancel_button);

        mTitleTextView = findViewById(R.id.title_text);
        mContentTextView = findViewById(R.id.content_text);

        mConfirmButton.setOnClickListener(this);
        mConfirmButton.setFocusable(false);
        mCancelButton.setOnClickListener(this);
        mCancelButton.setFocusable(false);

        setCancelText(mCancelText);
        setConfirmText(mConfirmText);
        setTitle(mTitle);
        setContent(mContent);
        showCancelButton(mShowCancel);
        showConfirmButton(mShowConfirm);
    }

    public void setTitle(String text) {
        mTitle = text;
        if (mTitleTextView != null) {
            mTitleTextView.setText(text);
            mTitleTextView.setVisibility(mTitleTextView.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
        }
    }

    public void setContent(String content) {
        mContent = content;
        if (mContentTextView != null) {
            mContentTextView.setText(content);
            mContentTextView.setVisibility(mContentTextView.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
        }
    }

    public void showCancelButton(boolean isShow) {
        mShowCancel = isShow;
        if (mCancelButton != null) {
            mCancelButton.setVisibility(mShowCancel ? View.VISIBLE : View.GONE);
        }
    }

    public void showConfirmButton(boolean isShow) {
        mShowConfirm = isShow;
        if (mConfirmButton != null) {
            mConfirmButton.setVisibility(mShowConfirm ? View.VISIBLE : View.GONE);
        }
    }

    public void setCancelText(String text) {
        mCancelText = text;
        if (mCancelButton != null && mCancelText != null) {
            showCancelButton(true);
            mCancelButton.setText(mCancelText);
        }
    }

    public void setConfirmText(String text) {
        mConfirmText = text;
        if (mConfirmButton != null && mConfirmText != null) {
            mConfirmButton.setText(mConfirmText);
        }
    }

    public void setCancelButtonColor(@DrawableRes int color) {
        if (mCancelButton != null) {
            mCancelButton.setBackgroundColor(color);
        }
    }

    public void setConfirmButtonColor(@DrawableRes int color) {
        if (mConfirmButton != null) {
            mConfirmButton.setBackgroundColor(color);
        }
    }

    public CustomAlertDialog setCancelClickListener(OnCustomClickListener listener) {
        mCancelClickListener = listener;
        return this;
    }

    public CustomAlertDialog setConfirmClickListener(OnCustomClickListener listener) {
        mConfirmClickListener = listener;
        return this;
    }

    public CustomAlertDialog setKeycodeBackClickListener(OnCustomClickListener listener) {
        mKeycodeBackClickListener = listener;
        return this;
    }

    private void confirmClick() {
        if (mConfirmClickListener != null) {
            mConfirmClickListener.onClick(CustomAlertDialog.this);
        } else {
            dismiss();
        }
    }

    private void cancelClick() {
        if (mCancelClickListener != null) {
            mCancelClickListener.onClick(CustomAlertDialog.this);
        } else {
            dismiss();
        }
    }

    private void keycodeBackClick() {
        if (mKeycodeBackClickListener != null) {
            mKeycodeBackClickListener.onClick(CustomAlertDialog.this);
        } else {
            cancelClick();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cancel_button) {
            cancelClick();
        } else if (v.getId() == R.id.confirm_button) {
            confirmClick();
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

    @IntDef({NORMAL_TYPE, ERROR_TYPE, CUSTOM_TYPE})
    @Retention(RetentionPolicy.SOURCE)
    @Target(value = {ElementType.METHOD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER, ElementType.FIELD})
    public @interface DialogType {
    }

    public interface OnCustomClickListener {
        void onClick(CustomAlertDialog alertDialog);
    }

}
