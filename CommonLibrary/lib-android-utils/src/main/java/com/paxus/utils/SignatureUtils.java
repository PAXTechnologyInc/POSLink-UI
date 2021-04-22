package com.paxus.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by yanina on 5/29/2019.
 */

public class SignatureUtils {

    public static Bitmap generateBitmap(short[] signData) {
        if (signData == null || signData.length <= 0) {
            return null;
        }

        int minX = signData[0];
        int maxX = signData[0];
        int minY = signData[1];
        int maxY = signData[1];
        int margin = 10;

        for (int i = 0; i < signData.length; i = i + 2) {
            short x = signData[i];
            short y = signData[i + 1];

            if (x == -1 || y == -1) {
                continue;
            }
            if (x < minX) {
                minX = x;
            }
            if (x > maxX) {
                maxX = x;
            }
            if (y > maxY) {
                maxY = y;
            }
            if (y < minY) {
                minY = y;
            }
        }

        int newWidth = maxX - minX + 1 + margin * 2;
        int newHeight = maxY - minY + 1 + margin * 2;


        //Fix APMN-13, Scale the bitmap to let it match receipt
        int scaledHeight = 200; //Fixed height of signature rect
        float scale = scaledHeight * 1.0f / newHeight;
        int scaledWidth = (int) (newWidth * scale);

        Bitmap bmp = Bitmap.createBitmap(scaledWidth, scaledHeight, Bitmap.Config.RGB_565);
        //set background white
        bmp.eraseColor(Color.WHITE);

        //connect dots
        Paint blackPen = new Paint();
        blackPen.setColor(Color.BLACK);
        blackPen.setStrokeWidth(2);
        blackPen.setAntiAlias(true);
        blackPen.setStyle(Paint.Style.STROKE);
        blackPen.setStrokeJoin(Paint.Join.ROUND);
        blackPen.setStrokeCap(Paint.Cap.ROUND);

        Canvas canvas = new Canvas(bmp);
        for (int i = 0; i < signData.length - 2; i = i + 2) {
            int x1 = signData[i];
            int y1 = signData[i + 1];
            int x2 = signData[i + 2];
            int y2 = signData[i + 3];

            if (x1 == -1 || y1 == -1) {
                continue;
            }
            if (x2 == -1 || y2 == -1) {
                continue;
            }

            x1 = (int) (scale * (x1 + margin - minX));
            y1 = (int) (scale * (y1 + margin - minY));
            x2 = (int) (scale * (x2 + margin - minX));
            y2 = (int) (scale * (y2 + margin - minY));

            canvas.drawLine(x1, y1, x2, y2, blackPen);
        }
        return bmp;
    }

    public static String generateSignVector(short[] signData) {
        if (signData == null || signData.length <= 0) {
            return "";
        }
        String separator = "^";
        String terminator = "~";
        String penUpPoint = "0,65535";

        StringBuilder builder = new StringBuilder();
        //Yanina: Per Spec
        // where 0,65535 signals that a pointer, such as stylus or finger, was lifted from capture pad
        // It should be a pen-up event

        int count = 0;
        for (int i = 0; i < signData.length; i = i + 2) {
            short x = signData[i];
            short y = signData[i + 1];

            if (x == -1 && y == -1) {
                count = count + penUpPoint.length() + separator.length();
            } else {
                count = count + Integer.toString(x).length() + 1 + Integer.toString(y).length() + 1 + separator.length();
            }
        }

        int var = 0;
        if (count > 6144) { //Fix PETE-616
            var = (int) Math.ceil(count * 1.0f / 6144);
        }

        int j = 0;
        for (int i = 0; i < signData.length; i = i + 2) {
            short x = signData[i];
            short y = signData[i + 1];

            if (x == -1 && y == -1) {
                builder.append(penUpPoint).append(separator);
                j = 0;
            } else {
                if (var == 0 || j % var == 0) {
                    builder.append(x).append(",").append(y).append(separator);
                }
                j++;
            }
        }

        builder.append(terminator);
        return builder.toString();
    }

    public static short[] convertVectorDataToSignData(String vectorData) {
        if (vectorData == null || vectorData.isEmpty()) {
            return new short[0];
        }

        String[] splits = vectorData.split("\\^");
        int length = 1;
        if (splits.length > 1) {
            length = splits.length - 1;
        }
        short[] data = new short[length * 2];
        for (int i = 0; i < length; i++) {
            if ("0,65535".equals(splits[i])) {
                data[i * 2] = -1;
                data[i * 2 + 1] = -1;
            } else if ("~".equals(splits[i])) {
                break;
            } else {
                String[] parts = splits[i].split(",");
                if (parts.length > 0) {
                    try {
                        data[i * 2] = (short) Integer.parseInt(parts[0]);
                    } catch (IllegalArgumentException e) {
                        //avoid crash when the sigdata may be invalid.
                        data[i * 2] = 0;
                    }
                } else {
                    data[i * 2] = 0;
                }
                if (parts.length > 1) {
                    try {
                        data[i * 2 + 1] = (short) Integer.parseInt(parts[1]);
                    } catch (IllegalArgumentException e) {
                        //avoid crash when the sigdata may be invalid.
                        data[i * 2] = 0;
                    }
                } else {
                    data[i * 2 + 1] = 0;
                }
            }
        }
        return data;
    }

    public static Bitmap generateBitmapFromVectorData(String vectData) {
        if (vectData == null || vectData.isEmpty()) {
            return null;
        }

        return generateBitmap(convertVectorDataToSignData(vectData));
    }
}
