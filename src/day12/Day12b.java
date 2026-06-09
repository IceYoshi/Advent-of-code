package day12;

import day12.json.JSON;

/**
 *
 * @author permi
 */
public class Day12b {

    public Day12b(String input) {
        JSON jsonParser = new JSON(input);
        IO.println(jsonParser);
    }

}
