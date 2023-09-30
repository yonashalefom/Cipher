package ciphers.caesarcipher;

import java.util.Scanner;

public class CaesarCipher {
    public final String allAlphabets = "abcdefghijklmnopqrstuvwxyz";

    public String encryptMessage(String plainText, int lengthToShift) {
        if (plainText.matches("[a-zA-Z]+")) {
            plainText = plainText.toLowerCase();
            String cipherText = "";
            for (int i = 0; i < plainText.length(); i++) {
                int characterPosition = allAlphabets.indexOf(plainText.charAt(i));
                int newPosition = (lengthToShift + characterPosition) % 26;
                char newCharacter = allAlphabets.charAt(newPosition);
                cipherText = cipherText + newCharacter;
            }
            return cipherText;
        } else {
            return "Make sure your message contains only a text.";
        }
    }

    public String decryptMessage(String cipherText, int lengthToShift) {
        if (cipherText.matches("[a-zA-Z]+") && cipherText != null) {
            cipherText = cipherText.toLowerCase();
            String plainText = "";
            for (int i = 0; i < cipherText.length(); i++) {
                int characterPosition = allAlphabets.indexOf(cipherText.charAt(i));
                int newPosition = (characterPosition - lengthToShift) % 26;
                if (newPosition < 0) {
                    newPosition = allAlphabets.length() + newPosition;
                }
                char newCharacter = allAlphabets.charAt(newPosition);
                plainText += newCharacter;
            }
            return plainText;
        } else {
            return "Make sure your message contains only a text.";
        }
    }

    public String singleLevelEncryption(String plainText, int lengthToShift) {
        return encryptMessage(plainText, lengthToShift);
    }

    public String multiLevelEncryption(String plainText, int lengthToShift, int encryptionStage) {
        String cipherText = plainText;
        for (int i = 0; i < encryptionStage; i++) {
            cipherText = encryptMessage(cipherText, lengthToShift);
        }
        return cipherText;
    }

    public String singleLevelDecryption(String cipherText, int lengthToShift) {
        return decryptMessage(cipherText, lengthToShift);
    }

    public String multiLevelDecryption(String cipherText, int lengthToShift, int encryptionStage) {
        String plainText = cipherText;
        for (int i = 0; i < encryptionStage; i++) {
            plainText = decryptMessage(plainText, lengthToShift);
        }
        return plainText;
    }
}