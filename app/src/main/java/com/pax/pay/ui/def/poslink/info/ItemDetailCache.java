/*
 * ============================================================================
 * = COPYRIGHT
 *          PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or nondisclosure
 *   agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *   disclosed except in accordance with the terms in that agreement.
 *     Copyright (C) 2018-? PAX Computer Technology(Shenzhen) CO., LTD All rights reserved.
 *
 * Module Date: 2020/1/18
 * Module Auth: Fahy.F
 * Description:
 *
 * Revision History:
 * Date                   Author                       Action
 * 2020/1/18              Fahy.F                       Create
 * ============================================================================
 */

package com.pax.pay.ui.def.poslink.info;

import android.os.Bundle;

import androidx.annotation.Nullable;

import android.text.TextUtils;

import com.paxus.utils.log.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class ItemDetailCache {
    private static ArrayList<ItemDetailWrapper> cachedItemDetailList = new ArrayList<>();
    private static String initTopDown;
    private static boolean initECRUI;

    public static ArrayList<ItemDetailWrapper> getCachedItemDetailList() {
        return cachedItemDetailList;
    }

    public static void setCachedItemDetailList(ArrayList<ItemDetailWrapper> cachedItemDetailList) {
        ItemDetailCache.cachedItemDetailList = cachedItemDetailList;
    }

    public static void initItemDetailList(boolean isECRUI, Bundle requestData) {
        if (initECRUI != isECRUI) {
            clear();
            initECRUI = isECRUI;
        }
        //Todo temp for compile, remove it
//        if (TextUtils.isEmpty(initTopDown)) {
//            if (!TextUtils.isEmpty(requestData.getTopDown())) {
//                initTopDown = requestData.getTopDown();
//            }else {
//                initTopDown = "Y";
//            }
//        }
        initItemList(requestData);
    }

    private static void initItemList(Bundle requestData) {
        //Todo temp for compile, remove it
//        List<ItemDetail> tmpList = requestData.getItemDetailList();
//        List<String> itemIndex = requestData.getItemIndex();
//        int itemAction = requestData.getLineItemAction();
//        boolean indexEmpty = itemIndex.isEmpty();
//        switch (itemAction) {
//            case 0:
//                addItemList(buildWrapperList(tmpList, itemIndex, indexEmpty));
//                break;
//            case 1:
//                ArrayList<ItemDetailWrapper> wrapperList = buildWrapperList(tmpList, itemIndex, indexEmpty);
//                ArrayList<ItemDetailWrapper> cachedList = getCachedItemDetailList();
//                for (ItemDetailWrapper wrapper : wrapperList) {
//                    for (int i = 0; i < size(); i++) {
//                        if (TextUtils.equals(wrapper.getIndex(), cachedList.get(i).getIndex())) {
//                            cachedList.set(i, wrapper);
//                        }
//                    }
//                }
//                break;
//            case 2:
//                if (indexEmpty) {
//                    deleteItemByIndex(null);
//                } else {
//                    for (String index : itemIndex) {
//                        deleteItemByIndex(index);
//                    }
//                }
//                break;
//        }
    }

    private static ArrayList<ItemDetailWrapper> buildWrapperList(List<ItemDetail> tmpList, List<String> itemIndex, boolean indexEmpty) {
        ArrayList<ItemDetailWrapper> wrappers = new ArrayList<>();
        for (int i = 0; i < tmpList.size(); i++) {
            wrappers.add(new ItemDetailWrapper(indexEmpty ? null : itemIndex.get(i), tmpList.get(i)));
        }
        return wrappers;
    }

    private static void addItemList(List<ItemDetailWrapper> wrapperList) {
        if (isTopDown()) {
            Collections.reverse(wrapperList);
            getCachedItemDetailList().addAll(0, wrapperList);
        } else {
            getCachedItemDetailList().addAll(wrapperList);
        }
    }

    private static boolean isTopDown() {
        return "Y".equals(initTopDown);
    }

    public static boolean isEmpty() {
        return size() == 0;
    }

    public static int size() {
        return cachedItemDetailList == null ? 0 : cachedItemDetailList.size();
    }

    private static void clear() {
        Logger.v("clear items");
        if (cachedItemDetailList != null) cachedItemDetailList.clear();
        initTopDown = null;
    }

    public static void clearDefaultCache() {
        if (!initECRUI) clear();
    }

    public static void clearECRCache() {
        if (initECRUI) clear();
    }


    public static @Nullable ItemDetailWrapper foundItemByIndex(String index) {
        if (cachedItemDetailList != null) {
            for (ItemDetailWrapper wrapper : cachedItemDetailList) {
                if (TextUtils.equals(wrapper.getIndex(), index)) {
                    return wrapper;
                }
            }
        }
        return null;
    }

    public static void deleteItemByIndex(String index) {
        if (cachedItemDetailList != null) {
            ListIterator<ItemDetailWrapper> iter = cachedItemDetailList.listIterator();
            while (iter.hasNext()) {
                ItemDetailWrapper wrapper = iter.next();
                if (TextUtils.equals(wrapper.getIndex(), index))
                    iter.remove();
            }
        }
    }
}
