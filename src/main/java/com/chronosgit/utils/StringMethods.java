package com.chronosgit.utils;

public class StringMethods {
    public static boolean contains(String[] array, String target) {
        for (String s : array) {
            if (s.equals(target)) {
                return true;
            }
        }

        return false;
    }
}
