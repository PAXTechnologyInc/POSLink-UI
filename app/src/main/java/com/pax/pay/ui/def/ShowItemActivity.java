package com.pax.pay.ui.def;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.base.BaseStackActivity;
import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.pay.ui.def.poslink.ItemListAdapter;
import com.pax.pay.ui.def.poslink.TextController;
import com.pax.pay.ui.def.poslink.info.ItemDetailWrapper;
import com.pax.pay.ui.def.utils.FileUtils;
import com.pax.pay.ui.def.utils.JsonUtils;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.helper.ShowOnlyHelper;
import com.paxus.utils.log.Logger;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jolie.Yang on 3/29/2021
 */
public class ShowItemActivity extends BaseStackActivity implements ShowOnlyHelper.IShowOnlyListener {

    private ShowOnlyHelper helper;
    public static final String POSLINK_REQUEST_ACTION_CLEAR_MESSAGE = "com.pax.us.pay.poslink.request.CLEAR_MESSAGE";

    RecyclerView recyclerViewShowItem;
    TextView tvTotalLineShowItem;
    TextView tvTaxLineShowItem;
    LinearLayout tvTitle;


    private String title;
    private ItemListAdapter itemListAdapter;

    private List<ItemDetailWrapper> itemWrapperList = new ArrayList<>();

    private LinearLayout.LayoutParams lp;
    private ClearMsgReceiver clearMsgReceiver;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_item;
    }

    @Override
    protected void initViews() {
        String title = getIntent().getStringExtra(EntryExtraData.PARAM_TITLE);

        //imgUrl = getIntent().getStringExtra(EntryExtraData.PARAM_IMAGE_URL);
        //imgDescStr = getIntent().getStringExtra(EntryExtraData.PARAM_IMAGE_DESC);

        String taxLine = getIntent().getStringExtra(EntryExtraData.PARAM_TAX_LINE);
        String totalLine = getIntent().getStringExtra(EntryExtraData.PARAM_TOTAL_LINE);
        tvTaxLineShowItem.setText(taxLine);
        tvTotalLineShowItem.setText(totalLine);

        setTextView(TextController.getTitleViewList(this, title, lp));

        if (title == null || title.length() == 0) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewShowItem.setLayoutManager(linearLayoutManager);
        recyclerViewShowItem.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        String currencySymbol = getIntent().getStringExtra(EntryExtraData.PARAM_CURRENCY_SYMBOL);
        Logger.d("currencySymbol :" + currencySymbol);
        itemListAdapter = new ItemListAdapter(this, itemWrapperList, currencySymbol);
        recyclerViewShowItem.setAdapter(itemListAdapter);


    }

    @Override
    protected void loadParam() {
        super.loadParam();
        recyclerViewShowItem = findViewById(R.id.recycler_View_show_item);
        tvTotalLineShowItem = findViewById(R.id.tv_total_line_show_item);
        tvTaxLineShowItem = findViewById(R.id.tv_tax_line_show_item);
        tvTitle = findViewById(R.id.tv_title_show_item);

        clearMsgReceiver = new ClearMsgReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(POSLINK_REQUEST_ACTION_CLEAR_MESSAGE);
        registerReceiver(clearMsgReceiver, intentFilter);
        //MsgInfoCache.initMsgList(true, bundle);
        Bundle bundle = getIntent().getExtras();
        InputStream inputStream = new ByteArrayInputStream(bundle.getString(EntryExtraData.PARAM_MESSAGE_LIST).getBytes());
        itemWrapperList = JsonUtils.readListFromJSON(inputStream, ItemDetailWrapper.class);
        //Logger.d("itemWrapperList : " + itemWrapperList.toArray(new String[0]));

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
                //ItemDetailCache.clearDefaultCache();
                itemWrapperList.clear();
                itemListAdapter.notifyDataSetChanged();

                tvTaxLineShowItem.setText(null);
                tvTotalLineShowItem.setText(null);
                tvTitle.removeAllViews();
                Logger.i("Item cleared");
            }
        });
    }

    private void setTextView(List<TextView> viewList) {

        for (TextView textView : viewList) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.ui_text_size_18));
            textView.setMaxLines(1);
            textView.setTextColor(ContextCompat.getColor(this, R.color.common_text_color));
            textView.setEllipsize(null);
            tvTitle.addView(textView, lp);
            //msgLayout.addView(textView, lp);
        }
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
