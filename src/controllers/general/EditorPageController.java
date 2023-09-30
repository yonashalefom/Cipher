package controllers.general;

import ciphers.EncryptionKeyHolder;
import ciphers.EncryptionMode;
import ciphers.caesarcipher.CaesarCipher;
import ciphers.hillcipher.EncryptionDataHolderForHillCipher;
import ciphers.hillcipher.HillCipher;
import controllers.dialogs.ShowPopup;
import helperclass.animation.RotationAnimation;
import helperclass.animation.TranslationAnimation;
import javafx.animation.Interpolator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.web.PromptData;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import main.Main;

import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditorPageController extends Main implements Initializable {
    //region checkups
    boolean isEncryptionKeySet = false;
    //endregion

    //region general items
    public TextArea mainEditor;
    public Pane outputWindow;
    public SVGPath toHomeIcon;
    //region Output Window
    public TextArea outputWindowTextArea;
    public Pane outputCopy;
    public Pane outputSave;
    //endregion
    //region Tabs
    public Tab tabHome;
    public Tab tabEncryption;
    public Tab tabCurrentCipher;
    public Tab exportTab;
    //endregion

    public SVGPath outputWindowOpener;
    public Slider zoomSlider;
    //endregion

    //region Home Page Items
    public Pane openFile;
    public Pane copyText;
    public Pane cutText;
    public Pane pasteText;
    public Pane editText;
    public Pane zoomIn;
    public Pane zoomOut;
    //endregion
    //region Encryption Page Items
    public Pane encryptMessage;
    public Pane decryptMessage;
    public Pane checkEncryption;

    public static Popup popup = new Popup();

    //endregion
    //region Current Encryption Page Items
    public Pane setEncryptionKey;

    //endregion
    @Override

    public void initialize(URL location, ResourceBundle resources) {
        outputWindow.setVisible(false);
        outputWindow.setOpacity(0.0);
        outputWindow.setLayoutY(645 - 29);
        outputCopy.setDisable(true);
        outputSave.setDisable(true);
        System.out.println(new EncryptionMode().getEncryptionMode());
        if (new EncryptionMode().getEncryptionMode() == "HILL_CIPHER") {
            System.out.println("Called! HILL_CIPHER");
            tabCurrentCipher.setText("HILL CIPHER");
        } else if (new EncryptionMode().getEncryptionMode() == "CAESAR_CIPHER") {
            System.out.println("Called! CAESAR_CIPHER");
            tabCurrentCipher.setText("CAESAR CIPHER");
        } else {
            System.out.println("Not sure the mode that you are in.");
        }
    }

    //region Home Page Handlers
    public void copyText_OnMouseClick() {
        mainEditor.copy();
    }

    public void cutText_OnMouseClick() {
        mainEditor.cut();
    }

    public void pasteText_OnMouseClick() {
        mainEditor.paste();
    }

    public void openFile_OnMouseClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        fileChooser.setTitle("Open Text File");
        File file = fileChooser.showOpenDialog(mainWindow);
        if (file != null) {
            try {
                byte[] resume = Files.readAllBytes(file.toPath());
                mainEditor.setText(new String(resume));
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, "Unable to open file.", ButtonType.OK).show();
            }
        }
    }

    //region General Items Handler
    public void zoomIn_OnMouseClick() {
        if (mainEditor.getFont().getSize() >= 15 && mainEditor.getFont().getSize() < 51) {
            mainEditor.setFont(new Font(mainEditor.getFont().getSize() + 2));
            outputWindowTextArea.setFont(new Font(outputWindowTextArea.getFont().getSize() + 2));
            zoomSlider.setValue(mainEditor.getFont().getSize());
        }
        System.out.println(mainEditor.getFont().getSize());
    }

    public void setZoomOut_OnMouseClick() {
        if (mainEditor.getFont().getSize() > 15 && mainEditor.getFont().getSize() <= 51) {
            mainEditor.setFont(new Font(mainEditor.getFont().getSize() - 2));
            outputWindowTextArea.setFont(new Font(outputWindowTextArea.getFont().getSize() - 2));
            zoomSlider.setValue(mainEditor.getFont().getSize());
        }
        System.out.println(mainEditor.getFont().getSize());
    }

    public void zoomSlider_Handler() {
        mainEditor.setFont(new Font(zoomSlider.getValue()));
        outputWindowTextArea.setFont(new Font(zoomSlider.getValue()));
        System.out.println(mainEditor.getFont().getSize());
    }

    public void outputWindowOpener_OnMouseClick() {
        if (outputWindow.isVisible()) {
            outputCopy.setDisable(true);
            outputSave.setDisable(true);
            new TranslationAnimation().translateNodeTo(outputWindow, 0.0, 645.0 - 29.0, 1.0, 250, Interpolator.EASE_BOTH, false, false);
            new RotationAnimation().rotateControlTo(outputWindowOpener, 90.0, 1.0, 350, Interpolator.EASE_BOTH, false, true);
        } else {
            outputWindow.setVisible(true);
            new TranslationAnimation().translateNodeTo(outputWindow, 0.0, 179.0, 1.0, 250, Interpolator.EASE_BOTH, false, true);
            new RotationAnimation().rotateControlTo(outputWindowOpener, -90.0, 1.0, 350, Interpolator.EASE_BOTH, false, true);
            if (outputWindowTextArea.getText() != null || outputWindowTextArea.getText() != "") {
                outputCopy.setDisable(false);
                outputSave.setDisable(false);
            }
        }
    }

    public void outputCopy_OnMouseClick() {
        outputWindowTextArea.copy();
    }

    public void outputSave_OnMouseClick() throws IOException {
        if (outputWindowTextArea.getText() != null && outputWindowTextArea.getText() != "" && outputWindowTextArea.getText().length() >= 3) {
            System.out.println("Text: " + outputWindowTextArea.getText());
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter txtExt = new FileChooser.ExtensionFilter("Text Files", "*.txt");
            fileChooser.getExtensionFilters().add(txtExt);
            fileChooser.setTitle("Save File");
            File outputFile = fileChooser.showSaveDialog(mainWindow);
            FileOutputStream out = null;
            if (outputFile != null) {
                try {
                    out = new FileOutputStream(outputFile);
                    char dataToSave[] = outputWindowTextArea.getText().toCharArray();
                    for (char c : dataToSave) {
                        out.write(c);
                    }
                } finally {
                    if (out != null) {
                        out.close();
                    }
                }
            }
        }
    }

    public void mainEditor_OnFocus() {
        if (outputWindow.isVisible()) {
            outputWindowOpener_OnMouseClick();
        }
    }

    public void toHomeIcon_OnMouseClick() {
        new HomePageController().openHomePage();
    }
    //endregion
    //endregion

    public void setEncryptionKey_OmMouseClick() throws MalformedURLException {
        if (new EncryptionMode().getEncryptionMode() == "HILL_CIPHER") {
            new ShowPopup().showKeySetterPopup(popup, mainWindow);
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

    //region Encryption Page Handlers
    public void encryptMessage_OnMouseClick() {
        if (new EncryptionMode().getEncryptionMode() == "HILL_CIPHER") {
            if (new EncryptionKeyHolder().isHillSet) {
                new HillCipher().encryptPlainText(mainEditor.getText(), new EncryptionKeyHolder().getHillCipherEncryptionKey(), new EncryptionKeyHolder().getHillCipherEncryptionKey().length);
                outputWindowTextArea.setText(new EncryptionDataHolderForHillCipher().getCipherText());
                if (!outputWindow.isVisible()) {
                    outputWindowOpener_OnMouseClick();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Key is not set. Set Hill Cipher key and try again", ButtonType.OK).show();
            }
        } else if (new EncryptionMode().getEncryptionMode() == "CAESAR_CIPHER") {
            if (new EncryptionKeyHolder().isCaesarSet) {
                if (JOptionPane.showConfirmDialog(null, "Would you like to try multi-level encryption?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                    int level = Integer.valueOf(JOptionPane.showInputDialog("Enter level"));
                    System.out.println("Level is: " + level);
                    outputWindowTextArea.setText(new CaesarCipher().multiLevelEncryption(new HillCipher().removeSpecialCharactersRegex(mainEditor.getText()), new EncryptionKeyHolder().getCaesarCipherEncryptionKey(), level));
                    if (!outputWindow.isVisible()) {
                        outputWindowOpener_OnMouseClick();
                    }
                } else {
                    System.out.println("Button No Selected");
                    outputWindowTextArea.setText(new CaesarCipher().singleLevelEncryption(new HillCipher().removeSpecialCharactersRegex(mainEditor.getText()), new EncryptionKeyHolder().getCaesarCipherEncryptionKey()));
                    if (!outputWindow.isVisible()) {
                        outputWindowOpener_OnMouseClick();
                    }
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Key is not set. Set Caesar Cipher key and try again", ButtonType.OK).show();
            }
        }
    }

    public void decryptMessage_OmMouseClick() {
        if (new EncryptionMode().getEncryptionMode() == "HILL_CIPHER") {
            if (new EncryptionKeyHolder().isHillSet) {
                new HillCipher().decryptPlainText(mainEditor.getText(), new EncryptionKeyHolder().getHillCipherEncryptionKey());
                outputWindowTextArea.setText(new EncryptionDataHolderForHillCipher().getPlainText());
                if (!outputWindow.isVisible()) {
                    outputWindowOpener_OnMouseClick();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Key is not set. Set Hill Cipher key and try again", ButtonType.OK).show();
            }
        } else if (new EncryptionMode().getEncryptionMode() == "CAESAR_CIPHER") {
            if (new EncryptionKeyHolder().isCaesarSet) {
                if (JOptionPane.showConfirmDialog(null, "Would you like to try multi-level decryption", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                    int level = Integer.valueOf(JOptionPane.showInputDialog("Enter level"));
                    System.out.println("Level is: " + level);
                    outputWindowTextArea.setText(new CaesarCipher().multiLevelDecryption(new HillCipher().removeSpecialCharactersRegex(mainEditor.getText()), new EncryptionKeyHolder().getCaesarCipherEncryptionKey(), level));
                    if (!outputWindow.isVisible()) {
                        outputWindowOpener_OnMouseClick();
                    }
                } else {
                    System.out.println("Button No Selected");
                    outputWindowTextArea.setText(new CaesarCipher().singleLevelDecryption(new HillCipher().removeSpecialCharactersRegex(mainEditor.getText()), new EncryptionKeyHolder().getCaesarCipherEncryptionKey()));
                    if (!outputWindow.isVisible()) {
                        outputWindowOpener_OnMouseClick();
                    }
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Key is not set. Set Caesar Cipher key and try again", ButtonType.OK).show();
            }
        }
    }

    public void check_OnMouseClick() {
        if (new EncryptionMode().getEncryptionMode() == "HILL_CIPHER") {
            new ShowPopup().showReviewPopup(popup, mainWindow);
        } else if (new EncryptionMode().getEncryptionMode() == "CAESAR_CIPHER") {
            new ShowPopup().showReviewPopup(popup, mainWindow);
        }
    }
    //endregion
}