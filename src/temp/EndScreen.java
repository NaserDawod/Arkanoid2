package temp;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * EndScreen.
 * this class represents the end screen in the game which will show the result of the game either win or lose.
 *
 * @author Naser Dawod
 *  */
public class EndScreen implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;
    private String saying;
    private int score;

    /**
     * the constructor.
     * @param k the keyboard
     * @param score the score of player
     * @param saying what to say*/
    public EndScreen(KeyboardSensor k, String saying, int score) {
        this.keyboard = k;
        this.saying = saying;
        this.score = score;
        this.stop = false;
    }

    /**
     * draw one frame of the given animation.
     * @param d the drawSurface*/
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, saying + score, 32);
    }

    /**
     * shouldStop - return if the loop should stop or not.
     * @return true/false*/
    @Override
    public boolean shouldStop() {
        return stop;
    }
}
