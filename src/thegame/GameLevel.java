package thegame;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import objects.Ball;
import objects.Sprite;
import objects.Block;
import objects.Collidable;
import objects.SpriteCollection;
import objects.Paddle;
import objects.ScoreIndicator;
import objects.Velocity;
import temp.KeyPressStoppableAnimation;
import temp.AnimationRunner;
import temp.Animation;
import temp.CountdownAnimation;
import temp.PauseScreen;
import java.awt.Color;
import java.util.List;

/**
 * thegame.Game class.
 * the class that runs the game, meaning creates all the objects ball/blocks/paddle.
 * by creating a objects.SpriteCollection and a thegame.GameEnvironment that holds all the objects
 * then runs them
 *
 * @author Naser Dawod
 *
 * */
public class GameLevel implements Animation {

    private SpriteCollection sprites;       /* all the created sprites */
    private GameEnvironment environment;        /* all the collidables */
    private GUI gui;
    private KeyboardSensor keyboard;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreListener;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private boolean winOrLose;
    private static final int BALL_X = 450;
    private static final int BALL_Y = 550;
    private static final int RADIOS = 5;
    private static final int PADDLE_X = 400;
    private static final int PADDLE_X2 = 100;
    private static final int PADDLE_Y = 560;

    private LevelInformation level;
    /**
     * the constructor - initialize the game.
     * @param score the score of player
     * @param gui the gui
     * @param level the level to play
     * @param keyboard the keyboard
     * @param runner the animation
     * @param winOrLose */
    public GameLevel(LevelInformation level, GUI gui, KeyboardSensor keyboard, AnimationRunner runner,
                     Counter score, boolean winOrLose) {
        this.winOrLose = winOrLose;
        this.level = level;
        this.gui = gui;
        this.keyboard = keyboard;
        this.runner = runner;
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        this.score = score;
        this.running = true;
    }

    /**
     * addCollidable - method that adds the given collidable to the environment.
     * @param c - the collidable*/
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * addSprite - method that adds the given sprite to the sprite collection.
     * @param s - the collidable*/
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }
    /**
     * removeCollidable - remove the given Collidable.
     * @param c  the Collidable to remove*/
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * removeSprite - remove the given sprite.
     * @param s thesprite to remove*/
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * initialize - Initializes a new game: create the Blocks and objects.Ball (and objects.Paddle)
     * and add them to the game.*/
    public void initialize() {
        remainingBlocks = new Counter();
        remainingBalls = new Counter();
        remainingBalls.increase(level.numberOfBalls());

        Sprite back = level.getBackground();
        back.addToGame(this);

        List<Velocity> speeds = level.initialBallVelocities();
        for (int i = 0; i < level.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(BALL_X, BALL_Y), RADIOS, Color.white, environment);
            ball.setVelocity(i+2,i+2);
            ball.addToGame(this);
        }

        Paddle p = createPaddle(level.paddleSpeed(), level.paddleWidth());
        p.addToGame(this);

        ballRemover = new BallRemover(this, remainingBalls);
        Block death = new Block(new Rectangle(new Point(0, 620), 800, 20), Color.darkGray);
        death.addToGame(this);
        death.addHitListener(ballRemover);
        this.blockRemover = new BlockRemover(this, this.remainingBlocks);
        this.scoreListener = new ScoreTrackingListener(score);

        Block[] bariars = new Block[3];
        bariars[0] = new Block(new Rectangle(new Point(0, 20), 800, 20), Color.darkGray);
        bariars[1] = new Block(new Rectangle(new Point(0, 0), 20, 600), Color.darkGray);
        bariars[2] = new Block(new Rectangle(new Point(780, 0), 20, 600), Color.darkGray);
        for (Block b : bariars) {
            b.addToGame(this);
        }

        List<Block> blocks = level.blocks();
        for (Block b : blocks) {
            b.addToGame(this);
            blockRemover.addBlock();
            b.addHitListener(blockRemover);
            b.addHitListener(scoreListener);
        }

        ScoreIndicator indicator = new ScoreIndicator(scoreListener);
        indicator.setLevelName(level.levelName());
        indicator.addToGame(this);
    }

    /**
     * objects.Paddle - creates a new paddle for the game.
     * @param speed the speed
     * @param width the width
     * @return - the new paddle*/
    public Paddle createPaddle(int speed, int width) {
        int x = width > 100 ? PADDLE_X2 : PADDLE_X;
        Rectangle rect = new Rectangle(new Point(x, PADDLE_Y), width, 20);
        int barrier = 780;
        int startBarrier = 20;
        return new Paddle(rect, Color.YELLOW, keyboard, barrier, speed, startBarrier);
    }

    /**
     * shouldStop - return if the loop should stop or not.
     * @return true/false*/
    @Override
    public boolean shouldStop() {
        return !this.running;
    }


    /**
     * draw one frame of the given animation.
     * @param d the drawSurface*/
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            /* pause the game using PauseScreen */
            KeyPressStoppableAnimation press = new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.keyboard));
            this.runner.run(press);
        }
        if (remainingBlocks.getValue() == 0) {
            // if the there is no more blocks the player move for the next level
            score.increase(100);
            this.winOrLose = true;
            this.running = false;
        }
        if (remainingBalls.getValue() == 0) {
            // if there is no more balls the player loses
            this.winOrLose = false;
            this.running = false;
        }
    }

    /**
     * getWinOrLose - return if the player is a winner or a loser.
     * @return true/false*/
    public boolean getWinOrLose() {
        return this.winOrLose;
    }

    /**
     * Run the game -- starts the game animation loop.*/
    public void run() {
        this.runner.run(new CountdownAnimation(2.0, 3, sprites));
        this.runner.run(this);
    }
}