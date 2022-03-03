package objects;

import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import thegame.GameEnvironment;
import thegame.GameLevel;
import java.awt.Color;
import java.util.Random;

/**
 * Class objects.Ball.
 *  this class called objects.Ball represents a objects.Ball on the screen (having a center point and a radios)
 *  the ball has a objects.Velocity to make her move by changing the center of the ball
 *
 * @version 29 march 2001
 * @author Naser Dawod
 */
public class Ball implements Sprite {

    private Point center;     /* the center point of the point */
    private int r;    /* the radios */
    private Color color;      /* the color of the ball */
    private Velocity velocity;      /* the velocity of the ball */
    private GameEnvironment game;
    private boolean temp;


    /** objects.Ball -- the constructor that build the ball by receiving its center/radios/color.
     * @param center the center point
     * @param color the color of the ball
     * @param r the radios of the ball
     * @param newGame - the game*/
    public Ball(Point center, int r, Color color, GameEnvironment newGame) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.game = newGame;
        temp = false;
    }

    /** the accessor to return the the x coordinates.
     * @return the x coordinates */
    public int getX() {
        return (int) this.center.getX();
    }

    /** the accessor to return the the y coordinates.
     * @return the y coordinates */
    public int getY() {
        return (int) this.center.getY();
    }

    /** the accessor to return the the radios.
     * @return the radios */
    public int getSize() {
        return this.r;
    }

    /** the accessor to return the color.
     * @return the color */
    public Color getColor() {
        return this.color;
    }

    /**
     * removeCollidable remove the givin collidable from the enviroment.
     * @param c the collidable*/
    public void removeCollidable(Collidable c) {
        game.removeCollidable(c);
    }

    /**
     * removeFromGame - remove the ball from the game.
     * @param g the game*/
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /** setVelocity -- the constructor to set the velocity.
     * @param v the new velocity*/
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /** setVelocity -- the second constructor to set the velocity.
     * @param dx the new dx velocity
     * @param dy the new dy velocity*/
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /** the accessor to return the velocity.
     * @return the current velocity*/
    public Velocity getVelocity() {
        return this.velocity;
    }

    /** drawOn -- draw the ball on the given DrawSurface.
     *  @param surface the surface to draw on*/
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(getX(), getY(), this.r);
        surface.setColor(Color.BLACK);
        surface.drawCircle(getX(), getY(), this.r);
    }

    /**
     * the method moves the ball according to its location, radius,
     * velocity and the objects that can be collided with.
     */
    public void moveOneStep() {
        Point end = this.velocity.applyToPoint(this.center);        /* the target point */
        Line trajectory = new Line(this.center, end);       /* crate the trajectory line */

        /* find if there is a collision*/
        CollisionInfo c = game.getClosestCollision(trajectory);
        if (c == null) {

            /* if there is no collision move to the end point*/
            this.center = end;
        } else {
            double x = this.center.getX();
            double y = this.center.getY();
            Point collisionPoint = c.collisionPoint();
            if (this.center.getX() > collisionPoint.getX()) {
                x = collisionPoint.getX() + this.r;
            } else {
                x = collisionPoint.getX() - this.r;
            }
            if (this.center.getY() > c.collisionPoint().getY()) {
                y = collisionPoint.getY() + this.r;
            } else if (this.center.getY() < c.collisionPoint().getY()) {
                y = collisionPoint.getY() - this.r;
            } else {
                y = collisionPoint.getY();
            }
            Point t = new Point(x, y);
            /* if there is a Collision change the velocity */
            this.velocity = c.collisionObject().hit(this, collisionPoint, this.velocity);
            Line tempt = new Line(center, t);
            this.center = tempt.middle();
            if (game.getClosestCollision(tempt) == null) {
                this.center = t;
            }
        }
    }

    /** randomColor -- generates a random color.
     * @return the color*/
    public static Color randomColor() {
        Random rand = new Random();
        float r = rand.nextFloat(), g = rand.nextFloat(), b = rand.nextFloat();
        return new Color(r, g, b);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * add the ball to the game.
     * @param g - the new game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    /** changeDirection -- to change the direction of the ball by changing the velocity.
     *  in the opposite way  if it has reach the given rangeX/rangeY
     *  @param rangeX the x border
     *  @param rangeY the y border
     *  @param min the min border*/
    public void changeDirection(int rangeX, int rangeY, int min) {

        /* if the ball reached the x range*/
        if (this.getX() > rangeX || this.getX() < min) {
            Velocity v = this.getVelocity();
            double dx1 = -v.getDx();
            double dy1 = v.getDy();
            this.setVelocity(dx1, dy1);
        }

        /* if the ball reached the y range*/
        if (this.getY() > rangeY || this.getY() < min) {
            Velocity v = this.getVelocity();
            double dx1 = v.getDx();
            double dy1 = -v.getDy();
            this.setVelocity(dx1, dy1);
        }
    }
}

