package helperclass.animation;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

public class ScaleAnimation {
    public <T extends Node> void scaleNodeTo(T control, Double finalScaleX, Double finalScaleY, Double finalOpacity, int animationDuration,
                                             Interpolator interpolator, boolean isAutoReverse, boolean visibilityOnFinished) {
        KeyValue scaleXKey = new KeyValue(control.scaleXProperty(), finalScaleX, interpolator);
        KeyValue scaleYKey = new KeyValue(control.scaleYProperty(), finalScaleY, interpolator);
        KeyValue opacityKey = new KeyValue(control.opacityProperty(), finalOpacity, interpolator);
        KeyFrame keyframe = new KeyFrame(new Duration(animationDuration), scaleXKey, scaleYKey, opacityKey);
        Timeline timeline = new Timeline(keyframe);
        timeline.setAutoReverse(isAutoReverse);
        timeline.setOnFinished(event -> control.setVisible(visibilityOnFinished));
        timeline.play();
    }
}
