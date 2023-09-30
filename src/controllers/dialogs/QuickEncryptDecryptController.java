package controllers.dialogs;

import ciphers.EncryptionKeyHolder;
import ciphers.EncryptionMode;
import ciphers.caesarcipher.CaesarCipher;
import ciphers.hillcipher.EncryptionDataHolderForHillCipher;
import ciphers.hillcipher.HillCipher;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import main.Main;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuickEncryptDecryptController extends Main implements Initializable {
    private static String currentMode = "Encryption";
    public TabPane mainTabPane;
    public Button setKey;
    public Button finishButton;
    public TextArea inputText;
    public TextArea inputTextDec;
    public TextArea outputText;
    public TextArea outputTextDec;
    public Tab decryptionTab;
    public Tab encryptionTab;

    public static Popup popup = new Popup();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(currentMode);
        if (currentMode == "Encryption") {
            finishButton.setText("Encrypt");
            mainTabPane.getSelectionModel().select(encryptionTab);
        } else if (currentMode == "Decryption") {
            finishButton.setText("Decrypt");
            mainTabPane.getSelectionModel().select(decryptionTab);
        }
    }

    public void finishButton_OnMouseClick() {
        System.out.println("On click is called.");
        System.out.println(currentMode);
        if (currentMode == "Encryption") {
            System.out.println("You are in Encryption mode");
            if (new EncryptionMode().getEncryptionMode() == "HILL_CIPHER") {
                System.out.println("Called!");
                if (new EncryptionKeyHolder().isHillSet) {
                    new HillCipher().encryptPlainText(inputText.getText(), new EncryptionKeyHolder().getHillCipherEncryptionKey(), new EncryptionKeyHolder().getHillCipherEncryptionKey().length);
                    outputText.setText(new EncryptionDataHolderForHillCipher().getCipherText());
                } else {
                    new Alert(Alert.AlertType.ERROR, "Key is not set. Set Hill Cipher key and try again", ButtonType.OK).show();
                }
            } else if (new EncryptionMode().getEncryptionMode() == "CAESAR_CIPHER") {
                System.out.println("Caesar cipher called.");
                System.out.println();
                if (new EncryptionKeyHolder().isCaesarSet) {
                    outputText.setText(new CaesarCipher().singleLevelEncryption(new HillCipher().removeSpecialCharactersRegex(inputText.getText()), new EncryptionKeyHolder().getCaesarCipherEncryptionKey()));
                } else {
                    new Alert(Alert.AlertType.ERROR, "Key is not set. Set Caesar Cipher key and try again", ButtonType.OK).show();
                }
            }
        } else if (currentMode == "Decryption") {
            System.out.println("You are in Decryption mode");
            if (new EncryptionMode().getEncryptionMode() == "HILL_CIPHER") {
                if (new EncryptionKeyHolder().isHillSet) {
                    new HillCipher().decryptPlainText(inputTextDec.getText(), new EncryptionKeyHolder().getHillCipherEncryptionKey());
                    outputTextDec.setText(new EncryptionDataHolderForHillCipher().getPlainText());
                } else {
                    new Alert(Alert.AlertType.ERROR, "Key is not set. Set Hill Cipher key and try again", ButtonType.OK).show();
                }
            }
        } else if (new EncryptionMode().getEncryptionMode() == "CAESAR_CIPHER") {
            if (new EncryptionKeyHolder().isCaesarSet) {
                if (JOptionPane.showConfirmDialog(null, "Would you like to try multi", "ATTENTION", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                    int level = Integer.valueOf(JOptionPane.showInputDialog("Enter level"));
                    System.out.println("Level is: " + level);
                    outputTextDec.setText(new CaesarCipher().multiLevelEncryption(new HillCipher().removeSpecialCharactersRegex(inputTextDec.getText()), new EncryptionKeyHolder().getCaesarCipherEncryptionKey(), level));
                } else {
                    System.out.println("Button No Selected");
                    outputTextDec.setText(new CaesarCipher().singleLevelEncryption(new HillCipher().removeSpecialCharactersRegex(inputTextDec.getText()), new EncryptionKeyHolder().getCaesarCipherEncryptionKey()));
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Key is not set. Set Caesar Cipher key and try again", ButtonType.OK).show();
            }
        }
    }

    public void setKey_OnMouseClick() {
        if (new EncryptionMode().getEncryptionMode() == "HILL_CIPHER") {
            new ShowPopup().showKeySetterPopup(popup, Main.mainWindow);
        } else if (new EncryptionMode().getEncryptionMode() == "CAESAR_CIPHER") {
            popup.setWidth(448);
            popup.setHeight(185);
            popup.centerOnScreen();
            Pane mainPane = new Pane();
            Pane titleBar = new Pane();
            Label title = new Label();
            Button btnOk = new Button("Ok");
            TextField textField = new TextField();
            textField.setPrefWidth(243);
            textField.setPrefHeight(25);
            textField.setLayoutX(103);
            textField.setLayoutY(109);
            textField.setStyle("-fx-border-color: #cfcfcf; -fx-padding: 5px 15px 5px 15px; -fx-background-color: #dfdfdf; -fx-background-radius: 25px; -fx-background-radius: 25px; -fx-border-radius: 100%; -fx-text-fill: black;");
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                Pattern pattern = Pattern.compile("[^1-9]");
                Matcher matcher = pattern.matcher(textField.getText());
                String newString = matcher.replaceAll("");
                textField.setText(newString);
                Tooltip tooltip = new Tooltip();
                tooltip.setText("Only numbers are allowed.");
                textField.setTooltip(tooltip);
            });
            title.setText("SET KEY");
            title.setFont(new Font(27));
            title.setLayoutX(177);
            title.setLayoutY(18);
            title.setTextFill(Color.valueOf("#1457a4"));
            mainPane.setPrefWidth(448);
            mainPane.setPrefHeight(185);
            mainPane.setLayoutX(0);
            mainPane.setLayoutY(0);
            mainPane.setStyle("-fx-background-color: #f2f2f2");
            mainPane.setEffect(new DropShadow());
            titleBar.setMinWidth(448);
            titleBar.setMinHeight(74);
            titleBar.setLayoutX(0);
            titleBar.setLayoutY(0);
            titleBar.setStyle("-fx-background-color: #dfdfdf");
            btnOk.setPrefSize(72, 25);
            btnOk.setLayoutX(189);
            btnOk.setLayoutY(146);
            btnOk.setStyle("-fx-background-color: #dfdfdf;\n" +
                    "    -fx-padding: 5px;\n" +
                    "    -fx-border-radius: 25px;\n" +
                    "    -fx-background-radius: 25px;");
            btnOk.setOnMouseClicked(event -> {
                if (textField.getText().length() > 0) {
                    Pattern pattern = Pattern.compile("[^1-9]");
                    Matcher matcher = pattern.matcher(textField.getText());
                    String newString = matcher.replaceAll("");
                    textField.setText(newString);
                    System.out.println("Filtered key: " + newString);
                    if (textField.getText().length() > 0) {
                        new EncryptionKeyHolder().setCaesarCipherEncryptionKey(Integer.valueOf(newString));
                        popup.hide();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "You must provide a key to continue. Make sure that your key is integer. You can press esc to exit.", ButtonType.OK).show();
                    }
                }
            });
            mainPane.getChildren().add(titleBar);
            mainPane.getChildren().add(textField);
            mainPane.getChildren().add(btnOk);
            titleBar.getChildren().add(title);
            popup.getContent().add(mainPane);
            popup.getContent().get(0).getStyleClass().add("../controllers/general/dialogStyle.css");
            popup.show(mainWindow);
        }
    }

    public void setCurrentMode(String mode) {
        currentMode = mode;
    }

    public void encryptionTab_OnSelectionChanged() {
        try {
            currentMode = "Encryption";
            finishButton.setText("Encrypt");
            inputTextDec.setText("");
            outputTextDec.setText("");
        } catch (Exception ex) {

        }

    }

    public void decryptionTab_OnSelectionChanged() {
        try {
            currentMode = "Decryption";
            finishButton.setText("Decrypt");
            inputText.setText("");
            outputText.setText("");
        } catch (Exception ex) {

        }
    }

    public String getCurrentMode() {
        return currentMode;
    }
}