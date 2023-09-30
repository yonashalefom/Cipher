package ciphers.hillcipher;

public class MatrixAndKeyModifier extends HillCipherHelper {
    public void changeSubStringToMatrix(String line) {
        subStringMatrix = new int[line.length()];
        for (int i = 0; i < line.length(); i++) {
            subStringMatrix[i] = ((int) line.charAt(i)) - 97;
        }
    }

    public int[][] changeKeyToMatrix(String key, int len) {
        int[][] KeyMatrixForm = new int[len][len];
        int num1 = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                KeyMatrixForm[i][j] = ((int) key.charAt(num1)) - 97;
                num1++;
            }
        }
        return KeyMatrixForm;
    }

    public void matrixToInverseKey(int inv[][], int n) {
        String inverseKey = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverseKey += (char) (inv[i][j] + 97);
            }
        }
        new EncryptionDataHolderForHillCipher().setDecryptionKey(inverseKey);
    }

    public void multiplyPlainSubStringWithMatrix(int len) {
        finalMatrix = new int[len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                finalMatrix[i] += keyMatrixForm[i][j] * subStringMatrix[j];
            }
            finalMatrix[i] %= 26;
        }
    }

    public void plainTextToSubString(String plainText, int keySize) {
        while (plainText.length() > keySize) {
            String subString = plainText.substring(0, keySize);
            plainText = plainText.substring(keySize, plainText.length());
            encryptDecrypt(subString);
        }
        if (plainText.length() < keySize) {
            for (int i = plainText.length(); i < keySize; i++) {
                plainText = plainText + '0';
            }
            encryptDecrypt(plainText);
        } else if (plainText.length() == keySize) {
            encryptDecrypt(plainText);
        }
    }
}
