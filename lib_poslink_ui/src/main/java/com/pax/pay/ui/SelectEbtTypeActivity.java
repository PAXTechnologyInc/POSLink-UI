package com.pax.pay.ui;


import java.util.List;

/**
 * Created by Charles.S on 2017/5/5.
 */

public class SelectEbtTypeActivity extends SelectLineActivity<Integer> {

    private List<String> selectOption;

    @Override
    protected void loadOtherParam(List<String> options) {
        selectOption = options;
        return;
    }

    ;

    @Override
    protected Integer convert(String content) {
        return selectOption.indexOf(content);
    }

}
