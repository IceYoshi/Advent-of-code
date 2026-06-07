package aoc2015;

import common.Day;

/**
 *
 * @author permi
 */
public class Day8 extends Day {

    public Day8() {
        super(FileType.Input);

        int totalShrinkingDifference = 0; // Part One
        int totalExpansionDifference = 0; // Part Two
        String lines[] = input.split("\n");
        for (String line : lines) {
            // Part One
            String shortenedLine = removeEscapeCharacters(line);
            //IO.println(line + " -> " + shortenedLine);
            
            totalShrinkingDifference += line.length() - shortenedLine.length();
            
            // Part Two
            String expandedLine = addEscapeCharacters(line);
            //IO.println(line + " -> " + expandedLine);
            
            totalExpansionDifference += expandedLine.length() - line.length();
        }
        
        IO.println("Total shrinking difference (Part One): " + totalShrinkingDifference);
        IO.println("Total expansion difference (Part Two): " + totalExpansionDifference);
    }

    /**
     * Backslash is both an escape character for String as well as for regex.
     * Therefore the need for 4 (!!) backslashes for each "real" backslash.
     * Essentially, we want to replace every double backslash with a single one
     * instead.
     */
    private String removeEscapeCharacters(String s) {
        s = s.replaceAll("\\\\\\\\", "X");              //  \\ -> X
        s = s.replaceAll("\\\\\"", "X");                //  \" -> X
        s = s.replaceAll("\"", "");                     //  "  ->
        s = s.replaceAll("\\\\x[0-9a-fA-F]{2}", "X");   //  \x followed by any 2 hexadecimal digits -> X
        
        return s;
    }
    
    private String addEscapeCharacters(String s) {
        // Order of replacement matters
        s = s.replaceAll("\\\\", "\\\\\\\\");     //  \ -> \\
        s = s.replaceAll("\"", "\\\\\"");         //  " -> \"
        s = "\"" + s + "\"";                      // Add quotes around the string
        
        return s;
    }

}
