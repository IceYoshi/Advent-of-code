package day17;

import common.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author permi
 */
public class Day17 extends Day {

    private final List<Integer> containerSizes = new ArrayList<>();

    private int minimumNumberOfContainers = -1;
    private int combinationsOfMinimumNumberOfContainers = 0;

    private static final int TOTAL_LITERS = 150;

    public Day17() {
        super(FileType.Input);

        for (String line : input.lines().toList()) {
            containerSizes.add(Integer.valueOf(line));
        }

        IO.println("Number of different combinations to store all " + TOTAL_LITERS + " liters of eggnog: " + getAllCombinations(TOTAL_LITERS));
        IO.println("The minimum number of containers (" + minimumNumberOfContainers + ") has been reached a total of " + combinationsOfMinimumNumberOfContainers + " time(s).");

    }

    private int getAllCombinations(int remainingLiters) {
        //return getAllCombinations(0, remainingLiters, 0);
        return getAllCombinations(0, remainingLiters, 0, 0);
    }

    private int getAllCombinations(int startIndex, int remainingLiters, int totalCombinations, int depth) {
        if (remainingLiters > 0) {
            for (int i = startIndex; i < containerSizes.size(); i++) {
                // Make sure the subsequent recursive calls only work with the 
                // remaining list to avoid counting all permutations of 
                // equivalent combinations (e.g. [50,44] and [44,50])
                totalCombinations = getAllCombinations(i + 1, remainingLiters - containerSizes.get(i), totalCombinations, depth + 1);
            }
        } else {
            if (remainingLiters == 0) {
                if (minimumNumberOfContainers == -1 || minimumNumberOfContainers > depth) {
                    minimumNumberOfContainers = depth;
                    // Reset the counter back to 1, since we found a new minimum.
                    combinationsOfMinimumNumberOfContainers = 1;
                } else if(minimumNumberOfContainers == depth) {
                    combinationsOfMinimumNumberOfContainers++;
                }
                return totalCombinations + 1;
            }
        }
        return totalCombinations;
    }

}
