package objects;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import thegame.GameLevel;
import thegame.ScoreTrackingListener;
import java.awt.Color;

/**
 * ScoreIndicator - the class the presents the block that shows the score.
 * of the player
 * *
 * @author Naser dawod
 * */
public class ScoreIndicator implements Sprite {
    private Rectangle rect;     /* the rectangel */
    private Color color;        /* the color */
    private ScoreTrackingListener score;
    private String levelName;

    /**
     * the constructor.
     * @param score the score of the player*/
    public ScoreIndicator(ScoreTrackingListener score) {
        this.rect = new Rectangle(new Point(0, 0), 800, 20);
        this.color = Color.WHITE;
        this.score = score;
    }

    /**
     * setLevelName - set the name of the current level.
     * @param name the name*/
    public void setLevelName(String name) {
        this.levelName = name;
    }

    /**
     * drawOn - draw the sprite to the screen.
     * @param d - the surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) this.rect.getUpperLeft().getX();
        int y = (int) this.rect.getUpperLeft().getY();
        int width = (int) this.rect.getWidth();
        int height = (int) this.rect.getHeight();
        d.setColor(color);
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
        d.drawText(320, 15, "Score: " + score.getCurrentScore().getValue(), 15);
        d.drawText(570, 15, "Level Name: " + levelName, 15);
    }

    /**
     * add the prite to the game.
     * @param game the game*/
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * timePassed - notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
