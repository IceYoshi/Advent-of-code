package day14;

import common.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author permi
 */
public class Day14 extends Day {

    private final List<Reindeer> reindeers = new ArrayList<>();
//    private static final int ELAPSED_TIME = 1000; // For Debug
    private static final int ELAPSED_TIME = 2503;

    public Day14() {
        super(FileType.Input);

        String[] lines = input.split("\n");
        for (String line : lines) {
            String[] tokens = line.split(" ");
            int speed = Integer.parseInt(tokens[3]);
            int flyingDuration = Integer.parseInt(tokens[6]);
            int restingDuration = Integer.parseInt(tokens[13]);

            reindeers.add(new Reindeer(speed, flyingDuration, restingDuration));
        }

//        // Part One
//        int maxDistance = 0;
//        for(Reindeer reindeer: reindeers) {
//            maxDistance = Math.max(maxDistance, reindeer.getFlyDistance(ELAPSED_TIME));
//        }
        // Part Two
        if (reindeers.isEmpty()) {
            IO.println("No reindeers to be found :(");
            return;
        }
        Reindeer leader = reindeers.get(0);
        for (int i = 0; i < ELAPSED_TIME; i++) {
            for (Reindeer reindeer : reindeers) {
                reindeer.advanceByOneSecond();
                if (leader.getCoveredDistance() < reindeer.getCoveredDistance()) {
                    leader = reindeer;
                }
            }
            leader.scoreOnePoint();
        }

        // Assign leader to the reindeer with the most points instead of covered distance
        for (Reindeer reindeer : reindeers) {
            if (leader.getScore() < reindeer.getScore()) {
                leader = reindeer;
            }
        }

//        // Part One
//        IO.println("Distance traveled by the winning reindeer: " + maxDistance);
        // Part Two
        IO.println("Highest score: " + leader.getScore());
    }

}
