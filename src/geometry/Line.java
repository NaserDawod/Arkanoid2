package geometry;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Class geometry.Line.
 *  this class called geometry.Line represents a geometry.Line on the screen (having a starting/ending points)
 *  and also having a couple of methods to find the length of the line and the middle point
 *  also if it intersect with a given line and finding the slope of the line...
 *
 * @version 29 march 2001
 * @author Naser Dawod
 */
public class Line {

    private Point start;      /* the start of the line */
    private Point end;    /* the end of the line */
    private STATE state;      /* the state in witch the line is */
    private double slope;     /* the slope of the line */

    /** the first constructor that build the line by receiving tow points.
     * @param start the starting point
     * @param end  the ending point */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.slope = slope();
    }

    /** the first constructor that build the line by receiving 4 variables.
     * @param x1 the x coordinates of starting point
     * @param y1 the y coordinates of starting point
     * @param x2 the x coordinates of ending point
     * @param y2 the y coordinates of ending point*/
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.slope = slope();
    }

    /** Returns the start point of the line.
     * @return starting point */
    public Point start() {
        return this.start;
    }

    /** Returns the ending point of the line.
     * @return ending point */
    public Point end() {
        return this.end;
    }

    /** Returns the state of the line.
     * @return the state of line */
    public STATE state() {
        return this.state;
    }

    /** Returns the slope of the line.
     * @return the slope of line */
    public double getSlope() {
        return this.slope;
    }

    /** Returns the length of the line.
     * @return the length of line */
    public double length() {
        return this.start.distance(this.end);
    }

    /** Returns the middle point of the line.
     * @return the middle point of line */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /** slope -- calculate the slope of the line and saves its state.
     * (normal/ infinite/ point)
     * @return the slope of the line */
    public double slope() {
        if (this.start.equals(this.end)) {
            this.state = STATE.point;
            return 0;
        }
        if (this.start.getX() == this.end.getX()) {
            this.state = STATE.infinite;
            return 0;
        } else if (this.start.getY() == this.end.getY()) {
            this.state = STATE.zeroSlope;
            return 0;
        } else {
            this.state = STATE.normal;
            double x = (start.getX() - end.getX());
            double y = (start.getY() - end.getY());
            return y / x;
        }
    }

    /** drawLine -- uses DrawSurface to draw the line on the screen.
     * @param d the DrawSurface to use */
    public void  drawLine(DrawSurface d) {
        int x = (int) this.start.getX();
        int y = (int) this.start.getY();
        int x2 = (int) this.end.getX();
        int y2 = (int) this.end.getY();
        d.setColor(Color.BLACK);
        d.drawLine(x, y, x2, y2);
    }

    /** isIntersecting -- given a line the function finds if they intersect or not.
     * using a couple helper functions
     * @param other the line
     * @return true if they  intersect, false otherwise*/
    public boolean isIntersecting(Line other) {

        /* if one of the lines is a point then use the pointIntersection function */
        if (this.state == STATE.point || other.state() == STATE.point) {
            return pointIntersection(other) != null;
        }

        /* if the tow lines are equal then the do not intersect*/
        if (equals(other)) {
            return false;
        }

        /* if one of the lines has an infinite slope the use infiniteIntersection function*/
        if (this.state == STATE.infinite || other.state == STATE.infinite) {
            return infiniteIntersection(other) != null;
        }

        /* if the slope of the lines is the same use sameSlopeIntersection function */
        if (this.slope == other.getSlope()) {
            return sameSlopeIntersection(other) != null;
        }

        /* if the tow lines are normal then find the intersection point using intersectAt function */
        return intersectAt(other) != null;
    }

    /** intersectionWith -- given a line the function finds the intersection point,
     * using a couple helper functions.
     * @param other the line
     * @return the intersection point\ null if they do not intersect */
    public Point intersectionWith(Line other) {

        /* if the lines do not intersect return null*/
        if (!isIntersecting(other)) {
            return null;
        }

        /* if one of the lines are point use pointIntersection function */
        if (this.state == STATE.point || other.state() == STATE.point) {
            return pointIntersection(other);
        }

        /* if one of the lines has infinite slope use infiniteIntersection function */
        if (this.state == STATE.infinite || other.state == STATE.infinite) {
            return infiniteIntersection(other);
        }

        /* if the slope of the lines is the same use sameSlopeIntersection function */
        if (this.slope == other.getSlope()) {
            return sameSlopeIntersection(other);
        }

        /* the lines are normal use intersectAt function */
        return intersectAt(other);
    }

    /** equals -- find if lines are equal and return true if they are false otherwise.
     * @param other the line to compare
     * @return true if equal false otherwise*/
    public boolean equals(Line other) {

        /* use the equals function from the geometry.Point class to ditermain if the
           starting/ending points are equal */
        if ((this.start.equals(other.start()) && this.end.equals(other.end()))
                || (this.start.equals(other.end()) && this.end.equals(other.start()))) {
            return true;
        }
        return false;
    }

    /** inside -- given a point find if exists inside the line and not equal to the.
     * starting/ending points
     * @param p the point to check
     * @return true if the poin */
    public boolean inside(Point p) {
        double xStart = this.start.getX(), yStart = this.start.getY();
        double xEnd = this.end.getX(), yEnd = this.end.getY();
        double pX = p.getX(), pY = p.getY(); /* save the coordinates in variables*/

        /* check if the coordinates of the given point are inside the start/end */
        if (((pX > xStart && pX < xEnd) || (pX < xStart && pX > xEnd))
                || ((pY > yStart && pY < yEnd) || (pY < yStart && pY > yEnd))) {
            return true;
        }
        return false;
    }

    /** intersectAt -- using the equation of the lines find the intersection point between
     *  them then use between function from point class to check its in the right range.
     * @param other the other line
     * @return the point if its in the range, null otherwise*/
    private Point intersectAt(Line other) {
        Point p1 = this.start;
        Point p2 = other.start();
        double m = other.getSlope();
        double numerator = p1.getY() - p2.getY() + (m * p2.getX()) -  (this.getSlope() * p1.getX());
        double denominator = m - this.getSlope();
        double x = numerator / denominator;
        double y = f(x);

        Point intersection = new Point(x, y);   /* save the new point */

        /* use between to check if it between the start/end*/
        if (intersection.between(this.start, this.end)
                && intersection.between(other.start(), other.end())) {
            return intersection;
        }
        return null;
    }

    /** f -- given a value x find the value of y on the line.
     * @param x the x value
     * @return the y value */
    public double f(double x) {
        Point temp = this.start;
        double m = this.slope;
        return m * (x - temp.getX()) + temp.getY();
    }

    /** pointIntersection -- if one of the lines is a point then the function.
     *  chek if the point is inside the other line and return the point if its inside
     *  false otherwise
     *  @param other the other line
     *  @return the point*/
    private Point pointIntersection(Line other) {

        /* if both lines are point chekc if they are equal */
        if (this.state == STATE.point && other.state() == STATE.point
                && this.start.equals(other.start)) {
            return this.start;
        }

        /* if this line is a point */
        if ((this.state == STATE.point)) {

            /* if the other has infinite slope*/
            if (other.state == STATE.infinite
                    && this.start.getX() == other.start.getX()) {
                if (this.start.between(other.start, other.end)) {
                    return this.start;
                }
                return null;
            }
            double otherY = other.f(this.start.getX());
            if (otherY == this.start.getY()) {
                return this.start;
            }
            return null;
        }
        if (this.state == STATE.infinite
                && other.start().getX() == this.start.getX()) {
            if (other.start.between(this.start, this.end)) {
                return other.start;
            }
            return null;
        }
        double thisY = f(other.start.getX());
        if (thisY == other.start.getY()) {
            return other.start;
        }
        return null;
    }

    /** infiniteIntersection -- if ont of the lines has infinite slope the function.
     *  find the intersection between him and the other line
     *  @param other the other line
     *  @return the intersection point*/
    private Point infiniteIntersection(Line other) {

        /* if both lines has infinite slope */
        if (other.state == STATE.infinite && this.state == STATE.infinite) {

            /* if they are not on the same x return null */
            if (this.start.getX() != other.start.getX()) {
                return null;
            }

            /* if one line is inside the other return false */
            if (inside(other.start) || inside(other.end)
                    || other.inside(this.start()) || other.inside(this.end)) {
                return null;
            } else if (this.start.equals(other.end) || this.start.equals(other.start)) {
                /* if the starting and ending points are equal return them */
                return this.start;
            } else if (this.end.equals(other.end) || this.end.equals(other.start)) {
                return this.end;
            }
            return null;
        }

        /* if the other line has infinite slope */
        if (other.state == STATE.infinite) {

            /* find if the intersection point between them is on the line */
            double x = other.start.getX();
            double thisY = f(x);
            Point p = new Point(x, thisY);
            if (p.between(this.start, this.end) && p.between(other.start, other.end)) {
                return p;
            }
            return null;
        }

        /* if the this line has infinite slope */
        double x = this.start.getX();
        double thisY = other.f(x);

        /* find if the intersection point between them is on the line */
        Point p = new Point(x, thisY);
        if (p.between(other.start, other.end) && p.between(this.start, this.end)) {
            return p;
        }
        return null;
    }


    /** sameSlopeIntersection -- if the tow lines has the same slope find if there,
     *  is only one intersection and return true otherwise return false.
     *  @param other the other line
     *  @return the point*/
    private Point sameSlopeIntersection(Line other) {

        /* if one of the points is inside the other line then return null */
        if (inside(other.start) || inside(other.end)
                || other.inside(this.start) || other.inside(this.end)) {
            return null;
        } else if (this.start.equals(other.end) || this.start.equals(other.start)) {
            /* if the starting/ending points are equal */
            return this.start;
        } else if (this.end.equals(other.end) || this.end.equals(other.start)) {
            /* if the starting/ending points are equal */
            return this.end;
        }
        return null;
    }

    /** randomLine -- generate a random line using randomPoint function from geometry.Point class.
     * @param rangeX the x range of the points
     * @param rangeY the y range of the points
     * @return the random line*/
    public static Line randomLine(int rangeX, int rangeY) {
        Point start = Point.randomPoint(rangeX, rangeY, 0);
        Point end = Point.randomPoint(rangeX, rangeY, 0);
        if (start.equals(end) || start.getX() == end.getX()) {
            return randomLine(rangeX, rangeY);
        }
        return new Line(start, end);
    }


    /**
     * return the closest intersection point between the.
     * line and rectangle
     * @param rect - the rectangle
     * @return the intersection point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line thisLine = new Line(start, end);
        java.util.List<Point> points = rect.intersectionPoints(thisLine);
        if (points.size() == 0) {
            return null;
        }
        Point temp = points.get(0);
        double min = start.distance(temp);
        for (Point p : points) {
            if (start.distance(p) < min) {
                temp = p;
                min = start.distance(p);
            }
        }
        return temp;
    }

}

