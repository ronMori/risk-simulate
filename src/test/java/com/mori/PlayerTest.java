package com.mori;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: rjm
 * Date: 2/17/13
 * Time: 11:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerTest {


    @Test
    public void testGetDice() throws Exception {
        for (int x = 1; x <= 3; x++) {
            Player p = new Player(Player.TYPE.ATTACKER, x);
            Assert.assertEquals(p.getDice().size(), x);
        }
        for (int x = 1; x <= 2; x++) {
            Player p = new Player(Player.TYPE.DEFENDER, x);
            Assert.assertEquals(p.getDice().size(), x);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroDice() throws Exception {
        Player p = new Player(Player.TYPE.ATTACKER, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAttackerFourDice() throws Exception {
        Player p = new Player(Player.TYPE.ATTACKER, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDefender3Dice() throws Exception {
        Player p = new Player(Player.TYPE.DEFENDER, 3);
    }

    @Test
    public void testIsAttacker() throws Exception {
        Player p = new Player(Player.TYPE.ATTACKER, 1);
        Assert.assertTrue(p.isAttacker());
    }

    @Test
    public void testIsDefender() throws Exception {
        Player p = new Player(Player.TYPE.DEFENDER, 1);
        Assert.assertTrue(p.isDefender());
    }

    @Test
    public void testDiceSorting() throws Exception {
        Player p = new Player(Player.TYPE.ATTACKER, 2);
        for (int x = 0; x < 100; x++) {
            p.roll();
            int val = 7;
            for (Dice dice : p.getDice()) {
                Assert.assertTrue(dice.getValue() <= val);
                val = dice.getValue();
            }
        }
    }
}
