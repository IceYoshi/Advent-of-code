package day9;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This is a failed attempt at solving the challenge in a non-brute-force way.
 * While it can be used to get the shortest path from A to B, it does not
 * guarantee a pass through all locations. Since each location can only be
 * visited at most once, the generated structure is not usable due to the
 * high probability of star-intersections.
 * 
 * @author permi
 */
public class Day9a {

    private class Location {

        private String name;
        private boolean visited;
        private int traversalValue;

        public Location(String name) {
            this.name = name;
            traversalValue = Integer.MAX_VALUE;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj instanceof Location location) {
                return location.name.equals(name);
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 73 * hash + Objects.hashCode(this.name);
            return hash;
        }

    }

    private class Connection {

        private Location location1;
        private Location location2;
        private int distance;

        public Connection(Location location1, Location location2, int distance) {
            this.location1 = location1;
            this.location2 = location2;
            this.distance = distance;
        }

        public boolean contains(Location location) {
            return location1.equals(location) || location2.equals(location);
        }

    }

    private ArrayList<Location> locations = new ArrayList<>();
    private ArrayList<Connection> connections = new ArrayList<>();

    public Day9a(String input) {
        String lines[] = input.split("\n");
        for (String line : lines) {
            String tokens[] = line.split(" ");
            connections.add(new Connection(
                    createLocation(tokens[0]),
                    createLocation(tokens[2]),
                    Integer.parseInt(tokens[4])
            ));
        }

        int shortestRoute = Integer.MAX_VALUE;
        for (Location location : locations) {
            location.traversalValue = 0;
            traverse(location, 0);
            shortestRoute = Math.min(shortestRoute, getBiggestTraversalValue());

            // Reset location visited and traversalValue in order to repeat
            // the same traversal but starting from a different location
            for (Location l : locations) {
                l.visited = false;
                l.traversalValue = Integer.MAX_VALUE;
            }
        }

        IO.println("Shortest route: " + shortestRoute);
    }

    private Location createLocation(String name) {
        // Check if location already exists in order to avoid creating duplicates
        for (Location location : locations) {
            if (location.name.equals(name)) {
                return location;
            }
        }
        Location location = new Location(name);
        locations.add(location);
        return location;
    }

    private ArrayList<Connection> getConnectionsOf(Location location) {
        ArrayList<Connection> result = new ArrayList<>();
        for (Connection connection : connections) {
            if (connection.contains(location)) {
                result.add(connection);
            }
        }

        return result;
    }

    private void traverse(Location location, int traversedDistance) {
        location.visited = true;

        ArrayList<Connection> connections = getConnectionsOf(location);

        for (Connection connection : connections) {
            Location neighbor = connection.location1.equals(location) ? connection.location2 : connection.location1;
            int distance = traversedDistance + connection.distance;
            if (neighbor.traversalValue > distance) {
                neighbor.traversalValue = distance;
            }
            if (!neighbor.visited) {
                traverse(neighbor, distance);
            }
        }
    }

    private int getBiggestTraversalValue() {
        int traversalValue = 0;

        for (Location location : locations) {
            if (location.traversalValue > traversalValue) {
                traversalValue = location.traversalValue;

            }
        }
        return traversalValue;
    }

}
