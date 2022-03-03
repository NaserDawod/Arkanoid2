package temp;

import biuoop.DrawSurface;
import objects.SpriteCollection;
import java.awt.Color;

/**
 * CountdownAnimation.
 * this class is used to count the given number of time so the player could start the game.
 *
 * @author Naser Dawod
 *  */
public class CountdownAnimation implements Animation {

    private static final int X = 400;
    private static final int Y = 300;
    private double numOfSeconds;        /* seconds to wait */
    private int countFrom;      /* start counting */
    private SpriteCollection gameScreen;        /* objects to draw */
    private boolean start;
    private boolean first;
    private long startMillisecond;

    /**
     * the constructor.
     * @param countFrom the number to count
     * @param gameScreen the objects to draw
     * @param numOfSeconds the number of seconds*/
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;
        this.start = false;
        this.first = true;
    }

    /**
     * draw one frame of the given animation.
     * @param d the drawSurface*/
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        if (first) {
            first = false;
            startMillisecond = System.currentTimeMillis();
        }
        long currentMillisecond = System.currentTimeMillis();
        double singleCountLength = this.numOfSeconds / (double) this.countFrom;
        int currentNumber = (int) ((double) (1 + this.countFrom)
                - (double) (currentMillisecond - this.startMillisecond) / (singleCountLength * 1000.0D));
        d.setColor(Color.blue);
        d.drawText(X, Y, "" + currentNumber, 50);
        if ((double) (currentMillisecond - this.startMillisecond) > this.numOfSeconds * 1000.0) {
            this.start = true;
        }
    }

    /**
     * shouldStop - return if the loop should stop or not.
     * @return true/false*/
    @Override
    public boolean shouldStop() {
        return this.start;
    }
}
