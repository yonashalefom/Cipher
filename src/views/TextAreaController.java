package views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class TextAreaController implements Initializable {
    @FXML
    public ListView numberBar;
    @FXML
    public TextArea textArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textArea.setOnKeyTyped(event -> {
//            System.out.println(textArea.getParagraphs().size()); //get line number
//            System.out.println(textArea.getAnchor());
//            System.out.println(textArea.getCaretPosition());
        });
        textArea.setOnMouseClicked(event -> {
            System.out.println(textArea.getSelectedText().toString());
            StringBuffer oldText = new StringBuffer(textArea.getText());
            System.out.println(oldText);
            String toReplace = textArea.getSelectedText();
            oldText.indexOf(toReplace);
            oldText.replace(oldText.indexOf(toReplace), oldText.indexOf(toReplace) + toReplace.length(), "YONAS");
            System.out.println(oldText);
            textArea.setText(String.valueOf(oldText));
        });
        textArea.setOnMouseDragReleased(event -> {
            System.out.println(textArea.getSelectedText().toString());
        });
//        textArea.setOnMouseMoved(event -> {
//            System.out.println(textArea.getAnchor());
//            System.out.println(textArea.getCaretPosition());
//        });
    }
}
