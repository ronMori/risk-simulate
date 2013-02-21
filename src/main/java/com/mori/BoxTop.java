package com.mori;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;

/**
 * Rick box top - the place where we rollAndWhoWins.
 * User: rjm
 * Date: 2/18/13
 * Time: 12:23 AM
 */
public class BoxTop {

    private final List<Player> players = new ArrayList<Player>();

    public List<Player> getPlayers() {
        return players;
    }


    public void rollAndWhoWins() {
        checkState(players.size() == 2, "Need two and only two players to rollAndWhoWins, found: %s", players.size());
        Player attacker = null;
        Player defender = null;
        for (Player player : players) {
            if (player.isDefender()) {
                defender = player;
            }
            if (player.isAttacker()) {
                attacker = player;
            }
        }
        checkState(attacker != null, "No Attacker defined");
        checkState(defender != null, "No Defender defined");
        // rollAndWhoWins em
        attacker.roll();
        defender.roll();
        // tally it up
        whoWins(attacker, defender);
    }


    public Player getAttacker() {
        for (Player p : players) {
            if (p.isAttacker()) {
                return p;
            }
        }
        throw new IllegalStateException("No attacker found!");
    }

    public Player getDefender() {
        for (Player p : players) {
            if (p.isDefender()) {
                return p;
            }
        }
        throw new IllegalStateException("No defender found!");
    }

    public void whoWins(Player attacker, Player defender) {
        // go thru defenders dice and see who wins
        int numDefDice = defender.getDice().size();
        int numAttDice = attacker.getDice().size();

        int limit = numDefDice <= numAttDice ? numDefDice : numAttDice;
        for (int ndx = 0; ndx < limit; ndx++) {
            if (defender.getDice().get(ndx).getValue() >= attacker.getDice().get(ndx).getValue()) {
                // defense wins
                defender.wins();
                attacker.loses();
            } else {
                defender.loses();
                attacker.wins();
            }
        }
    }
}
