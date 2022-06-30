package com.paxus.view;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Yanina.Yang on 4/7/2021.
 */
public class WatermarkDrawable extends Drawable {
    private final Paint mPaint;
    /**
     * Watermarked text
     */
    private String mText;
    /**
     * font color, hex form, e.g .:0 xAEAEAEAE
     */
    private int mTextColor = 0xAEAEAEAE;
    /**
     * font size in px
     */
    private float mTextSize = 30;
    /**
     * angle of rotation
     */
    private float mRotation = -25;

    public WatermarkDrawable(String text) {
        mPaint = new Paint();
        mText = text;
    }

    public float getTextSize() {
        return mTextSize;
    }

    public void setTextSize(float mTextSize) {
        this.mTextSize = mTextSize;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
    }

    public float getRotation() {
        return mRotation;
    }

    public void setRotation(float mRotation) {
        this.mRotation = mRotation;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int width = getBounds().right;
        int height = getBounds().bottom;
        int diagonal = (int) Math.sqrt(width * width + height * height);

        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);
        String[] texts = mText.split("\n");
        float textWidth = 0;
        for(String line: texts){
            float w = mPaint.measureText(line);
            if(textWidth < w){
                textWidth = w;
            }
        }
        canvas.drawColor(0x00000000);
        canvas.rotate(mRotation);

        int index = 0;
        float fromX;
        // Take the length of the diagonal to make the height, so that the vertical and horizontal screen can be covered with watermarks
        for (int positionY = diagonal / 10; positionY <= diagonal; positionY += diagonal / 10) {
            // X axis starting point of upper and lower rows is different, staggered display
            fromX = -width + (index++ % 2) * textWidth + textWidth/2;
            for (float positionX = fromX; positionX < width; positionX += textWidth * 2) {
                for(int i = 0;i<texts.length;i++){
                    canvas.drawText(texts[i], positionX,positionY + i * mTextSize,mPaint);
                }
            }
        }

        canvas.save();
        canvas.restore();
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

}
