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
public class Green3Back implements Sprite {

    /**
     * the constructor.*/
    public Green3Back() { }

    /**
     * drawOn - draw the sprite to the screen.
     * @param d - the surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        Color color1 = new Color(60, 110, 0);
        d.setColor(color1);
        d.fillRectangle(0, 0, 800, 600);

        //creating the building
        d.setColor(Color.BLACK);
        d.fillRectangle(60, 450, 100, 150);

        d.setColor(Color.darkGray);
        d.fillRectangle(90, 370, 45, 80);

        Color color2 = new Color(110, 110, 110);
        d.setColor(color2);
        d.fillRectangle(107, 200, 10, 185);

        // creating building windows
        int y = 455;
        int x = 65;
        d.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            y = 455;
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(x, y, 10, 20);
                y += 30;
            }
            x += 20;
        }

        d.setColor(Color.ORANGE);
        d.fillCircle(110, 200, 12);

        d.setColor(Color.RED);
        d.fillCircle(110, 200, 9);
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
