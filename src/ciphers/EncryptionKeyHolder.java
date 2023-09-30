package ciphers;

public class EncryptionKeyHolder {
    private static double hillCipherEncryptionKey[][];
    public static boolean isHillSet = false;
    private static int railFenceEncryptionKey;
    public static boolean isRailSet = false;
    private static String routeCipherEncryptionKey;
    public static boolean isRouteSet = false;
    private static int caesarCipherEncryptionKey;
    public static boolean isCaesarSet = false;

    public void setHillCipherEncryptionKey(double key[][]) {
        hillCipherEncryptionKey = key;
        isHillSet = true;
    }

    public double[][] getHillCipherEncryptionKey() {
        if (isHillSet) {
            return hillCipherEncryptionKey;
        } else {
            return null;
        }
    }

    public void setRailFenceEncryptionKey(int key) {
        railFenceEncryptionKey = key;
        isRailSet = true;
    }

    public int getRailFenceEncryptionKey() {
        if (isRailSet) {
            return railFenceEncryptionKey;
        } else {
            return -1;
        }

    }

    public void setRouteCipherEncryptionKey(String key) {
        routeCipherEncryptionKey = key;
        isRouteSet = true;
    }

    public String getRouteCipherEncryptionKey() {
        if (isCaesarSet) {
            return routeCipherEncryptionKey;
        } else {
            return null;
        }
    }

    public void setCaesarCipherEncryptionKey(int key) {
        caesarCipherEncryptionKey = key;
        isCaesarSet = true;
    }

    public int getCaesarCipherEncryptionKey() {
        if (isCaesarSet) {
            return caesarCipherEncryptionKey;
        } else {
            return -1;
        }
    }

    public void resetAllKeys() {
        hillCipherEncryptionKey = null;
        isHillSet = false;
        railFenceEncryptionKey = -1;
        isRailSet = false;
        routeCipherEncryptionKey = null;
        isRouteSet = false;
        caesarCipherEncryptionKey = -1;
        isCaesarSet = false;
        System.out.println("------------------------ Encryption Key Reset ------------------------");
        System.out.println("Hill Cipher Encryption Key: " + hillCipherEncryptionKey);
        System.out.println("Caesar Cipher Encryption Key: " + railFenceEncryptionKey);
        System.out.println("Rail Fence Cipher Encryption Key: " + routeCipherEncryptionKey);
        System.out.println("Route Cipher Encryption Key: " + caesarCipherEncryptionKey);
        System.out.println("----------------------------------------------------------------------");
    }
}
