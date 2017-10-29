package com.cryptography.PMinusOne;

import com.cryptography.Factorizer;
import com.cryptography.Utils;
import com.google.common.math.LongMath;

import java.math.BigInteger;

public class PMinusOne implements Factorizer {
    @Override
    public long factorLong(long longToFactor) {
        long a = 2;
        int k = 2;
        long aToTheK = (long) Math.pow(a, LongMath.factorial(k));
        boolean finished = false;
        long gcd = -1;
        int bound = 5000;

        while (!finished ) {
            gcd = Utils.getGCD((aToTheK - 1) % longToFactor, longToFactor);
            System.out.println("GCD: " + gcd + " , k: " + k);
            finished = gcd != 1 || gcd == longToFactor || k == bound;
            aToTheK = (long) Math.pow(aToTheK, ++k);
        }

        return gcd;
    }

    @Override
    public BigInteger factorBigInteger(BigInteger bigIntegerToFactor) {
        BigInteger a = new BigInteger("2");
        int k = 2;
        int bound = 999999;

        BigInteger aToTheK = a.pow(k);
        BigInteger gcd = BigInteger.ZERO;
        boolean finished = false;

        while (!finished) {
            gcd = bigIntegerToFactor.gcd((aToTheK.subtract(BigInteger.ONE).mod(bigIntegerToFactor)));
            finished = !gcd.equals(BigInteger.ONE) || gcd.equals(bigIntegerToFactor) || k == bound;
            aToTheK = aToTheK.pow(++k);
        }

        return gcd;
    }
}
