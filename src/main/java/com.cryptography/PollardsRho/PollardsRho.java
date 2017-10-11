package com.cryptography.PollardsRho;

import com.cryptography.Utils;

import java.math.BigInteger;

public class PollardsRho {
    private static int n;
    private static BigInteger bigIntegerN;

    public static int factor(int numberToFactor) {
        n = numberToFactor;
        int a = 2, b = 2, p = 1;

        while (p == 1) {
            a = f(a);
            b = f(f(b));
            p = Utils.getGCD(Math.abs(b - a), n);
            if (p > 1) {
                return p;
            }
        }

        return -1;
    }

    private static int f(int a) {
        return ((int) Math.pow(a, 2) + 1) % n;
    }

    public static BigInteger factorBigInteger(BigInteger bigIntegerToFactor) {
        bigIntegerN = bigIntegerToFactor;
        BigInteger a = new BigInteger("2"), b = new BigInteger("2"), p = BigInteger.ONE;

        while (p.equals(BigInteger.ONE)) {
            a = bigIntegerF(a);
            b = bigIntegerF(bigIntegerF(b));
            p = b.subtract(a).gcd(bigIntegerN); // gcd() uses absolute values
            if (!p.equals(BigInteger.ONE)) {
                return p;
            }
        }

        return BigInteger.ZERO;
    }

    private static BigInteger bigIntegerF(BigInteger a) {
        return a.pow(2).add(BigInteger.ONE).mod(bigIntegerN);
    }
}
