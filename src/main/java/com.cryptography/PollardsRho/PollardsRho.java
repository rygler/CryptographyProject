package com.cryptography.PollardsRho;

import com.cryptography.Factorizer;
import com.cryptography.Utils;

import java.math.BigInteger;

public class PollardsRho implements Factorizer{
    private long n;
    private BigInteger bigIntegerN;

    @Override
    public long factorLong(long longToFactor) {
        n = longToFactor;
        long a = 2, b = 2, p = 1;

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

    private long f(long a) {
        return ((long) Math.pow(a, 2) + 1) % n;
    }

    @Override
    public BigInteger factorBigInteger(BigInteger bigIntegerToFactor) {
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

    private BigInteger bigIntegerF(BigInteger a) {
        return a.pow(2).add(BigInteger.ONE).mod(bigIntegerN);
    }
}
