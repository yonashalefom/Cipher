package ciphers.hillcipher;

public class TransposeFinder extends HillCipherHelper {
    private int temp[][], invertKey[][];

    void findTranspose(int fac[][], int r) {

        temp = new int[r][r];
        invertKey = new int[r][r];
        int d = new DeterminantFinder().findDeterminant(keyMatrixForm, r);
        int mi = transposeMultiplicationHelper(d % 26);
        mi %= 26;
        if (mi < 0)
            mi += 26;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < r; j++) {
                temp[i][j] = fac[j][i];
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < r; j++) {
                invertKey[i][j] = temp[i][j] % 26;
                if (invertKey[i][j] < 0)
                    invertKey[i][j] += 26;
                invertKey[i][j] *= mi;
                invertKey[i][j] %= 26;
            }
        }
        new EncryptionDataHolderForHillCipher().isTransposeGenerationFine = true;
        new MatrixAndKeyModifier().matrixToInverseKey(invertKey, r);
    }

    public int transposeMultiplicationHelper(int passedNumber) {
        int division;
        int num[] = new int[3];
        int transpose[] = new int[3];
        num[0] = 26;
        num[1] = passedNumber;
        transpose[0] = 0;
        transpose[1] = 1;
        while (num[0] != 1 && num[1] != 0) {
            division = num[0] / num[1];
            num[2] = num[0] % num[1];
            transpose[2] = transpose[0] - (transpose[1] * division);
            num[0] = num[1];
            num[1] = num[2];
            transpose[0] = transpose[1];
            transpose[1] = transpose[2];
        }
        return (transpose[0] + transpose[1]);
    }
}
