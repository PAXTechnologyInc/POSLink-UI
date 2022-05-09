package com.pax.pay.ui.def;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pax.pay.ui.def.base.BaseActivity;
import com.pax.pay.ui.def.base.SecurityRespStatusImpl;
import com.pax.pay.ui.def.constant.InstallmentFrequencyContext;
import com.pax.pay.ui.def.view.VisaInstallmentButtonView;
import com.pax.us.pay.ui.constant.entry.EntryExtraData;
import com.pax.us.pay.ui.core.api.IUIListener;
import com.pax.us.pay.ui.core.helper.ConfirmInstallmentPlanHelper;
import com.pax.us.pay.ui.message.CurrencyConverter;
import com.paxus.utils.log.Logger;

import java.util.ArrayList;
import java.util.List;


public class SelectInstallmentPlanActivity extends BaseActivity implements IUIListener {
    //this activity displays retrived egilible plans for a certain card
    Button fullAmount;
    LinearLayout view1; // view1 shows the overall visa plans
    LinearLayout view2; // view2 show the detail of a plan
    RecyclerView buttons;

    /*the following are for displaying plan details*/
    TextView displayDetailHeader1;
    TextView displayDetailHeader2;
    TextView displayDetailHeader3;
    TextView displayDetailTermsAndConditions;
    Button displayDetailConfirm;
    ScrollView displayDetailScrollView;
    int selectedPlanIndex;
    int totalPlans;

    private List<SpannableString> buttonMainText;
    private List<SpannableString> buttonSubText;
    private long amount;

    private ConfirmInstallmentPlanHelper helper;
    private RecyclerView.Adapter<VisaInstalllmentButtonViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        needToolBar = false;
        super.onCreate(savedInstanceState);
        helper = new ConfirmInstallmentPlanHelper(this, new SecurityRespStatusImpl(this));
        Logger.d(" onCreate");
    }

    @Override
    protected void onStart() {
        helper.start(this, getIntent());
        super.onStart();
    }

    @Override
    protected void onStop() {
        helper.stop();
        super.onStop();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_installment_plan;
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void loadParam() {
        Bundle bundle = getIntent().getExtras();
        fullAmount = findViewById(R.id.fullAmount);
        view1 = findViewById(R.id.view_number1);
        view2 = findViewById(R.id.view_number2);
        view2.setVisibility(View.GONE);

        //set param for displaying plan detail
        displayDetailScrollView = findViewById(R.id.display_detail_scrollView);
        displayDetailScrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {// when terms and conditions are to0 short to create a scroll bar
            @Override                                                                                                          //this will set confirm button enabled directly
            public void onGlobalLayout() {
                if (displayDetailScrollView.getChildAt(0).getBottom()
                        <= (displayDetailScrollView.getHeight() + displayDetailScrollView.getScrollY()))  {
                    displayDetailConfirm.setBackgroundResource(R.drawable.round_corner_button);
                    displayDetailConfirm.setEnabled(true);
                } else {
                    displayDetailConfirm.setBackgroundResource(R.drawable.round_corner_button_disabled);
                    displayDetailConfirm.setEnabled(false);
                }
            }
        });
        displayDetailScrollView.getViewTreeObserver().
                addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {

                        if (displayDetailScrollView.getChildAt(0).getBottom()
                                <= (displayDetailScrollView.getHeight() + displayDetailScrollView.getScrollY())) {
                            displayDetailConfirm.setBackgroundResource(R.drawable.round_corner_button);
                            displayDetailConfirm.setEnabled(true);
                        }
                    }
                });
        displayDetailHeader1 = findViewById(R.id.display_detail_header1);
        displayDetailHeader2 = findViewById(R.id.display_detail_header2);
        displayDetailHeader3 = findViewById(R.id.display_detail_header3);
        displayDetailTermsAndConditions = findViewById(R.id.display_detail_termAndConditions);
        displayDetailConfirm = findViewById(R.id.display_detail_confirm);
        displayDetailConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNext(selectedPlanIndex);
            }
        });

        //separator = findViewById(R.id.separator_02);
        buttons = findViewById(R.id.buttons);
        amount = bundle.getLong(EntryExtraData.PARAM_BASE_AMOUNT);

        String[] installmentPaymentAmounts = bundle.getStringArray("installmentPaymentAmounts");//EntryExtraData.PARAM_INSTALLMENT_PAYMENT_AMOUNTS);
        String[] installmentPaymentTermsAndConditions = bundle.getStringArray("termsAndConditions");//EntryExtraData.PARAM_INSTALLMENT_PAYMENT_TERMS_AND_CONDITIONS);
        String[] installmentPaymentTotalFees = bundle.getStringArray("totalFees");//EntryExtraData.PARAM_INSTALLMENT_PAYMENT_TOTAL_FEES);
        String[] installmentPaymentNumberOfInstallments = bundle.getStringArray("numberOfInstallmens");//EntryExtraData.PARAM_INSTALLMENT_PAYMENT_NUMBER_OF_INSTALLMENTS);
        String[] installmentPaymentFrequencys = bundle.getStringArray("frequencyOfInstallments");//EntryExtraData.PARAM_INSTALLMENT_PAYMENT_FREQUENCY_OF_INSTALLMENTS);
        String[] installmentPaymentTotalAmountInclusiveFees = bundle.getStringArray("totalAmountInclusiveFees");//EntryExtraData.PARAM_INSTALLMENT_PAYMENT_TOTAL_AMOUNT_INCLUSIVE_FEES);
        String[] installmentPaymentPlanIDs = bundle.getStringArray("planId");//EntryExtraData.PARAM_INSTALLMENT_PAYMENT_PLAN_ID);
        String[] installmentCurrencies = bundle.getStringArray("CURRENCIES");//EntryExtraData.PARAM_INSTALLMENT_PAYMENT_PLAN_CURRENCIES);
        totalPlans = installmentPaymentAmounts.length;

        getButtonText(installmentPaymentAmounts, installmentPaymentNumberOfInstallments, installmentPaymentFrequencys, installmentPaymentTotalFees, installmentPaymentTotalAmountInclusiveFees);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setAutoMeasureEnabled(true);
        buttons.setLayoutManager(layoutManager);
        adapter = new RecyclerView.Adapter<VisaInstalllmentButtonViewHolder>() {
            @NonNull
            @Override
            public VisaInstalllmentButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new VisaInstalllmentButtonViewHolder(LayoutInflater.from(SelectInstallmentPlanActivity.this).inflate(R.layout.visa_installment_recyler_view_element, parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull VisaInstalllmentButtonViewHolder holder, int position) {
                if (position >= installmentPaymentFrequencys.length) return;
                SpannableString mainText = buttonMainText.get(position);
                SpannableString subText = buttonSubText.get(position);
                String currency = installmentCurrencies[position];
                String installmentPaymentAmount = installmentPaymentAmounts[position];
                String installmentPaymentFrequency = installmentPaymentFrequencys[position];
                String installmentPaymentTotalFee = installmentPaymentTotalFees[position];
                String installmentPaymentTotalAmountInclusiveFee = installmentPaymentTotalAmountInclusiveFees[position];
                String installmentPaymentNumberOfInstallment = installmentPaymentNumberOfInstallments[position];
                String installmentPaymentTermsAndCondition = installmentPaymentTermsAndConditions[position];
                String installmentPaymentPlanID = installmentPaymentPlanIDs[position];

                holder.button.setMainViewText(mainText);
                holder.button.setSubViewText(subText);
                holder.button.setBackground(getResources().getDrawable(R.drawable.round_corner_button));
                holder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        view1.setVisibility(View.INVISIBLE);
                        view2.setVisibility(View.VISIBLE);
                        CurrencyConverter.setDefCurrency(currency);
                        String header1 = CurrencyConverter.convert(Long.parseLong(installmentPaymentAmount)) + "/" + InstallmentFrequencyContext.convertFrequencyToSingularNoun(installmentPaymentFrequency.toLowerCase(), getBaseContext());
                        String header2 = getString( R.string.visa_installment_display_plan_detail_header2, CurrencyConverter.convert(Long.parseLong(installmentPaymentTotalFee)), CurrencyConverter.convert(Long.parseLong(installmentPaymentTotalAmountInclusiveFee)));
                        String header3 = getString(R.string.visa_installment_display_plan_detail_header3, installmentPaymentNumberOfInstallment, installmentPaymentFrequency.toLowerCase());
                        displayDetailHeader1.setText(header1);
                        displayDetailHeader2.setText(header2);
                        displayDetailHeader3.setText(header3);
                        displayDetailTermsAndConditions.setText(installmentPaymentTermsAndCondition.replaceAll("~", ","));
                        //selectedPlanId = installmentPaymentPlanID;
                        selectedPlanIndex = position;
                        //[VI-14]confirm button disabled if user exit from the plan detail page and enter again
                        //requestlayout every time after a plan button is clicked, to trigger onGlobalLayout() of scroll view
                        displayDetailScrollView.requestLayout();
                    }
                });
            }

            @Override
            public int getItemCount() {
                return installmentPaymentFrequencys.length;
            }
        };

        buttons.setHasFixedSize(false);
        buttons.setAdapter(adapter);
        setButtonText(fullAmount, amount, "", "", "", "", String.valueOf(amount));
        fullAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNext(totalPlans);
            }
        });
    }


    private void setButtonText(Button button,
                                long amount,
                                String installment_payment_amount,
                                String number_of_installment,
                                String frequency_of_installment,
                                String total_fees,
                                String total_amount_include_fees) {
        String rawText = getString(R.string.visa_installment_display_plan_detail_full_amount, CurrencyConverter.convert(Long.parseLong(total_amount_include_fees)));
        int pos = rawText.indexOf('\n');
        SpannableString spannableString = new SpannableString(rawText);
        spannableString.setSpan(new RelativeSizeSpan(2.5f), 0, pos, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        button.setText(spannableString);
    }

    private void getButtonText(String[] installment_payment_amounts,
                               String[] number_of_installments,
                               String[] frequency_of_installments,
                               String[] total_fees,
                               String[] total_amount_include_fees){
        buttonMainText = new ArrayList<>();
        buttonSubText = new ArrayList<>();

        for (int i = 0; i < installment_payment_amounts.length; ++i){
            SpannableString subText = new SpannableString(getString(R.string.visa_installment_plan_numbers_ferquency, number_of_installments[i], InstallmentFrequencyContext.convertFrequencyToPluralNoun(frequency_of_installments[i].toLowerCase(), getBaseContext())));
            subText.setSpan(new RelativeSizeSpan(1.0f), 0, subText.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            String rawText = CurrencyConverter.convert(Long.parseLong(installment_payment_amounts[i]))  + "/" + InstallmentFrequencyContext.convertFrequencyToSingularNoun(frequency_of_installments[i].toLowerCase(), getBaseContext()) + "\n"
                    + getString(R.string.visa_installment_display_plan_detail_header2, CurrencyConverter.convert(Long.parseLong(total_fees[i])), CurrencyConverter.convert(Long.parseLong(total_amount_include_fees[i])));
            int pos1 = rawText.indexOf('/');
            int pos2 = rawText.indexOf('\n');
            SpannableString spannableString = new SpannableString(rawText + "\n" + "\n");
            spannableString.setSpan(new RelativeSizeSpan(2.5f), 0, pos1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new RelativeSizeSpan(1.5f), pos1, pos2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new RelativeSizeSpan(1.0f), pos2, rawText.length(), SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
            buttonMainText.add(spannableString);
            buttonSubText.add(subText);
        }
    }

    private void sendNext(int result){
        helper.sendNext(result);
    }

    private void sendAbort() {
        helper.sendAbort();
        helper.stop();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && view1.getVisibility() == View.INVISIBLE){
            selectedPlanIndex = totalPlans;
            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
            boolean isEnabled = displayDetailConfirm.isEnabled();
            if (isEnabled){//if the scroll bar is at the bottom the view, reset it to the top
                displayDetailScrollView.scrollTo(0, 0);
            }
            return true;
        } else {
            sendAbort();
            return super.onKeyDown(keyCode, event);
        }
    }

    public class VisaInstalllmentButtonViewHolder extends RecyclerView.ViewHolder {
        public VisaInstallmentButtonView button;

        public VisaInstalllmentButtonViewHolder(View itemView){
            super(itemView);
            button = itemView.findViewById(R.id.plan_button);
        }
    }
}