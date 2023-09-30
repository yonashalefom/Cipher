package ciphers.hillcipher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HillCipher {
    private Map charToIntegerMap = new HashMap();
    private Map integerToCharMap = new HashMap();
    private ArrayList finalCipherArrayNumbers = new ArrayList();
    private StringBuffer finalCipherArrayConcatenatedString = new StringBuffer();

    public HillCipher() {
        initializeCharToIntMap();
        initializeIntToCharMap();
    }

    private void initializeCharToIntMap() {
        charToIntegerMap.put('A', 0);
        charToIntegerMap.put('B', 1);
        charToIntegerMap.put('C', 2);
        charToIntegerMap.put('D', 3);
        charToIntegerMap.put('E', 4);
        charToIntegerMap.put('F', 5);
        charToIntegerMap.put('G', 6);
        charToIntegerMap.put('H', 7);
        charToIntegerMap.put('I', 8);
        charToIntegerMap.put('J', 9);
        charToIntegerMap.put('K', 10);
        charToIntegerMap.put('L', 11);
        charToIntegerMap.put('M', 12);
        charToIntegerMap.put('N', 13);
        charToIntegerMap.put('O', 14);
        charToIntegerMap.put('P', 15);
        charToIntegerMap.put('Q', 16);
        charToIntegerMap.put('R', 17);
        charToIntegerMap.put('S', 18);
        charToIntegerMap.put('T', 19);
        charToIntegerMap.put('U', 20);
        charToIntegerMap.put('V', 21);
        charToIntegerMap.put('W', 22);
        charToIntegerMap.put('X', 23);
        charToIntegerMap.put('Y', 24);
        charToIntegerMap.put('Z', 25);
        charToIntegerMap.put('a', 0);
        charToIntegerMap.put('b', 1);
        charToIntegerMap.put('c', 2);
        charToIntegerMap.put('d', 3);
        charToIntegerMap.put('e', 4);
        charToIntegerMap.put('f', 5);
        charToIntegerMap.put('g', 6);
        charToIntegerMap.put('h', 7);
        charToIntegerMap.put('i', 8);
        charToIntegerMap.put('j', 9);
        charToIntegerMap.put('k', 10);
        charToIntegerMap.put('l', 11);
        charToIntegerMap.put('m', 12);
        charToIntegerMap.put('n', 13);
        charToIntegerMap.put('o', 14);
        charToIntegerMap.put('p', 15);
        charToIntegerMap.put('q', 16);
        charToIntegerMap.put('r', 17);
        charToIntegerMap.put('s', 18);
        charToIntegerMap.put('t', 19);
        charToIntegerMap.put('u', 20);
        charToIntegerMap.put('v', 21);
        charToIntegerMap.put('w', 22);
        charToIntegerMap.put('x', 23);
        charToIntegerMap.put('y', 24);
        charToIntegerMap.put('z', 25);
    }

    private void initializeIntToCharMap() {
        integerToCharMap.put(0.0, 'A');
        integerToCharMap.put(1.0, 'B');
        integerToCharMap.put(2.0, 'C');
        integerToCharMap.put(3.0, 'D');
        integerToCharMap.put(4.0, 'E');
        integerToCharMap.put(5.0, 'F');
        integerToCharMap.put(6.0, 'G');
        integerToCharMap.put(7.0, 'H');
        integerToCharMap.put(8.0, 'I');
        integerToCharMap.put(9.0, 'J');
        integerToCharMap.put(10.0, 'K');
        integerToCharMap.put(11.0, 'L');
        integerToCharMap.put(12.0, 'M');
        integerToCharMap.put(13.0, 'N');
        integerToCharMap.put(14.0, 'O');
        integerToCharMap.put(15.0, 'P');
        integerToCharMap.put(16.0, 'Q');
        integerToCharMap.put(17.0, 'R');
        integerToCharMap.put(18.0, 'S');
        integerToCharMap.put(19.0, 'T');
        integerToCharMap.put(20.0, 'U');
        integerToCharMap.put(21.0, 'V');
        integerToCharMap.put(22.0, 'W');
        integerToCharMap.put(23.0, 'X');
        integerToCharMap.put(24.0, 'Y');
        integerToCharMap.put(25.0, 'Z');
        integerToCharMap.put(0.0, 'a');
        integerToCharMap.put(1.0, 'b');
        integerToCharMap.put(2.0, 'c');
        integerToCharMap.put(3.0, 'd');
        integerToCharMap.put(4.0, 'e');
        integerToCharMap.put(5.0, 'f');
        integerToCharMap.put(6.0, 'g');
        integerToCharMap.put(7.0, 'h');
        integerToCharMap.put(8.0, 'i');
        integerToCharMap.put(9.0, 'j');
        integerToCharMap.put(10.0, 'k');
        integerToCharMap.put(11.0, 'l');
        integerToCharMap.put(12.0, 'm');
        integerToCharMap.put(13.0, 'n');
        integerToCharMap.put(14.0, 'o');
        integerToCharMap.put(15.0, 'p');
        integerToCharMap.put(16.0, 'q');
        integerToCharMap.put(17.0, 'r');
        integerToCharMap.put(18.0, 's');
        integerToCharMap.put(19.0, 't');
        integerToCharMap.put(20.0, 'u');
        integerToCharMap.put(21.0, 'v');
        integerToCharMap.put(22.0, 'w');
        integerToCharMap.put(23.0, 'x');
        integerToCharMap.put(24.0, 'y');
        integerToCharMap.put(25.0, 'z');
    }

    public void encryptPlainText(String plainText, double key[][], int length) {
        computeCipher(plainText, key, length);
    }

    public void decryptPlainText(String cipherText, double key[][]) {
        HillCipherHelper hillCipherHelper = new HillCipherHelper();

        String changedKey = changeMatrixToKey(key);
        hillCipherHelper.calculate(2, cipherText, changedKey);

        String newDecryptionKey = new EncryptionDataHolderForHillCipher().getDecryptionKey();

        hillCipherHelper.calculate(1, cipherText, newDecryptionKey);
    }

    public String changeMatrixToKey(double key[][]) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                stringBuffer.append(integerToCharMap.get(key[i][j]));
            }
        }
        System.out.println("Matrix changed to: " + String.valueOf(stringBuffer));
        return String.valueOf(stringBuffer);
    }

    public double[][] changeKeyToMatrix(String key, int blockLength) {
        return null;
    }

    //This code compute the cipher of the plain text
    public void computeCipher(String plainText, double key[][], int n) {
        System.out.println(removeSpecialCharactersRegex(plainText)); //for debugging purposes

        String subStrings[] = splitString(removeSpecialCharactersRegex(plainText), n);

        System.out.println("-------------------------");
        for (String s : subStrings) {
            System.out.println(s);
        }
        System.out.println("-------------------------");

        changePlainTextSubStringsToMatrix(subStrings, key, n);
    }

    //This code change the plain text to a matrix
    public void changePlainTextSubStringsToMatrix(String oldSubStrings[], double key[][], int n) {
        String newSubString[] = fillPlainText(oldSubStrings, n);
        char characters[];
        double plainTextMatrixRepresentation[][] = new double[n][1];
        for (String subString : newSubString) {
            characters = subString.toCharArray();
            for (int i = 0; i < characters.length; i++) {
                plainTextMatrixRepresentation[i][0] = (int) charToIntegerMap.get(characters[i]);
            }
            multiplyPlainTextWithKey(plainTextMatrixRepresentation, key);
        }
        System.out.println("===========================");
        for (int i = 0; i < finalCipherArrayNumbers.size(); i++) {
            System.out.print(i + ": ");
            System.out.println(finalCipherArrayNumbers.get(i));
        }
        for (int i = 0; i < finalCipherArrayNumbers.size(); i++) {
            finalCipherArrayConcatenatedString.append(integerToCharMap.get(finalCipherArrayNumbers.get(i)));
        }
        new EncryptionDataHolderForHillCipher().setCipherText(String.valueOf(finalCipherArrayConcatenatedString));
        System.out.println("===========================");
    }

    //This code fills the remaining characters in the plain text if there is any
    public String[] fillPlainText(String[] plainText, int length) {
        StringBuffer toBeReturned[] = new StringBuffer[plainText.length];
        for (int i = 0; i < plainText.length; i++) {
            if (plainText[i].toString().length() == length) {
                toBeReturned[i] = new StringBuffer(plainText[i]);
            } else if (plainText[i].toString().length() < length) {
                toBeReturned[i] = new StringBuffer(plainText[i]);
                int j = toBeReturned[i].toString().length();
                for (; j < length; j++) {
                    toBeReturned[i].append(toBeReturned[i].toString().charAt(j - 1));
                }
            }
        }
        String[] newStringToBeReturned = new String[plainText.length];
        System.out.println("--------------------------------------------");
        for (int i = 0; i < toBeReturned.length; i++) {
            newStringToBeReturned[i] = toBeReturned[i].toString();
            System.out.print(newStringToBeReturned[i]);
        }
        System.out.println();
        System.out.println("--------------------------------------------");
        return newStringToBeReturned;
    }

    //This code multiply the plain text matrix with the key matrix
    public void multiplyPlainTextWithKey(double plain[][], double key[][]) {
        MatrixFunction matrixFunction = new MatrixFunction();
        double cipherArray[][] = matrixFunction.multiplyByMatrix(key, plain);
        for (int i = 0; i < cipherArray.length; i++) {
            for (int j = 0; j < cipherArray[0].length; j++) {
                finalCipherArrayNumbers.add(cipherArray[i][j] % 26);
            }
        }
    }

    //This code split the passed string into n equal parts.
    public String[] splitString(String text, int length) {
        return text.split("(?<=\\G.{" + String.valueOf(length) + "})");
    }

    //This code remove unnecessary characters from the given argument
    public String removeSpecialCharactersRegex(String oldString) {
        Pattern pattern = Pattern.compile("[^a-zA-Z]");
        Matcher matcher = pattern.matcher(oldString);
        String newString = matcher.replaceAll("");
        return newString;
    }
}
