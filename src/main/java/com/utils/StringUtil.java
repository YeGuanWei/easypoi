package com.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 字符串辅助类
 *
 * @author YeGuanWei
 */
public class StringUtil {

    private static final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String toString(Object object) {
        if (object != null) {
            return object.toString();
        } else {
            return "";
        }
    }

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !str.isEmpty();
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public static String urlEncode(String str, String charset) throws UnsupportedEncodingException {
        return !isEmpty(str) ? URLEncoder.encode(str, charset).replaceAll("\\+", "%20") : str;
    }

    public static String utf8UrlEncode(String str) {
        try {
            return urlEncode(str, "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            return str;
        }
    }

    public static String toHex(byte[] byteData) {
        return toHex(byteData, 0, byteData.length);
    }

    public static String toHex(String data, String encode) {
        try {
            return toHex(data.getBytes(encode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String toHex(byte[] byteData, int offset, int len) {
        char[] buf = new char[len * 2];
        int k = 0;
        for (int i = offset; i < len; i++) {
            buf[k++] = digits[(byteData[i] & 255) >> 4];
            buf[k++] = digits[byteData[i] & 15];
        }

        return new String(buf);
    }

    public static int countOccurrences(String text, String target) {
        if (text == null || target == null || target.isEmpty()) {
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(target, index)) != -1) {
            count++;
            index += target.length(); // Move past the last found occurrence
        }
        return count;
    }

}
