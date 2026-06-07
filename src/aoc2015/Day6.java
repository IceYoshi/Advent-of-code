package aoc2015;

import common.Day;
import java.awt.Point;


/**
 *
 * @author permi
 */
public class Day6 extends Day {

    public static final int SIZE = 1000;
    private boolean lightArray[][] = new boolean[SIZE][SIZE]; // Off by default
    private int dimmableLightArray[][] = new int[SIZE][SIZE]; // 0 by default
    
    public Day6() {
        super(FileType.Input);
        
        String commands[] = input.split("\n");
        for(String command: commands) {
            String tokens[] = command.split(" ");
            // Part One
            switch(tokens[0]) {
                case "turn" -> {
                    // turn on 952,417 through 954,845
                    // turn off 994,939 through 998,988
                    boolean state = tokens[1].equals("on");
                    setState(state, getPoint(tokens[2]), getPoint(tokens[4]));
                }
                case "toggle" -> {
                    //toggle 619,80 through 689,507
                    toggleState(getPoint(tokens[1]), getPoint(tokens[3]));
                }
                default -> System.out.println("Unknown command: " + tokens[0]);
            }
            
            // Part Two
            switch(tokens[0]) {
                case "turn" -> {
                    // turn on 952,417 through 954,845
                    // turn off 994,939 through 998,988
                    boolean state = tokens[1].equals("on");
                    changeBrightnessBy(state ? 1 : -1, getPoint(tokens[2]), getPoint(tokens[4]));
                }
                case "toggle" -> {
                    //toggle 619,80 through 689,507
                    changeBrightnessBy(2, getPoint(tokens[1]), getPoint(tokens[3]));
                }
                default -> System.out.println("Unknown command: " + tokens[0]);
            }
        }
        
        System.out.println("Number of lit lights: " + getNumberOfLitLights());
        System.out.println("Total brightness: " + getTotalBrightness());
        
    }
    
    private void setState(boolean state, Point startCoord, Point endCoord) {
        for(int x = startCoord.x; x <= endCoord.x; x++) {
            for(int y = startCoord.y; y <= endCoord.y; y++) {
                lightArray[x][y] = state;
            }
        }
    }
    
    private void toggleState(Point startCoord, Point endCoord) {
        for(int x = startCoord.x; x <= endCoord.x; x++) {
            for(int y = startCoord.y; y <= endCoord.y; y++) {
                lightArray[x][y] = !lightArray[x][y];
            }
        }
    }
    
    private void changeBrightnessBy(int value, Point startCoord, Point endCoord) {
        for(int x = startCoord.x; x <= endCoord.x; x++) {
            for(int y = startCoord.y; y <= endCoord.y; y++) {
                // Make sure brightness never gets negative
                dimmableLightArray[x][y] = Math.max(dimmableLightArray[x][y] + value, 0);
            }
        }
    }
    
    /**
     * The token is of the shape x,y (ex: 17,634)
     */
    private Point getPoint(String token) {
        String coords[] = token.split(",");
        return new Point(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
    }
    
    private int getNumberOfLitLights() {
        int counter = 0;
        for(int x = 0; x < SIZE; x++) {
            for(int y = 0; y < SIZE; y++) {
                if(lightArray[x][y]) counter++;
            }
        }
        return counter;
    }
    
    private int getTotalBrightness() {
        int counter = 0;
        for(int x = 0; x < SIZE; x++) {
            for(int y = 0; y < SIZE; y++) {
                counter += dimmableLightArray[x][y];
            }
        }
        return counter;
    }

}
