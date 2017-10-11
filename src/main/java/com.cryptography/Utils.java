package com.cryptography;

public class Utils {
    public static long getGCD(long a, long b) {
        if (b == 0) {
            return a;
        }

        return getGCD(b, a % b);
    }
}
