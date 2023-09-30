package ciphers;

public class EncryptionMode {
    private static String encryptionMode = "NOT_SET";
    public void setEncryptionMode(String encryptionMode){
        this.encryptionMode = encryptionMode;
    }

    public String getEncryptionMode(){
        return encryptionMode;
    }

    public Boolean resetEncryptionMode(){
        encryptionMode = "NOT_SET";
        return true;
    }
}
