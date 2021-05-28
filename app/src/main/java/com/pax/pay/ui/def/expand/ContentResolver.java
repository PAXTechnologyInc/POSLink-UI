package com.pax.pay.ui.def.expand;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.pax.us.pay.ui.constant.entry.EntryRequest;

public class ContentResolver {
    public static ContentResolver instance;
    //private ContentObserver contentObserver;

    public static ContentResolver getInstance() {
        if (instance == null) {
            instance = new ContentResolver();
        }
        return instance;
    }

    public void register(Context context, Uri returnUri) {
//        clearPreference(context);
//        if (contentObserver != null)
//            contentObserver = new ContentObserver(new Handler()) {
//                @Override
//                public void onChange(boolean selfChange) {
//                    super.onChange(selfChange);
//                    Map<String, String> transDataMap = ContentProviderUtil.query(returnUri, context);
//                    if (transDataMap != null) {
//                        ExchangeData.setTransDataMap(transDataMap);
//                    }
//                }
//            };
//        //register observer for result
//        context.getContentResolver().registerContentObserver(returnUri, true, contentObserver);

    }

    public void unregister(Context context) {
//        clearPreference(context);
//        if (contentObserver != null)
//            context.getContentResolver().unregisterContentObserver(contentObserver);
    }

    public void clearPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(EntryRequest.class.getName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

}
