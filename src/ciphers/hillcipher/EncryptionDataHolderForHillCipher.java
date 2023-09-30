package ciphers.hillcipher;

public class EncryptionDataHolderForHillCipher {
    public static String cipherText;
    public static String plainText;
    public static String decryptionKey;

    public static boolean isCoFactorGenerationFine;
    public static boolean isDeterminantGenerationFine;
    public static boolean isTransposeGenerationFine;

    public void setCipherText(String cText) {
        cipherText = cText;
    }

    public String getCipherText() {
        return cipherText;
    }

    public void setDecryptionKey(String rKey) {
        decryptionKey = rKey;
    }

    public String getDecryptionKey() {
        return decryptionKey;
    }

    public void setPlainText(String pText) {
        plainText = pText;
    }

    public String getPlainText() {
        return plainText;
    }
}
