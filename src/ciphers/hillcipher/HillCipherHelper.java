package ciphers.hillcipher;

public class HillCipherHelper {
    static int subStringMatrix[];
    static int finalMatrix[];
    static int keyMatrixForm[][];
    StringBuffer finalDecryptedPlainText = new StringBuffer();

    public void finalEncryptionResult(int len) {
        String output = "";
        for (int i = 0; i < len; i++) {
            output += (char) (finalMatrix[i] + 97);
        }
        finalDecryptedPlainText.append(output);
        System.out.print("Plain Text: " + finalDecryptedPlainText);
        System.out.println("Plain Text Length: " + finalDecryptedPlainText.length());
        new EncryptionDataHolderForHillCipher().setPlainText(String.valueOf(finalDecryptedPlainText));
    }

    public void encryptDecrypt(String plainSubString) {
        new MatrixAndKeyModifier().changeSubStringToMatrix(plainSubString);
        new MatrixAndKeyModifier().multiplyPlainSubStringWithMatrix(plainSubString.length());
        finalEncryptionResult(plainSubString.length());
    }

    public String calculate(int choice, String inputText, String key) {
        System.out.println();
        System.out.println();
        System.out.println("====================================================");
        System.out.println("Choice: " + choice);
        System.out.println("Input Text: " + inputText);
        System.out.println("Key: " + key);
        System.out.println("====================================================");
        double squareMatrixVerifier = Math.sqrt(key.length());
        if (squareMatrixVerifier != (long) squareMatrixVerifier) {
            return "WRONG";
        } else {
            int size = (int) squareMatrixVerifier;
            if (new MatrixValidator().validateKey(key, size)) {
                if (choice == 1) {
                    new MatrixAndKeyModifier().plainTextToSubString(inputText, size);
                } else if (choice == 2) {
                    new CoFactorFinder().findCoFactor(keyMatrixForm, size);
                }
            }
            return "CORRECT";
        }
    }
}
