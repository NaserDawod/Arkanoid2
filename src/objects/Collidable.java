package objects;

import geometry.Point;
import geometry.Rectangle;

/**
 * The following interface is used for every objects that can collide in the game.
 * it is used in order to know each objects location, the place of the hit
 * the shape of the object, and to draw the object
 *
 * @author Naser Dawod
 *
 */
public interface Collidable {

    /**
     * getCollisionRectangle.
     * Return the "collision shape" of the object.
     * @return object - the object shape we collided with.
     */
    Rectangle getCollisionRectangle();

    /**
     * hit.
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param collisionPoint - point that collisions occurs.
     * @param currentVelocity - velocity prior collision.
     * @param hitter the ball
     * @return the calculated velocity according to calculations.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
