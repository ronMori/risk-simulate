package com.mori;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created with IntelliJ IDEA.
 * User: rjm
 * Date: 2/17/13
 * Time: 10:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player {

    private static final Logger LOG = LoggerFactory.getLogger(Player.class);

    enum TYPE {
        ATTACKER, DEFENDER
    }

    private final List<Dice> dice = new ArrayList<Dice>();
    private final TYPE type;

    private int rolls;
    private int wins;

    /**
     * new player.
     *
     * @param type      - Attacker or Defender
     * @param numOfDice - number of dice.
     */
    public Player(TYPE type, int numOfDice) {
        this.type = type;
        checkArgument(numOfDice > 0, "Num of dice must be positive: %s", numOfDice);
        if (this.type.equals(TYPE.ATTACKER)) {
            checkArgument(numOfDice <= 3, "Num of Attacker dice < 3: %s", numOfDice);
        }
        if (this.type.equals(TYPE.DEFENDER)) {
            checkArgument(numOfDice <= 2, "Num of Defender dice < 2: %s", numOfDice);
        }
        for (int x = 0; x < numOfDice; x++) {
            this.dice.add(new Dice());
        }
        LOG.info("Created " + type + " player with " + numOfDice + " dice.");
    }

    /**
     * return dice in sorted order.
     * @return list of dice
     */
    public List<Dice> getDice() {
        Collections.sort(dice);
        return dice;
    }

    public TYPE getType() {
        return type;
    }

    public boolean isAttacker() {
        return this.type.equals(TYPE.ATTACKER);
    }

    public boolean isDefender() {
        return this.type.equals(TYPE.DEFENDER);
    }

    public void roll() {
        for (Dice die : dice) {
            die.roll();
        }
    }

    public int getRolls() {
        return rolls;
    }

    public void setRolls(int rolls) {
        this.rolls = rolls;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void wins() {
        rolls++;
        wins++;
    }
    public void loses() {
        rolls++;
    }

    public void reset() {
        this.rolls = 0;
        this.wins = 0;
    }
}
