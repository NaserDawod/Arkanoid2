package temp;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation.
 * this class is used to draw a given animation while until the given key is pressed.
 *
 * @author Naser Dawod
 *  */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;      /* the keyboard sensor */
    private String key;
    private Animation animation;        /* the animation to draw */
    private boolean shouldStop;
    private boolean isAlreadyPressed;

    /**
     * the constructor.
     * @param animation the animation
     * @param key the key to check
     * @param sensor the keyboard*/
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
        this.shouldStop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * draw one frame of the given animation.
     * @param d the drawSurface*/
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (sensor.isPressed(key)) {
            //shouldStop = isAlreadyPressed ? true : false;
            shouldStop = true;
            isAlreadyPressed = true;
        } else {
            isAlreadyPressed = false;
        }
    }

    /**
     * shouldStop - return if the loop should stop or not.
     * @return true/false*/
    @Override
    public boolean shouldStop() {
        return shouldStop;
    }
}
