package com.cryptography;

import com.cryptography.PollardsRho.PollardsRho;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.*;

class CryptographyTests {
    @Test
    void pollardsRhoTest() {
        assertThat(PollardsRho.factor(143)).isIn(13, 11);
    }

    @Test
    void pollardsRhoBigIntegerTest() {
        assertThat(PollardsRho.factorBigInteger(new BigInteger("143"))).isIn(new BigInteger("13"), new BigInteger("11"));
    }
}
