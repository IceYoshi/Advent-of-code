package day09;

public class Connection {
    private String location1;
    private String location2;
    private int distance;

    public Connection(String location1, String location2, int distance) {
        this.location1 = location1;
        this.location2 = location2;
        this.distance = distance;
    }

    /**
     * Checks whether the connection is between the specified locations,
     * independently of the order of the parameters.
     */
    public boolean isBetween(String location1, String location2) {
        return this.location1.equals(location1) && this.location2.equals(location2)
                || this.location2.equals(location1) && this.location1.equals(location2);
    }

    public int getDistance() {
        return distance;
    }
}
