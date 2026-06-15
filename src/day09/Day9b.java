package day09;

import common.PermutationList;
import java.util.ArrayList;

/**
 * Second attempt at this challenge. This time, we'll generate all possible
 * permutations of paths that visit every location and keep track of the
 * shortest total distance. Since there might not be a connection between any
 * two distinct locations, we will be able to skip over those impossible
 * permutations.
 *
 * For n locations, there is a total of n! permutations. For n=8, that gives us
 * a total of 40320 paths to check.
 *
 * @author permi
 */
public class Day9b {

    private final PermutationList<String> locations = new PermutationList<>();
    private final ArrayList<Connection> connections = new ArrayList<>();

    private int shortestDistance = -1;
    private int longestDistance = -1;

    public static final boolean DEBUG_FLAG = false;
    private int numberOfEvaluatedRoutes = 0;

    public Day9b(String input) {
        String lines[] = input.split("\n");
        for (String line : lines) {
            // Example of a line: Faerun to Tristram = 65
            String tokens[] = line.split(" ");

            String location1 = tokens[0];
            String location2 = tokens[2];
            int distance = Integer.parseInt(tokens[4]);

            addIfAbsent(location1);
            addIfAbsent(location2);

            connections.add(new Connection(location1, location2, distance));
        }

        IO.println("Number of locations: " + locations.size());
        IO.println("Number of connections: " + connections.size());

        calculateAllRouteDistances();

        IO.println("Number of routes evaluated: " + numberOfEvaluatedRoutes);
        IO.println();
        IO.println("Distance of the shortest route going through all locations exactly once: "
                + shortestDistance);
        IO.println("Distance of the longest route going through all locations exactly once: "
                + longestDistance);

    }

    private void addIfAbsent(String location) {
        if (!locations.contains(location)) {
            locations.add(location);
        }
    }

    /**
     * Goes through all permutations of the locations and finds the shortest and
     * longest route.
     */
    private void calculateAllRouteDistances() {
        locations.forEachPermutation((currentPermutation) -> {
            int currentTraversalDistance = getPermutationRouteDistance(currentPermutation);

            if (currentTraversalDistance > 0) {

                if (shortestDistance < 0) {
                    shortestDistance = currentTraversalDistance;
                } else {
                    shortestDistance = Math.min(shortestDistance, currentTraversalDistance);
                }

                longestDistance = Math.max(longestDistance, currentTraversalDistance);

                if (DEBUG_FLAG) {
                    IO.println("Calculated distance: " + currentTraversalDistance);
                }

            }
        });
    }

    /**
     * Travels from location to location in the order that they appear in the
     * current permutation ArrayList and sums up the distances.
     *
     * @return The total distance from the first to the last location, while
     * traversing all locations in between. Returns -1 if no connection was
     * found between two adjacent locations to show that the distance is invalid
     * and shall not be taken into account.
     */
    private int getPermutationRouteDistance(ArrayList<String> currentPermutation) {
        numberOfEvaluatedRoutes++;
        int totalDistance = 0;
        for (int i = 0; i < currentPermutation.size() - 1; i++) {
            String location1 = currentPermutation.get(i);
            String location2 = currentPermutation.get(i + 1);

            boolean connectionFound = false;
            for (Connection connection : connections) {
                if (connection.isBetween(location1, location2)) {
                    totalDistance += connection.getDistance();
                    connectionFound = true;
                    break;
                }
            }
            if (!connectionFound) {
                return -1;
            }
        }
        return totalDistance;
    }

}
