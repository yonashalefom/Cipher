package controllers.general;

import ciphers.EncryptionKeyHolder;
import ciphers.EncryptionMode;
import controllers.dialogs.QuickEncryptDecryptController;
import controllers.dialogs.ShowPopup;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import main.Main;
import views.PaneBasedMenuItem;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController extends Main implements Initializable {
    @FXML
    public Pane mainContainerPane;
    @FXML
    public Pagination homePageCipherSelector;

    public static Popup popup = new Popup();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPaginationItems();
    }

    public void setPaginationItems() {
        homePageCipherSelector.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
        PaneBasedMenuItem images[] = new PaneBasedMenuItem[7];

        VBox outerBox = new VBox();
        outerBox.setAlignment(Pos.CENTER);

        //Page Elements

        //region Hill Cipher
        images[0] = new PaneBasedMenuItem();
        images[0].setLeftOneButtonTitle("QUICK ENCRYPT");
        images[0].btnLeftOne.setOnMouseClicked(event -> {
            showQuickEditorPopup("HILL_CIPHER", "Encryption");
        });
        images[0].setLeftTwoButtonTitle("NEW PROJECT");
        images[0].btnLeftTwo.setOnMouseClicked(event -> openEditorPage("HILL_CIPHER"));
        images[0].setLeftThreeButtonTitle("HOW IT WORKS");
        images[0].setRightOneButtonTitle("QUICK DECRYPT");
        images[0].btnRightOne.setOnMouseClicked(event -> {
            showQuickEditorPopup("HILL_CIPHER", "Decryption");
        });
        images[0].setRightTwoButtonTitle("OPEN PROJECT");
        images[0].btnRightTwo.setOnMouseClicked(event -> {
            openEditorPage("HILL_CIPHER");
            new EditorPageController().openFile_OnMouseClick();
        });
        images[0].setRightThreeButtonTitle("ABOUT");
        // images[0].btnRightThree.setOnMouseClicked(event -> new ShowPopup().showAboutPopup(popup, mainWindow));
        images[0].setMainTitle("HILL CIPHER");

        //endregion

        //region Caesar Cipher
        images[1] = new PaneBasedMenuItem();
        images[1].setLeftOneButtonTitle("QUICK ENCRYPT");
        images[1].btnLeftOne.setOnMouseClicked(event -> {
            showQuickEditorPopup("CAESAR_CIPHER", "Encryption");
        });

        images[1].setLeftTwoButtonTitle("NEW PROJECT");
        images[1].btnLeftTwo.setOnMouseClicked(event -> openEditorPage("CAESAR_CIPHER"));
        images[1].setLeftThreeButtonTitle("HOW IT WORKS");
        images[1].setRightOneButtonTitle("QUICK DECRYPT");
        images[1].btnRightOne.setOnMouseClicked(event -> {
            showQuickEditorPopup("CAESAR_CIPHER", "Decryption");
        });
        images[1].setRightTwoButtonTitle("OPEN PROJECT");
        images[1].btnRightTwo.setOnMouseClicked(event -> {
            openEditorPage("CAESAR_CIPHER");
            new EditorPageController().openFile_OnMouseClick();
        });
        images[1].btnRightTwo.setOnMouseClicked(event -> {
            openEditorPage("CAESAR_CIPHER");
            new EditorPageController().openFile_OnMouseClick();
        });
        images[1].setRightThreeButtonTitle("HELP");
        images[1].setMainTitle("CAESAR CIPHER");
        images[1].centerPaneTitle.setStyle("-fx-font-size: 24px");
        //endregion

        //region Rail Fence Cipher
        images[2] = new PaneBasedMenuItem();
        images[2].setLeftOneButtonTitle("QUICK ENCRYPT");
        images[2].setLeftTwoButtonTitle("NEW PROJECT");
        images[2].setLeftThreeButtonTitle("HOW IT WORKS");
        images[2].setRightOneButtonTitle("QUICK DECRYPT");
        images[2].setRightTwoButtonTitle("OPEN PROJECT");
        images[2].setRightThreeButtonTitle("HELP");
        images[2].setMainTitle("RAIL FENCE CIPHER");
        images[2].centerPaneTitle.setStyle("-fx-font-size: 20px");
        //endregion

        //region Route Cipher
        images[3] = new PaneBasedMenuItem();
        images[3].setLeftOneButtonTitle("QUICK ENCRYPT");
        images[3].setLeftTwoButtonTitle("NEW PROJECT");
        images[3].setLeftThreeButtonTitle("HOW IT WORKS");
        images[3].setRightOneButtonTitle("QUICK DECRYPT");
        images[3].setRightTwoButtonTitle("OPEN PROJECT");
        images[3].setRightThreeButtonTitle("HELP");
        images[3].setMainTitle("ROUTE CIPHER");
        images[3].centerPaneTitle.setStyle("-fx-font-size: 24px");
        //endregion

        images[4] = new PaneBasedMenuItem();
        images[5] = new PaneBasedMenuItem();
        images[6] = new PaneBasedMenuItem();

        homePageCipherSelector.setPageFactory((Integer pageIndex) -> images[pageIndex]);
    }

    public void openEditorPage(String encryptionMode) {
        try {
            new EncryptionMode().setEncryptionMode(encryptionMode);
            mainContainerPane.getChildren().remove(0, mainContainerPane.getChildren().size());
            File file = new File("C:\\Users\\hp\\Desktop\\Personal Website Development\\Personal Data & Projects\\Projects\\Hill Cipher Project\\src\\views\\editor.fxml");
            mainContainerPane.getChildren().add(FXMLLoader.load(file.toURI().toURL()));
            System.out.println("Encryption mode has been set.");
            System.out.println("Encryption mode: " + new EncryptionMode().getEncryptionMode());
        } catch (IOException e) {
            new EncryptionMode().resetEncryptionMode();
        }
    }

    public void openHomePage() {
        try {
            File file = new File("C:\\Users\\hp\\Desktop\\Personal Website Development\\Personal Data & Projects\\Projects\\Hill Cipher Project\\src\\views\\home.fxml");
            Pane pane = FXMLLoader.load(file.toURI().toURL());
            mainWindow.setScene(new Scene(pane, 1200, 645));
//            mainContainerPane.getChildren().remove(0, mainContainerPane.getChildren().size());
//            File file = new File("C:\\Users\\Yonas Halefom\\IdeaProjects\\Hill Cipher Project\\src\\views\\home.fxml");
//            mainContainerPane.getChildren().add(FXMLLoader.load(file.toURI().toURL()));
            new EncryptionMode().resetEncryptionMode();
            new EncryptionKeyHolder().resetAllKeys();
        } catch (IOException e) {
        }
    }

    public void showQuickEditorPopup(String encryptionMode, String currentMode) {
        new EncryptionMode().setEncryptionMode(encryptionMode);
        new QuickEncryptDecryptController().setCurrentMode(currentMode);
        new ShowPopup().showQuickEncDecPopup(popup, mainWindow);
    }
}
