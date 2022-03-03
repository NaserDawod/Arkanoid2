package thegame;

/**
 * Counter class - a simple counter.
 *
 * @author Naser Dawod
 *
 *  */
public class Counter {
    private int counter;        /* the coounter */

    /** the constructor.*/
    Counter() {

    }
    /** add number to current count.
     * @param number the number to add*/
    public void increase(int number) {
        this.counter += number;
    }
    /** subtract number from current count.
     * @param number the number to subtract*/
    public void decrease(int number) {
        this.counter -= number;
    }
    /** get current count.
     * @return the counter*/
    public int getValue() {
        return this.counter;
    }
}