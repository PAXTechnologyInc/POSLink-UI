package com.pax.pay.ui.def.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.pax.pay.ui.def.R;

public class WarningDialog extends Dialog {

    private TextView mTitleTextView;
    private TextView mContentTextView;
    private ImageView mImageView;

    private String mTitleText;
    private String mContentText;

    public WarningDialog(Context context, String title, String content) {
        super(context, R.style.PaxTheme_AlertDialog);
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        this.mTitleText = title;
        this.mContentText = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_warn_dialog);
        Window window = getWindow();
        if (window == null)
            return;
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mImageView = findViewById(R.id.custom_image);

        mTitleTextView = findViewById(R.id.title_text);
        mContentTextView = findViewById(R.id.content_text);

        setTitle(mTitleText);
        setContent(mContentText);
    }

    public void setTitle(String text) {
        mTitleText = text;
        if (mTitleTextView != null) {
            mTitleTextView.setText(text);
            mTitleTextView.setVisibility(mTitleTextView.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
        }
    }

    public void setContent(String content) {
        mContentText = content;
        if (mContentTextView != null) {
            mContentTextView.setText(content);
            mContentTextView.setVisibility(mContentTextView.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
        }
    }

    public void setImage(Bitmap bitmap) {
        if (mImageView != null) {
            mImageView.setImageBitmap(bitmap);
        }
    }

}
