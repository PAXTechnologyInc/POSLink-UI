/*
 * ============================================================================
 * COPYRIGHT
 *              Pax CORPORATION PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with Pax Corporation and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *      Copyright (C) 2017 - ? Pax Corporation. All rights reserved.
 * Module Date: 2017-7-18
 * Module Author: Kim.L
 * Description:
 *
 * ============================================================================
 */
package com.paxus.view.utils;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.List;

import static android.content.Context.AUDIO_SERVICE;

public class KeyboardUtils implements KeyboardView.OnKeyboardActionListener {

    private final Keyboard mKeyboard;
    private final EditText mEditText;
    private final Context mContext;

    public KeyboardUtils(Context context, Keyboard keyboard, EditText editText) {
        mContext = context;
        mKeyboard = keyboard;
        mEditText = editText;
    }

    public static void bind(KeyboardView keyboardView, KeyboardUtils keyboardUtils) {
        keyboardView.setKeyboard(keyboardUtils.mKeyboard);
        keyboardView.setEnabled(true);
        keyboardView.setLongClickable(true);
        keyboardView.setPreviewEnabled(false);
        keyboardView.setOnKeyboardActionListener(keyboardUtils);
    }

    private static void playClick(Context context, int keyCode) {
        AudioManager am = (AudioManager) context.getSystemService(AUDIO_SERVICE);
        if (am == null) {
            return;
        }
        switch (keyCode) {
            case 32:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case Keyboard.KEYCODE_DONE:
                am.playSoundEffect(AudioManager.FX_KEY_CLICK);
                break;
            case 10:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
                break;
        }
    }

    public static void hideSystemKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showSystemKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    @Override
    public void onPress(int primaryCode) {
        // do nothing
    }

    @Override
    public void onRelease(int primaryCode) {
        //do nothing
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        Editable editable = mEditText.getText();
        int start = mEditText.getText().length();
        playClick(mContext, primaryCode);

        if (primaryCode == Keyboard.KEYCODE_CANCEL) {// cancel
            mEditText.onEditorAction(EditorInfo.IME_ACTION_NONE);
        } else if (primaryCode == Keyboard.KEYCODE_DONE) {// done
            mEditText.onEditorAction(EditorInfo.IME_ACTION_DONE);
        } else if (primaryCode == Keyboard.KEYCODE_DELETE) {// delete
            if (editable != null && editable.length() > 0 && start > 0) {
                editable.delete(start - 1, start);
            }
        } else if (0x0 <= primaryCode && primaryCode <= 0x7f) {
            editable.insert(start, Character.toString((char) primaryCode));
        } else if (primaryCode > 0x7f) {
            Keyboard.Key key = getKeyByKeyCode(primaryCode);
            if (key != null)
                editable.insert(start, key.label);
        }
    }

    private Keyboard.Key getKeyByKeyCode(int primaryCode) {
        if (mKeyboard != null) {
            List<Keyboard.Key> keyList = mKeyboard.getKeys();
            for (Keyboard.Key key : keyList) {
                if (key.codes[0] == primaryCode) {
                    return key;
                }
            }
        }

        return null;
    }

    @Override
    public void onText(CharSequence text) {
        // do nothing
    }

    @Override
    public void swipeLeft() {
        // do nothing
    }

    @Override
    public void swipeRight() {
        // do nothing
    }

    @Override
    public void swipeDown() {
        // do nothing
    }

    @Override
    public void swipeUp() {
        // do nothing
    }
}
