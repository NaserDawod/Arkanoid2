package thegame;

import biuoop.KeyboardSensor;
import levels.LevelInformation;
import temp.AnimationRunner;
import java.util.List;
import biuoop.GUI;
import temp.KeyPressStoppableAnimation;
import temp.EndScreen;

/**
 * GameFlow.
 * this class is the class that will play all the levels by reciving a list of levels and drawing.
 * them on the given gui screen.
 *
 * @author Naser Dawod
 *  */
public class GameFlow {
    private AnimationRunner ar;     /* the animation runer to use */
    private KeyboardSensor keyboard;        /* the keyboard */
    private GUI gui;        /* the gui */
    private boolean winOrLose;

    /**
     * the constructor.
     * @param gui the giu
     * @param ar the animation runner
     * @param ks the keyboard*/
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.ar = ar;
        this.keyboard = ks;
        this.gui = gui;
        this.winOrLose = true;
    }

    /**
     * runLevels - the function recive a list of levels and runs all of them.
     * @param levels the levels*/
    public void runLevels(List<LevelInformation> levels) {
        Counter score = new Counter();        /* the score  */
        for (LevelInformation level : levels) {
            /* run all the levels in the list */
            GameLevel current = new GameLevel(level, gui, keyboard, ar, score, winOrLose);
            current.initialize();
            current.run();
            if (!current.getWinOrLose()) {
                /* if the player lose */
                KeyPressStoppableAnimation press = new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                        new EndScreen(keyboard, "Game Over. Your score is ", score.getValue()));
                ar.run(press);
                gui.close();
                return;
            }
        }
        /* the player win */
        KeyPressStoppableAnimation press = new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                new EndScreen(keyboard, "You Win! Your score is ", score.getValue()));
        ar.run(press);
        gui.close();
    }
}
