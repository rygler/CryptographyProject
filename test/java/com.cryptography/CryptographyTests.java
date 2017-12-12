package com.cryptography;

import com.cryptography.FermatFactorization.FermatFactorization;
import com.cryptography.PMinusOne.PMinusOne;
import com.cryptography.PollardsRho.PollardsRho;
import com.cryptography.QuadraticSieve.QuadraticSieve;
import com.cryptography.QuadraticSieve.QuadraticSieveDraft;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

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
//        assertThat(new PMinusOne().factorLong(143)).isIn(13L, 11L);
        assertThat(new PMinusOne().factorLong(1043)).isIn(7L, 149L);

//        assertThat(new PMinusOne().factorLong(988027)).isIn(991L, 997L);
    }

    @Test
    void pollardsPMinusOneBigIntegerTest() {
        assertThat(new PMinusOne().factorBigInteger(new BigInteger("143"))).isIn(new BigInteger("13"), new BigInteger("11"));
        assertThat(new PMinusOne().factorBigInteger(new BigInteger("988027"))).isIn(new BigInteger("991"), new BigInteger("997"));
    }

    @Test
    void primeFactorizationTest() {
//        assertThat(new QuadraticSieveDraft().getPrimeFactorization(78))
//        System.out.println(new QuadraticSieveDraft().getPrimeFactorization(new BigInteger("4581")).toString());
//        int[] ints = new int[]{318, 921, 1526, 2133, 2742, 3353, 3966, 4581, 5198, 5817, 6438, 7061, 7686, 8313, 8942, 9573, 10206, 10841, 11478, 12117, 12758, 13401, 14046, 14693, 15342, 15993, 16646, 17301, 17958, 18617, 19278, 19941, 20606, 21273, 21942, 22613, 23286, 23961, 24638, 25317, 25998, 26681, 27366, 28053, 28742, 29433, 30126, 30821, 31518, 32217, 32918, 33621, 34326, 35033, 35742, 36453, 37166, 37881, 38598, 39317};
//
//        List<BigInteger> numbers = new ArrayList<>();
//        for (int anInt : ints) {
//            numbers.add(new BigInteger(String.valueOf(anInt)));
//        }
//        System.out.println(new QuadraticSieveDraft().findBSmoothNumbers(numbers, 43));

//        int[] bSmoothInts = new int[]{19278,19941,30821,35742 };
//        List<BigInteger> bSmoothBigIntegers = new ArrayList<>();
//        for (int anInt : bSmoothInts) {
//            bSmoothBigIntegers.add(new BigInteger(String.valueOf(anInt)));
//        }
//
//        int[] originInts = new int[]{331, 332, 348, 355};
//        List<BigInteger> originBigIntegers = new ArrayList<>();
//        for (int anInt : originInts) {
//            originBigIntegers.add(new BigInteger(String.valueOf(anInt)));
//        }
//
//        System.out.println(new QuadraticSieveDraft().findGcdOfSquareAndN(bSmoothBigIntegers, originBigIntegers, new BigInteger("90283")));

//        int[] ints = new int[] {10, 24, 35, 52, 54, 78};
//        List<BigInteger> bigIntegers = new ArrayList<>();
//        for (int anInt : ints) {
//            bigIntegers.add(new BigInteger(String.valueOf(anInt)));
//        }
//
//        QuadraticSieveDraft q = new QuadraticSieveDraft();
//
//
//        BitSet[] matrix = q.getBitMatrix(bigIntegers, 13);
//        BitSet[] transposedMatrix = q.transposeMatrix(matrix);
//        BitSet[] rrefMatrix = q.rref(transposedMatrix);
//
//        System.out.println(Arrays.toString(matrix));
//        System.out.println(Arrays.toString(transposedMatrix));
//        System.out.println(Arrays.toString(rrefMatrix));
//
//        BitSet aSolution = new BitSet();
//        aSolution.set(4);
//
//        aSolution = q.solveSLE(rrefMatrix, aSolution);
//        System.out.println(aSolution);
//        System.out.println(q.findSolutionVectors(rrefMatrix));
        QuadraticSieve.factor(new BigInteger("90283"));

    }

}
