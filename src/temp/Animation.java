package temp;

import biuoop.DrawSurface;

/**
 * Animation.
 * this interface represents classes that draw objects on the screen by using doOneFrame method.
 *
 * @author Naser Dawod
 *  */
public interface Animation {

    /**
     * draw one frame of the given animation.
     * @param d the drawSurface*/
    void doOneFrame(DrawSurface d);
    /**
     * shouldStop - return if the loop should stop or not.
     * @return true/false*/
    boolean shouldStop();
}
