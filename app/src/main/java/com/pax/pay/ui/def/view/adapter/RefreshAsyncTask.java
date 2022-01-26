package com.pax.pay.ui.def.view.adapter;

import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.lang.ref.WeakReference;

/**
 * Created by Kim.L 2020/03/3/18/2020
 */
public class RefreshAsyncTask extends AsyncTask<Void, Void, Void> {
    private final IDataViewConverter dataViewConverter;
    private final WeakReference<LinearLayout> detailLayout;
    private final WeakReference<View> loading;
    private final Object data;

    public RefreshAsyncTask(IDataViewConverter dataViewConverter, LinearLayout target, View loading, Object data) {
        this.dataViewConverter = dataViewConverter;
        this.detailLayout = new WeakReference<>(target);
        this.loading = new WeakReference<>(loading);
        this.data = data;
    }

    @Override
    protected void onPreExecute() {
        View loadingView = loading.get();
        if (loadingView != null && data != null) {
            loadingView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dataViewConverter.refreshData(data);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        LinearLayout layout = detailLayout.get();
        if (layout != null) {
            layout.removeAllViews();
            dataViewConverter.addViewsToGroup(layout);
        }else
            dataViewConverter.addViewsToGroup(null);
        View loadingView = loading.get();
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
    }
}
