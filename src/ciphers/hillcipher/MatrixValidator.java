package ciphers.hillcipher;

public class MatrixValidator extends HillCipherHelper {
    public boolean validateKey(String key, int len) {
        keyMatrixForm = new MatrixAndKeyModifier().changeKeyToMatrix(key, len);
        int d = new DeterminantFinder().findDeterminant(keyMatrixForm, len);
        d %= 26;
        if (d == 0) {
            System.out.println("Your key is not invertible.");
            return false;
        } else if (d % 2 == 0 || d % 13 == 0) {
            System.out.println("Your key is not invertible. Determinant has common factor with 26");
            return false;
        } else {
            return true;
        }
    }
}
