package com.cryptography.QuadraticSieve;

import com.cryptography.Utils;

import java.math.BigInteger;
import java.util.*;

public class QuadraticSieveDraft {
    BitSet[] rows;
    BitSet[] solutionRows;
    List<BitSet> matrix;
    List<BitSet> solutionVectorsForSLE;

    public BigInteger factor(BigInteger n) {
//        BigInteger a =
        return null;
    }

//    public List<BigInteger> getPrimeFactorization(BigInteger n) {
//        List<BigInteger> factors = new ArrayList<BigInteger>();
//        for (BigInteger i = new BigInteger("2"); i.compareTo(n.divide(i)) < 0; i = i.add(BigInteger.ONE)) {
//            while (n.mod(i).equals(BigInteger.ZERO)) {
//                factors.add(i);
//                n = n.divide(i);
//            }
//        }
//        if (n.compareTo(BigInteger.ONE) > 0) {
//            factors.add(n);
//        }
//        return factors;
//    }


//    public List<BigInteger> findSubsetsThatProducesSquare(List<BigInteger> bSmoothNumbers, int b) {
//        BitSet[] matrix = getBitMatrix(bSmoothNumbers, b);
//        solve(new ArrayList<>(Arrays.asList(matrix)));
////        BitSet[] A = transposeMatrix(matrix);
//
//
//        return null;
//    }

//    public BitSet[] rref(BitSet[] matrix) {
//        BitSet[] resultMatrix = new BitSet[matrix.length];
//        for (int i = 0; i < matrix.length; i++) {
//            resultMatrix[i] = (BitSet) matrix[i].clone();
//        }
//
//        for (int i = 0; i < matrix.length; i++) {
//            int specialRowIndex = findRowWithLeadingOne(matrix, i, i);
//            System.out.println("Row with Leading One: " + specialRowIndex);
//            if (specialRowIndex == -1) {
//                continue;
//            }
//            if (specialRowIndex != i) {
//                resultMatrix = moveRowUp(resultMatrix, specialRowIndex, i);
//            }
//
//            resultMatrix = addRows(resultMatrix, specialRowIndex, i);
//        }
//
//        return resultMatrix;
//    }

//    public List<BitSet> findSolutionVectors(BitSet[] rrefMatrix) {
//        List<Integer> emptyRowIndices = findEmptyRows(rrefMatrix);
//        solutionVectorsForSLE = new ArrayList<>();
//        if (emptyRowIndices.isEmpty() ) {
//
//            solutionVectorsForSLE.add(solveSLE(rrefMatrix, new BitSet(rrefMatrix.length)));
//            return solutionVectorsForSLE;
//        }
//        else {
//            generateTruthTable(0, emptyRowIndices.size(), new int[emptyRowIndices.size()], rrefMatrix.length);
//        }
//        return solutionVectorsForSLE;
//    }

//    public void generateTruthTable(int index, int size, int[] current, int solutionLength) {
//        if (index == size) {
//            BitSet bitSet = new BitSet(solutionLength);
//            for (int i = 0; i < size; i++) {
//
//                bitSet.set(solutionLength - 1 - i, current[i] == 1);
//
//            }
//            solutionVectorsForSLE.add(bitSet);
//        } else {
//            for (int i = 0; i < 2; i++) {
//                current[index] = i;
//                generateTruthTable(index + 1, size, current, solutionLength);
//            }
//        }
//    }

//    private List<Integer> findEmptyRows(BitSet[] rrefMatrix) {
//        List<Integer> emptyRowIndices = new ArrayList<>();
//
//        for (int i = 0; i < rrefMatrix.length; i++) {
//            if (rrefMatrix[i].isEmpty()) {
//                emptyRowIndices.add(i);
//            }
//        }
//
//        return emptyRowIndices;
//    }

//    public BitSet solveSLE(BitSet[] matrix, BitSet solution) {
//        for (int i = matrix.length - 1; i >= 0; i--) {
//            if (!matrix[i].get(i)) {
//                continue;
//            }
//            int counter = 0;
//            for (int j = i + 1; j < matrix[i].length(); j++) {
//
//                if (matrix[i].get(j)) {
//                    counter += solution.get(j) ? 1 : 0;
//                }
//            }
//            solution.set(i, counter % 2 == 1);
//        }
//        return solution;
//    }

//    private BitSet[] addRows(BitSet[] resultMatrix, int specialRowIndex, int currentCol) {
//        for (int i = 0; i < resultMatrix.length; i++) {
//            if (i == specialRowIndex) {
//                continue;
//            }
//
//            if (resultMatrix[i].get(currentCol)) {
//                resultMatrix[specialRowIndex].and(resultMatrix[i]);
//            }
//        }
//
//        return resultMatrix;
//
//    }
//
//    private BitSet[] moveRowUp(BitSet[] matrix, int rowIndex, int rowToSwap) {
//        System.out.println("Blehljoerjfpjeprfjpoerf");
//        BitSet temp = matrix[rowToSwap];
//        matrix[rowToSwap] = matrix[rowIndex];
//        matrix[rowIndex] = temp;
//
//        return matrix;
//    }

//    private int findRowWithLeadingOne(BitSet[] matrix, int startRow, int col) {
//        for (int i = startRow; i < matrix.length; i++) {
//            if (matrix[i].get(col)) {
//                return i;
//            }
//        }
//
//        return -1;
//    }

//    public BitSet[] transposeMatrix(BitSet[] matrix) {
//        BitSet[] newMatrix = new BitSet[matrix.length];
//
//        for (int row = 0; row < matrix.length; row++) {
//            newMatrix[row] = new BitSet();
//            for (int col = 0; col < matrix.length; col++) {
//                newMatrix[row].set(col, matrix[col].get(row)) ;
//            }
//        }
//        return newMatrix;
//    }



//    public BitSet[] getBitMatrix(List<BigInteger> bSmoothNumbers, int b) {
//        List<Integer> primes = findPrimesUpTo(b);
//        int numOfColumns = primes.size();
//        int numOfRows = bSmoothNumbers.size();
//        BitSet[] matrix = new BitSet[bSmoothNumbers.size()];
//
//        for (int row = 0; row < numOfRows; row++) {
//            List<BigInteger> primeFactors = getPrimeFactorization(bSmoothNumbers.get(row));
//            matrix[row] = new BitSet(numOfColumns);
//            for (int col = 0; col < numOfColumns; col++) {
//                int prime = primes.get(col);
//                int frequency = Collections.frequency(primeFactors, new BigInteger(String.valueOf(prime)));
//                if (frequency % 2 == 1) {
//                    matrix[row].set(col);
//                } else {
//                    matrix[row].clear(col);
//                }
//
//
//            }
//        }
//
//        return matrix;
//    }

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

//    public List<BigInteger> findBSmoothNumbers(List<BigInteger> numbers, int b) {
//        List<Integer> primes = findPrimesUpTo(b);
//        List<BigInteger> numbersAfterDivision = new ArrayList<>(numbers);
//
//        for (int prime : primes) {
//            for (int i = 0; i < numbers.size(); i++) {
//                BigInteger bigPrime = new BigInteger(String.valueOf(prime));
//                while (numbersAfterDivision.get(i).mod(bigPrime).equals(BigInteger.ZERO)) {
//                    numbersAfterDivision.set(i, numbersAfterDivision.get(i).divide(bigPrime));
//                }
//            }
//        }
//
//        List<BigInteger> results = new ArrayList<>();
//
//        for (int i = 0; i < numbersAfterDivision.size(); i++) {
//            if (numbersAfterDivision.get(i).equals(BigInteger.ONE)) {
//                results.add(numbers.get(i));
//            }
//        }
//
//        return results;
//    }
//
//    private List<Integer> findPrimesUpTo(int b) {
//        List<Integer> primes = new ArrayList<>();
//        int upperBoundSquareRoot = (int) Math.sqrt(b);
//        boolean[] isComposite = new boolean[b + 1];
//        for (int m = 2; m <= upperBoundSquareRoot; m++) {
//            if (!isComposite[m]) {
//                for (int k = m * m; k <= b; k += m) {
//                    isComposite[k] = true;
//                }
//            }
//        }
//        for (int m = 2; m <= b; m++) {
//            if (!isComposite[m]) {
//                primes.add(m);
//            }
//        }
//
//        return primes;
//    }
}
