package com.mori;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: rjm
 * Date: 2/15/13
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class DiceFrequencyTest {

    private static final int TRIES = 1000000;
    private Random random = new Random();
    private Map<Integer, Integer> tally = new HashMap<Integer, Integer>();

    public DiceFrequencyTest() {
        for (int x = 1; x <= 6; x++) {
            tally.put(x, 0);
        }
    }

    private int roll() {
        return random.nextInt(6) + 1;
    }

    private void tally(int val) {
        try {
            Integer cnt = tally.get(val);
            cnt++;
            tally.put(val, cnt);
        } catch (NullPointerException e) {
            System.out.println("NPE " + val);
        }
    }

    private void printTally() {
        int total = 0;
        for (Map.Entry<Integer, Integer> entry : tally.entrySet()) {
            total += entry.getValue();
            System.out.println(entry.getKey() + " : " + entry.getValue() + " - " + (entry.getValue() * 100.0 / TRIES) + "%" );
        }
        System.out.println("Total " + total);
    }

    public static void main(String[] args) {
        DiceFrequencyTest test = new DiceFrequencyTest();
        for (int x = 0; x < TRIES; x++) {
            test.tally(test.roll());
        }
        test.printTally();
    }
}
