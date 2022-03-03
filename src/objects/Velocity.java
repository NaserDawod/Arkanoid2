package objects;

import geometry.Point;

import java.util.Random;

/**
 * Class objects.Velocity.
 *  this class called objects.Velocity and it indicates the objects.Velocity of the ball in class objects.Ball
 *  meaning it has a speed for both x, y directions, and also some more methods
 *  to implement the speed
 *
 * @version 29 march 2001
 * @author Naser Dawod
 */
public class Velocity {

    private double dx;      /* the x direction speed */
    private double dy;      /* the y direction speed */
    /** the constructor.
     * @param dx the x direction speed
     * @param dy the x direction speed*/
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /** the accessor to return the the x speed.
     * @return the x speed */
    public double getDx() {
        return this.dx;
    }

    /** the accessor to return the the y speed.
     * @return the y speed */
    public double getDy() {
        return this.dy;
    }

    /** fromAngleAndSpeed -- find the wanted objects.Velocity from a given angel and speed.
     * @param angle the angel
     * @param speed the speed
     * @return thhe new objects.Velocity*/
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
//        double dx = speed * Math.cos(angle);
//        double dy = speed * Math.sin(angle);
        double dx = speed * Math.cos(Math.toRadians(angle - 90));
        double dy = speed * Math.sin(Math.toRadians(angle - 90));
        return new Velocity(dx, dy);
    }

    /** randomVelocity -- create a random velocity in a given range.
     * @param range the range of speed
     * @return the new objects.Velocity*/
    public static Velocity randomVelocity(int range) {
        Random rand = new Random();
        int dx = rand.nextInt(range) + 1;
        int dy = rand.nextInt(range) + 1;
        return new Velocity(dx, dy);
    }

    /** applyToPoint -- Take a point with position (x,y) and return a new point.
     *  with position (x+dx, y+dy)
     *  @param p the given point
     *  @return the point after change*/
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * return the speed given dx, dy.
     * @param dx - the x speed
     * @param dy - the y speed
     * @return - the new velocity
     */
    public static double vectorSpeed(double dx, double dy) {
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }
}



