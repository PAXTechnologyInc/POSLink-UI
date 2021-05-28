package com.pax.pay.ui.def.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.pax.pay.ui.def.EInputType;
import com.pax.pay.ui.def.EditTextDataLimit;
import com.pax.pay.ui.def.R;
import com.pax.pay.ui.def.utils.EnterDataLineHelper;
import com.pax.pay.ui.def.utils.LanguageConvertUtils;
import com.pax.pay.ui.def.utils.RangeFilter;
import com.pax.us.pay.ui.component.keyboard.CustomKeyboardEditText;
import com.paxus.view.utils.ToastHelper;

import java.util.List;

public class FleetDataFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM_DISPLAY_TEXT = "DISPLAY_TEXT";
    private static final String ARG_PARAM_LENGTH_RANGE = "LENGTH_RANGE";
    private static final String ARG_PARAM_INPUT_TYPE = "INPUT_TYPE";
    private static final String ARG_PARAM_HAS_PHY_KEYBOARD = "HAS_PHY_KEYBOARD";

    TextView promptTv;
    CustomKeyboardEditText mEditText;
    Button confirmBtn;

    private String mDisplayText;
    private String mLengthRange;
    private String mInputType;
    private boolean mHasPhyKeyboard;

    private OnFragmentInteractionListener mListener;
    private EditTextDataLimit limit;
    private Context context;

    public FleetDataFragment() {

    }

    public static FleetDataFragment newInstance(String displayText, String lengthRange, String inputType, String hasPhyKeyboard) {
        FleetDataFragment fragment = new FleetDataFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_DISPLAY_TEXT, displayText);
        args.putString(ARG_PARAM_LENGTH_RANGE, lengthRange);
        args.putString(ARG_PARAM_INPUT_TYPE, inputType);
        if (!TextUtils.isEmpty(hasPhyKeyboard))
            args.putString(ARG_PARAM_HAS_PHY_KEYBOARD, hasPhyKeyboard);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDisplayText = getArguments().getString(ARG_PARAM_DISPLAY_TEXT);
            mLengthRange = getArguments().getString(ARG_PARAM_LENGTH_RANGE);
            mInputType = getArguments().getString(ARG_PARAM_INPUT_TYPE);
            mHasPhyKeyboard = getArguments().containsKey(ARG_PARAM_HAS_PHY_KEYBOARD) &&
                    "hasPhyKeyboard".equals(getArguments().getString(ARG_PARAM_HAS_PHY_KEYBOARD));
            limit = new EditTextDataLimit(mDisplayText, "", mLengthRange, EInputType.valueOf(mInputType), false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_enter_fleet_data_default, container, false);
        promptTv = (TextView) view.findViewById(R.id.prompt_tv);
        //textView.setText(mParam1);
        mEditText = view.findViewById(R.id.data_edt);
        confirmBtn = view.findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(this);
        mEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NONE) {
                //Fix ANFDRC-226, After click KEY_BACK_DOWN
                //helper.sendAbort();
                if (mListener != null) {
                    mListener.onDataAbort();
                }
                return true;
            } else if (actionId == EditorInfo.IME_ACTION_DONE) {
                onDataConfirmed();
            }
            return false;
        });
        mEditText.requestFocus();
        promptTv.setText(limit.prompt);
        setEditText();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        onDataConfirmed();
    }


    @Override
    public void onResume(){
        super.onResume();
        if(mHasPhyKeyboard && (!EInputType.ALLTEXT.name().equals(mInputType))){
            confirmBtn.setVisibility(View.VISIBLE);
            mEditText.hideKeyboard(true, false);
        }
        mEditText.requestFocus();
    }

    //for first window
    public void onWindowFocusChanged(boolean hasPhyKeyboard) {
        if(hasPhyKeyboard && (!EInputType.ALLTEXT.name().equals(mInputType))){
            confirmBtn.setVisibility(View.VISIBLE);
            mEditText.hideKeyboard(true, false);
        }
    }

    public void onKeyEnterDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            onDataConfirmed();
        }
    }

    @SuppressLint("StringFormatMatches")
    private void onDataConfirmed() {
        try {
            confirmBtn.setClickable(false);
            String content = mEditText.getText().toString();
            if (limit != null && limit.isMustEnter && TextUtils.isEmpty(content)) {
                return;
            }
            if (!TextUtils.isEmpty(limit.lengthRange)) {
                List<Integer> lengthList = RangeFilter.getLengthList(limit.lengthRange);
                if (content != null && !lengthList.contains(content.length())) {
                    //ToastHelper.showMessage(getActivity(), getString(R.string.notice_out_of_range, "length", limit.lengthRange));
//                    String pro = getString(R.string.notice_out_of_range);
//                    if (pro.contains("\n")){
//                        ToastHelper.showMessage(getActivity(), getString(R.string.notice_out_of_range, getString(R.string.length_en), limit.lengthRange, getString(R.string.length_fr), limit.lengthRange));
//                    }else {
//                        ToastHelper.showMessage(getActivity(), getString(R.string.notice_out_of_range, getString(R.string.length), limit.lengthRange));
//                    }
                    String title = LanguageConvertUtils.convertString(getActivity(), R.string.notice_out_of_range, R.string.length, limit.lengthRange);
                    ToastHelper.showMessage(getActivity(), title);
                    return;
                }
            }
            if (mListener != null) {
                mListener.onConfirmValue(content);
            }
        } finally {
            confirmBtn.setClickable(true);
        }

    }

    private void setEditText() {
        switch (limit.inputType) {
            case NUM:
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                        | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                mEditText.setKeepKeyBoardOn(true);
                EnterDataLineHelper.setEditTextNumber(getActivity(), mEditText, limit);
                mEditText.enableCustomization(R.xml.keyboard_numeric_confirm);
                break;
            case ALLTEXT:
                mEditText.disableCustomization();
                mEditText.postDelayed(() -> {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(mEditText, InputMethodManager.SHOW_IMPLICIT);
                }, 100);
                EnterDataLineHelper.setEditTextAllText(getActivity(), mEditText, limit);
                break;
            default:
                break;

        }
        mEditText.requestFocus();
    }


    public interface OnFragmentInteractionListener {
        void onDataAbort();

        void onConfirmValue(String value);
    }
}
