package backgrounds;

import biuoop.DrawSurface;
import objects.Sprite;
import thegame.GameLevel;
import java.awt.Color;

/**
 * FinalFourBack.
 * this class represents a back ground for the FinalFour level in the game.
 *
 * @author Naser Dawod
 *
 */
public class FinalFourBack implements Sprite {

    /**
     * the constructor.*/
    public FinalFourBack() { }

    /**
     * drawOn - draw the sprite to the screen.
     * @param d - the surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        Color color1 = new Color(40, 150, 255);
        d.setColor(color1);
        d.drawRectangle(10, 10, 780, 600);
        d.fillRectangle(10, 10, 780, 600);

        d.setColor(Color.WHITE);
        for (int i = 70; i < 170; i += 10) {
            d.drawLine(i, 350, i - 55, 800);
        }

        d.setColor(Color.WHITE);
        for (int i = 550; i < 650; i += 10) {
            d.drawLine(i, 480, i - 55, 800);
        }

        Color color3 = new Color(145, 145, 145);
        d.setColor(color3);
        d.fillCircle(610, 510, 26);
        d.fillCircle(635, 500, 40);
        d.fillCircle(130, 380, 26);
        d.fillCircle(150, 355, 40);

        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(550, 480, 30);
        d.fillCircle(570, 520, 33);
        d.fillCircle(70, 350, 30);
        d.fillCircle(90, 385, 33);

        Color color2 = new Color(179, 179, 179);
        d.setColor(color2);
        d.fillCircle(595, 480, 35);
        d.fillCircle(110, 345, 35);

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
