package listeners;

import objects.Block;
import objects.Ball;

/**
 * HitListener calls :Objects that want to be notified of hit events.
 * should implement the HitListener interface,
 * and register themselves with a HitNotifier object using its addHitListener method
 *
 * @author Naser Dawod
 *
 * */
public interface HitListener {
    /** This method is called whenever the beingHit object is hit.
    * The hitter parameter is the Ball that's doing the hitting.
     * @param hitter the ball
     * @param beingHit the block*/
    void hitEvent(Block beingHit, Ball hitter);
}
