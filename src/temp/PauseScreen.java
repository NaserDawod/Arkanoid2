package temp;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * PauseScreen.
 * this class represents the pause screen in the game which will be showen if p is pressed.
 * the class implements Animation interface so it could be drawn
 *
 * @author Naser Dawod
 *  */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;        /* the keyboard */
    private boolean stop;       /* stopping indicator */

    /**
     * the constructor.
     * @param k the keyboard*/
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * draw one frame of the given animation.
     * @param d the drawSurface*/
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * shouldStop - return if the loop should stop or not.
     * @return true/false*/
    public boolean shouldStop() {
        return this.stop;
    }
}
