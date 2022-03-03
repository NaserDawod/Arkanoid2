package levels;

import backgrounds.FinalFourBack;
import geometry.Rectangle;
import objects.Ball;
import objects.Block;
import objects.Sprite;
import objects.Velocity;
import java.util.LinkedList;
import java.util.List;
import java.awt.Color;

/**
 * DirectHit.
 * this class is used to create a level in the game, it implements the LevelInformation interface.
 * and create blocks, balls.., for the game level
 *
 * @author Naser Dawod
 *
 */
public class FinalFour implements LevelInformation {

    private static final int BALLS_NUM = 3;
    private static final int P_SPEED = 7;
    private static final int P_WIDTH = 100;
    private static final String LEVEL_NAME = "Final Four";
    private int blocksNum;

    /** numberOfBalls - return the number of the balls.
     * @return the balls number*/
    @Override
    public int numberOfBalls() {
        return BALLS_NUM;
    }

    /** The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return the list of Velocities*/
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> speeds = new LinkedList<>();
        speeds.add(Velocity.fromAngleAndSpeed(-10, 4));
        speeds.add(Velocity.fromAngleAndSpeed(-0, 4));
        speeds.add(Velocity.fromAngleAndSpeed(10, 4));
        return speeds;
    }

    /**
     * paddleSpeed - return the paddle speed.
     * @return paddle speed*/
    @Override
    public int paddleSpeed() {
        return P_SPEED;
    }

    /**
     * paddleWidth - return the paddle width.
     * @return paddle width*/
    @Override
    public int paddleWidth() {
        return P_WIDTH;
    }

    /** the level name will be displayed at the top of the screen.
     * @return the name*/
    @Override
    public String levelName() {
        return LEVEL_NAME;
    }

    /** Returns a sprite with the background of the level.
     * @return the background*/
    @Override
    public Sprite getBackground() {
        return new FinalFourBack();
    }

    /** The Blocks that make up this level, each block contains.
     * its size, color and location.*
     * @return the list of blocks */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList<>();
        /* create the cllidable blocks in the screen */
        int width = 76, height = 25;
        for (int i = 0, level = 0, y = 110; i < 7; ++i, y += 25, ++level) {
            Color color = Ball.randomColor();
            for (int j = 0, x = 20; j < 10; ++j, x += 76) {
                geometry.Point upperLeft = new geometry.Point(x, y);
                blocks.add(new Block(new Rectangle(upperLeft, width, height), color));
                blocksNum++;
            }
        }
        return blocks;
    }

    /** Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return the number of blocks*/
    @Override
    public int numberOfBlocksToRemove() {
        return blocksNum;
    }
}
