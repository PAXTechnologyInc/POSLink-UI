package com.pax.pay.ui.def;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextPaint;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.base.BaseStackActivity;
import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.pay.ui.def.poslink.MsgListAdapter;
import com.pax.pay.ui.def.poslink.TextController;
import com.pax.pay.ui.def.poslink.info.MsgInfoWrapper;
import com.pax.pay.ui.def.utils.FileUtils;
import com.pax.pay.ui.def.utils.JsonUtils;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.constant.entry.enumeration.TransMode;
import com.pax.us.pay.ui.core.helper.ShowOnlyHelper;
import com.paxus.utils.log.Logger;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jolie.Yang on 3/29/2021
 */
public class ShowMessageActivity extends BaseStackActivity implements ShowOnlyHelper.IShowOnlyListener {

    private ShowOnlyHelper helper;
    public static final String POSLINK_REQUEST_ACTION_CLEAR_MESSAGE = "com.pax.us.pay.poslink.request.CLEAR_MESSAGE";

    RecyclerView recyclerView;
    ImageView msgImgView;
    LinearLayout llTotalLine;
    LinearLayout llTaxLine;
    LinearLayout llDescMsg;
    LinearLayout rlDescMsg;
    LinearLayout llMsgTitle;
    LinearLayout llTaxLineMsg;
    LinearLayout llTotalLineMsg;
    View llDevide;
    View trailDevide;

    private MsgListAdapter msgListAdapter;

    private String imgUrl;

    private String imgDescStr;

    private LinearLayout.LayoutParams lp;
    private ClearMsgReceiver clearMsgReceiver;


    private final int titlePaddingTB = 10;
    private final int titlePaddingRL = 15;
    private List<MsgInfoWrapper> showMsgList = new ArrayList<>();

    private static final String TAX_LINE_TAG = "taxLineTag";
    private static final String TOTAL_LINE_TAG = "totaLineTag";
    private static final String IMG_DESC_TAG = "imgDescTag";
    private static final String TITLE_TAG = "titleTag";



    @Override
    protected int getLayoutId() {
        return R.layout.activity_msg_list_pete;
    }

    @Override
    protected void initViews() {
        String title = getIntent().getStringExtra(EntryExtraData.PARAM_TITLE);
        if (TextUtils.isEmpty(title)) {
            title = "Message";
        }

        imgUrl = getIntent().getStringExtra(EntryExtraData.PARAM_IMAGE_URL);
        imgDescStr = getIntent().getStringExtra(EntryExtraData.PARAM_IMAGE_DESC);

        String taxLine = getIntent().getStringExtra(EntryExtraData.PARAM_TAX_LINE);
        String totalLine = getIntent().getStringExtra(EntryExtraData.PARAM_TOTAL_LINE);
        setTextView(TextController.getTitleViewList(this, title, lp), llMsgTitle, TITLE_TAG);
        setTextView(TextController.getViewList(this, taxLine, lp), llTaxLine, TAX_LINE_TAG);
        setTextView(TextController.getViewList(this, totalLine, lp), llTotalLine, TOTAL_LINE_TAG);
        boolean isTrail = false;

        if (taxLine == null || taxLine.length() == 0) {
            llTaxLineMsg.setVisibility(View.GONE);
        } else {
            isTrail = true;
            llTaxLineMsg.setVisibility(View.VISIBLE);
        }

        if (totalLine == null || totalLine.length() == 0) {
            llTotalLineMsg.setVisibility(View.GONE);
        } else {
            isTrail = true;
            llTotalLineMsg.setVisibility(View.VISIBLE);
        }

        if (isTrail)
            trailDevide.setVisibility(View.VISIBLE);
        else
            trailDevide.setVisibility(View.GONE);


        String pathName = saveFile(imgUrl);
        Bitmap bitmap = BitmapFactory.decodeFile(pathName);
        if (bitmap != null) {
            msgImgView.setVisibility(View.VISIBLE);
            msgImgView.setImageBitmap(bitmap);

            if (!TextUtils.isEmpty(imgDescStr)) {
                llDescMsg.setVisibility(View.VISIBLE);

                setTextView(TextController.getViewList(this, imgDescStr, lp), llDescMsg, IMG_DESC_TAG);
            } else {
                llDescMsg.setVisibility(View.GONE);
            }
        } else {
            llDescMsg.setVisibility(View.GONE);
            msgImgView.setVisibility(View.GONE);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        msgListAdapter = new MsgListAdapter(this, showMsgList);
        recyclerView.setAdapter(msgListAdapter);

        int lr = getPixel(titlePaddingRL + 5);
        recyclerView.setPadding(lr, 0, lr, 0);
        llTaxLineMsg.setPadding(lr, 0, lr, 0);
        llTotalLineMsg.setPadding(lr, 0, lr, 0);
        rlDescMsg.setPadding(lr, 10, lr, 0);
        llDevide.setPadding(lr, 0, lr, 0);


    }

    @Override
    protected void loadParam() {
        recyclerView = findViewById(R.id.recycler_View);
        msgImgView = findViewById(R.id.img_view);
        llTotalLine = findViewById(R.id.ll_total_line_msg_list);
        llTaxLine = findViewById(R.id.ll_tax_line_msg_list);
        llDescMsg = findViewById(R.id.ll_desc_msg_list_pete);
        rlDescMsg = findViewById(R.id.rl_desc_msg_list_pete);
        llMsgTitle = findViewById(R.id.ll_msg_title);
        llTaxLineMsg = findViewById(R.id.ll_tax_line_msg);
        llTotalLineMsg = findViewById(R.id.ll_total_line_msg);
        llDevide = findViewById(R.id.ll_devide);
        trailDevide = findViewById(R.id.trail_devide);

        Bundle bundle = getIntent().getExtras();
        if (bundle.containsKey(EntryExtraData.PARAM_TRANS_MODE) && TransMode.DEMO.equals(bundle.getString(EntryExtraData.PARAM_TRANS_MODE))){
            navTitle = "Demo";
        } else
            navTitle = "BroadPOS";

        clearMsgReceiver = new ClearMsgReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(POSLINK_REQUEST_ACTION_CLEAR_MESSAGE);
        registerReceiver(clearMsgReceiver, intentFilter);
        //MsgInfoCache.initMsgList(true, bundle);
        InputStream inputStream = new ByteArrayInputStream(bundle.getString(EntryExtraData.PARAM_MESSAGE_LIST).getBytes());
        showMsgList = (List<MsgInfoWrapper>) JsonUtils.readListFromJSON(inputStream, MsgInfoWrapper.class);

        lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        helper = new ShowOnlyHelper(this, new RespStatus(this));

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus ){
            helper.sendNext();
        }
    }


    @Override
    protected boolean onKeyBackDown() {
        helper.sendAbort();
        finish();
        return true;
    }

    @Override
    protected void setListeners() {

    }



    @Override
    public void finish() {
        helper.stop();
        super.finish();
        //remove activity animation
        overridePendingTransition(0, 0);
    }

    @Override
    public void onDestroy(){
        //MsgInfoCache.clearDefaultCache();
        if (clearMsgReceiver!= null) {
            unregisterReceiver(clearMsgReceiver);
        }
        super.onDestroy();

    }

    @Override
    public void onStartHelper() {
        helper.start(this, getIntent());
    }

    @Override
    public void onAbortHelper() {
        helper.sendAbort();
        finish();
    }

    public void clearContent() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //MsgInfoCache.clearDefaultCache();
                showMsgList.clear();
                msgListAdapter.notifyDataSetChanged();

                msgImgView.setImageBitmap(null);
                msgImgView.setVisibility(View.GONE);

                llDescMsg.removeAllViews();
                llDescMsg.setVisibility(View.GONE);

                llMsgTitle.removeAllViews();
                llTaxLine.removeAllViews();
                llTotalLine.removeAllViews();
                Logger.v("Msg cleared");
            }
        });
    }

    private void setTextView(List<TextView> viewList, LinearLayout msgLayout, String tag) {

        for (TextView textView : viewList) {

            if (TOTAL_LINE_TAG.equals(tag)) {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.font_size_value));
                textView.setTextColor(ContextCompat.getColor(this, R.color.primary_dark));
                TextPaint textPaint = textView.getPaint();
                textPaint.setFakeBoldText(true);
                textView.setTypeface(null);
            } else if (TAX_LINE_TAG.equals(tag)) {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.font_size_value));
                textView.setTextColor(ContextCompat.getColor(this, R.color.primary_dark));
                textView.setTypeface(null);
            } else if (IMG_DESC_TAG.equals(tag)) {
                textView.setBackgroundColor(Color.WHITE);
            } else if (TITLE_TAG.equals(tag)) {

                textView.setPadding(getPixel(titlePaddingRL), getPixel(titlePaddingTB), getPixel(titlePaddingRL), getPixel(titlePaddingTB));
                textView.setTextColor(Color.BLACK);
                textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            }

            if (IMG_DESC_TAG.equals(tag)) {
                textView.setMaxLines(2);
            } else
                textView.setMaxLines(1);
            msgLayout.addView(textView, lp);
        }
    }

    private int getPixel(int dp) {
        float dpMul = getResources().getDisplayMetrics().density;
        BigDecimal decimalDp = new BigDecimal(dp + "");
        BigDecimal decimalMul = new BigDecimal(dpMul + "");
        BigDecimal decimal = decimalDp.multiply(decimalMul);
        return decimal.intValue();
    }

    private String saveFile(String uri){
        if (TextUtils.isEmpty(uri))
            return null;
        Uri fileUri = Uri.parse(uri);
        if (fileUri == null)
            return null;
        String desfile = getFilesDir().getAbsolutePath() + File.separator + fileUri.getLastPathSegment();

        File file = FileUtils.saveFileFromUri(this,fileUri,desfile);
        if (file == null)
            return null;
        else
            return desfile;
    }

    private class RespStatus extends RespStatusImpl{

        public RespStatus(Activity activity) {
            super(activity);
        }
        @Override
        public void onAccepted() {
            //don't close activity.
        }
    }

    private class ClearMsgReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (POSLINK_REQUEST_ACTION_CLEAR_MESSAGE.equals(intent.getAction())){
                clearContent();
            }
        }
    }
}
