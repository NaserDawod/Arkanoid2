package objects;

import biuoop.DrawSurface;
import java.util.LinkedList;
import java.util.List;

/**
 * objects.SpriteCollection class holds a collection of sprites.
 * sprites are game objects that can be drawn on the screen,
 * meaning the ball, blocks and paddle of the game.
 *
 * @author Naser Dawod
 *
 */
public class SpriteCollection {

    private List<Sprite> sprites;       /* the list to save all the objects */

    /**
     * addSprite - constructor of the sprite collection.
     * which creates an empty LinkedList of sprites.
     */
    public SpriteCollection() {
        this.sprites = new LinkedList<>();
    }

    /**
     * addSprite - adds a objects.Sprite object to the list of sprite objects.
     * @param s - the objects.Sprite object
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * removeSprite - remove the given sprite from the list.
     * @param s the given sprite*/
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * notifyAllTimePassed - call timePassed() on all Sprites.
     * in the collectionOfSprite array.
     */
    public void notifyAllTimePassed() {
        LinkedList<Sprite> copy = new LinkedList<>(sprites);
        for (Sprite s : copy) {
            s.timePassed();
        }
    }

    /**
     * drawAllOn - calls drawOn for all sprites.
     * in the collectionOfSprite list.
     * @param d - surface to be drawn.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}
