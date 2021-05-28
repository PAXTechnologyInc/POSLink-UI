/*
 * ============================================================================
 * = COPYRIGHT
 *          PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or nondisclosure
 *   agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *   disclosed except in accordance with the terms in that agreement.
 *     Copyright (C) 2018-? PAX Computer Technology(Shenzhen) CO., LTD All rights reserved.

 * Module Date: 2019-3-7
 * Module Auth: Justin.Z
 * Description:

 * Revision History:
 * Date                   Author                       Action
 * 2019-3-7               Justin.Z                      Create
 * ============================================================================
 */
package com.pax.pay.ui.def;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.pax.pay.ui.def.base.FinishRespStatusImpl;
import com.pax.us.pay.ui.core.helper.ConfirmEULAViewHelper;


public class ConfirmEULAActivity extends AppCompatActivity implements View.OnClickListener, ConfirmEULAViewHelper.IConfirmEULAViewListener {

    private ConfirmEULAViewHelper helper = null;
    //private String title;
    private TextView mContentTextView;
    private WebView mWebView;
    private Button mConfirmButton;
    private Button mCancelButton;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_eula);
        mContentTextView = findViewById(R.id.eula_title);
        mWebView = findViewById(R.id.eula_content);
        mContentTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NONE) {
                    sendAbort();
                    ConfirmEULAActivity.this.finish();
                    return true;
                } else if (actionId == EditorInfo.IME_ACTION_DONE) {
                    sendNext(true, mCheckBox.isChecked());
                }
                return false;
            }
        });

        mConfirmButton = findViewById(R.id.confirm_btn);
        mCancelButton = findViewById(R.id.cancel_btn);


        mConfirmButton.setOnClickListener(this);
        mConfirmButton.setFocusable(false);
        mCancelButton.setOnClickListener(this);
        mCancelButton.setFocusable(false);

        mCheckBox = findViewById(R.id.prompt_again);

        //this.title = "";
        helper = new ConfirmEULAViewHelper(this, new FinishRespStatusImpl(this));
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {

    }


    @Override
    protected void onStart() {
        helper.start(this, getIntent());
        super.onStart();
    }

    protected void sendNext(boolean flag, boolean promptAgain) {
        helper.sendNext(flag, promptAgain);
    }

    protected void sendAbort() {
        helper.sendAbort();
    }

    @Override
    protected void onStop() {
        helper.stop();
        super.onStop();
    }

    @Override
    public void finish() {
        super.finish();
        //remove activity animation
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onShowEULAView(@NonNull String eulaUri) {
        if (!TextUtils.isEmpty(eulaUri)) {
            mWebView.loadUrl(eulaUri);
//            Uri EULAUri = Uri.parse(eulaUri);
//            if (EULAUri != null) {
//                String desFileName = getFilesDir() + File.separator + EULAUri.getLastPathSegment();
//                File file = saveFileFromUri(this, EULAUri, desFileName);
//                if (file != null) {
//                    Uri eulUri = FileProvider.getUriForFile(this, getPackageName() + ".fileProvider", file);
//                    mWebView.loadUrl(eulUri.toString());
//                }
//               // String cont = FileUtils.readFile2String(file, StandardCharsets.UTF_8);
//                //setContent(cont);
//
//            }
        } else
            finish();
    }

//    private void setContent(String content) {
//        //mContentText = content;
//        if (mContentTextView != null) {
//            if (!TextUtils.isEmpty(content))
//                mContentTextView.setText(Html.fromHtml(content));
//            mContentTextView.setVisibility(mContentTextView.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
//        }
//    }

//    private  File saveFileFromUri(Context context, Uri uri, String desFile){
//        File file = new File(desFile);
//        if (file.exists())
//            file.delete();
//        InputStream inS = null;
//        BufferedInputStream bis = null;
//        BufferedOutputStream bos = null;
//        FileOutputStream fos = null;
//        try {
//            ContentResolver resolver = context.getContentResolver();
//            try {
//                inS = resolver.openInputStream(uri);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//                return null;
//            }
//            bis = new BufferedInputStream(inS);
//            fos = new FileOutputStream(file);
//            bos = new BufferedOutputStream(fos);
//
//            byte[] buf = new byte[1024];
//            int len;
//            while ((len = inS.read(buf)) != -1) {
//                bos.write(buf, 0, len);
//            }
//            return file;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (bos != null) {
//                    bos.flush();
//                    bos.close();
//                }
//                if (fos != null)
//                    fos.close();
//                if (bis != null)
//                    bis.close();
//                if (inS != null) {
//                    inS.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//        return null;
//    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cancel_btn) {
            sendAbort();
            finish();
        } else if (v.getId() == R.id.confirm_btn) {
            sendNext(true, mCheckBox.isChecked());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            sendAbort();
            finish();
        }
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            sendNext(true, mCheckBox.isChecked());
        }
        return super.onKeyDown(keyCode, event);
    }
}
