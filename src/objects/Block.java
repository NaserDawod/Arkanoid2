package objects;

import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.STATE;
import listeners.HitListener;
import listeners.HitNotifier;
import thegame.GameLevel;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * class objects.Block with indicates a block in the game, the class has methods that allow him to.
 * change speed and draw block
 * @author Naser Dawod
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * objects.Block - the constructor.
     * @param rectangel - the block shape
     * @param color - block color
     */
    public Block(Rectangle rectangel, Color color) {
        this.rect = rectangel;
        this.color = color;
        this.hitListeners = new LinkedList<>();
    }


    /**
     * @return - the shape of block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * change the speed according to the point that the ball hits the rectangle.
     * @param collisionPoint - point that collisions occurs.
     * @param currentVelocity - velocity prior collision.
     * @return
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        Velocity v = new Velocity(dx, dy);
        Line[] lines = rect.getLines();
        for (Line l : lines) {
            if (collisionPoint.between(l.start(), l.end())) {
                if (l.state() == STATE.infinite) {
                    v = new Velocity(-dx, dy);
                } else {
                    v = new Velocity(dx, -dy);
                }
            }
        }
        this.notifyHit(hitter);
        return v;
    }

    /**
     * draw the block.
     * @param drawSurface the draw surface
     */
    @Override
    public void drawOn(DrawSurface drawSurface) {
        int x = (int) this.rect.getUpperLeft().getX();
        int y = (int) this.rect.getUpperLeft().getY();
        int width = (int) this.rect.getWidth();
        int height = (int) this.rect.getHeight();
        drawSurface.setColor(color);
        drawSurface.fillRectangle(x, y, width, height);
        drawSurface.setColor(Color.BLACK);
        drawSurface.drawRectangle(x, y, width, height);
    }

    /**
     * add the block to the game.
     * @param game - the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * removeFromGame - remove the block from the given game.
     * @param game the game*/
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * time passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * addHitListener - add the given HitListener to the list.
     * @param hl the HitListener*/
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * notifyHit - send a message if a ball hits a block.
     * @param hitter the ball*/
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new LinkedList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * removeHitListener - remove the given HitListener from the list.
     * @param hl the HitListener*/
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}

