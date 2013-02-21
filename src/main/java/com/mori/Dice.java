package com.mori;

import java.util.Random;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * User: rjm
 * Date: 2/17/13
 * Time: 10:53 PM
 */
public class Dice implements Comparable<Dice> {

    private Random random = new Random();
    private int value;

    public void roll() {
        this.value = random.nextInt(6) + 1;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int newValue) {
        checkArgument(newValue > 0 && newValue <7, "dice value between 1-6");
        this.value = newValue;
    }

    /**
     * conparator on dice value.
     * @param d2
     * @return
     */
    @Override
    public int compareTo(Dice d2) {
        return (value < d2.getValue() ? 1 : (value > d2.getValue() ? -1 : 0));
    }
}
