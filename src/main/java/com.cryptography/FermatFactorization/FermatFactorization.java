package com.cryptography.FermatFactorization;

import com.cryptography.Factorizer;

import java.math.BigInteger;

public class FermatFactorization implements Factorizer {

    @Override
    public long factorLong(long n) {
        long result;
        long a = (long) Math.ceil(Math.sqrt(n));
        long b2 = (a * a) - n;
        long b = (long) Math.sqrt(b2);
        long aMinusB = a - b;
        long bound = 30;
        long prevAMinusB = a - b + bound;

        while (((long) Math.pow(Math.sqrt(b2), 2)) != b2 && (prevAMinusB - aMinusB) < bound) {
            a++;
            b2 = (a * a) - n;
            b = (long) Math.sqrt(b2);
            prevAMinusB = aMinusB;
            aMinusB = a - b;
        }

        if (((long) Math.pow(Math.sqrt(b2), 2)) == b2) {
            result = aMinusB;
        } else { // Trial Division
            boolean finished = false;
            long p = aMinusB + 2;
            if (p % 2 == 0) {
                p++;
            }
            while (!finished) {
                p -= 2;
                if (n % p == 0) {
                    finished = true;
                }
            }

            result = p;
        }

        return result;

    }

    @Override
    public BigInteger factorBigInteger(BigInteger bigIntegerToFactor) {
        return null;
    }
}
