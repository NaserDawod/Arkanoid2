package thegame;

import geometry.Line;
import geometry.Point;
import objects.Collidable;
import objects.CollisionInfo;

import java.util.LinkedList;
import java.util.List;

/**
 * thegame.GameEnvironment class that holds all objects that collide.
 * thus the thegame.GameEnvironment contains a collection - LinkedList that
 * contain all of the objects that are collidable.
 *
 * @author Naser Dawod
 *
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * the constructor.
     * initializes the environment empty list.
     */
    public GameEnvironment() {
        this.collidables = new LinkedList<>();
    }

    /**
     * addCollidable - adds a given collidable to the environment.
     * @param c - a objects.Collidable object.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * removeCollidable - remove the given Collidable from the list.
     * @param c - th Collidable to remove*/
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * getClosestCollision.
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory - a line.
     * @return objects.CollisionInfo - the information about the collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point start = trajectory.start();       /* the starting point */
        List<CollisionInfo> collisions = new LinkedList<>();        /* all the coming collisions*/

        for (Collidable c : collidables) {
            /* for every collidable in the enviroment find if it intersect with the line*/

            Point temp = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (temp != null) {

                /* if it intersect add if to the list */
                collisions.add(new CollisionInfo(temp, c));
            }
        }
        if (collisions.isEmpty()) {

            /* if theres no collisions then return null */
            return null;
        }


        CollisionInfo closest = collisions.get(0);      /* save the first collision */
        double distance = closest.collisionPoint().distance(start);     /* the distance */
        for (CollisionInfo c : collisions) {

            /* for ever collision that happen compare them to find the closest */
            double tempDistance = c.collisionPoint().distance(start);
            if (distance < tempDistance) {

                /* if the distance is smaller then save it */
                distance = tempDistance;
                closest = c;
            }
        }

        /* return the closest collision information */
        return closest;
    }
}
