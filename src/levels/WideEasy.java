package levels;

import backgrounds.WideEasyBackground;
import geometry.Rectangle;
import objects.Ball;
import objects.Block;
import objects.Sprite;
import objects.Velocity;
import java.util.LinkedList;
import java.util.List;

/**
 * WideEasy.
 * this class is used to create a level in the game, it implements the LevelInformation interface.
 * and create blocks, balls.., for the game level
 *
 * @author Naser Dawod
 *
 */
public class WideEasy implements LevelInformation {

    private static final int BALLS_NUM = 8;
    private static final int P_SPEED = 4;
    private static final int P_WIDTH = 600;
    private static final String LEVEL_NAME = "Easy Wide";
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
        speeds.add(Velocity.fromAngleAndSpeed(-30, 4));
        speeds.add(Velocity.fromAngleAndSpeed(-20, 4));
        speeds.add(Velocity.fromAngleAndSpeed(-32, 4));
        speeds.add(Velocity.fromAngleAndSpeed(-40, 4));
        speeds.add(Velocity.fromAngleAndSpeed(45, 4));
        speeds.add(Velocity.fromAngleAndSpeed(30, 4));
        speeds.add(Velocity.fromAngleAndSpeed(25, 4));
        speeds.add(Velocity.fromAngleAndSpeed(0, 4));
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
        return new WideEasyBackground();
    }

    /** The Blocks that make up this level, each block contains.
     * its size, color and location.*
     * @return the list of blocks */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList<>();
        int width = 76, height = 25, y = 300;
        for (int i = 0, x = 20; i < 10; i++, x += width) {
            geometry.Point upperLeft = new geometry.Point(x, y);
            blocks.add(new Block(new Rectangle(upperLeft, width, height), Ball.randomColor()));
        }
        return blocks;
    }

    /** Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return the number of blocks*/
    @Override
    public int numberOfBlocksToRemove() {
        return 10;
    }
}
