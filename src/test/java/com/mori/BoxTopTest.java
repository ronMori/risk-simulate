package com.mori;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rjm
 * Date: 2/18/13
 * Time: 12:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class BoxTopTest {

    private BoxTop boxTop;

    @Before
    public void setup() {
        boxTop = new BoxTop();
        boxTop.getPlayers().add(new Player(Player.TYPE.ATTACKER, 3));
        boxTop.getPlayers().add(new Player(Player.TYPE.DEFENDER, 2));
    }

    @Test
    public void testGetPlayers() throws Exception {
        List<Player> players = boxTop.getPlayers();
        boolean hasAttacker = false;
        boolean hasDefender = false;
        for (Player p : players) {
            if (p.isAttacker()) {
                hasAttacker = true;
            }
            if (p.isDefender()) {
                hasDefender = true;
            }
        }
        Assert.assertTrue(hasAttacker);
        Assert.assertTrue(hasDefender);
    }

    @Test
    public void testRoll() throws Exception {
        Player attacker = boxTop.getAttacker();
        Player defender = boxTop.getDefender();

        setDie(attacker, 6);
        setDie(defender, 6);
        boxTop.whoWins(attacker, defender);
        assertWinner(defender);
        assertLoser(attacker);
        resetPlayers(attacker, defender);

        setDie(attacker, 6);
        setDie(defender, 5);
        boxTop.whoWins(attacker, defender);
        assertWinner(attacker);
        assertLoser(defender);
        resetPlayers(attacker, defender);
    }

    @Test
    public void testSplitResult() throws Exception {
        Player attacker = boxTop.getAttacker();
        Player defender = boxTop.getDefender();
        setDie(attacker, 6);
        setDie(defender, 6);
        // defense rolls 1-6, attackers all 6
        defender.getDice().get(0).setValue(1);
        boxTop.whoWins(attacker, defender);

        Assert.assertTrue(attacker.getWins()==1);
        Assert.assertTrue(attacker.getRolls() == 2);
        Assert.assertTrue(defender.getWins()==1);
        Assert.assertTrue(defender.getRolls() == 2);
    }

    private void assertLoser(Player player) {
        Assert.assertTrue(player.getWins() == 0);
        Assert.assertTrue(player.getRolls() == 2);
    }

    private void assertWinner(Player player) {
        Assert.assertTrue(player.getWins() == 2);
        Assert.assertTrue(player.getRolls() == 2);
    }

    private void resetPlayers(Player attacker, Player defender) {
        attacker.reset();
        defender.reset();
    }

    private void setDie(Player p, int val) {
        for (Dice die : p.getDice()) {
            die.setValue(val);
        }
    }
}
