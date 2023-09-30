package controllers.dialogs;

import ciphers.EncryptionKeyHolder;
import ciphers.EncryptionMode;
import controllers.general.EditorPageController;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Popup;

import java.net.URL;
import java.util.ResourceBundle;

public class ReviewPageController implements Initializable {

    //region
    public SVGPath keyIcon;
    public Label keyLabel;
    public SVGPath lockIcon;
    public Label lockLabel;
    public Button readMoreButton;
    public Button okButton;

    //endregion

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (new EncryptionMode().getEncryptionMode() == "HILL_CIPHER") {
            if (new EncryptionKeyHolder().isHillSet) {
                keyIcon.setFill(Color.GREEN);
                keyLabel.setText("Hill Cipher key is set");
                lockLabel.setText("Hill Cipher");
            } else {
                keyIcon.setFill(Color.RED);
                lockLabel.setText("Hill Cipher");
            }
        } else if (new EncryptionMode().getEncryptionMode() == "CAESAR_CIPHER") {
            if (new EncryptionKeyHolder().isCaesarSet) {
                keyIcon.setFill(Color.GREEN);
                keyLabel.setText("Caesar cipher key is set");
                lockLabel.setText("Caesar Cipher");
            } else {
                keyIcon.setFill(Color.RED);
                lockLabel.setText("Caesar Cipher");
            }
        }
    }

    public void okButton_SetOnMouseClick() {
        EditorPageController.popup.hide();
    }
}
