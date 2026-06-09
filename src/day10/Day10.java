package day10;

import common.*;

/**
 * Video from Numberphile about this sequence: https://www.youtube.com/watch?v=ea7lJkEhytA
 * According to the video, the sequence increases in length on average by about 1.3x (Conway's Constant) 
 * per iteration. For 50 iterations and an input size of 10, we'd expect to see a result string of about 
 * 5 million digits. Since the char data type in Java represents a 2-byte Unicode character, the result 
 * string takes about 10 MB memory to store.
 * 
 * @author permi
 */
public class Day10 extends Day {

    public static final int ITERATION_NUMBER = 50;

    public Day10() {
        super(FileType.Input);
        
        String result = input;
        IO.println("Progress: (" + ITERATION_NUMBER + " iterations)");
        for(int i = 1; i <= ITERATION_NUMBER; i++) {
            IO.print(i + "..\t");
            if(i % 10 == 0) IO.println();
            result = getLookAndSay(result);
        }
        IO.println();
        IO.println("Length of the output string after " + ITERATION_NUMBER + " iterations: " + result.length());
    }

    private String getLookAndSay(String input) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while(i < input.length()) {
            char digit = input.charAt(i);
            int digitCount = 1;
            i++;
            while(i < input.length() && digit == input.charAt(i)) {
                digitCount++;
                i++;
            }
            // Contrary to String, the + operator of char does not concatenate but 
            // rather adds up the values of chars. We force therefore an implicit String 
            // cast using an empty string to avoid this problem.
            result.append(digitCount);
            result.append(digit);
        }

        return result.toString();
    }

}
