/*
 * ============================================================================
 * COPYRIGHT
 *              Pax CORPORATION PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with Pax Corporation and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *      Copyright (C) 2016 - ? Pax Corporation. All rights reserved.
 * Module Date: 2016-12-1
 * Module Author: Steven.W
 * Description:
 *
 * ============================================================================
 */
package com.pax.pay.poslink.ui.demo.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.pax.pay.poslink.ui.demo.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ElectronicSignatureView extends View {

    /**
     * Paint
     */
    private final Paint mGesturePaint = new Paint();
    /**
     * path
     */
    private final Path mPath = new Path();
    /**
     * X  paint start point
     */
    private float mX;
    /**
     * Y paint start point
     */
    private float mY;
    /**
     * Background Canvas
     */
    private Canvas cacheCanvas;
    /**
     * Bitmap catche
     */
    private Bitmap cachebBitmap;
    /**
     * touch screen or not
     */
    private boolean isTouched = false;

    /**
     * Paint width px；
     */
    private int mPaintWidth;

    /**
     * foreground color
     */
    private int mPenColor = Color.BLACK;

    private int mBackColor = Color.TRANSPARENT;

    private int textColor;
    private String text;
    private Rect rect;
    private int padding;
    private int background;
    private boolean isFirst = true;

    private List<float[]> mPathPos = new ArrayList<>();
    private int sampleRate = 3;

    public ElectronicSignatureView(Context context) {
        super(context);
        init(context);
    }

    public ElectronicSignatureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ElectronicSignatureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private static int getTopBorder(Bitmap bp, int width, int height, int blank, int backColor) {
        int[] pixels = new int[width];
        for (int y = 0; y < height; y++) {
            bp.getPixels(pixels, 0, width, 0, y, width, 1);
            for (int pix : pixels) {
                if (pix != backColor) {
                    return y - blank > 0 ? y - blank : 0;
                }
            }
        }
        return 0;
    }

    private static int getBottomBorder(Bitmap bp, int width, int height, int blank, int backColor) {
        int[] pixels = new int[width];
        for (int y = height - 1; y >= 0; y--) {
            bp.getPixels(pixels, 0, width, 0, y, width, 1);
            for (int pix : pixels) {
                if (pix != backColor) {
                    return y + blank > height - 1 ? height - 1 : y + blank;
                }
            }
        }
        return height - 1;
    }

    private static int getLeftBorder(Bitmap bp, int width, int height, int blank, int backColor) {
        int[] pixels = new int[height];
        for (int x = 0; x < width; x++) {
            bp.getPixels(pixels, 0, 1, x, 0, 1, height);
            for (int pix : pixels) {
                if (pix != backColor) {
                    return x - blank > 0 ? x - blank : 0;
                }
            }
        }
        return 0;
    }

    private static int getRightBorder(Bitmap bp, int width, int height, int blank, int backColor) {
        int[] pixels = new int[height];
        for (int x = width - 1; x > 0; x--) {
            bp.getPixels(pixels, 0, 1, x, 0, 1, height);
            for (int pix : pixels) {
                if (pix != backColor) {
                    return x + blank > width - 1 ? width - 1 : x + blank;
                }
            }
        }
        return width - 1;
    }

    /**
     * Progressive scan clear boundary blank。
     *
     * @param bp    the bitmap
     * @param blank How many pixels are left in the margin
     * @return formatted bitmap
     */
    private static Bitmap clearBlank(Bitmap bp, int blank, int backColor) {
        int height = bp.getHeight();
        int width = bp.getWidth();

        int newBlank = blank < 0 ? 0 : blank;

        int top = getTopBorder(bp, width, height, newBlank, backColor);
        int bottom = getBottomBorder(bp, width, height, newBlank, backColor);
        int left = getLeftBorder(bp, width, height, newBlank, backColor);
        int right = getRightBorder(bp, width, height, newBlank, backColor);

        return Bitmap.createBitmap(bp, left, top, right - left, bottom - top);
    }

    public static Bitmap placeBitmapIntoRect(final Bitmap bitmap, final Rect rect, int padding, int background) {
        int newPadding = (padding * 2 >= rect.height() || padding * 2 >= rect.width() || padding < 0) ? 0 : padding;

        int height = rect.height() - newPadding * 2;
        int width = rect.width() - newPadding * 2;
        Matrix matrix = new Matrix();
        float h = (float) height / bitmap.getHeight();
        float w = (float) width / bitmap.getWidth();
        float size = h > w ? w : h;
        if (size > 0.5f) {
            size = 0.5f;
        }
        matrix.postScale(size, size);// get zoom size
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true); // 根据缩放比例获取新的位图

        Bitmap newBitmap = Bitmap.createBitmap(rect.width(), rect.height(), Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        Paint paint = new Paint();
        // set border color
        paint.setColor(background);
        canvas.drawRect(new Rect(0, 0, rect.width(), rect.height()), paint);
        if ((h > w ? w : h) > 0.5f) {
            canvas.drawBitmap(bitmap1, rect.width() / 2 - bitmap1.getWidth() / 2,
                    rect.height() / 2 - bitmap1.getHeight() / 2, null);
        } else {
            canvas.drawBitmap(bitmap1, rect.width() / 2 - bitmap1.getWidth() / 2, newPadding, null);
        }

        bitmap1.recycle();

        return newBitmap;
    }

    public void init(Context context) {
        mPaintWidth = (int) context.getResources().getDimension(R.dimen.paint_width);
        mGesturePaint.setAntiAlias(true);
        mGesturePaint.setStyle(Style.STROKE);
        mGesturePaint.setStrokeWidth(mPaintWidth);
        mGesturePaint.setColor(mPenColor);
        mGesturePaint.setStrokeJoin(Paint.Join.ROUND);
        mGesturePaint.setStrokeCap(Paint.Cap.ROUND);

        this.text = "";
        this.textColor = Color.BLACK;
        this.rect = new Rect(0, 0, 500, 200);
        this.padding = 0;
        this.background = Color.WHITE;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cachebBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
        cacheCanvas = new Canvas(cachebBitmap);
        cacheCanvas.drawColor(mBackColor);
        isTouched = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                isTouched = true;
                touchMove(event);
                break;
            case MotionEvent.ACTION_UP:
                touchUp(event);
                break;
            default:
                break;
        }
        // 更新绘制
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(cachebBitmap, 0, 0, mGesturePaint);
        // connect multi point to draw a picture on canvas
        canvas.drawPath(mPath, mGesturePaint);

        if (isFirst) {
            Paint paint = new Paint();
            paint.setColor(textColor);
            paint.setFlags(Paint.ANTI_ALIAS_FLAG);
            Rect bounds = new Rect();
            paint.getTextBounds(text, 0, text.length(), bounds);
            cacheCanvas.drawText(text, getMeasuredWidth() / 2 - bounds.width() / 2,
                    getMeasuredHeight() / 2 + bounds.height() / 2, paint);
            isFirst = false;
        }
    }

    public void setText(int textColor, String text) {
        this.textColor = textColor;
        this.text = text;
    }

    public void setBitmap(Rect rect, int padding, int background) {
        this.background = background;
        this.rect = rect;
        this.padding = padding;
    }

    // touch down the screen
    private void touchDown(MotionEvent event) {
        // reset path
        mPath.reset();
        float x = event.getX();
        float y = event.getY();

        mX = x;
        mY = y;
        // path move to start point
        mPath.moveTo(x, y);
    }

    // move on screen
    private void touchMove(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();

        final float previousX = mX;
        final float previousY = mY;

        final float dx = Math.abs(x - previousX);
        final float dy = Math.abs(y - previousY);

        // if the distance between two points far away 3, draw bezier curve
        if (dx >= 3 || dy >= 3) {
            // set the working point of bezier curve as half of distance between start point and end point
            float cX = (x + previousX) / 2;
            float cY = (y + previousY) / 2;

            // seconds bezier curve to smooth curve；previousX, previousY as working point，cX, cY as end point
            mPath.quadTo(previousX, previousY, cX, cY);

            // for next start, take the previous end point x\y coordinate as next start point x\y coordinate
            mX = x;
            mY = y;
        }
    }

    // up from screen
    private void touchUp(MotionEvent event) {
        cacheCanvas.drawPath(mPath, mGesturePaint);
        PathMeasure pathMeasure = new PathMeasure(mPath, false);

        for (int i = 0; i < pathMeasure.getLength(); i += sampleRate) {
            float[] pos = new float[]{0.0f, 0.0f};
            pathMeasure.getPosTan(i, pos, null);
            mPathPos.add(pos);
        }
        mPathPos.add(new float[]{-1, -1}); //end flag
        mPath.reset();
    }

    /**
     * clear board
     */
    public void clear() {
        isFirst = true;
        if (cacheCanvas != null) {
            isTouched = false;
            mGesturePaint.setColor(mPenColor);
            cacheCanvas.drawColor(mBackColor, PorterDuff.Mode.CLEAR);
            mGesturePaint.setColor(mPenColor);
            invalidate();
        }
    }

    /**
     * save board
     *
     * @param path save to path
     */

    public void save(String path) throws IOException {
        save(path, false, 0);
    }

    /**
     * 保存bitmap
     *
     * @param clearBlank to clear blank
     * @param blank      board size
     * @return bitmap object
     */
    public Bitmap save(boolean clearBlank, int blank) {
        Bitmap bitmap = cachebBitmap;
        if (clearBlank) {
            bitmap = clearBlank(bitmap, blank, mBackColor);
        }
        bitmap = placeBitmapIntoRect(bitmap, rect, padding, background);

        return bitmap;
    }

    /**
     * save board
     *
     * @param path       save to path
     * @param clearBlank clear blank area or not
     * @param blank      side blank area
     */
    @SuppressLint("WrongThread")
    public void save(String path, boolean clearBlank, int blank) {

        Bitmap bitmap = cachebBitmap;
        // BitmapUtil.createScaledBitmapByHeight(srcBitmap, 300);// compress bitmap
        if (clearBlank) {
            bitmap = clearBlank(bitmap, blank, mBackColor);
        }
        bitmap = placeBitmapIntoRect(bitmap, rect, padding, background);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] buffer = bos.toByteArray();
        if (buffer != null) {
            File file = new File(path);
            if (!file.delete()) {
                Log.w("TAG", file.toString() + "is not existed");
            }

            try (OutputStream outputStream = new FileOutputStream(file)) {
                outputStream.write(buffer);
            } catch (Exception e) {
                Log.w("TAG", e.getMessage());
            }
        }
    }

    /**
     * get bitmap
     *
     * @return the bitmap
     */
    public Bitmap getBitMap() {
        setDrawingCacheEnabled(true);
        buildDrawingCache();
        Bitmap bitmap = getDrawingCache();
        setDrawingCacheEnabled(false);
        return bitmap;
    }

    /**
     * set paint width, default 10px
     *
     * @param paintWidth paint width
     */
    public void setPaintWidth(int paintWidth) {
        this.mPaintWidth = paintWidth > 0 ? paintWidth : 5;
        mGesturePaint.setStrokeWidth(paintWidth);
    }

    public void setBackColor(int backColor) {
        mBackColor = backColor;
    }

    /**
     * set pen color
     *
     * @param penColor pen color
     */
    public void setPenColor(int penColor) {
        this.mPenColor = penColor;
        mGesturePaint.setColor(penColor);
    }

    /**
     * has signature or not
     *
     * @return has signature or not
     */
    public boolean getTouched() {
        return isTouched;
    }

    public List<float[]> getPathPos() {
        return mPathPos;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(int sampleRate) {
        if (sampleRate >= 1)
            this.sampleRate = sampleRate;
    }
}
