package day12;

import day12.json.JSON;

/**
 *
 * @author permi
 */
public class Day12b {

    public Day12b(String input) {
        JSON json = new JSON(input);
        IO.println("Sum of all numbers outside of blacklisted JSON objects: " + crawler(json));
    }
    
    /**
     * Traverses through all the JSON and adds up all numeric values, but skips
     * JSON objects that contain the value "red"
     */
    private int crawler(JSON json) {
        return 0;
    }

}
