import biuoop.GUI;

import levels.LevelInformation;
import levels.FinalFour;
import levels.WideEasy;
import levels.Green3;
import levels.DirectHit;
import temp.AnimationRunner;
import thegame.GameFlow;
import java.util.LinkedList;
import java.util.List;

/**
 * Ass3Game - the class that will run the game.
 * @author Naser Dawod
 *
 */
public class Ass6Game {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int LEVELS_NUM = 4;
    /**
     * main function to run the game.
     * @param args - the args
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", WIDTH, HEIGHT);
        GameFlow game = new GameFlow(new AnimationRunner(gui), gui.getKeyboardSensor(), gui);
        List<LevelInformation> level = new LinkedList<>();
//        level.add(null);
        level.add(new Green3());
        level.add(new WideEasy());
        level.add(new FinalFour());
        level.add(new DirectHit());

//        List<LevelInformation> levels = new LinkedList<>();
//        int len = 0;
//        try {
//            len = args.length;
//        } catch (Exception e) {
//
//        }
//        if (len != 0) {
//            for (int i = 0; i < args.length; ++i) {
//                if (validLevel(args[i])) {
//                    levels.add(level.get(Integer.parseInt(args[i])));
//                }
//            }
//        } else {
//            for (int i = 1; i < 5 ; ++i) {
//                levels.add(level.get(i));
//            }
//        }
        game.runLevels(level);
        gui.close();
    }

    /**
     * validLevel function to check if the given arg is a valid level number.
     * @param arg - the string to check
     * @return boolean true/false
     */
    private static boolean validLevel(String arg) {
        for (int i = 0; i < arg.length(); ++i) {
            /* check if the string is a number */
            if (arg.charAt(i) > '9' && arg.charAt(i) < '0') {
                return false;
            }
        }
        if (Integer.parseInt(arg) > 4 || Integer.parseInt(arg) < 1) {
            /* check if its a valid level */
            return false;
        }
        return true;
    }
}
