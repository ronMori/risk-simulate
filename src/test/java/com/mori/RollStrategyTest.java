package com.mori;

import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.NumberFormat;

/**
 * Rolling strategy tests.
 * User: rjm
 * Date: 2/18/13
 */
public class RollStrategyTest {

    private static final Logger LOG = LoggerFactory.getLogger(RollStrategyTest.class);

    private final NumberFormat numberFormat = NumberFormat.getIntegerInstance();
    private final NumberFormat pctFormat = NumberFormat.getPercentInstance();

    private static final int TURNS = 1000000;

    private void testCombo(int attackerDice, int defenderDice, String label) {
        LOG.info(label);
        BoxTop boxTop = new BoxTop();
        boxTop.getPlayers().add(new Player(Player.TYPE.ATTACKER, attackerDice));
        boxTop.getPlayers().add(new Player(Player.TYPE.DEFENDER, defenderDice));

        for (int x = 0; x < TURNS; x++) {
            boxTop.rollAndWhoWins();
        }
        LOG.info(label + " results for " + numberFormat.format(TURNS) + " turns.");
        showResults(boxTop.getAttacker());
        showResults(boxTop.getDefender());
    }

    @Test
    public void test3On2() throws Exception {
        testCombo(3, 2, "3 on 2");
    }

    @Test
    public void test3On1() throws Exception {
        testCombo(3, 1, "3 on 1");
    }

    @Test
    public void test2On2() throws Exception {
        testCombo(2, 2, "2 on 2");
    }

    @Test
    public void test2On1() throws Exception {
        testCombo(2, 1, "2 on 1");
    }


    @Test
    public void test1On1() throws Exception {
        testCombo(1, 1, "1 on 1");
    }

    @Test
    public void test1On2() throws Exception {
        testCombo(1, 2, "1 on 2");
    }

    @After
    public void after() {
        LOG.info("");
    }

    private void showResults(Player p) {
        LOG.info(p.getType().name() +
                ", wins: " + numberFormat.format(p.getWins()) +
                ", of possible: " + numberFormat.format(p.getRolls()) +
                " rolls.  Pct: " + pctFormat.format(p.getWins() * 1.0 / p.getRolls()));
    }
}
