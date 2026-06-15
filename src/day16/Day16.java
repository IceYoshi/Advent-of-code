package day16;

import common.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author permi
 */
public class Day16 extends Day {

    private final Map<String, Integer> targetCompounds = new HashMap();

    public Day16() {
        super(FileType.Input);

        parseData("""
                  children: 3
                  cats: 7
                  samoyeds: 2
                  pomeranians: 3
                  akitas: 0
                  vizslas: 0
                  goldfish: 5
                  trees: 3
                  cars: 2
                  perfumes: 1
                  """);

        input = input.replaceAll(",", "");
        input = input.replaceAll(":", "");
        int auntNumber = 0;
        for (String aunt : input.lines().toList()) {
            String[] tokens = aunt.split(" ");
            if (matches(tokens)) {
                auntNumber = Integer.parseInt(tokens[1]);
                break;
            }
        }

        if (auntNumber > 0) {
            IO.println("Number of the aunt that gave the gift: " + auntNumber);
        } else {
            IO.println("No aunt matches the MFCSAM (My First Crime Scene Analysis Machine) values of the gift.");
        }
    }

    private void parseData(String data) {
        data = data.replaceAll(":", "");
        for(String line: data.lines().toList()) {
            String[] tokens = line.split(" ");
            targetCompounds.put(tokens[0], Integer.valueOf(tokens[1]));
        }
    }
    
    private boolean matches(String[] tokens) {
        for (int i = 2; i < tokens.length; i += 2) {
            String key = tokens[i];
            int value = Integer.parseInt(tokens[i + 1]);

            if (targetCompounds.containsKey(key)) {
                switch (key) {
                    case "cats", "trees" -> {
                        // Value has to be strictly bigger
                        if (value <= targetCompounds.get(key)) {
                            return false;
                        }
                    }
                    case "pomeranians", "goldfish" -> {
                        // Value has to be strictly smaller
                        if (value >= targetCompounds.get(key)) {
                            return false;
                        }
                    }
                    default -> {
                        // Value has to be the same
                        if (value != targetCompounds.get(key) ) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

}
