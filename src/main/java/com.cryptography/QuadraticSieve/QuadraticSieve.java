package com.cryptography.QuadraticSieve;

import com.cryptography.Utils;
import flanagan.math.Matrix;
import org.ejml.simple.SimpleMatrix;

import java.math.BigInteger;
import java.util.*;

public class QuadraticSieve {
    BitSet[] rows;
    BitSet[] solutionRows;
    List<BitSet> matrix;
    public BigInteger factor(BigInteger n) {
//        BigInteger a =
        return null;
    }

    public List<BigInteger> getPrimeFactorization(BigInteger n) {
        List<BigInteger> factors = new ArrayList<BigInteger>();
        for (BigInteger i = new BigInteger("2"); i.compareTo(n.divide(i)) < 0; i = i.add(BigInteger.ONE)) {
            while (n.mod(i).equals(BigInteger.ZERO)) {
                factors.add(i);
                n = n.divide(i);
            }
        }
        if (n.compareTo(BigInteger.ONE) > 0) {
            factors.add(n);
        }
        return factors;
    }


    public List<BigInteger> findSubsetsThatProducesSquare(List<BigInteger> bSmoothNumbers, int b) {
        BitSet[] matrix = getBitMatrix(bSmoothNumbers, b);
        solve(new ArrayList<BitSet>(Arrays.asList(matrix)));
//        BitSet[] A = transposeMatrix(matrix);


        return null;
    }

//    private BitSet transposeMatrix(BitSet[] matrix) {
//        BitSet[] newMatrix = new BitSet[matrix.length];
//        for (int row = 0; row < matrix.length; row++) {
//            for (int col = 0; col < numOfCols; col++) {
//                newArray[row][col] = matrix[col][row];
//            }
//        }
//        return newArray;
//    }

    public ArrayList<ArrayList<BitSet>> solve(List<BitSet> matrix) {
        this.matrix = matrix;
        HashMap<Integer, Object> map = new HashMap<>();
        rows = new BitSet[matrix.size()];
        solutionRows = new BitSet[matrix.size()];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = matrix.get(i);
            solutionRows[i] = new BitSet();
            solutionRows[i].set(i);
        }


        for (int column = 0; column < rows.length; column++) {
            int selectedRow = -1;
            for (int row = 0; row < rows.length; row++) {
                if (!rows[row].get(column)) {
                    continue;
                }
                if (selectedRow == -1 && !map.containsKey(row)) {
                    selectedRow = row;
                    map.put(row, row);
                    continue;
                }
                if (selectedRow != -1) {
                    xor(row, selectedRow);
                }
            }
            for (int row = 0; row < selectedRow; row++) {
                if (!rows[row].get(column)) {
                    continue;
                }
                xor(row, selectedRow);
            }
        }

        ArrayList<ArrayList<BitSet>> solutions = new ArrayList<>();
        for (int i = 0; i < rows.length; i++) {
            if (rows[i].isEmpty()) {
                solutions.add(createSolution(i));
            }
        }
        System.out.println(solutions);
        return solutions;
    }

    private ArrayList<BitSet> createSolution(int row) {
        ArrayList<BitSet> solution = new ArrayList<>();
        for (int column = 0; column < rows.length; column++) {
            if (solutionRows[row].get(column)) {
                solution.add(matrix.get(column));
            }
        }
        return solution;
    }

    private void xor(int rowA, int rowB) {
        rows[rowA].xor(rows[rowB]);
        solutionRows[rowA].xor(solutionRows[rowB]);
    }

    private BitSet[] getBitMatrix(List<BigInteger> bSmoothNumbers, int b) {
        List<Integer> primes = findPrimesUpTo(b);
        int numOfColumns = primes.size();
        int numOfRows = bSmoothNumbers.size();
        BitSet[] matrix = new BitSet[bSmoothNumbers.size()];

        for (int row = 0; row < numOfRows; row++) {
            List<BigInteger> primeFactors = getPrimeFactorization(bSmoothNumbers.get(row));
            matrix[row] = new BitSet();
            for (int col = 0; col < numOfColumns; col++) {
                int prime = primes.get(col);
                int frequency = Collections.frequency(primeFactors, new BigInteger(String.valueOf(prime)));
                if (frequency % 2 == 1) {
                    matrix[row].set(col);
                }


            }
        }

        return matrix;
    }

    public BigInteger findGcdOfSquareAndN(List<BigInteger> numbersThatMultiplyToSquare, List<BigInteger> correspondingOrigins, BigInteger n) {

        BigInteger square = BigInteger.ONE;
        for (BigInteger bigInteger : numbersThatMultiplyToSquare) {
            square = square.multiply(bigInteger);
        }
        BigInteger squareRoot = Utils.bigIntegerSqrt(square);

        BigInteger productOfOrigins = BigInteger.ONE;
        for (BigInteger bigInteger : correspondingOrigins) {
            productOfOrigins = productOfOrigins.multiply(bigInteger);
        }

        return productOfOrigins.add(squareRoot).gcd(n);

    }

    public List<BigInteger> findBSmoothNumbers(List<BigInteger> numbers, int b) {
        List<Integer> primes = findPrimesUpTo(b);
        List<BigInteger> numbersAfterDivision = new ArrayList<>(numbers);

        for (int prime : primes) {
            for (int i = 0; i < numbers.size(); i++) {
                BigInteger bigPrime = new BigInteger(String.valueOf(prime));
                while (numbersAfterDivision.get(i).mod(bigPrime).equals(BigInteger.ZERO)) {
                    numbersAfterDivision.set(i, numbersAfterDivision.get(i).divide(bigPrime));
                }
            }
        }

        List<BigInteger> results = new ArrayList<>();

        for (int i = 0; i < numbersAfterDivision.size(); i++) {
            if (numbersAfterDivision.get(i).equals(BigInteger.ONE)) {
                results.add(numbers.get(i));
            }
        }

        return results;
    }

    private List<Integer> findPrimesUpTo(int b) {
        List<Integer> primes = new ArrayList<>();
        int upperBoundSquareRoot = (int) Math.sqrt(b);
        boolean[] isComposite = new boolean[b + 1];
        for (int m = 2; m <= upperBoundSquareRoot; m++) {
            if (!isComposite[m]) {
                for (int k = m * m; k <= b; k += m) {
                    isComposite[k] = true;
                }
            }
        }
        for (int m = 2; m <= b; m++) {
            if (!isComposite[m]) {
                primes.add(m);
            }
        }

        return primes;
    }
}
