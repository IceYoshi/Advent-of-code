package aoc2015;


import common.Day;
import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author permi
 */
public class Day3 extends Day {

    private Set<Point> positionsVisited = new HashSet<>();

    private PlayerType activePlayer = PlayerType.SANTA;

    private enum PlayerType {
        SANTA,
        ROBO_SANTA
    };

    private Map<PlayerType, Point> playerPosition;

    public Day3() {
        super(FileType.Input);

        playerPosition = new HashMap<>();
        playerPosition.put(PlayerType.SANTA, new Point());
        playerPosition.put(PlayerType.ROBO_SANTA, new Point());

        // Every player delivers a present to the initial position
        for (int i = 0; i < PlayerType.values().length; i++) {
            markPosition(playerPosition.get(activePlayer));
        }

        for (int i = 0; i < input.length(); i++) {
            char token = input.charAt(i);

            switch (token) {
                case '^' ->
                    playerPosition.get(activePlayer).y++;
                case '>' ->
                    playerPosition.get(activePlayer).x++;
                case 'v' ->
                    playerPosition.get(activePlayer).y--;
                case '<' ->
                    playerPosition.get(activePlayer).x--;
                default -> {
                    System.out.println("Invalid character found in input");
                }
            }
            markPosition(playerPosition.get(activePlayer));
        }
        //printSet();

        System.out.println("Number of houses with at least one present: " + positionsVisited.size());
    }

    private void markPosition(Point position) {
        positionsVisited.add(new Point(position));
        selectNextPlayer();
    }

    private void selectNextPlayer() {
        activePlayer = PlayerType.values()[(activePlayer.ordinal() + 1) % PlayerType.values().length];
    }

    private void printSet() {
        for (Point p : positionsVisited) {
            System.out.println("(" + p.x + ", " + p.y + ")");
        }
    }

}
