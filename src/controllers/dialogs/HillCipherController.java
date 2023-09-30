package controllers.dialogs;

import ciphers.EncryptionKeyHolder;
import ciphers.hillcipher.HillCipher;
import ciphers.hillcipher.MatrixValidator;
import controllers.general.EditorPageController;
import controllers.general.HomePageController;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HillCipherController extends EditorPageController implements Initializable {
    //region TextFields
    public TextField textField2_1;
    public TextField textField2_2;
    public TextField textField2_3;
    public TextField textField2_4;

    public TextField textField3_1;
    public TextField textField3_2;
    public TextField textField3_3;
    public TextField textField3_4;
    public TextField textField3_5;
    public TextField textField3_6;
    public TextField textField3_7;
    public TextField textField3_8;
    public TextField textField3_9;

    public TextField textField4_1;
    public TextField textField4_2;
    public TextField textField4_3;
    public TextField textField4_4;
    public TextField textField4_5;
    public TextField textField4_6;
    public TextField textField4_7;
    public TextField textField4_8;
    public TextField textField4_9;
    public TextField textField4_10;
    public TextField textField4_11;
    public TextField textField4_12;
    public TextField textField4_13;
    public TextField textField4_14;
    public TextField textField4_15;
    public TextField textField4_16;

    public TextField textField5_1;
    public TextField textField5_2;
    public TextField textField5_3;
    public TextField textField5_4;
    public TextField textField5_5;
    public TextField textField5_6;
    public TextField textField5_7;
    public TextField textField5_8;
    public TextField textField5_9;
    public TextField textField5_10;
    public TextField textField5_11;
    public TextField textField5_12;
    public TextField textField5_13;
    public TextField textField5_14;
    public TextField textField5_15;
    public TextField textField5_16;
    public TextField textField5_17;
    public TextField textField5_18;
    public TextField textField5_19;
    public TextField textField5_20;
    public TextField textField5_21;
    public TextField textField5_22;
    public TextField textField5_23;
    public TextField textField5_24;
    public TextField textField5_25;

    //endregion

    private Boolean isAllSet[];

    public Spinner spinner;
    public GridPane twoByTwoGrid;
    public GridPane threeByThreeGrid;
    public GridPane fourByFourGrid;
    public GridPane fiveByFiveGrid;

    public Button finishButton;
    public Button cancelButton;

    private static int currentMatrixSize = 3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory spinnerValueFactory = new SpinnerValueFactory() {
            private int size = 3;

            @Override
            public void decrement(int steps) {
                if (size > 2) {
                    size--;
                    currentMatrixSize = size;
                    spinner.getEditor().setText(String.valueOf(size) + " by " + String.valueOf(size) + " Matrix");
                    System.out.println(size);
                    setVisibilityOfGridAccordingly(size);
                }
            }

            @Override
            public void increment(int steps) {
                if (size < 5) {
                    size++;
                    currentMatrixSize = size;
                    spinner.getEditor().setText(String.valueOf(size) + " by " + String.valueOf(size) + " Matrix");
                    System.out.println(size);
                    setVisibilityOfGridAccordingly(size);
                }
            }
        };
        spinner.setValueFactory(spinnerValueFactory);
        spinner.getEditor().setText("3 by 3 Matrix");
    }

    private void setVisibilityOfGridAccordingly(int size) {
        if (size == 2) {
            twoByTwoGrid.setVisible(true);
            threeByThreeGrid.setVisible(false);
            fourByFourGrid.setVisible(false);
            fiveByFiveGrid.setVisible(false);
        } else if (size == 3) {
            twoByTwoGrid.setVisible(false);
            threeByThreeGrid.setVisible(true);
            fourByFourGrid.setVisible(false);
            fiveByFiveGrid.setVisible(false);
        } else if (size == 4) {
            twoByTwoGrid.setVisible(false);
            threeByThreeGrid.setVisible(false);
            fourByFourGrid.setVisible(true);
            fiveByFiveGrid.setVisible(false);
        } else if (size == 5) {
            twoByTwoGrid.setVisible(false);
            threeByThreeGrid.setVisible(false);
            fourByFourGrid.setVisible(false);
            fiveByFiveGrid.setVisible(true);
        }
    }

    public void checkAllFieldsAreFilled() {
        if (currentMatrixSize == 2) {
            checkFill(twoByTwoGrid);
        } else if (currentMatrixSize == 3) {
            checkFill(threeByThreeGrid);
        } else if (currentMatrixSize == 4) {
            checkFill(fourByFourGrid);
        } else if (currentMatrixSize == 5) {
            checkFill(fiveByFiveGrid);
        }
    }

    private void checkFill(GridPane mainGrid) {
        isAllSet = new Boolean[mainGrid.getChildren().size()];
        for (int i = 0; i < mainGrid.getChildren().size(); i++) {
            TextField textField = (TextField) mainGrid.getChildren().get(i);
            if (textField.getText().length() > 0) {
                textField.setStyle("-fx-border-color: green");
                isAllSet[i] = true;
            } else {
                textField.setStyle("-fx-border-color: red");
                isAllSet[i] = false;
            }
        }
    }

    public void finishKey_OnMouseClick() {
        try {
            checkAllFieldsAreFilled();
            for (int k = 0; k < isAllSet.length; k++) {
                System.out.println("b is: " + isAllSet[k].toString());
                if (isAllSet[k] == false || isAllSet[k] == null) {
                    new Alert(Alert.AlertType.WARNING, "Please fill the remaining fields (Highlighted red) before continuing.", ButtonType.OK).show();
                    break;
                } else if (isAllSet[k] == true) {
                    if (setupKey(generateKey(currentMatrixSize))) {
                        System.out.println("Key is generated");
                        System.out.println("-------------------------------");
                        EditorPageController.popup.hide();
                        double key[][] = new EncryptionKeyHolder().getHillCipherEncryptionKey();
                        for (int i = 0; i < key.length; i++) {
                            for (int j = 0; j < key.length; j++) {
                                System.out.println(key[i][j]);
                            }
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Something is wrong with your key, make sure your matrix is a square matrix and its determinant doesn't have a common factor with 26. ", ButtonType.OK);
                        alert.setX(50);
                        alert.setY(50);
                        alert.show();
                    }
                }
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Something is wrong with your key.", ButtonType.OK);
            alert.setX(50);
            alert.setY(50);
            alert.show();
        }
    }

    public void cancelKey_OnMouseClick() {
        EditorPageController.popup.hide();
    }

    private double[][] generateKey(int which) {
        double key[][] = new double[which][which];
        int j = 0;
        if (which == 2) {
            for (int i = 0; i < key.length; i++) {
                for (int k = 0; j < twoByTwoGrid.getChildren().size() && k < key.length; j++, k++) {
                    TextField textField = (TextField) twoByTwoGrid.getChildren().get(j);
                    key[i][k] = Integer.valueOf(textField.getText().toString());
                }
            }
        } else if (which == 3) {
            for (int i = 0; i < key.length; i++) {
                for (int k = 0; j < threeByThreeGrid.getChildren().size() && k < key.length; j++, k++) {
                    TextField textField = (TextField) threeByThreeGrid.getChildren().get(j);
                    key[i][k] = Integer.valueOf(textField.getText().toString());
                }
            }
        } else if (which == 4) {
            for (int i = 0; i < key.length; i++) {
                for (int k = 0; j < fourByFourGrid.getChildren().size() && k < key.length; j++, k++) {
                    TextField textField = (TextField) fourByFourGrid.getChildren().get(j);
                    key[i][k] = Integer.valueOf(textField.getText().toString());
                }
            }
        } else if (which == 5) {
            for (int i = 0; i < key.length; i++) {
                for (int k = 0; j < fiveByFiveGrid.getChildren().size() && k < key.length; j++, k++) {
                    TextField textField = (TextField) fiveByFiveGrid.getChildren().get(j);
                    key[i][k] = Integer.valueOf(textField.getText().toString());
                }
            }
        }
        return key;
    }

    private boolean setupKey(double key[][]) {
        if (new MatrixValidator().validateKey(new HillCipher().changeMatrixToKey(key), currentMatrixSize)) {
            new EncryptionKeyHolder().setHillCipherEncryptionKey(key);
            System.out.println();
            System.out.println();
            System.out.println();
            return true;
        } else {
            return false;
        }
    }
}
