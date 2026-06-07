package common;

import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author permi
 */
public class Main {

    public static final int ADVENT_YEAR = 2015; // https://adventofcode.com/2015
    public static final int CURRENT_DAY = 7;
    public static final boolean EXECUTE_ONLY_LAST_DAY = true;

    /**
     * Using reflection, dynamically create an instance of the advent day
     * classes of a given year. It also prints the execution time (including
     * loading and processing the input file).
     *
     * Set <code>EXECUTE_ONLY_LAST_DAY</code> to <code>true</code> to only
     * create an instance of the class of a given day.
     */
    public static void main(String[] args) throws Exception {

        try {
            for (int i = EXECUTE_ONLY_LAST_DAY ? CURRENT_DAY : 1; i <= CURRENT_DAY; i++) {
                String className = "Day" + i;

                long startTime = System.nanoTime();
                Class.forName("aoc" + ADVENT_YEAR + "." + className).getDeclaredConstructor().newInstance();
                long executionTime = (System.nanoTime() - startTime) / 1_000_000;

                System.out.println("Execution time: " + executionTime + "ms");
            }
        } catch (InvocationTargetException e) {
            e.getCause().printStackTrace();
        }

    }

}
