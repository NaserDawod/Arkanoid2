package temp;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * AnimationRunner.
 * this class is responsble of drawing the given animation by calling its doOneFrame function.
 *
 * @author Naser Dawod
 *  */
public class AnimationRunner {
    private GUI gui;        /* the gui to draw on */
    private int framesPerSecond;        /* the number of frames */
    private Sleeper sleeper;        /* the sleeper */

    /**
     * the constructor.
     * @param gui the giu to draw on*/
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * run - this function draws the given animation by calling its doOneFrame functions.
     * the loop will keep going until the animation send to stop using shouldStop function
     * @param animation the animation*/
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            /* draw the animation */
            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
