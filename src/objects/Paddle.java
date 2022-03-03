package objects;

import thegame.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;

/**
 * objects.Paddle class is used to create a paddle that is used in the game.
 * it used for paddle movement, hit calculations and more aspects of the paddle object.
 *
 * @author Naser Dawod
 */
public class Paddle implements Sprite, Collidable {

    private biuoop.KeyboardSensor keyboard;     /* the keboard check */
    private Rectangle rect;     /* the shape of the paddle */
    private Color color;        /* the color od the paddle */
    private int barrier;        /* the barrier of the screen */
    private double speed;       /* the sped of the paddle */
    private int startBarrier;

    /**
     * objects.Paddle constructor is used to create a paddle.
     * @param rect - the rectangle information for paddle.
     * @param color - given color for the paddle.
     * @param keyboard - keyboard sensor for paddle control.
     * @param barrier - width of gui screen side barriers.
     * @param speed - the speed of the paddle.
     * @param startBarrier - start of screen
     */
    public Paddle(Rectangle rect, Color color, KeyboardSensor keyboard, int barrier, int speed, int startBarrier) {
        this.rect = rect;
        this.color = color;
        this.keyboard = keyboard;
        this.speed = speed;
        this.barrier = barrier;
        this.startBarrier = startBarrier;
    }

    /**
     * moveLeft - method is called once the left keyboard arrow is pressed.
     * it used for moving the object to the left side.
     */
    public void moveLeft() {
        double x = rect.getUpperLeft().getX();
        if (x - speed > this.startBarrier) {
            x -= speed;
        }
        Rectangle newR = new Rectangle(new Point(x, rect.getUpperLeft().getY()), rect.getWidth(), rect.getHeight());
        this.rect = newR;
    }

    /**
     * moveRight - method is called once the right keyboard arrow is pressed.
     * it used for moving the object to the right side.
     */
    public void moveRight() {
        double x = rect.getUpperLeft().getX();
        double width = rect.getWidth();
        if (x + width < barrier) {
            x += speed;
        }
        Rectangle newR = new Rectangle(new Point(x, rect.getUpperLeft().getY()), rect.getWidth(), rect.getHeight());
        this.rect = newR;
    }

    /**
     * timePassed method is used to check what keys are pressed each time the game loops.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * the method draws the paddle on the given drawSurface.
     * @param drawSurface - the surface.
     */
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(color);
        int x = (int) rect.getUpperLeft().getX(), y = (int) rect.getUpperLeft().getY();
        drawSurface.fillRectangle(x, y, (int) rect.getWidth(), (int) rect.getHeight());
        drawSurface.setColor(Color.BLACK);
        drawSurface.drawRectangle(x, y, (int) rect.getWidth(), (int) rect.getHeight());
    }

    /**
     * getCollisionRectangle - returns the rectangle that is the paddle.
     * @return rect - rectangle that is the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * objects.Velocity - method checks the place of the collusion with the object and
     * changes the velocity according to hit made.
     * @param collisionPoint - point of collision with object.
     * @param currentVelocity - velocity at given position.
     * @return the changed velocity after the hit was done.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line upperLine = rect.getUpperLine();     /* the upper line of the body */
        double width = upperLine.length() / 5;      /* divide the line length by 5 */
        double x = upperLine.start().getX(), y = upperLine.start().getY();

        /* create all the points */
        Point start = new Point(x, y);
        Point first = new Point(x + width, y);
        Point second =  new Point(x + (2 * width), y);
        Point third = new Point(x + (3 * width), y);
        Point forth = new Point(x + (4 * width), y);
        Point fifth = new Point(x + (5 * width), y);

        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        double vector = Velocity.vectorSpeed(dx, -dy);      /* the current velocity */

        /* check for the intersection point in what part of the line if exist
        * then return the new calculated velocity */
        if (collisionPoint.between(start, first)) {
            return Velocity.fromAngleAndSpeed(330, vector);
        } else if (collisionPoint.between(first, second)) {
            return Velocity.fromAngleAndSpeed(300, vector);
        } else if (collisionPoint.between(second, third)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (collisionPoint.between(third, forth)) {
            return Velocity.fromAngleAndSpeed(30, vector);
        } else if (collisionPoint.between(forth, fifth)) {
            return Velocity.fromAngleAndSpeed(60, vector);
        } else {
            /* if it dose not hit the top of the paddle the it hits the side
             * so return the speed according to the normal */
            return new Velocity(-dx, dy);
        }
    }

    /**
     * addToGame - adds the paddle to the collidable and the sprite lists.
     * @param g - the current game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
