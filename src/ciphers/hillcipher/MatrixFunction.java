package ciphers.hillcipher;

public class MatrixFunction {
    public static double[][] multiplyByMatrix(double[][] m1, double[][] m2) {
        int m1ColLength = m1[0].length; // m1 columns length
        System.out.println(m1ColLength);
        int m2RowLength = m2.length;    // m2 rows length

        if (m1ColLength != m2RowLength) return null; // matrix multiplication is not possible
        int mRRowLength = m1.length;    // m finalEncryptionResult rows length
        int mRColLength = m2[0].length; // m finalEncryptionResult columns length
        double[][] mResult = new double[mRRowLength][mRColLength];
        for (int i = 0; i < mRRowLength; i++) {         // rows from m1
            for (int j = 0; j < mRColLength; j++) {     // columns from m2
                for (int k = 0; k < m1ColLength; k++) { // columns from m1
                    mResult[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return mResult;
    }

    public static String toString(double[][] m) {
        String result = "";
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                result += m[i][j] + "\t";
            }
            result += "\n";
        }
        return result;
    }

    public static void main(String[] args) {
        double[][] multiplicand = new double[][]{
                {17, 17, 5}, {21, 18, 21}, {2, 2, 19}
        };
        double[][] multiplier = new double[][]{
                {11}, {13}, {18}
        };
        System.out.println("#1\n" + toString(multiplyByMatrix(multiplicand, multiplier)));
    }
}
