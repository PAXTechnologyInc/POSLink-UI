package com.pax.pay.ui.def.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.R;
import com.pax.us.pay.ui.message.CurrencyConverter;
import com.paxus.view.dialog.CustomAlertDialog;

public class FeeAlertDialog extends CustomAlertDialog {

    private FrameLayout mTableContent;
    private String feeName;
    private long totalAmount;
    private long feeAmount;
    private final String currency;
    private final Context context;

    public FeeAlertDialog(Context context, String currency) {
        super(context, CUSTOM_TYPE);
        this.currency = currency;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feeName = context.getString(R.string.additional_fee);
        mTableContent = findViewById(R.id.custom_layout);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_fee_table, null);
        mTableContent.addView(view);

        mTableContent.setVisibility(View.VISIBLE);
        showTableContent();
    }

    public void setTableContent(String feeName, long totalAmount, long feeAmount) {
        this.feeName = feeName;
        this.totalAmount = totalAmount;
        this.feeAmount = feeAmount;
    }

    private void showTableContent() {
        long primaryAmount = totalAmount - feeAmount;
        setTitle(context.getString(R.string.confirm_amount));
        CurrencyConverter.setDefCurrency(currency);
        TextView primaryAmtNameTV = mTableContent.findViewById(R.id.fee_primary_amount_name);
        TextView primaryAmtTv = mTableContent.findViewById(R.id.fee_primary_amount);
        primaryAmtNameTV.setText(R.string.sale_amount);
        primaryAmtTv.setText(CurrencyConverter.convert(primaryAmount));
        TextView feeAmtNameTV = mTableContent.findViewById(R.id.fee_amount_name);
        TextView feeAmtTv = mTableContent.findViewById(R.id.fee_amount);
        feeAmtNameTV.setText(feeName);
        feeAmtTv.setText(CurrencyConverter.convert(feeAmount));
        TextView totalAmtNameTV = mTableContent.findViewById(R.id.fee_total_amount_name);
        TextView totalAmtTv = mTableContent.findViewById(R.id.fee_total_amount);
        totalAmtNameTV.setText(R.string.total_amount);
        totalAmtTv.setText(CurrencyConverter.convert(totalAmount));
    }
}
