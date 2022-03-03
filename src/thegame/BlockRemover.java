package thegame;

import listeners.HitListener;
import objects.Ball;
import objects.Block;

/**
 * BlockRemover the class that removes the block if a ball hit it.
 *
 * @author Naser Dawod
 * */
public class BlockRemover implements HitListener {
    private GameLevel game;      /* the game */
    private Counter remainingBlocks;        /* the counter of the balls */

    /**
     * the constructor.
     * @param game the game
     * @param removedBlocks the counter of the blocks*/
    BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * addBlock - increase the number of blocks.*/
    public void addBlock() {
        remainingBlocks.increase(1);
    }
    /**
    * Blocks that are hit should be removed.
    * from the game. Remember to remove this listener from the block
    * that is being removed from the game
     * @param beingHit the block
     * @param hitter the ball*/
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBlocks.decrease(1);
        beingHit.removeFromGame(game);
        hitter.removeCollidable(beingHit);
    }
}
