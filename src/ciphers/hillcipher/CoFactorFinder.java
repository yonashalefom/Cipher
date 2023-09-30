package ciphers.hillcipher;

public class CoFactorFinder {
    private int temp[][];
    private int facFinal[][];

    public void findCoFactor(int key[][], int arraySize) {
        temp = new int[arraySize][arraySize];
        facFinal = new int[arraySize][arraySize];
        setTemp(arraySize);
        setFacFinal(arraySize);

        //region Co-facFinal Loop
        for (int num2 = 0; num2 < arraySize; num2++) {
            for (int num1 = 0; num1 < arraySize; num1++) {
                int num3 = 0;
                int num4 = 0;
                for (int num5 = 0; num5 < arraySize; num5++) {
                    for (int num6 = 0; num6 < arraySize; num6++) {
                        temp[num5][num6] = 0;
                        if (num5 != num2 && num6 != num1) {
                            temp[num3][num4] = key[num5][num6];
                            if (num4 < (arraySize - 2)) {
                                num4++;
                            } else {
                                num4 = 0;
                                num3++;
                            }
                        }
                    }
                }
                facFinal[num2][num1] = (int) Math.pow(-1, num2 + num1) * new DeterminantFinder().findDeterminant(temp, arraySize - 1);
            }
        }
        //endregion
        new EncryptionDataHolderForHillCipher().isCoFactorGenerationFine = true;
        findTranspose(arraySize);
    }

    private void setTemp(int arraySize) {
        temp = new int[arraySize][arraySize];
    }

    private void setFacFinal(int arraySize) {
        facFinal = new int[arraySize][arraySize];
    }

    private void findTranspose(int size) {
        new TransposeFinder().findTranspose(facFinal, size);
    }
}
