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
public class WideEasyBackground implements Sprite {

    /**
     * the constructor.*/
    public WideEasyBackground() { }

    /**
     * drawOn - draw the sprite to the screen.
     * @param d - the surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        Color color = new Color(219, 219, 0);
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 600);
        Color color1 = new Color(236, 236, 202);
        d.setColor(color1);
        for (int i = 0; i < 700; i += 10) {
            d.drawLine(200, 150, i, 300);
        }
        d.setColor(color);
        d.fillCircle(200, 150, 70);
        d.setColor(Color.ORANGE);
        d.fillCircle(200, 150, 60);
        d.setColor(Color.yellow);
        d.fillCircle(200, 150, 40);
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
