package day15;

import common.*;
import java.util.ArrayList;
import java.util.List;

/**
 * If you consider this problem to be like a combination lock where you have n
 * digits and each digit can have p different values, you get a total number of
 * p^n combinations.
 *
 * For 4 digits, 100 values, that's 100.000.000 combinations.
 *
 * However, this does not take into account the fact that all 4 digits must add
 * up to exactly 100. This limitation reduces significantly the number of valid
 * combinations.
 *
 * @author permi
 */
public class Day15 extends Day {

    // Contains a list of property coefficients per ingredient
    private final List<int[]> ingredients = new ArrayList<>();

    // Amount of teaspoons per ingredient
    private final List<Integer> teaspoons = new ArrayList<>();

    public Day15() {
        super(FileType.Input);

        // Remove commas to make it easier to extract the numbers.
        input = input.replaceAll(",", "");

        String[] lines = input.split("\n");
        for (String line : lines) {
            String[] tokens = line.split(" ");

            ingredients.add(new int[]{
                Integer.parseInt(tokens[2]), // capacity
                Integer.parseInt(tokens[4]), // durability
                Integer.parseInt(tokens[6]), // flavor
                Integer.parseInt(tokens[8]), // texture
                Integer.parseInt(tokens[10]) // calories
            });
        }

        while (teaspoons.size() < ingredients.size()) {
            teaspoons.add(0);
        }

        int maxScore = createHighestScoreCookie(100);

        IO.println("Total score of the highest-scoring cookie: " + maxScore);
    }

    private int createHighestScoreCookie(int totalTeaspoonAmount) {
        return createHighestScoreCookie(totalTeaspoonAmount, 0, 0);
    }

    private int createHighestScoreCookie(int remainingTeaspoons, int index, int maxScore) {
        if (index == teaspoons.size() - 1) {
            teaspoons.set(index, remainingTeaspoons);
            return Math.max(maxScore, getScore());
        }

        for (int i = 0; i <= remainingTeaspoons; i++) {
            teaspoons.set(index, remainingTeaspoons - i);
            maxScore = Math.max(maxScore, createHighestScoreCookie(i, index + 1, maxScore));
        }

        return maxScore;
    }

    private int getScore() {
        if (ingredients.isEmpty() || ingredients.get(0).length == 0) {
            return 0;
        }

        // Filter by calories
        if (getCalories() != 500) {
            return 0;
        }

        int totalScore = 1;
        for (int propertyIndex = 0; propertyIndex < ingredients.get(0).length - 1; propertyIndex++) {
            int score = 0;
            for (int i = 0; i < ingredients.size(); i++) {
                score += ingredients.get(i)[propertyIndex] * teaspoons.get(i);
            }
            totalScore *= Math.max(0, score);
        }

        return totalScore;
    }

    private int getCalories() {
        int calories = 0;
        for (int i = 0; i < ingredients.size(); i++) {
            calories += ingredients.get(i)[4] * teaspoons.get(i);
        }
        return calories;
    }

}
