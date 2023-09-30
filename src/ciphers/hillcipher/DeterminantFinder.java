package ciphers.hillcipher;

public class DeterminantFinder {
    int finalDeterminant = 0;

    public int findDeterminant(int matrix[][], int arraySize) {
        if (arraySize == 2) {
            finalDeterminant = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
        } else {
            finalDeterminant = 0;
            for (int i = 0; i < arraySize; i++) {
                int[][] num = new int[arraySize - 1][];
                for (int j = 0; j < (arraySize - 1); j++) {
                    num[j] = new int[arraySize - 1];
                }
                for (int k = 1; k < arraySize; k++) {
                    int temp = 0;
                    for (int l = 0; l < arraySize; l++) {
                        if (l == i)
                            continue;
                        num[k - 1][temp] = matrix[k][l];
                        temp++;
                    }
                }
                finalDeterminant += Math.pow(-1.0, 1.0 + i + 1.0) * matrix[0][i] * findDeterminant(num, arraySize - 1);
            }
        }
        new EncryptionDataHolderForHillCipher().isDeterminantGenerationFine = true;
        return finalDeterminant;
    }
}
