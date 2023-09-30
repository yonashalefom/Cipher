package controllers.dialogs;

import ciphers.EncryptionMode;
import javafx.fxml.FXMLLoader;
import javafx.stage.Popup;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class ShowPopup {
    public void showKeySetterPopup(Popup popup, Stage mainWindow) {
        if (new EncryptionMode().getEncryptionMode() == "HILL_CIPHER") {
            popup.setWidth(745);
            popup.setHeight(400);
            popup.setAutoHide(true);
            popup.centerOnScreen();
            try {
                File file = new File("C:\\Users\\hp\\Desktop\\Personal Website Development\\Personal Data & Projects\\Projects\\Hill Cipher Project\\src\\views\\hillCipherKeyAccepter.fxml");
                popup.getContent().add(FXMLLoader.load(file.toURI().toURL()));
            } catch (IOException e) {
            }
            popup.getContent().get(0).getStyleClass().add("../controllers/general/dialogStyle.css");
            popup.show(mainWindow);
        } else {
            System.out.println("not sure what encryption mode you are in.");
        }
    }

    public void showQuickEncDecPopup(Popup popup, Stage mainWindow) {
        popup.setWidth(745);
        popup.setHeight(400);
        popup.setAutoHide(true);
        popup.centerOnScreen();
        try {
            File file = new File("C:\\Users\\hp\\Desktop\\Personal Website Development\\Personal Data & Projects\\Projects\\Hill Cipher Project\\src\\views\\quickEncryptDecryptPage.fxml");
            popup.getContent().add(FXMLLoader.load(file.toURI().toURL()));
        } catch (IOException e) {
        }
        popup.getContent().get(0).getStyleClass().add("../controllers/general/quickEditorStyle.css");
        popup.show(mainWindow);
    }

    public void showReviewPopup(Popup popup, Stage mainWindow) {
        if (new EncryptionMode().getEncryptionMode() == "HILL_CIPHER") {
            showReviewPopupImplementation(popup, mainWindow);
        } else if (new EncryptionMode().getEncryptionMode() == "CAESAR_CIPHER") {
            showReviewPopupImplementation(popup, mainWindow);
        }
    }

    public void showAboutPopup(Popup popup, Stage mainWindow) {
        popup.setWidth(600);
        popup.setHeight(785);
        popup.setAutoHide(true);
        popup.centerOnScreen();
        try {
            File file = new File("C:\\Users\\hp\\Desktop\\Personal Website Development\\Personal Data & Projects\\Projects\\Hill Cipher Project\\src\\views\\AboutHillCipher.fxml");
            popup.getContent().add(FXMLLoader.load(file.toURI().toURL()));
        } catch (IOException e) {
        }
        popup.getContent().get(0).getStyleClass().add("../controllers/general/quickEditorStyle.css");
        popup.show(mainWindow);
    }

    private void showReviewPopupImplementation(Popup popup, Stage mainWindow) {
        popup.setWidth(745);
        popup.setHeight(400);
        popup.setAutoHide(true);
        popup.centerOnScreen();
        try {
            File file = new File("C:\\Users\\hp\\Desktop\\Personal Website Development\\Personal Data & Projects\\Projects\\Hill Cipher Project\\src\\views\\encryptionReview.fxml");
            popup.getContent().add(FXMLLoader.load(file.toURI().toURL()));
        } catch (IOException e) {
        }
        popup.getContent().get(0).getStyleClass().add("../controllers/general/quickEditorStyle.css");
        popup.show(mainWindow);
    }
}
