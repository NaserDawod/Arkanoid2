package objects;

import geometry.Point;

/**
 * objects.CollisionInfo class is used to hold the information of the
 * collision point and the collision object when called.
 *
 * @author Naser Dawod
 *
 */
public class CollisionInfo {

    private Point collisionPoint;       /* the collision geometry.Point */
    private Collidable collisionObject;     /* the collision Object */

    /**
     * The constructor:
     * constructs an object that includes collisionPoint and collisionObject.
     * @param collisionPoint - point of collision.
     * @param collisionObject - object of collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * This method returns the the point of collisions that happen.
     * @return collisionPoint - the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * return the object of that the collision happened with.
     * @return collisionObject - the closest object to collide with.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}

