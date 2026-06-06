package aoc2015;

import common.Day;


/**
 *
 * @author permi
 */
public class Day1 extends Day {
    
    private int floor = 0;
    
    public Day1() {
        super(FileType.Input);
        
        boolean hasVisitedBasement = false;
        
        for(int i = 0; i < input.length(); i++) {
            char token = input.charAt(i);
            
            switch (token) {
                case '(' -> floor++;
                case ')' -> floor--;
            }
            
            if(floor == -1 && !hasVisitedBasement) {
                hasVisitedBasement = true;
                System.out.println("Basement entered for the first time at position: " + (i+1));
            }
        }        
        
        System.out.println("Floor after reaching the end: " + floor);
    }
    
}
