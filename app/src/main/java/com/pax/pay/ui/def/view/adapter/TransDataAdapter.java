package com.pax.pay.ui.def.view.adapter;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.pax.pay.ui.def.R;
import com.pax.pay.ui.def.constant.CardContext;
import com.pax.pay.ui.def.constant.EdcContext;
import com.pax.pay.ui.def.constant.EdcTransContract;
import com.pax.pay.ui.def.constant.StoreForwardContext;
import com.pax.pay.ui.def.constant.TransContext;
import com.pax.pay.ui.def.utils.DataProviderUtils;
import com.pax.us.pay.ui.message.CurrencyConverter;
import com.paxus.utils.log.Logger;
import com.paxus.view.expandablerecyclerview.ExpandableCursorAdapter;
import com.paxus.view.expandablerecyclerview.ExpandableViewHolder;

import java.util.Locale;
import java.util.Observable;

/**
 * Created by Kim.L 2020/03/12
 */
public class TransDataAdapter extends ExpandableCursorAdapter<TransDataAdapter.TransDataViewHolder> {

    private final IDataViewConverter dataViewConverter;
    private long transNo;
    private final String transUri;
    private IAdjustListener adjustListener;
    private final Observable observableDelegate = new Observable() {
        @Override
        public void notifyObservers() {
            setChanged();
            super.notifyObservers();
        }

        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(arg);
        }
    };

    public TransDataAdapter(Context context, Cursor c, IDataViewConverter dataViewConverter, int flags, String transUri) {
        super(context, c, flags);
        this.dataViewConverter = dataViewConverter;
        this.transUri = transUri;
    }

    public void setAdjustListener(IAdjustListener adjustListener){
        this.adjustListener = adjustListener;
    }



    public void addExpandActionObserver(IDataObserver o) {
        observableDelegate.addObserver(o);
    }

    public long getTransNo() {
        return transNo;
    }

    @Override
    public void onBindViewHolder(@NonNull TransDataViewHolder holder, Cursor c) {
        final int pos = c.getPosition();
        //Trans No.
        transNo = c.getLong(c.getColumnIndex(EdcTransContract.CommonTrans.TRANS_NUMBER));

        Logger.i(holder + " onBindViewHolder:" + transNo);
        holder.transNoTv.setText(String.format(Locale.US, "%d",
                transNo));
        // EDC Type
        String edc = c.getString(c.getColumnIndex(EdcTransContract.CommonTrans.EDC_TYPE));
        holder.edcTypeTv.setText(EdcContext.getEdcTypeDesc(getContext(),
                edc));
        // Trans Type
        String transType = c.getString(c.getColumnIndex(EdcTransContract.CommonTrans.TRANS_TYPE));
        holder.transTypeTv.setText(TransContext.getTransTypeDesc(getContext(),
                transType));
        // Status
        holder.statusTv.setText(getStatusDetails(c));

        // Card Number
        String account = c.getString(c.getColumnIndex(EdcTransContract.CommonTrans.PAN));
        if (TextUtils.isEmpty(account)) {
            holder.cardNumTv.setVisibility(View.GONE);
        } else {
            holder.cardNumTv.setVisibility(View.VISIBLE);
            holder.cardNumTv.setText(DataProviderUtils.maskCardNoFixLength(account));
        }

        // Card Type
        String cardType = c.getString(c.getColumnIndex(EdcTransContract.CommonTrans.CARD_TYPE));

        if (cardType == null) {
            holder.cardTypeTv.setVisibility(View.GONE);
        } else {
            holder.cardTypeTv.setVisibility(View.VISIBLE);
            holder.cardTypeTv.setText(CardContext.getCardTypeDesc(getContext(), cardType));
        }

        // Amount
        if (TransContext.isVoidTransType(transType) ||
                (EdcContext.EDC_TYPE_GIFT.equals(edc) &&
                        TransContext.TYPE_DEACTIVATE.equals(transType))) {
            // Arvind: https://pax-us.atlassian.net/browse/ANBP-211
            holder.amountTv.setTextColor(getContext().getResources().getColor(R.color.accent));
        } else {
            holder.amountTv.setTextColor(getContext().getResources().getColor(R.color.accent_amount));
        }

        long authAmount = c.getLong(c.getColumnIndex(EdcTransContract.CommonTrans.AUTH_AMOUNT));
        if (authAmount != 0) {
            String amount = CurrencyConverter.convert(authAmount,
                    c.getString(c.getColumnIndex(EdcTransContract.CommonTrans.CURRENCY)));
            holder.amountTv.setText(amount);
        } else {
            holder.amountTv.setText("");
        }

        // Date/Time
        String date = c.getString(c.getColumnIndex(EdcTransContract.CommonTrans.TRANS_DATE));
        String time = c.getString(c.getColumnIndex(EdcTransContract.CommonTrans.TRANS_TIME));
        date = date.substring(4, 6) + "/" + date.substring(6) + "/" + date.substring(0, 4); //MM/DD/YYYY, Fix ANFDRC-416
        time = time.substring(0, 2) + ":" + time.substring(2, 4) + ":" + time.substring(4);
        holder.dateTv.setText(String.format("%s  %s", date, time));

        holder.itemView.setContentDescription(Long.toString(transNo));

        if (holder.expandView != null && holder.expandView.getVisibility() == View.VISIBLE) {
            int id = c.getInt(c.getColumnIndex(EdcTransContract.CommonTrans._ID)); //ANFDRC-1258
            if (observableDelegate.countObservers() > 0) {
                holder.loading.setVisibility(View.GONE);
                observableDelegate.notifyObservers(id);
            } else {

                new RefereshTransDataAsynTask(getContext(),
                        dataViewConverter, holder.detailLayout, holder.loading, id, transUri).execute();
            }
            //button
            //we should let TransEntry decline if it's not allowed
            //holder.adjustBtn.setEnabled(isAdjustable(c));

            holder.adjustBtn.setOnClickListener(v -> doAdjustAction(c, pos));
        }
    }

    @Override
    protected void onCollapsedAll() {
        super.onCollapsedAll();
        if (observableDelegate.countObservers() > 0) {
            observableDelegate.notifyObservers(null);
        }
    }

    private String getStatusDetails(Cursor c) {

        String reqStatus = c.getString(c.getColumnIndex(EdcTransContract.CommonTrans.REQ_STATUS));
        int sfUploadStatus = c.getInt(c.getColumnIndex(EdcTransContract.CommonTrans.SF_UPLOAD_STATUS));

        String sf = "";
        if (sfUploadStatus != -1) {
            sf = "\n(" + StoreForwardContext.getUploadStatusDesc(getContext(), sfUploadStatus) + ")";
        }
        return (TransContext.getReqStatusDesc(getContext(), reqStatus)) +
                (TransContext.REQ_STATUS_STORE_OFFLINE.equals(reqStatus) ? sf : "");
    }

    private synchronized void doAdjustAction(Cursor c, int pos) {
        c.moveToPosition(pos);
        transNo = c.getLong(c.getColumnIndex(EdcTransContract.CommonTrans.TRANS_NUMBER));
        if (adjustListener != null)
            adjustListener.onAdjustConfirm(transNo);
    }


    @NonNull
    @Override
    public TransDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.item_trans, parent, false);

        return new TransDataViewHolder(v);
    }

    static class TransDataViewHolder extends ExpandableViewHolder {
        TextView transNoTv;
        TextView edcTypeTv;
        TextView transTypeTv;
        TextView statusTv;
        TextView cardNumTv;
        TextView cardTypeTv;
        TextView amountTv;
        TextView dateTv;

        LinearLayout detailLayout;
        View loading;

        Button adjustBtn;

        TransDataViewHolder(View itemView) {
            super(itemView, R.id.expandable);
            transNoTv = itemView.findViewById(R.id.trans_no_tv);
            edcTypeTv = itemView.findViewById(R.id.trans_edctype_tv);
            transTypeTv = itemView.findViewById(R.id.trans_transtype_tv);
            statusTv = itemView.findViewById(R.id.trans_status_tv);
            cardNumTv = itemView.findViewById(R.id.trans_cardnum_tv);
            cardTypeTv = itemView.findViewById(R.id.trans_cardtype_tv);
            amountTv = itemView.findViewById(R.id.trans_amount_tv);
            dateTv = itemView.findViewById(R.id.trans_date_tv);

            detailLayout = itemView.findViewById(R.id.details);
            loading = itemView.findViewById(R.id.loading_bar);

            adjustBtn = itemView.findViewById(R.id.history_trans_action_adjust);
        }
    }
}
