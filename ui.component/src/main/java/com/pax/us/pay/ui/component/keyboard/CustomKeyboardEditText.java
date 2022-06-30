/*
 * ============================================================================
 * COPYRIGHT
 *              Pax CORPORATION PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with Pax Corporation and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *      Copyright (C) 2017 - ? Pax Corporation. All rights reserved.
 * Module Date: 2017-2-21
 * Module Author: Kim.L
 * Description:
 *
 * ============================================================================
 */
package com.pax.us.pay.ui.component.keyboard;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.InputType;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.annotation.XmlRes;
import androidx.appcompat.widget.AppCompatEditText;

import com.pax.us.pay.ui.component.R;
import com.paxus.inputmethodservice.Keyboard;
import com.paxus.view.utils.KeyboardUtils;
import com.paxus.view.utils.ViewUtils;

public class CustomKeyboardEditText extends AppCompatEditText implements View.OnClickListener {

    private Keyboard mKeyboard;
    private CustomKeyboardView mKeyboardView;

    private boolean autoSize = false;
    private boolean keepKeyBoardOn = false;

    private Window mWindow;
    private View mDecorView;
    private View mContentView;
    private final Context context;

    private CustomPopupWindow mKeyboardWindow;

    /**
     * adjusted distance
     */
    private int mScrollDistance = 0;

    /**
     * the real height : screen height - guide height - status height
     */
    private int screenContentHeight = -1;

    private int maxFontSize = 0;

    private OnClickListener mOnClickListener;
    @XmlRes
    private int keyboardId = -1;

    public CustomKeyboardEditText(Context context) {
        this(context, null);
    }

    public CustomKeyboardEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomKeyboardEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initKeyboard(attrs);
    }

    private void initKeyboard(AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomKeyboardEditText);
        if (array.hasValue(R.styleable.CustomKeyboardEditText_xml)) {
            int xmlId = array.getResourceId(R.styleable.CustomKeyboardEditText_xml, 0);
            setAutoSize(array.getBoolean(R.styleable.CustomKeyboardEditText_autoSize, false));
            setKeepKeyBoardOn(array.getBoolean(R.styleable.CustomKeyboardEditText_keepKeyboardOn, false));
            setKeyboardId(xmlId);
        } else {
            // can call enableCustomization to reactivate
        }

        // AET-65
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Ensure you call it only once :
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                setText(getText());
            }
        });

        array.recycle();
    }

    private void initAttributes() {
        initScreenParams();
        setClickable(true);
        setLongClickable(false);
        setCursorVisible(false);
        setFocusable(true);
        setOnClickListener(this);
        setFocusableInTouchMode(true);
        setInputType(InputType.TYPE_NULL);
        setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        removeCopyAbility();

        setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(false);
            } else {
                onClick(v);
            }
        });
    }

    @Override
    public final void setOnClickListener(@Nullable OnClickListener l) {
        mOnClickListener = !this.equals(l) ? l : null;
        super.setOnClickListener(this);
    }

    @TargetApi(11)
    private void removeCopyAbility() {
        setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                //do nothing
            }
        });
    }

    private void initScreenParams() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(metrics);

        screenContentHeight = metrics.heightPixels - getStatusBarHeight();
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void onEditorAction(int actionCode) {
        if (actionCode == EditorInfo.IME_ACTION_NONE || actionCode == EditorInfo.IME_ACTION_DONE) {
            hideKeyboard(false);
        }
        super.onEditorAction(actionCode);
    }

    @Override
    public void onClick(View v) {
        if (mKeyboard != null && ViewUtils.canShowSoftInputOnFocus()) {
            hideSysInput();
            showKeyboard();
        }

        if (mOnClickListener != null) {
            mOnClickListener.onClick(v);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!hasFocus()) {
            return super.onKeyDown(keyCode, event);
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!keepKeyBoardOn) {
                onEditorAction(EditorInfo.IME_ACTION_NONE);
                return true;
            }
        } else if (keyCode == KeyEvent.KEYCODE_ENTER) {
            //if (!keepKeyBoardOn) {
            {
                onEditorAction(EditorInfo.IME_ACTION_DONE);
                return true;
            }
        }
        this.setSelection(this.getText().toString().length());
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode()) {
            return;
        }
        mWindow = ((Activity) getContext()).getWindow();
        mDecorView = mWindow.getDecorView();
        mContentView = mWindow.findViewById(Window.ID_ANDROID_CONTENT);
        mContentView.setFocusableInTouchMode(true);

        if (mKeyboard != null && isFocused()) {
            hideSysInput();
            showKeyboard();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        hideSysInput();
        releaseCustomKeyboard();
        mDecorView = null;
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (isAutoSize()) {
            TextPaint textPaint = getPaint();
            int width = getMeasuredWidth() - getPaddingEnd() - getPaddingStart(); //AET-20
            if (width > 0) {
                int size = updateTextSize(text, textPaint, width);
                setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
            }
        }
    }

    private int updateTextSize(CharSequence text, TextPaint textPaint, int width) {
        int size = maxFontSize > 0 ? maxFontSize : (int) (textPaint.getTextSize());
        if (size > maxFontSize) {
            maxFontSize = size;
        }

        while (true) {
            textPaint.setTextSize(size);
            float w = textPaint.measureText(text.toString());
            if (w > width) {
                size -= 2;
            } else {
                break;
            }
        }
        return size;
    }

    private void showKeyboard() {
        if (mKeyboardWindow != null && !mKeyboardWindow.isShowing() && mDecorView != null) {
            mKeyboardView.setKeyboard(mKeyboard);
            mKeyboardWindow.forceDismiss();
            mKeyboardWindow.showAtLocation(mDecorView, Gravity.BOTTOM | Gravity.END, 0, 0);
            //mKeyboardWindow.update(); //bug on Android 7.0, it hardcode Gravity!!!!!
            if (ViewUtils.canNavigationBarImmersiveSticky()) {
                ViewUtils.hideNavigationBar(mKeyboardView);
            }
            setSelection(getText().length());
            KeyBoardStatus.setKeyBoardPopuped(true);

            if (mContentView != null) {
                int[] pos = new int[2];
                getLocationOnScreen(pos);
                float height = ViewUtils.dp2px(getContext(), 240);

                Rect outRect = new Rect();
                mDecorView.getWindowVisibleDisplayFrame(outRect);

                int screen = screenContentHeight;
                mScrollDistance = (int) ((pos[1] + getMeasuredHeight() - outRect.top) - (screen - height));

                if (mScrollDistance > 0) {
                    mContentView.scrollBy(0, mScrollDistance);
                }
            }
        } else if (mKeyboardWindow != null  && mDecorView != null){
            if(mKeyboardView!=null)
                mKeyboardView.setVisibility(View.VISIBLE);
        }
    }

    public void hideKeyboard(boolean force) {
        hideKeyboard(force,true);
    }

    public void hideKeyboard(boolean force, boolean clear) {
        if (mKeyboardWindow != null && mKeyboardWindow.isShowing()) {
            if (force || !isKeepKeyBoardOn()) {
                mKeyboardWindow.forceDismiss();
                KeyBoardStatus.setKeyBoardPopuped(false);
            }
            if (clear)
                this.clearFocus();
        }
    }

    private void hideSysInput() {
        if (getWindowToken() != null) {
            KeyboardUtils.hideSystemKeyboard(getContext(), this);
        }
    }

    private void showSysInput() {
        if (getWindowToken() != null) {
            requestFocus();
            postDelayed(() -> {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT);
            }, 100);
        }
    }

    public boolean isAutoSize() {
        return autoSize;
    }

    public void setAutoSize(boolean autoSize) {
        this.autoSize = autoSize;
    }

    public boolean isKeepKeyBoardOn() {
        return keepKeyBoardOn;
    }

    public void setKeepKeyBoardOn(boolean keepKeyBoardOn) {
        this.keepKeyBoardOn = keepKeyBoardOn;
    }

    public void setKeyboardId(@XmlRes int id) {
        if (mKeyboard == null || keyboardId != id) {
            keyboardId = id;
            mKeyboard = new Keyboard(context, id);
            mKeyboardView = loadKeyboardView(context);
            KeyboardUtils.bind(mKeyboardView, new KeyboardUtils(context, mKeyboard, this));

            mKeyboardWindow = new CustomPopupWindow(mKeyboardView,
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mKeyboardWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
            mKeyboardWindow.setOutsideTouchable(true);
            mKeyboardWindow.setOnDismissListener(() -> {
                if (mScrollDistance > 0) {
                    int temp = mScrollDistance;
                    mScrollDistance = 0;
                    if (mContentView != null) {
                        mContentView.scrollBy(0, -temp);
                    }
                }
            });

            ViewTreeObserver observer = mKeyboardView.getViewTreeObserver();
            observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mKeyboardView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    if (mKeyboardView.getWeight() < 1.0f) {
                        int width = mKeyboardView.getMeasuredWidth();
                        if ("Aries8".equals(Build.MODEL)) { // Ar8 bug, the system keyboard cannot fill the screen either when display size is not default
                            width = 1280; // should not change metrics.widthPixels directly
                        }
                        mKeyboardWindow.update((int) (width * mKeyboardView.getWeight()), -1);
                    }
                }
            });

            mKeyboardWindow.setOnEnableDismissListener(() -> false);
            initAttributes();
        }
    }

    protected CustomKeyboardView loadKeyboardView(Context context){
        return (CustomKeyboardView) LayoutInflater.from(context).inflate(R.layout.custom_keyboard_view_default, null);
    }

    public void enableCustomization(@XmlRes int id) {
        hideSysInput();
        showKeyboard();
    }

    public void disableCustomization() {
        releaseCustomKeyboard();
        showSysInput();
    }

    private void releaseCustomKeyboard() {
        hideKeyboard(true);
        keyboardId = -1;
        mKeyboardWindow = null;
        mKeyboardView = null;
        mKeyboard = null;
        //mDecorView = null;
        mContentView = null;
        mWindow = null;
    }
}
