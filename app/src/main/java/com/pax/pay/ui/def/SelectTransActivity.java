package com.pax.pay.ui.def;

import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.pax.pay.ui.def.base.BaseAppActivity;
import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.pay.ui.def.constant.EdcTransContract;
import com.pax.pay.ui.def.fragment.ExpandFragment;
import com.pax.pay.ui.def.utils.DataProviderUtils;
import com.pax.pay.ui.def.utils.DisplayTransInfoUtils;
import com.pax.pay.ui.def.view.adapter.IAdjustListener;
import com.pax.pay.ui.def.view.adapter.IDataObserver;
import com.pax.pay.ui.def.view.adapter.IDataViewConverter;
import com.pax.pay.ui.def.view.adapter.RefereshTransDataAsynTask;
import com.pax.pay.ui.def.view.adapter.RefreshAsyncTask;
import com.pax.pay.ui.def.view.adapter.TransDataAdapter;
import com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType;
import com.pax.us.pay.ui.core.helper.SelectTransHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;
import com.paxus.utils.log.Logger;
import com.paxus.view.expandablerecyclerview.ExpandItemAnimator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class SelectTransActivity extends BaseAppActivity implements SelectTransHelper.ISelectTransListener, IDataObserver, IAdjustListener {

    private boolean expandFlag = false;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private TextView emptyView;
    private IDataViewConverter expandedViewConverter;

    //private TransDataNoDecryptHelper mDaoHelper;
    private TransDataAdapter mAdapter;

    private SelectTransHelper helper;
    private String transUri;
    private String selection;
    private String[] selectArgs;
    private boolean restart = false;


    LoaderManager.LoaderCallbacks<Cursor> callback = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        @NonNull
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            Uri uri = Uri.parse(transUri);
            String selectionStr = selection;
            String[] selectionArgsArray = selectArgs;
            return new CursorLoader(SelectTransActivity.this,
                    uri,
                    null,
                    selectionStr,
                    selectionArgsArray,
                     EdcTransContract.CommonTrans._ID + " DESC");
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
            mSwipeRefreshLayout.setRefreshing(false);
            if (mAdapter == null) {
                mAdapter = new TransDataAdapter(SelectTransActivity.this, data, expandedViewConverter, 0, transUri);
                if ( expandFlag)
                    mAdapter.addExpandActionObserver(SelectTransActivity.this);
                mAdapter.setAdjustListener(SelectTransActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            } else{
                mAdapter.swapCursor(data);
            }
            emptyView.setVisibility(data.getCount() == 0 ? View.VISIBLE : View.GONE);
            emptyView.requestFocus();
        }

        @Override
        public void onLoaderReset(@NonNull Loader<Cursor> loader) {
            mAdapter.swapCursor(null);
        }
    };


    @Override
    protected void setListeners() {
        mSwipeRefreshLayout.setOnRefreshListener(this::refresh);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        expandedViewConverter = new ExpandViewConvert();

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        float density = dm.density;
        float swDp = Math.min(width, height) / density;
        Logger.d("SW DP:" + swDp); //Fix ANFDRC-1161, A30-sw360, Aries6-sw640
        //expandFragment.setDataViewConverter(expandedViewConverter);
        expandFlag = swDp >= 400 && getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        mSwipeRefreshLayout = findViewById(R.id.swipe_container);
        mRecyclerView = findViewById(R.id.trans_list);
        emptyView = findViewById(R.id.no_trans_record);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new ExpandItemAnimator());
    }

    @Override
    protected void loadParam() {
        navTitle = "";
        CurrencyConverter.setDefCurrency(CurrencyType.USD);
        restart = false;
        helper = new SelectTransHelper(this, new RespStatusImpl(this));
    }


    @Override
    protected void onStart() {
        helper.start(this, getIntent());
        super.onStart();
    }

    @Override
    protected void onStop() {
        helper.stop();
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
        Logger.i("onDestroy");
        // any time,when you finish your activity or fragment,call this below
//        if (mRecyclerView != null) {
//            mRecyclerView.destroy(); // this will totally release XR's memory
//            mRecyclerView = null;
//        }
    }

    @Override
    protected boolean onKeyEnterDown() {
        long transNo = mAdapter.getTransNo();

        if (transNo >= 0)
            helper.sendNext(transNo);
        return true;
    }

    @Override
    protected boolean onKeyBackDown() {
        helper.sendAbort();
        return true;
    }

    @Override
    public void onClickProtected(View v) {
        if (v.getId() == R.id.history_trans_action_adjust) {
            long transNo = mAdapter.getTransNo();
            if (transNo >= 0)
                helper.sendNext(transNo);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_transaction_layout;
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        setTitle(transName);
        setDemo(transMode);
    }

    @Override
    public void onShowTrans(@NonNull String transUrl, @NonNull String selection, @NonNull String[] selectArgs) {
        this.transUri = transUrl;
        this.selection = selection;
        this.selectArgs = selectArgs;

        refresh();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus){
        }
    }

    private void refresh() {
        collapseAll();
        if(!mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(true);
        }

        if (restart) {
            getSupportLoaderManager().restartLoader(1, null, callback);
        } else {
            getSupportLoaderManager().initLoader(1, null, callback);
        }
        restart = true;
    }

    private void collapseAll() {
        if (mAdapter != null) {
            mAdapter.collapseAll();
        }
    }


    @Override
    public Fragment onCreateExpandedPane() {
        return Fragment.instantiate(this, ExpandFragment.class.getName());
    }

    @Override
    public void update(Observable o, Object arg) {
        if (expandedViewConverter != null) {
            FragmentManager manager = getSupportFragmentManager();
            Fragment expandedPane = manager.findFragmentByTag(ExpandFragment.class.getSimpleName());
            LinearLayout detailLayout = expandedPane.getView().findViewById(R.id.detail_layout);
            if (arg instanceof Integer) {
                new RefereshTransDataAsynTask(this, expandedViewConverter, detailLayout, null, (Integer) arg, transUri).execute();
            } else {
                new RefreshAsyncTask(expandedViewConverter, detailLayout, null, arg).execute();
            }
        }
    }

    @Override
    public void onAdjustConfirm(long transNo) {
        helper.sendNext(transNo);
    }

    class ExpandViewConvert implements IDataViewConverter{

        private List<String> keyList= null;
        private List<String> valueList = null;
        private LinkedHashMap<String, String> transMap = null;
        @Override
        public void refreshData( Object arg) {
            if (arg != null) {
                keyList = new ArrayList<>();
                valueList = new ArrayList<>();
                transMap = new LinkedHashMap<>();
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    transMap = DataProviderUtils.prepareData(SelectTransActivity.this, (Map<String, Object>) arg);
                } else {
                    DataProviderUtils.prepareData(SelectTransActivity.this, (Map<String, Object>) arg, keyList, valueList);
                }
            }
        }

        @Override
        public void addViewsToGroup( LinearLayout detailLayout) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE && transMap != null &&transMap.size()>0) {
                notifyObservers(transMap);
            } else if (keyList != null && valueList != null && keyList.size()>0 && valueList.size()>0){
                DisplayTransInfoUtils displayTransInfoUtils = new DisplayTransInfoUtils(detailLayout,
                        keyList.toArray(new String[0]), valueList.toArray(new String[0]),
                        DisplayTransInfoUtils.ShowType.TRANS);
                displayTransInfoUtils.addViewsToViewGroup(detailLayout);
            }
        }
    }    

}
