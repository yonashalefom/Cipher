package helperclass.animation;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

public class TranslationAnimation {
    public <T extends Node> void translateNodeTo(T control, Double finalX, Double finalY, Double finalOpacity, int animationDuration,
                                                 Interpolator interpolator, boolean isAutoReverse, boolean visibilityOnFinished) {
        KeyValue layoutXKey = new KeyValue(control.layoutXProperty(), finalX, interpolator);
        KeyValue layoutYKey = new KeyValue(control.layoutYProperty(), finalY, interpolator);
        KeyValue opacityKey = new KeyValue(control.opacityProperty(), finalOpacity, interpolator);
        KeyFrame keyframe = new KeyFrame(new Duration(animationDuration), layoutXKey, layoutYKey, opacityKey);
        Timeline timeline = new Timeline(keyframe);
        timeline.setAutoReverse(isAutoReverse);
        timeline.setOnFinished(event -> control.setVisible(visibilityOnFinished));
        timeline.play();
    }
}
