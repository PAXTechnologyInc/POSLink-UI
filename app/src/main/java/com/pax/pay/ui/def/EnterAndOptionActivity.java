package com.pax.pay.ui.def;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pax.pay.ui.def.base.BaseAppActivity;
import com.pax.pay.ui.def.utils.EnterDataLineHelper;
import com.pax.us.pay.ui.component.keyboard.CustomKeyboardEditText;
import com.pax.us.pay.ui.core.api.IMessageListener;
import com.pax.us.pay.ui.message.CurrencyConverter;
import com.paxus.utils.StringUtils;
import com.paxus.view.dialog.CustomAlertDialog;
import com.paxus.view.dialog.DialogUtils;
import com.paxus.view.utils.KeyboardUtils;
import com.paxus.view.utils.ToastHelper;

import java.lang.ref.WeakReference;
import java.util.List;
@Deprecated
abstract class EnterAndOptionActivity<T> extends BaseAppActivity implements IMessageListener {

    //private MyHandler mHandler;

    LinearLayout transAmountLayout;
    TextView promptTv;

    LinearLayout inputAmountLayout;
    TextView amountPromptTv;
    TextView amountTv;
    CustomKeyboardEditText mEditText;
    Button confirmBtn;

    LinearLayout selectLayout;
    TextView tvPrompt;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter<OptionModelViewHolder> mAdapter;
    protected int selected = -1;

    private EditTextDataLimit limit;
    private long amount;
    private List<String> mAmountOption;
    private int viewType = 0;
    private static final int SELECT_AMOUNT = 1;
    private static final int INPUT_AMOUNT = 0;
    private boolean isPoint = false;
    private Dialog selectDialog = null;
    private String titleName;
    private String subOption;

    @Override
    protected void loadParam() {
        transAmountLayout = findViewById(R.id.trans_amount_layout);
        promptTv = findViewById(R.id.prompt_tv);

        inputAmountLayout = findViewById(R.id.amount_input_layout);
        amountTv = findViewById(R.id.amount_tv);
        amountPromptTv = findViewById(R.id.amount_prompt_tv);
        mEditText = findViewById(R.id.data_edt);
        confirmBtn = findViewById(R.id.confirm_btn);

        selectLayout = findViewById(R.id.amount_option_layout);
        tvPrompt = findViewById(R.id.prompt_select);
        mRecyclerView = findViewById(R.id.option_select);

        amount = 0;

        loadOtherParam();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enter_option_default;
    }

    @Override
    protected void setListeners() {
        confirmBtn.setOnClickListener(this);
    }

    public void setLimit(@NonNull EditTextDataLimit limit) {
        this.limit = limit;
        promptTv.setText(limit.prompt);
        EnterDataLineHelper.setEditTextAmount(this, mEditText, limit);
        mEditText.requestFocus();
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public void setTipName(String tipName) {
        this.limit = new EditTextDataLimit(getResources().getString(R.string.prompt_input_tip).replace("Tip", tipName),
                "", 0, 255, EInputType.AMOUNT, false);
        this.titleName = tipName;
        promptTv.setText(limit.prompt);

    }

    public void setAmount(long amount) {
        this.amount = amount;
        amountTv.setText(isPoint ? String.valueOf(amount) : CurrencyConverter.convert(amount, ""));
        transAmountLayout.setVisibility(View.VISIBLE);
    }

    public void setPoint(boolean point) {
        isPoint = point;
    }

    public void setCurrencyName(String currencyName) {
        if (!TextUtils.isEmpty(currencyName)) {
            CurrencyConverter.setDefCurrency(currencyName);
            mEditText.setText(isPoint ? String.valueOf(0L) : CurrencyConverter.convert(0L, ""));
        }
    }

    public void setAmountOption(List<String> amountOption, String subOption) {
        this.mAmountOption = amountOption;
        this.subOption = subOption;
        if (mAmountOption != null) {
            if (!TextUtils.isEmpty(subOption))
                mAmountOption.add(subOption);
            viewType = SELECT_AMOUNT;
            initDialog();
        }
    }

    private void initDialog() {
        selectLayout.setVisibility(View.GONE);
        transAmountLayout.setVisibility(View.INVISIBLE);
        confirmBtn.setVisibility(View.VISIBLE);
        KeyboardUtils.hideSystemKeyboard(this, mEditText);
        String title = getString(R.string.pls_select) + " " + titleName;
        selectDialog = DialogUtils.showSelectDialog(this,
                title,
                (dialog, which) -> {
                    if (mAmountOption.get(which).equals(subOption)) {
                        dialog.dismiss();
                        viewType = INPUT_AMOUNT;
                        //initInputView();
                    } else {
                        selected = which;
                        EnterDataLineHelper.getWatcher().setAmount(CurrencyConverter.parse(mAmountOption.get(selected)), 0);
                        mEditText.setText(mAmountOption.get(selected));
                        dialog.dismiss();
                        onDataConfirmed();
                    }
                },
                true,
                dialog -> {
                    sendAbort();
                },
                true,
                mAmountOption.toArray(new String[0]),
                -1
        );

    }

    @Override
    protected void onStop() {
        if (selectDialog != null) {
            selectDialog.dismiss();
            selectDialog = null;
        }
        super.onStop();
    }

    @Deprecated
    private void initSelectView() {
//        inputAmountLayout.setVisibility(View.GONE);
//        selectLayout.setVisibility(View.VISIBLE);
//        if (amount != 0) {
//            if (isPoint) {
//                amountPromptTv.setText(R.string.history_detail_point);
//                amountTv.setText(String.valueOf(amount));
//            } else {
//                amountTv.setText(CurrencyConverter.convert(amount, ""));
//            }
//        } else {
//            transAmountLayout.setVisibility(View.GONE);
//        }
//
//        confirmBtn.setEnabled(false);
//        mEditText.hideKeyboard(true);
//        KeyboardUtils.hideSystemKeyboard(this, mEditText);
//
//
//        tvPrompt.setText("Please Select:");
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
//        mRecyclerView.setLayoutManager(layoutManager);
//        mAdapter = new RecyclerView.Adapter<OptionModelViewHolder>() {
//            @NonNull
//            @Override
//            public OptionModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                return new OptionModelViewHolder(LayoutInflater.from(EnterAndOptionActivity.this).inflate(R.layout.item_mode_grid, parent, false));
//            }
//
//            @Override
//            public void onBindViewHolder(@NonNull OptionModelViewHolder holder, int position) {
//
//                String viewData = mAmountOption.get(position);
//
//                if (viewData != null) {
//                    holder.itemView.setContentDescription(viewData);
//                    holder.textView.setText(viewData);
//                    holder.textView.setSelected(selected == position);
//                    holder.textView.setOnClickListener(v -> {
//                        selected = holder.getAdapterPosition();
//                        if ("Others".equals(mAmountOption.get(selected))) {
//                            viewType = INPUT_AMOUNT;
//                            initInputView();
//                        } else {
//                            confirmBtn.setEnabled(true);
//                        }
//                        mAdapter.notifyDataSetChanged();
//                    });
//                }
//            }
//
//            @Override
//            public int getItemCount() {
//                return mAmountOption == null ? 0 : mAmountOption.size();
//            }
//        };
//        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, getResources().getDimensionPixelSize(R.dimen.space_horizontal), true));
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initViews() {
        if (mAmountOption != null) {
            viewType = SELECT_AMOUNT;
            initDialog();
        } else {
            viewType = INPUT_AMOUNT;
            initInputView();
        }
    }

    private void initInputView() {
        selectLayout.setVisibility(View.GONE);
        inputAmountLayout.setVisibility(View.VISIBLE);
        transAmountLayout.setVisibility(View.VISIBLE);
        confirmBtn.setVisibility(View.VISIBLE);
        confirmBtn.setEnabled(true);
        if (amount != 0) {
            amountTv.setText(isPoint ? String.valueOf(amount) : CurrencyConverter.convert(amount, ""));
        } else {
            transAmountLayout.setVisibility(View.INVISIBLE);
        }
        promptTv.setText(limit != null ? limit.prompt : "");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mEditText.setKeepKeyBoardOn(true);
        // EnterDataLineHelper.setEditTextAmount(this, mEditText, limit);
        mEditText.requestFocus();

        mEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onDataConfirmed();
            }
            return false;
        });
    }

    private void onDataConfirmed() {
        try {
            confirmBtn.setClickable(false);
            String content;
            if (viewType == INPUT_AMOUNT) {
                content = mEditText.getText().toString();
                if (limit != null && limit.isMustEnter && StringUtils.isEmpty(content)) {
                    return;
                }
            } else {
                content = mAmountOption.get(selected);
                if (content.equals(getString(R.string.other_amount))) {
                    ToastHelper.showMessage(this, getString(R.string.pls_select_again));
                    return;
                }
            }
            if (!validate(content)) {
                return;
            }

            T data = convert(content);

            if (limit != null && limit.needConfirm) {
                confirm(data);
            } else {
                sendNext(data);
            }
        } finally {
            confirmBtn.setClickable(true);
        }
    }

    @Override
    protected boolean onKeyEnterDown() {
        onDataConfirmed();
        return true;
    }

    @Override
    protected boolean onKeyBackDown() {
        sendAbort();
        return true;
    }

    @Override
    public void onShowMessage(@Nullable String transName, @Nullable String message, String transMode) {
        setTitle(transName);
        setDemo(transMode);
    }

    @Override
    public void onClickProtected(View view) {
        if (view.getId() == R.id.confirm_btn) {
            onDataConfirmed();
        }
    }

    protected void confirm(final T data) {
        final WeakReference<Context> contextWeakReference = new WeakReference<>(this);
        runOnUiThread(() -> {
            Context ctx = contextWeakReference.get();
            CustomAlertDialog dialog = new CustomAlertDialog(ctx, CustomAlertDialog.NORMAL_TYPE);
            dialog.setCancelClickListener(alertDialog -> {
                alertDialog.dismiss();
                sendAbort();
            }).setConfirmClickListener(alertDialog -> {
                alertDialog.dismiss();
                sendNext(data);
            }).create();
            dialog.setContent(limit != null ? limit.confirmPrompt : "");
            dialog.showConfirmButton(true);
            dialog.showCancelButton(true);
            dialog.show();
        });
    }

    protected boolean validate(String content) {
        if (CurrencyConverter.parse(content) == 0 && limit.isMustEnter) {
            ToastHelper.showMessage(this, getString(R.string.pls_input_again));
            mEditText.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * convert String content to base UI data type
     *
     * @param content string content(from EditText usually)
     * @return base UI data type
     */
    protected abstract T convert(String content);

    /**
     * load custom data
     */
    protected abstract void loadOtherParam();

    /**
     * send ABORT request
     */
    protected abstract void sendAbort();

    /**
     * send NEXT request with data
     *
     * @param data UI data, see data ui from Entry Actions
     */
    protected abstract void sendNext(T data);

    private static class MyHandler extends Handler {

        private final WeakReference<Context> reference;

        MyHandler(Context context) {
            reference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            EnterAndOptionActivity activity = (EnterAndOptionActivity) reference.get();
            if (activity != null) {
                if (msg.what == SELECT_AMOUNT) {
                    //activity.initSelectView();
                    activity.initDialog();
                } else {
                    activity.initInputView();
                }
            }
        }
    }


    static class OptionModelViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        OptionModelViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.mode_grid_tv);
        }
    }

}
