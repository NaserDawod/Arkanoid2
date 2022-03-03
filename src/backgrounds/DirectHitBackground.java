package backgrounds;

import biuoop.DrawSurface;
import objects.Sprite;
import thegame.GameLevel;
import java.awt.Color;

/**
 * DirectHitBackground.
 * this class represents a back ground for the DirectHit level in the game.
 *
 * @author Naser Dawod
 *
 */
public class DirectHitBackground implements Sprite {

    /**
     * the constructor.*/
    public DirectHitBackground() { }

    /**
     * drawOn - draw the sprite to the screen.
     * @param d - the surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.red);
        d.drawCircle(455, 175, 60);
        d.drawCircle(455, 175, 90);
        d.drawCircle(455, 175, 120);
        d.drawLine(455, 140, 455, 45);
        d.drawLine(455, 210, 455, 310);
        d.drawLine(430, 175, 320, 175);
        d.drawLine(480, 175, 590, 175);
    }

    /**
     * timePassed - notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * addToGame - add the sprite to the given game.
     */
    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}