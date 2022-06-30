package com.pax.pay.ui.def.view.adapter;


import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;

import java.util.Map;

/**
 * Created by Kim.L on 2020/3/18
 */
public interface IDataViewConverter {

    @WorkerThread
    void refreshData(Object arg);

    @MainThread
    void addViewsToGroup(LinearLayout detailLayout);
}
