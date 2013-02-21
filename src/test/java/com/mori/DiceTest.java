package com.mori;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: rjm
 * Date: 2/18/13
 * Time: 12:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class DiceTest {

    private Dice dice;

    @Before
    public void setup() {
        dice = new Dice();
    }

    @Test
    public void testRoll() throws Exception {
        dice.roll();
        Assert.assertTrue(dice.getValue() > 0);
        Assert.assertTrue(dice.getValue() < 7);
    }


    @Test
    public void testSetValue() throws Exception {
        for (int x = 1; x < 7; x++) {
            dice.setValue(x);
            Assert.assertEquals(dice.getValue(), x);
        }
    }

    @Test
    public void testCompareTo() throws Exception {
        Dice d1 = new Dice();
        Dice d2 = new Dice();
        d1.setValue(1);
        d2.setValue(2);
        Assert.assertEquals(1, d1.compareTo(d2));
        d1.setValue(2);
        Assert.assertEquals(0, d1.compareTo(d2));
        d1.setValue(3);
        Assert.assertEquals(-1, d1.compareTo(d2));
    }
}
