package helperclass.animation;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.util.Duration;

public class RotationAnimation {
    public <T extends Node> void rotateControlTo(T control, Double finalAngle, Double finalOpacity, int animationDuration, Interpolator interpolator, boolean isAutoReverse, boolean visibilityOnFinished) {
        KeyValue angleValue = new KeyValue(control.rotateProperty(), finalAngle, interpolator);
        KeyValue opacityKey = new KeyValue(control.opacityProperty(), finalOpacity, interpolator);
        KeyFrame keyframe = new KeyFrame(new Duration(animationDuration),  angleValue, opacityKey);
        Timeline timeline = new Timeline(keyframe);
        timeline.setAutoReverse(isAutoReverse);
        timeline.setOnFinished(event -> control.setVisible(visibilityOnFinished));
        timeline.play();
    }
}
