package com.pax.pay.ui.message;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/**
 * Create by Fahy.F on 3/29/2019
 */
public class CustomViewModel extends AndroidViewModel {

    private MutableLiveData<RespMessage> respLiveData;

    public CustomViewModel(@NonNull Application application) {
        super(application);
        UIMessageCenter.getInstance(application.getApplicationContext()).registerUIDesignListener(getRespLiveData());
    }

    public MutableLiveData<RespMessage> getRespLiveData() {
        if (respLiveData == null) {
            synchronized (CustomViewModel.class) {
                if (respLiveData == null) respLiveData = new MutableLiveData<>();
            }
        }
        return respLiveData;
    }

}
