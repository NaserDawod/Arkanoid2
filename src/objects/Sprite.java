package objects;

import biuoop.DrawSurface;
import thegame.GameLevel;

/**
 * objects.Sprite interface.
 * the classes that implements this interface must
 * contain the drawOn method and the timePassed method.
 *
 * @author Naser Dawod
 *
 */
public interface Sprite {

    /**
     * drawOn - draw the sprite to the screen.
     * @param d - the surface.
     */
    void drawOn(DrawSurface d);

    /**
     * timePassed - notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * addToGame - add the sprite to the given game.
     * @param game the game */
    void addToGame(GameLevel game);
}
