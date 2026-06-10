package day14;

/**
 *
 * @author permi
 */
public class Reindeer {

    private final int speed; // in km/s
    private final int flyingDuration; // in seconds
    private final int restingDuration; // in seconds

    private int coveredDistance = 0;
    private int score = 0;

    private enum State {
        Flying,
        Resting
    }

    private State currentState;
    private int remainingTime;

    public Reindeer(int speed, int flyingDuration, int restingDuration) {
        this.speed = speed;
        this.flyingDuration = flyingDuration;
        this.restingDuration = restingDuration;

        currentState = State.Flying;
        remainingTime = flyingDuration;
    }

    public int getFlyDistance(int durationInSeconds) {
        // Instead of simulating every single second, find out how many
        // full flying + resting cycles there will be and use a formula to
        // directly calculate the flying distance.
        int cycleDuration = flyingDuration + restingDuration;
        coveredDistance = (durationInSeconds / cycleDuration) * flyingDuration * speed;
        int remainder = durationInSeconds % cycleDuration;

        // Resulting back to simulating every remaining second of the flight time
        while (remainder > 0) {
            advanceByOneSecond();
            remainder--;
        }
        
        return coveredDistance;
    }

    public void advanceByOneSecond() {

        if (remainingTime == 0) {
            toggleState();
        }
        if (currentState == State.Flying) {
            coveredDistance += speed;
        }
        remainingTime--;

    }
    
    public void scoreOnePoint() {
        score++;
    }

    private void toggleState() {
        switch (currentState) {
            case Flying -> {
                currentState = State.Resting;
                remainingTime = restingDuration;
            }
            case Resting -> {
                currentState = State.Flying;
                remainingTime = flyingDuration;
            }
        }
    }

    public int getCoveredDistance() {
        return coveredDistance;
    }

    public int getScore() {
        return score;
    }

}
