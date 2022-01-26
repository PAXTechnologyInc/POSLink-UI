package com.pax.pay.ui.def.view.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;

import com.pax.pay.ui.def.constant.EdcTransContract;
import com.pax.pay.ui.def.utils.DataProviderUtils;

import java.lang.ref.WeakReference;
import java.util.Map;

/**
 * Created by Yanina.Yang on 6/21/2021.
 */
public class RefereshTransDataAsynTask extends AsyncTask<Void, Void, Void> {
    private final IDataViewConverter dataViewConverter;
    private final WeakReference<LinearLayout> detailLayout;
    private final WeakReference<View> loading;
    private final int _id;
    private final String transUri;
    private final WeakReference<Context> mContext;

    public RefereshTransDataAsynTask(Context context, IDataViewConverter dataViewConverter, LinearLayout target, View loading, int _id, String transUri) {
        this.dataViewConverter = dataViewConverter;
        this.detailLayout = new WeakReference<>(target);
        this.loading = new WeakReference<>(loading);
        this.mContext = new WeakReference<>(context);

        this._id = _id;
        this.transUri = transUri;
    }

    @Override
    protected void onPreExecute() {
        View loadingView = loading.get();
        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Context context = mContext.get();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(transUri);
        try (Cursor cursor = cr.query(uri,
                null, EdcTransContract.CommonTrans._ID + "=?", new String[]{String.valueOf(_id)}, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                Map<String, Object> data = DataProviderUtils.parseCursor(cursor);
                dataViewConverter.refreshData(data);
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        LinearLayout detailViewGroup = detailLayout.get();
        if (detailViewGroup != null) {
            detailViewGroup.removeAllViews();
            dataViewConverter.addViewsToGroup(detailViewGroup);
        }else
            dataViewConverter.addViewsToGroup(null);
        View loadingView = loading.get();
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
    }
}