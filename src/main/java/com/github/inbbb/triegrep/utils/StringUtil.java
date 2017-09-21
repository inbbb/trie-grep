package com.github.inbbb.triegrep.utils;

public class StringUtil {
    public static String head(String s) throws IndexOutOfBoundsException {
        return s.substring(0, 1);
    }

    public static String tail(String s) throws IndexOutOfBoundsException {
        return s.substring(1, s.length());
    }

    public static String get(String s, int index) throws IndexOutOfBoundsException {
        return s.substring(index, index + 1);
    }

    public static String getCommonPrefix(String a, String b) {
        int min = Math.min(a.length(), b.length());
        for (int i = 0; i < min; i++) {
            if (!StringUtil.get(a, i).equals(StringUtil.get(b, i))) {
                return a.substring(0, i);
            }
        }
        return a.substring(0, min);
    }
}
