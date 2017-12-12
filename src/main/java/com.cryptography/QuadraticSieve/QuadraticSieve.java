package com.cryptography.QuadraticSieve;

import com.cryptography.Utils;

import java.math.BigInteger;
import java.util.*;

public class QuadraticSieve {
    private static int bSmoothBound = 43;
    private static int numOfInitialMatrixRows;
    private static int numOfInitialMatrixCols;
    private static int numOfTransposedMatrixRows;
    private static int numOfTransposedMatrixCols;
    private static List<BitSet> solutionVectorsForSLE;
    private static List<BigInteger> ays;

    public static BigInteger factor(BigInteger n) {
        List<BigInteger> numbersToFilter = generateNumbersToFilter(n, 60);
        List<BigInteger> bSmoothNumbers = findBSmoothNumbers(numbersToFilter, bSmoothBound);
        List<BigInteger> squares = findSquares(bSmoothNumbers, bSmoothBound);

        return null;
    }

    private static List<BigInteger> findSquares(List<BigInteger> bSmoothNumbers,int b) {
        BitSet[] matrix = getBitMatrix(bSmoothNumbers, b);
        BitSet[] transposedMatrix = transposeMatrix(matrix);
        BitSet[] rrefMatrix = rref(transposedMatrix);
        System.out.println(Arrays.toString(rrefMatrix));
        List<BitSet> solutionVectors = findSolutionVectors(rrefMatrix);
//        System.out.println(bSmoothNumbers);
//        System.out.println(solutionVectors);
        List<BigInteger> squares = new ArrayList<>();
//        for (BitSet solutionVector : solutionVectors) {
//            BigInteger square = constructSquare(solutionVector, bSmoothNumbers);
//            squares.add(square);
//        }
//        System.out.println(squares);
//        squares.add()

        return null;
    }

    private static BigInteger constructSquare(BitSet solutionVector, List<BigInteger> bSmoothNumbers) {
        BigInteger result = BigInteger.ONE;
        for (int i = solutionVector.nextSetBit(0); i >= 0; i = solutionVector.nextSetBit(i+1)) {
            // operate on index i here
            result = result.multiply(bSmoothNumbers.get(i));
            if (i == Integer.MAX_VALUE) {
                break; // or (i+1) would overflow
            }
        }

        return result;
    }

    private static List<BitSet> findSolutionVectors(BitSet[] rrefMatrix) {
        List<Integer> emptyRowIndices = findEmptyRows(rrefMatrix);
        solutionVectorsForSLE = new ArrayList<>();
        if (emptyRowIndices.isEmpty() ) {

            solutionVectorsForSLE.add(solveSLE(rrefMatrix, new BitSet(rrefMatrix.length)));
            return solutionVectorsForSLE;
        }
        else {
            generateTruthTable(rrefMatrix, 0, emptyRowIndices.size(), new int[emptyRowIndices.size()], rrefMatrix.length);

        }
        return solutionVectorsForSLE;
    }

    private static void generateTruthTable(BitSet[] rrefMatrix, int index, int size, int[] current, int solutionLength) {
        if (index == size) {
            BitSet bitSet = new BitSet(solutionLength);
            for (int i = 0; i < size; i++) {

                bitSet.set(solutionLength - 1 - i, current[i] == 1);

            }
            solutionVectorsForSLE.add(solveSLE(rrefMatrix, bitSet));
        } else {
            for (int i = 0; i < 2; i++) {
                current[index] = i;
                generateTruthTable(rrefMatrix, index + 1, size, current, solutionLength);
            }
        }
    }

    private static BitSet solveSLE(BitSet[] matrix, BitSet solution) {
        for (int i = matrix.length - 1; i >= 0; i--) {
            if (!matrix[i].get(i)) {
                continue;
            }
            int counter = 0;
            for (int j = i + 1; j < matrix[i].length(); j++) {

                if (matrix[i].get(j)) {
                    counter += solution.get(j) ? 1 : 0;
                }
            }
            solution.set(i, counter % 2 == 1);
        }
        return solution;
    }

    private static List<Integer> findEmptyRows(BitSet[] rrefMatrix) {
        List<Integer> emptyRowIndices = new ArrayList<>();

        for (int i = 0; i < rrefMatrix.length; i++) {
            if (rrefMatrix[i].isEmpty()) {
                emptyRowIndices.add(i);
            }
        }

        return emptyRowIndices;
    }

    private static BitSet[] getBitMatrix(List<BigInteger> bSmoothNumbers, int b) {
        List<Integer> primes = findPrimesUpTo(b);
        numOfInitialMatrixCols = primes.size();
        numOfInitialMatrixRows = bSmoothNumbers.size();
        BitSet[] matrix = new BitSet[numOfInitialMatrixRows];

        for (int row = 0; row < numOfInitialMatrixRows; row++) {
            List<BigInteger> primeFactors = getPrimeFactorization(bSmoothNumbers.get(row));
            matrix[row] = new BitSet(numOfInitialMatrixCols);
            for (int col = 0; col < numOfInitialMatrixCols; col++) {
                int prime = primes.get(col);
                int frequency = Collections.frequency(primeFactors, new BigInteger(String.valueOf(prime)));
                if (frequency % 2 == 1) {
                    matrix[row].set(col);
                } else {
                    matrix[row].clear(col);
                }
            }
        }

        return matrix;
    }

    private static BitSet[] transposeMatrix(BitSet[] matrix) {
        numOfTransposedMatrixCols = numOfInitialMatrixRows;
        numOfTransposedMatrixRows = numOfInitialMatrixCols;
        BitSet[] newMatrix = new BitSet[numOfTransposedMatrixRows];

        for (int row = 0; row < numOfTransposedMatrixRows; row++) {
            newMatrix[row] = new BitSet();
            for (int col = 0; col < numOfTransposedMatrixCols; col++) {
                newMatrix[row].set(col, matrix[col].get(row)) ;
            }
        }
        return newMatrix;
    }

    private static BitSet[] rref(BitSet[] matrix) {
        BitSet[] resultMatrix = new BitSet[numOfTransposedMatrixRows];
        for (int i = 0; i < numOfTransposedMatrixRows; i++) {
            resultMatrix[i] = (BitSet) matrix[i].clone();
        }

        for (int currentCol = 0, currentRow = 0; currentCol < numOfTransposedMatrixCols; currentCol++, currentRow++) {
            int transformationRowIndex = findRowWithLeadingOne(matrix, currentRow + 1, currentCol);
            if (transformationRowIndex == -1) {
                continue;
            }
            if (transformationRowIndex != currentCol) {
                System.out.println("moving row " + transformationRowIndex + " to " + currentCol);
                resultMatrix = moveRowUp(resultMatrix, transformationRowIndex, currentCol);
            }

            resultMatrix = addRows(resultMatrix, transformationRowIndex, currentCol);
        }

        return resultMatrix;
    }

    private static int findRowWithLeadingOne(BitSet[] matrix, int startRow, int col) {
        for (int i = startRow; i < numOfTransposedMatrixRows; i++) {
            if (matrix[i].get(col)) {
                return i;
            }
        }

        return -1;
    }

    private static BitSet[] addRows(BitSet[] resultMatrix, int specialRowIndex, int currentCol) {
        for (int i = 0; i < numOfTransposedMatrixRows; i++) {
            if (i == specialRowIndex) {
                continue;
            }

            if (resultMatrix[i].get(currentCol)) {
//                resultMatrix[specialRowIndex].xor(resultMatrix[i]);
                resultMatrix[i].xor(resultMatrix[specialRowIndex]);
            }
        }

        return resultMatrix;
    }

    private static BitSet[] moveRowUp(BitSet[] matrix, int rowIndex, int rowToSwap) {
        BitSet temp = matrix[rowToSwap];
        matrix[rowToSwap] = matrix[rowIndex];
        matrix[rowIndex] = temp;

        return matrix;
    }

    private static List<BigInteger> getPrimeFactorization(BigInteger n) {
        List<BigInteger> factors = new ArrayList<>();
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

    private static List<BigInteger> findBSmoothNumbers(List<BigInteger> numbers, int b) {
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

    private static List<Integer> findPrimesUpTo(int b) {
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

    private static List<BigInteger> generateNumbersToFilter (BigInteger n, int amountOfNumbers) {
        List<BigInteger> numbers = new ArrayList<>();
        BigInteger a = Utils.bigIntegerSqrt(n).add(BigInteger.ONE); // Effectively a ceiling function
        ays = new ArrayList<>();
        for (int i = 0; i < amountOfNumbers; i++) {
            ays.add(a);
            numbers.add(a.pow(2).subtract(n));
            a = a.add(BigInteger.ONE);
        }
        return numbers;
    }


}
