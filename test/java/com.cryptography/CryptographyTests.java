package com.cryptography;

import com.cryptography.FermatFactorization.FermatFactorization;
import com.cryptography.PMinusOne.PMinusOne;
import com.cryptography.PollardsRho.PollardsRho;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.*;

class CryptographyTests {
    @Test
    void pollardsRhoTest() {
        assertThat(new PollardsRho().factorLong(143)).isIn(13L, 11L);
    }

    @Test
    void pollardsRhoBigIntegerTest() {
        assertThat(new PollardsRho().factorBigInteger(new BigInteger("143"))).isIn(new BigInteger("13"), new BigInteger("11"));
    }

    @Test
    void fermatFactorizationTest() {
        assertThat(new FermatFactorization().factorLong(143)).isIn(13L, 11L);
        assertThat(new FermatFactorization().factorLong(988027)).isIn(991L, 997L);

    }

    @Test
    void fermatFactorizationBigIntegerTest() {
        assertThat(new FermatFactorization().factorBigInteger(new BigInteger("143"))).isIn(new BigInteger("13"), new BigInteger("11"));
        assertThat(new FermatFactorization().factorBigInteger(new BigInteger("988027"))).isIn(new BigInteger("991"), new BigInteger("997"));
    }

    @Test
    void pollardsPMinusOneLongTest() {
        assertThat(new PMinusOne().factorLong(143)).isIn(13L, 11L);
        assertThat(new PMinusOne().factorLong(988027)).isIn(991L, 997L);
    }

    @Test
    void pollardsPMinusOneBigIntegerTest() {
        assertThat(new PMinusOne().factorBigInteger(new BigInteger("143"))).isIn(new BigInteger("13"), new BigInteger("11"));
        assertThat(new PMinusOne().factorBigInteger(new BigInteger("988027"))).isIn(new BigInteger("991"), new BigInteger("997"));
    }
}
