package levels;

import objects.Block;
import objects.Sprite;
import objects.Velocity;

import java.util.List;

/**
 * LevelInformation.
 * this interface is used to represnts the diffrent calsses that hold all the levels of the game.
 * which are responsable of creating a balls/blocks..
 *
 * @author Naser Dawod
 *
 */
public interface LevelInformation {

    /** numberOfBalls - return the number of the balls.
     * @return the balls number*/
    int numberOfBalls();

    /** The initial velocity of each ball.
    * Note that initialBallVelocities().size() == numberOfBalls()
     * @return the list of Velocities*/
    List<Velocity> initialBallVelocities();

    /**
     * paddleSpeed - return the paddle speed.
     * @return paddle speed*/
    int paddleSpeed();

    /**
     * paddleWidth - return the paddle width.
     * @return paddle width*/
    int paddleWidth();

    /** the level name will be displayed at the top of the screen.
     * @return the name*/
    String levelName();

    /** Returns a sprite with the background of the level.
     * @return the background*/
    Sprite getBackground();

    /** The Blocks that make up this level, each block contains.
    * its size, color and location.*
    * @return the list of blocks */
    List<Block> blocks();

    /** Number of blocks that should be removed
    * before the level is considered to be "cleared".
    * This number should be <= blocks.size();
     * @return the number of blocks*/
    int numberOfBlocksToRemove();
}