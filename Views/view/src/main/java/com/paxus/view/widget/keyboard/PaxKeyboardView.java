package com.paxus.view.widget.keyboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.text.Editable;
import android.text.Selection;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.paxus.view.R;
import com.paxus.view.utils.ViewUtils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

import static android.content.Context.AUDIO_SERVICE;

/**
 * Created by Yanina.Yang on 2/26/2021.
 */
public class PaxKeyboardView extends KeyboardView implements KeyboardView.OnKeyboardActionListener{
    private final Context mContext;
    private TextPaint numberPaint, textPaint;
    private int vSpecLength, hSpecLength;
    private EditText[] mEditTexts;
    private Keyboard keyBoard;


    public PaxKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initialize();
    }
    public static final int TYPE_MASK_CLASS = 0x0000000f;
    public static final int TYPE_CLASS_TEXT = 0x00000001;
    public static final int TYPE_CLASS_NUMBER = 0x00000002;

    public static final int TYPE_MASK_FLAGS = 0x00fff000;
    public static final int TYPE_TEXT_FLAG_SYMBOLS = 0x00001000;

    private int mInputType = TYPE_CLASS_TEXT;

    public void setInputType(int inputType) {
        this.mInputType = inputType;
        initKeyBoard();
        setOnKeyboardActionListener(this);
    }

    public Keyboard getKeyBoard() {
        return keyBoard;
    }

    public void bindEditText(EditText[] editTexts){
        this.mEditTexts = editTexts;
        if(this.mEditTexts != null){
            try {
                Class<EditText> cls = EditText.class;
                Method method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                if (method != null) {
                    method.setAccessible(true);
                    for(EditText editText:editTexts) {
                        method.invoke(editText, false);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            for(EditText editText:editTexts){
                editText.setOnFocusChangeListener((v, hasFocus) -> {
                    if(hasFocus && getFocusedEditText() != null){
                        showKeyboard();
                    }
                });
            }
        }
    }
    private void showKeyboard(){
        this.setVisibility(VISIBLE);
        if(this.mEditTexts != null){
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            if(imm != null) {
                for (EditText editText : mEditTexts) {
                    if(imm.isActive()) {
                        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    }

                }
            }
        }

    }

    private void initialize() {
        vSpecLength = ViewUtils.dp2px(mContext, 3);
        hSpecLength = ViewUtils.dp2px(mContext, 3);

        numberPaint = new TextPaint();
        numberPaint.setColor(Color.BLACK);
        numberPaint.setAntiAlias(true);
        numberPaint.setTextAlign(Paint.Align.CENTER);
        numberPaint.setTextSize(ViewUtils.dp2px(mContext, 32));
        numberPaint.setStrokeWidth(5);

        textPaint = new TextPaint();
        textPaint.setColor(Color.GRAY);
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(ViewUtils.dp2px(mContext, 20));
        textPaint.setStrokeWidth(3);

        setPreviewEnabled(false);

    }

    private Drawable getDrawableFromRes(int resId) {
        return getContext().getResources().getDrawable(resId);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(getKeyboard() == null){
            setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        List<Keyboard.Key> keys = getKeyboard().getKeys();
        Keyboard.Key lastKey = keys.get(keys.size()-1);
        int w = lastKey.x+lastKey.width + hSpecLength;
        int h = lastKey.y+lastKey.height+ vSpecLength;
        setMeasuredDimension(w,h);

    }

    private void initKeyBoard() {
        //Keyboard keyBoard;
        int inputClass = mInputType & TYPE_MASK_CLASS;
        int flag = mInputType & TYPE_MASK_FLAGS;
        if(inputClass == TYPE_CLASS_TEXT){
            if(flag == TYPE_TEXT_FLAG_SYMBOLS){
                keyBoard = new Keyboard(mContext, R.xml.pax_special_character_keyboard, 0);
            }else {
                keyBoard = new Keyboard(mContext, R.xml.pax_alpha_keyboard, 0);
            }
        }else if(inputClass == TYPE_CLASS_NUMBER){
            keyBoard = new Keyboard(mContext, R.xml.pax_other_keyboard_num, 0);
        }else {
            return;
        }

        setKeyboard(keyBoard);

        List<Keyboard.Key> keys = keyBoard.getKeys();

        for(Keyboard.Key key:keys){
            key.x += hSpecLength/2;
            key.y += vSpecLength/2;
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
//        super.onDraw(canvas);

        if(getKeyboard() == null){
            return;
        }
        List<Keyboard.Key> keys = getKeyboard().getKeys();
        if (keys != null) {
            for (Keyboard.Key key : keys) {
                drawKey(key, canvas);
            }
        }
    }

    private void drawKey(Keyboard.Key key, Canvas canvas) {
        int backgroundId = R.drawable.pax_selection_button_number;
        int iconId = 0;

        int inputClass = mInputType & TYPE_MASK_CLASS;
        if (inputClass == TYPE_CLASS_NUMBER) {
            backgroundId = R.drawable.pax_selection_button_line_number;
        }

        int code = key.codes[0];
        if(code == Keyboard.KEYCODE_DELETE){
            if (inputClass == TYPE_CLASS_NUMBER ) {
                backgroundId = R.drawable.pax_selection_button_special;
                iconId = R.mipmap.pax_deletenum;
            } else {
                backgroundId = R.drawable.pax_selection_button_cancel;
            }
        }else if(code == Keyboard.KEYCODE_CANCEL){
            backgroundId = R.drawable.pax_selection_button_cancel;
        }else if(code == Keyboard.KEYCODE_DONE){
            backgroundId = R.drawable.pax_selection_button_confirm;
        }else if(code == 57418){
            backgroundId = R.drawable.pax_selection_button_special;
            iconId = R.mipmap.pax_hideboard;
        }

        String keyStr = "";
        if (key.label != null)
                keyStr = key.label.toString();
        drawKeyBackground(backgroundId, keyStr, canvas, key, iconId);
    }

    @Override
    public void setOnKeyboardActionListener(OnKeyboardActionListener listener) {
        OnKeyboardActionListener keyboardActionListener = new OnKeyboardActionListener() {
            @Override
            public void onPress(int primaryCode) {
                listener.onPress(primaryCode);
            }

            @Override
            public void onRelease(int primaryCode) {
                listener.onRelease(primaryCode);
            }

            @Override
            public void onKey(int primaryCode, int[] keyCodes) {
                if (primaryCode == 57425) {
                    setInputType(mInputType | TYPE_TEXT_FLAG_SYMBOLS);
                } else if (primaryCode == 57423) {
                    setInputType(mInputType & (~(TYPE_TEXT_FLAG_SYMBOLS)));
                } else if (primaryCode == Keyboard.KEYCODE_SHIFT) {
                    shiftEnglish();
                } else
                    listener.onKey(primaryCode, keyCodes);
            }

            @Override
            public void onText(CharSequence text) {
                listener.onText(text);
            }

            @Override
            public void swipeLeft() {
                listener.swipeLeft();
            }

            @Override
            public void swipeRight() {
                listener.swipeRight();
            }

            @Override
            public void swipeDown() {
                listener.swipeDown();
            }

            @Override
            public void swipeUp() {
                listener.swipeUp();
            }
        };
        super.setOnKeyboardActionListener(keyboardActionListener);
    }

    private void drawKeyBackground(int drawableId, String keyValue, Canvas canvas, Keyboard.Key key, int iconId) {
        key.label = keyValue;
        Drawable backgroundDrawable = getDrawableFromRes(drawableId);
        int[] drawableState = key.getCurrentDrawableState();
        if (key.codes[0] != 0) {
            backgroundDrawable.setState(drawableState);
        }

        backgroundDrawable.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
        backgroundDrawable.draw(canvas);
        if (key.icon != null) {
            Drawable icon = key.icon;
            icon.setBounds(key.x + key.width / 4, key.y + key.height / 4,  key.x + key.width - key.width / 4, key.y + key.height - key.height / 4);
            icon.draw(canvas);
        }

        //draw text
        Paint.FontMetrics fontMetrics = numberPaint.getFontMetrics();
        float fontHeight = fontMetrics.bottom - fontMetrics.top;
        float textBaseY = key.y + key.height - (key.height - fontHeight) / 2 - fontMetrics.bottom;

//        if (keyValue != null && keyValue.matches("^[0-9]+$")) {
//            canvas.drawText(keyValue, key.x + key.width / 2, textBaseY, numberPaint);
//        } else {
            if (drawableId == R.drawable.pax_selection_button_confirm) {
                textPaint.setColor(Color.WHITE);
            } else {
                textPaint.setColor(Color.DKGRAY);
            }

            if (iconId == 0) {
                fontMetrics = textPaint.getFontMetrics();
                fontHeight = fontMetrics.bottom - fontMetrics.top;
                textBaseY = key.y + key.height - (key.height - fontHeight) / 2 - fontMetrics.bottom;
                canvas.drawText(keyValue, key.x + key.width / 2, textBaseY, textPaint);
            } else {
                Bitmap m = BitmapFactory.decodeResource(getResources(), iconId);
                if (m.getHeight() > key.height || m.getWidth() > key.width) {
                    BigDecimal mWidth = new BigDecimal(m.getWidth());
                    BigDecimal keyWidth = new BigDecimal(key.width);
                    BigDecimal mHeight = new BigDecimal(m.getHeight());
                    BigDecimal keyHeight = new BigDecimal(key.height);
                    float scale = Math.min(keyWidth.divide(mWidth, 2, BigDecimal.ROUND_DOWN).floatValue(),
                            keyHeight.divide(mHeight, 2, BigDecimal.ROUND_DOWN).floatValue());
                    Matrix matrix = new Matrix();
                    matrix.postScale(scale, scale);
                    m = Bitmap.createBitmap(m, 0, 0, m.getWidth(),
                            m.getHeight(), matrix, true);
                }

                canvas.drawBitmap(m, key.x + key.width / 2 - m.getWidth() / 2, key.y + (key.height - m.getHeight()) / 2, textPaint);
            }
//        }
    }

    private void shiftEnglish() {
        List<Keyboard.Key> keyList = getKeyboard().getKeys();
        for (Keyboard.Key key : keyList) {
            if(key.codes.length == 1){
                int code = key.codes[0];
                if(code>= 65 && code<=90){//A,B,...,Z
                    key.label = key.label.toString().toLowerCase();
                    key.codes[0] = code+32;
                }else if(code >= 97 && code <= 122){//a,b,...,Z
                    key.label = key.label.toString().toUpperCase();
                    key.codes[0] = code-32;
                }

            }
        }
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        playClick(getContext(), primaryCode);

        EditText editText = getFocusedEditText();
        if(editText != null){
            Editable editable = editText.getText();
            int start = editText.getText().length();
            if (primaryCode == Keyboard.KEYCODE_CANCEL) {// cancel
                editText.onEditorAction(EditorInfo.IME_ACTION_NONE);
            } else if (primaryCode == Keyboard.KEYCODE_DONE) {// done
                editText.onEditorAction(EditorInfo.IME_ACTION_DONE);
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
    }

    private Keyboard.Key getKeyByKeyCode(int primaryCode) {
        if (getKeyboard() != null) {
            List<Keyboard.Key> keyList = getKeyboard().getKeys();
            for (Keyboard.Key key : keyList) {
                if (key.codes[0] == primaryCode) {
                    return key;
                }
            }
        }

        return null;
    }

    private void playClick(Context context, int keyCode) {
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

    @Override
    public void onText(CharSequence text) {
        EditText ed = getFocusedEditText();
        if(ed != null) {
            Editable editable = ed.getText();
            int start = ed.getSelectionStart();
            String temp = editable.subSequence(0, start) + text.toString()
                    + editable.subSequence(start, editable.length());
            ed.setText(temp);
            Selection.setSelection(editable, start + 1);
        }
    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

    private EditText getFocusedEditText(){
        if(mEditTexts != null){
            for(EditText et:mEditTexts){
                if(et.hasFocus()){
                    return et;
                }
            }
        }
        return null;
    }
}
