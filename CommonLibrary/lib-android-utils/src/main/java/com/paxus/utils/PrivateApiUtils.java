package com.paxus.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Kim.L 2021/7/16
 */
public class PrivateApiUtils {

    public static String getSystemProperty(String key) {
        String result = null;
        try {
            Class<?> spCls = Class.forName("android.os.SystemProperties");
            Class<?>[] typeArgs = new Class[2];
            typeArgs[0] = String.class;
            typeArgs[1] = String.class;
            Constructor<?> spcs = spCls.getConstructor();
            Object[] valueArgs = new Object[2];
            valueArgs[0] = key;
            valueArgs[1] = null;
            Object sp = spcs.newInstance();
            Method method = spCls.getMethod("get", typeArgs);
            result = (String) method.invoke(sp, valueArgs);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            //Logger.e(e.getMessage());
        }
        return result;
    }
}