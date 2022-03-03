package geometry;

import java.util.Random;
/**
 * Class geometry.Point.
 *  this class called point represents a point on the screen (meaning x, y coordinates)
 *  na dit also had some methods to use
 *
 * @version 29 march 2001
 * @author Naser Dawod
 */
public class Point {

    private double x;     /* represents x coordinat on the screen */
    private double y;     /* represents y coordinat on the screen */

    /** constructor which receive tow variables.
     * @param x the x coordinates
     * @param y the y coordinates */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /** Return the x value of this point.
     * @return the x value */
    public double getX() {
        return this.x;
    }

    /** Return the y value of this point.
     * @return the y value */
    public double getY() {
        return this.y;
    }

    /** distance -- return the distance of this point to the other point.
     * @param other the point we will measure its distance
     * @return the distance between the points */
    public double distance(Point other) {
        double x2 = other.getX();
        double y2 = other.getY();
        return Math.sqrt(((x - x2) * (x - x2)) + ((y - y2) * (y - y2)));
    }

    /** equals -- return true is the points are equal, false otherwise.
     * @param other the point we compare
     * @return true in equals false otherwise */
    public boolean equals(Point other) {
        if (this.x == other.getX() && this.y == other.getY()) {
            return true;
        }
        return false;
    }

    /** set a new value the the x coordinates.
     * @param newX the new value */
    public void setX(double newX) {
        this.x = newX;
    }
    /** set a new value the the y coordinates.
     * @param newY the new value */
    public void setY(double newY) {
        this.y = newY;
    }

    /** between -- return true if the point is between the given tow points,
     *  false otherwise.
     *  @param start the starting point
     *  @param end the ending point
     *  @return true if between, false otherwise */
    public boolean between(Point start, Point end) {
        int xStart = (int) start.getX(), yStart = (int) start.getY();
        int xEnd = (int) end.getX(), yEnd = (int) end.getY();
        int pX = (int) this.x, pY = (int) this.y;
        if (((pX >= xStart && pX <= xEnd) || (pX <= xStart && pX >= xEnd))
                && ((pY >= yStart && pY <= yEnd) || (pY <= yStart && pY >= yEnd))) {
            return true;
        }
        return false;
    }

    /** randomPoint -- creates a random point t=in the given x, y range.
     * @param rangeX the range of the x coordinates
     * @param rangeY the range of the y coordinates
     * @param specifier to specifi the range
     * @return the new random point */
    public static Point randomPoint(int rangeX, int rangeY, int specifier) {
        Random rand = new Random();
        int x = rand.nextInt(rangeX - specifier);
        int y = rand.nextInt(rangeY - specifier);
        return new Point(x, y);
    }
}

