package com.pax.pay.ui.def.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import com.paxus.utils.log.Logger;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created by Sim.G on 2017/5/16 16:19
 */
public final class FileUtils {
    private FileUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 删除文件
     *
     * @param file 文件
     * @return {@code true}: 删除成功<br>
     * {@code false}: 删除失败
     */
    public static boolean deleteFile(File file) {
        return file != null && (!file.exists() || file.isFile() && file.delete());
    }


    private static BufferedReader genReaderFromCharsetName(File file, Charset charsetName) throws IOException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));
    }

    /**
     * 指定编码按行读取文件到字符串中
     *
     * @param file        文件
     * @param charsetName 编码格式
     * @return 字符串
     */
    public static String readFile2String(File file, Charset charsetName) {
        if (file == null)
            return null;
        try (BufferedReader reader = genReaderFromCharsetName(file, charsetName)) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\r\n");// windows系统换行为\r\n，Linux为\n
            }
            // 要去除最后的换行符
            return sb.delete(sb.length() - 2, sb.length()).toString();
        } catch (IOException e) {
            Logger.e(e.getMessage());
            return null;
        }
    }

    public static File saveFileFromUri(Context context, Uri uri, String desFile){
        File file = new File(desFile);
        if (file.exists())
            file.delete();
        InputStream inS = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            ContentResolver resolver = context.getContentResolver();
            try {
                inS = ((ContentResolver) resolver).openInputStream(uri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
            bis = new BufferedInputStream(inS);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte[] buf = new byte[1024];
            int len;
            while ((len = inS.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            return file;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
                if (fos != null)
                    fos.close();
                if (bis != null)
                    bis.close();
                if (inS != null) {
                    inS.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

}