package com.paxus.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.paxus.view.R;

public class SingleChoiceDialog extends Dialog implements View.OnClickListener {
    private TextView mTitleTextView;
    private TextView mContentTextView;
    private String[] mItems;
    private int mWhich = -1;
    private Button mConfirmButton;
    private Button mCancelButton;

    private boolean mShowCancel;
    private boolean mShowConfirm;

    private String mConfirmText;
    private String mCancelText;

    private OnCancelListener mCancelClickListener;
    private DialogInterface.OnClickListener mConfirmClickListener;
    private String mTitle;
    private String mContent;

    public SingleChoiceDialog(Context context, String[] items, int defaultCheckId) {
        super(context, R.style.PaxTheme_AlertDialog);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        this.mItems = items;
        if (defaultCheckId >= -1) {
            this.mWhich = defaultCheckId;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_cust_alert_dialog_select);
        RadioGroup mRadioGroupView = findViewById(R.id.item_radio_group);
        mRadioGroupView.removeAllViews();
        if (mItems != null) {
            for (int i = 0; i < mItems.length; i++) {
                RadioButton radioButton = (RadioButton) LayoutInflater.from(getContext()).inflate(R.layout.layout_radio_button, null);
                radioButton.setLayoutParams(new ViewGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT));
                radioButton.setText(mItems[i]);
                radioButton.setTag(i);
                radioButton.setId(i);
                mRadioGroupView.addView(radioButton);
            }
        }
        mRadioGroupView.setOnCheckedChangeListener((radioGroup, i) -> mWhich = i);
        Window window = getWindow();
        if (window == null)
            return;
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

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

    public SingleChoiceDialog setCancelClickListener(OnCancelListener listener) {
        mCancelClickListener = listener;
        return this;
    }

    public SingleChoiceDialog setConfirmClickListener(DialogInterface.OnClickListener listener) {
        mConfirmClickListener = listener;
        return this;
    }


    private void confirmClick() {
        if (mWhich < 0) {
            return;
        }
        if (mConfirmClickListener != null) {
            mConfirmClickListener.onClick(SingleChoiceDialog.this, mWhich);
        } else {
            dismiss();
        }
    }

    private void cancelClick() {
        if (mCancelClickListener != null) {
            mCancelClickListener.onCancel(SingleChoiceDialog.this);
        } else {
            dismiss();
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
            cancelClick();
        } else if (keyCode == KeyEvent.KEYCODE_ENTER) {
            confirmClick();
        }
        return super.onKeyDown(keyCode, event);
    }

}
