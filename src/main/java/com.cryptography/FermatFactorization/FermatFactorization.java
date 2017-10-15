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
        long bound = 36;
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
    public BigInteger factorBigInteger(BigInteger n) {
        BigInteger result = null;
        BigInteger a = bigIntegerSqrt(n);
        BigInteger b2 = a.multiply(a).subtract(n);
        BigInteger b = bigIntegerSqrt(b2);
        BigInteger aMinusB = a.subtract(b);
        BigInteger bound = new BigInteger("30");
        BigInteger prevAMinusB = aMinusB.add(bound);

        while (!bigIntegerSqrt(b2).pow(2).equals(b2) && prevAMinusB.subtract(aMinusB).compareTo(bound) > -1) {
            a = a.add(BigInteger.ONE);
            b2 = a.multiply(a).subtract(n);
            b = bigIntegerSqrt(b2);
            prevAMinusB = aMinusB;
            aMinusB = a.subtract(b);

            if (bigIntegerSqrt(b2).pow(2).equals(b2)) {
                result = aMinusB;
            } else {
                boolean finished = false;
                BigInteger bigTwo = new BigInteger("2");
                BigInteger p = aMinusB.add(bigTwo);
                if (p.remainder(bigTwo).intValue() == 0) {
                    p = p.add(BigInteger.ONE);
                }
                while (!finished) {
                    p = p.subtract(bigTwo);
                    if (n.remainder(p).equals(BigInteger.ZERO)) {
                        finished = true;
                    }
                }
                result = p;
            }
        }

        return result;
    }

    private static BigInteger bigIntegerSqrt(BigInteger n) {
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
