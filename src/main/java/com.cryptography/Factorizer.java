package com.cryptography;

import java.math.BigInteger;

public interface Factorizer {
    long factorLong(long longToFactor);
    BigInteger factorBigInteger(BigInteger bigIntegerToFactor);
}
