package geometry;

import java.util.ArrayList;

/**
 * Class geometry.geometry.Rectangle.
 * that represents the geometry.geometry.Rectangle object that contains.
 *  a point - the upper left point of the rectangle position
 *  a point - the upper Right point of the rectangle position
 *  a point - the lower Left point of the rectangle position
 *  a point - the lower Right point of the rectangle position
 *  a width - the rectangle width
 *  a height - the rectangle height
 *
 *  @author Naser Dawod
 *  */
public class Rectangle {

    private Point upperLeft;        /* the upper left point */
    private Point upperRight;       /* the upper Right point */
    private Point lowerLeft;        /* the lower Left point */
    private Point lowerRight;       /* the lower Right point */
    private double width;       /* the width */
    private double height;      /* the height */


    /**
     * The constructor geometry.geometry.Rectangle.
     * Create a new rectangle with location and width/height.
     * @param upperLeft - the upper left point of the rectangle
     * @param width - the rectangle width
     * @param height - the rectangle height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
        complete();
    }

    /**
     * complete.
     * a function that is used to complete calculating the 3
     * other corner points of the rectangle
     * */
    private void complete() {

        /* get the x, y coardinits of the upper left point */
        double x = upperLeft.getX(), y = upperLeft.getY();

        /* add the height/width to find the other points */
        upperRight = new Point(x + width, y);
        lowerLeft = new Point(x, y + height);
        lowerRight = new Point(x + width, y + height);
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.

    /**
     * intersectionPoints.
     * return a (possibly empty) List of intersection points
     * with the specified line.
     * @param line - a given line.
     * @return an array list of all intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line)  {

        /* create a new list to save all the points */
        java.util.List<Point> points = new ArrayList<>();

        /* use get lines functions to get all the lines of the rectangle */
        Line[] lines = getLines();

        /* check for intersection using isIntersecting from geometry.Line class */
        for (Line l : lines) {
            if (l.isIntersecting(line)) {

                /* if there is an intersection add it to the list */
                points.add(l.intersectionWith(line));
            }
        }
        return points;
    }

    /**
     * getLines.
     * get lines function is used to get all the 4 lines of the rectangle
     * by adding the radios of the ball for each point
     * @return the array of lines*/
    public Line[] getLines() {
        Line[] lines = new Line[4];
//        geometry.Point new_upperLeft = new geometry.Point(upperLeft.getX() - radios, upperLeft.getY() - radios);
//        geometry.Point new_upperRight = new geometry.Point(upperRight.getX() + radios, upperRight.getY() - radios);
//        geometry.Point new_lowerLeft = new geometry.Point(lowerLeft.getX() - radios, lowerLeft.getY() + radios);
//        geometry.Point new_lowerRight = new geometry.Point(lowerRight.getX() + radios, lowerRight.getY() + radios);
//        lines[0] = new geometry.Line(new_upperLeft, new_upperRight);
//        lines[1] = new geometry.Line(new_upperLeft, new_lowerLeft);
//        lines[2] = new geometry.Line(new_upperRight, new_lowerRight);
//        lines[3] = new geometry.Line(new_lowerLeft, new_lowerRight);
        lines[0] = new Line(upperLeft, upperRight);
        lines[1] = new Line(upperLeft, lowerLeft);
        lines[2] = new Line(upperRight, lowerRight);
        lines[3] = new Line(lowerLeft, lowerRight);
        return lines;
    }

    /**
     * Return the width of the rectangle.
     * @return width - the width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the rectangle.
     * @return height - the height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     * @return location - the point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the upper-Right point of the rectangle.
     * @return location - the point.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * Returns the lower-Left point of the rectangle.
     * @return location - the point.
     */
    public Point getLowerLeft() {
        return this.lowerLeft;
    }

    /**
     * Returns the lower-Right point of the rectangle.
     * @return location - the point.
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }

    /**
     * getUpperLine the upper line of the rectangle.
     * @return the upper line of the rectangle
     */
    public Line getUpperLine() {
        Point newUpperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        Point newUpperRight = new Point(upperRight.getX(), upperRight.getY());
        return new Line(newUpperLeft, newUpperRight);
    }
}
