package com.cryptography;

import java.math.BigInteger;

public class Utils {
    public static long getGCD(long a, long b) {
        if (b == 0) {
            return a;
        }

        return getGCD(b, a % b);
    }

    public static BigInteger bigIntegerSqrt(BigInteger n) {
        BigInteger r = BigInteger.ZERO;
        BigInteger m = r.setBit(2 * n.bitLength());
        BigInteger nr;

        do {
            nr = r.add(m);
            if (nr.compareTo(n) != 1) {
                n = n.subtract(nr);
                r = nr.add(m);
            }
            r = r.shiftRight(1);
            m = m.shiftRight(2);
        } while (m.bitCount() != 0);

        return r;
    }
}
