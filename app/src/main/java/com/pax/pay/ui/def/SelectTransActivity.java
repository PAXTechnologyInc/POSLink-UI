package com.pax.pay.ui.def;

import android.content.res.Configuration;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.pax.pay.ui.def.base.BaseAppActivity;
import com.pax.pay.ui.def.base.RespStatusImpl;
import com.pax.pay.ui.def.constant.EdcContext;
import com.pax.pay.ui.def.constant.EdcTransContract;
import com.pax.pay.ui.def.constant.TransContext;
import com.pax.pay.ui.def.fragment.ExpandFragment;
import com.pax.pay.ui.def.utils.DataProviderUtils;
import com.pax.pay.ui.def.utils.DisplayTransInfoUtils;
import com.pax.us.pay.ui.constant.entry.enumeration.CurrencyType;
import com.pax.us.pay.ui.core.helper.SelectTransHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;
import com.paxus.utils.log.Logger;
import com.paxus.view.expandablerecyclerview.BaseExpandableViewHolder;
import com.paxus.view.expandablerecyclerview.ExpandItemAnimator;
import com.paxus.view.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.paxus.view.utils.ToastHelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;

public class SelectTransActivity extends BaseAppActivity implements SelectTransHelper.ISelectTransListener {
    private static final long PAGE_SIZE = 7;

    XRecyclerView mRecyclerView;
    TextView emptyView;

    //private TransDataNoDecryptHelper mDaoHelper;
    private ExpandableRecyclerAdapter mAdapter;
    private int currentPageCount = 1;
    private long transTotalCount = 0;
    private long currentOffset = 0;

    private SelectTransHelper helper;
    private String transUri;
    private String selection;
    private String[] selectArgs;
    private long transNo;

    private final Timer timer = null;

    @Override
    protected void setListeners() {
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refresh();
            }

            @Override
            public void onLoadMore() {
                loadMore();
            }
        });
        mRecyclerView.refresh();
    }

    @Override
    protected void initViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mAdapter = new ExpandableRecyclerAdapter<Map<String, Object>>() {
            @Override
            public @NonNull
            BaseExpandableViewHolder<Map<String, Object>> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return generateViewHolder(parent, viewType);
            }
        };
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new ExpandItemAnimator());
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLimitNumberToCallLoadMore(2);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void loadParam() {
        navTitle = "";
        mRecyclerView = findViewById(R.id.trans_list);
        emptyView = findViewById(R.id.no_trans_record);
        CurrencyConverter.setDefCurrency(CurrencyType.USD);
        //DataProviderUtils.initTransData();
        transNo = -1;
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
        if (mRecyclerView != null) {
            mRecyclerView.destroy(); // this will totally release XR's memory
            mRecyclerView = null;
        }
    }

    @Override
    protected boolean onKeyEnterDown() {
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
        transTotalCount = DataProviderUtils.getCanAdjustTransCount(this, transUri,
                this.selection,
                this.selectArgs);
        refresh();
    }

    protected TransDataViewHolder generateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TransDataViewHolder(LayoutInflater.from(this)
                .inflate(R.layout.item_trans, parent, false));
    }

    private void refresh() {
        currentPageCount = 1;
        getTransByPage(currentPageCount, 0);
    }

    private void loadMore() {
        if (mAdapter.getItemCount() == transTotalCount) {
            ToastHelper.showMessage(this, getString(R.string.no_more_data));
            mRecyclerView.setLoadingMoreEnabled(false);
            mAdapter.notifyDataSetChanged(); //Fix ANDROIDNAT-482. java.lang.IndexOutOfBoundsException: Inconsistency detected
            return;
        }

        currentPageCount++;
        getTransByPage(currentPageCount, -1);
    }

    private void getTransByPage(final int pageCount, final int offset) {
        new Handler().post(() -> {
            if (pageCount < 1) {
                return;
            }

            if (TextUtils.isEmpty(transUri)) {
                return;
            }
            long startIndex = offset == -1 ? currentOffset : offset;
            List<Map<String, Object>> transDataList = DataProviderUtils.findTransDataByPageForAdjust(this, transUri, selection, selectArgs, startIndex, PAGE_SIZE);// 查询一页数据
            if (transDataList == null || transDataList.isEmpty()) {
                if (currentPageCount > 1)
                    currentPageCount--;
                updateUI(transDataList);
                return;
            }

            if (pageCount > 1) {
                currentOffset += transDataList.size();
            } else {
                currentOffset = transDataList.size();
            }

            updateUI(transDataList);

        });
    }

    private void updateUI(final List<Map<String, Object>> transDataList) {
        new Handler().postDelayed(() -> {
            if (mRecyclerView != null) {
                if (currentPageCount > 1) {
                    mAdapter.addDataBeanList(transDataList);
                    mRecyclerView.loadMoreComplete();
                } else {
                    mAdapter.setDataBeanList(transDataList);
                    mRecyclerView.refreshComplete();
                    mRecyclerView.setLoadingMoreEnabled(transDataList.size() < transTotalCount);
                }
                mRecyclerView.setEmptyView(emptyView);
                mAdapter.notifyDataSetChanged();
                emptyView.requestFocus();
            }
        }, 1000);
    }

    @Override
    public Fragment onCreateExpandedPane() {
        return Fragment.instantiate(this, ExpandFragment.class.getName());
    }

    class TransDataViewHolder extends BaseExpandableViewHolder<Map<String, Object>> {

        TextView edcTypeTv;
        TextView transTypeTv;
        TextView statusTv;
        TextView cardNumTv;
        TextView cardTypeTv;
        TextView amountTv;
        TextView dateTv;
        TextView transNoTv;

        LinearLayout detailLayout;

        Button confirmBtn;

        public TransDataViewHolder(View itemView) {
            super(itemView, R.id.trans_item_header, R.id.expandable, R.id.loading_bar);
        }

        private String getStatusDetails(String reqStatus, Integer sfUploadStatus) {

            String sf = "";
            if (sfUploadStatus != null) {
                sf = "\n(" + DataProviderUtils.statusConvert(sfUploadStatus) + ")";
            }
            return (TextUtils.isEmpty(reqStatus) ? "" : reqStatus) + (TransContext.REQ_STATUS_STORE_OFFLINE.equals(reqStatus) ? sf : "");
        }

        @Override
        protected void initView() {
            edcTypeTv = itemView.findViewById(R.id.trans_edctype_tv);
            transTypeTv = itemView.findViewById(R.id.trans_transtype_tv);
            statusTv = itemView.findViewById(R.id.trans_status_tv);
            cardNumTv = itemView.findViewById(R.id.trans_cardnum_tv);
            cardTypeTv = itemView.findViewById(R.id.trans_cardtype_tv);
            amountTv = itemView.findViewById(R.id.trans_amount_tv);
            dateTv = itemView.findViewById(R.id.trans_date_tv);
            transNoTv = itemView.findViewById(R.id.trans_no_tv);

            detailLayout = itemView.findViewById(R.id.details);

            confirmBtn = itemView.findViewById(R.id.history_trans_action_adjust);
        }

        @Override
        protected void setListener() {
            confirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    helper.sendNext(transNo);
                }
            });
        }

        protected void bindView(View view, final Map<String, Object> transData, final int pos) {
            //Trans No.
            transNoTv.setText(String.format(Locale.US, "%d", transData.get(EdcTransContract.CommonTrans.TRANS_NUMBER)));

            // EDC Type
            edcTypeTv.setText((String) transData.get(EdcTransContract.CommonTrans.EDC_TYPE));
            // Trans Type
            transTypeTv.setText(TransContext.getTransTypeDesc(SelectTransActivity.this, (String) transData.get(EdcTransContract.CommonTrans.TRANS_TYPE)));
            // Status
            statusTv.setText(getStatusDetails((String) transData.get(EdcTransContract.CommonTrans.REQ_STATUS),
                    (Integer) transData.get(EdcTransContract.CommonTrans.SF_UPLOAD_STATUS)));

            // Card Number
            if (TextUtils.isEmpty((String) transData.get(EdcTransContract.CommonTrans.PAN))) {
                cardNumTv.setVisibility(View.GONE);
            } else {
                cardNumTv.setVisibility(View.VISIBLE);

                cardNumTv.setText(DataProviderUtils.maskCardNoFixLength((String) transData.get(EdcTransContract.CommonTrans.PAN)));
            }

            // Card Type
            if (TextUtils.isEmpty((String) transData.get(EdcTransContract.CommonTrans.CARD_TYPE))) {
                cardTypeTv.setVisibility(View.GONE);
            } else {
                cardTypeTv.setVisibility(View.VISIBLE);
                cardTypeTv.setText((String) transData.get(EdcTransContract.CommonTrans.CARD_TYPE));
            }

            // Amount
            if (TransContext.isVoidTransType((String) transData.get(EdcTransContract.CommonTrans.TRANS_TYPE))) {
                amountTv.setTextColor(view.getResources().getColor(R.color.accent));
            } else {
                amountTv.setTextColor(view.getResources().getColor(R.color.accent_amount));
            }
            if (EdcContext.EDC_TYPE_CASH.equals(transData.get(EdcTransContract.CommonTrans.EDC_TYPE))) {
                if ((long) transData.get(EdcTransContract.CommonTrans.AMOUNT) != 0) {
                    amountTv.setVisibility(View.VISIBLE);

                    amountTv.setText("POINT".equals(transData.get(EdcTransContract.CommonTrans.CURRENCY)) ?
                            Long.toString((long) transData.get(EdcTransContract.CommonTrans.AMOUNT)) :
                            CurrencyConverter.convert((long) transData.get(EdcTransContract.CommonTrans.AMOUNT)));
                } else {
                    amountTv.setVisibility(View.GONE);
                }
            } else {
                if ((long) transData.get(EdcTransContract.CommonTrans.AUTH_AMOUNT) != 0) {
                    amountTv.setVisibility(View.VISIBLE);
                    amountTv.setText("POINT".equals(transData.get(EdcTransContract.CommonTrans.CURRENCY)) ?
                            Long.toString((long) transData.get(EdcTransContract.CommonTrans.AUTH_AMOUNT)) :
                            CurrencyConverter.convert((long) transData.get(EdcTransContract.CommonTrans.AUTH_AMOUNT)));
                } else {
                    amountTv.setVisibility(View.GONE);
                }
            }

            // Date/Time
            String date = (String) transData.get(EdcTransContract.CommonTrans.TRANS_DATE);
            String time = (String) transData.get(EdcTransContract.CommonTrans.TRANS_TIME);
            date = date.substring(4, 6) + "/" + date.substring(6) + "/" + date.substring(0, 4); //MM/DD/YYYY, Fix ANFDRC-416
            time = time.substring(0, 2) + ":" + time.substring(2, 4) + ":" + time.substring(4);
            dateTv.setText(String.format("%s  %s", date, time));
            view.setContentDescription(Long.toString((long) transData.get(EdcTransContract.CommonTrans.TRANS_NUMBER)));

        }

        @Override
        protected void updateExpandView(View view, Map<String, Object> transData, int pos) {

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                LinkedHashMap<String, String> transMap = DataProviderUtils.prepareData(transData);
                notifyObservers(transMap);
            } else {
                List<String> keyList = new ArrayList<>();
                List<String> valueList = new ArrayList<>();
                DataProviderUtils.prepareData(transData, keyList, valueList);
                detailLayout.removeAllViews();
                DisplayTransInfoUtils displayTransInfoUtils = new DisplayTransInfoUtils(detailLayout,
                        keyList.toArray(new String[keyList.size()]), valueList.toArray(new String[valueList.size()]),
                        DisplayTransInfoUtils.ShowType.TRANS);
                displayTransInfoUtils.addViewsToViewGroup(detailLayout);
            }
            transNo = (Long) transData.get(EdcTransContract.CommonTrans.TRANS_NUMBER);
            view.setContentDescription(Long.toString(transNo));
        }
    }

}
