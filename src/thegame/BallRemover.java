package thegame;

import listeners.HitListener;
import objects.Ball;
import objects.Block;

/**
 * BallRemover the class that removes the ball if if get out of the border.
 *
 * @author Naser Dawod
 *  */
public class BallRemover implements HitListener {
    private GameLevel game;      /* the game */
    private Counter remainingBalls;     /* the counter of the balls */

    /**
     * the constructor.
     * @param game the game
     * @param removedBlocks the counter of the balls*/
    BallRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBalls = removedBlocks;
    }

    /** Blocks that are hit should be removed.
     *  from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     * @param beingHit the block
     * @param hitter the ball*/
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
