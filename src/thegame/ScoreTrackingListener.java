package thegame;

import listeners.HitListener;
import objects.Ball;
import objects.Block;

/**
 * ScoreTrackingListener the class that tracks the score for the player.
 * adding points when a block is hit
 *
 * @author Naser Dawod
*/
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * the constructor.
     * @param scoreCounter the counter of the score*/
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * addLevel - add blocks the the given level.
     * @param quantity the number of blocks*/

    /**
     * hitEvent - add points to the score when a hit is made.
     * @param beingHit the block
     * @param hitter the ball*/
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }

    /**
     * getCurrentScore - return the current score.
     * @return the score*/
    public Counter getCurrentScore() {
        return currentScore;
    }
}
