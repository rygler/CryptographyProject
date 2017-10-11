package com.cryptography;

/**
 * Created by Raphael on 10/11/2017.
 */
public class Utils {
    public static int getGCD(int a, int b) {
        if (b == 0) {
            return a;
        }

        return getGCD(b, a % b);
    }
}
