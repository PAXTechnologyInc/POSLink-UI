package com.pax.pay.ui.def.view;

import android.content.Context;

import androidx.annotation.Nullable;

import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.R;
import com.paxus.utils.StringUtils;

public class VisaInstallmentButtonView extends RelativeLayout {
    private TextView subView;
    private TextView mainView;

    public VisaInstallmentButtonView(Context context){
        this(context, null);
    }

    public VisaInstallmentButtonView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VisaInstallmentButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public VisaInstallmentButtonView(Context context,@Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes){
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutInflater.from(context).inflate(R.layout.visa_installment_button, this);
        subView = findViewById(R.id.sub);
        mainView = findViewById(R.id.main);
    }

    public void setSubViewText(SpannableString text){
        String temp = text.toString();
        if (StringUtils.isEmpty(temp)){
            subView.setVisibility(INVISIBLE);
        } else {
            subView.setText(text);
        }
    }

    public void setMainViewText(SpannableString text){
        mainView.setText(text);
    }

}
