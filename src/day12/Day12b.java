package day12;

import common.json.JSON;
import common.json.JsonAny;
import common.json.JsonArray;
import common.json.JsonNumber;
import common.json.JsonObject;
import common.json.JsonString;
import common.json.JsonAny.Type;

/**
 *
 * @author permi
 */
public class Day12b {

    private static final String BLACKLISTED_WORD = "red";
    
    public Day12b(String input) {
        JSON json = new JSON(input);
        double sum = crawler(json.value);

        IO.print("Sum of all numbers outside of blacklisted JSON objects: ");
        if ((int) sum == sum) {
            IO.println((int) sum);
        } else {
            IO.println((int) sum);
        }
    }

    /**
     * Traverses through all the JSON and adds up all numeric values, but skips
     * JSON objects that contain the value "red"
     */
    private double crawler(JsonAny any) {
        if (any == null) {
            IO.println("Invalid JSON");
            return -1;
        }

        switch (any.type) {
            case String, Boolean, Null -> {
                return 0;
            }
            case Number -> {
                return ((JsonNumber) any).value;
            }
            case Array -> {
                int sum = 0;
                JsonArray array = (JsonArray) any;
                for (int i = 0; i < array.value.size(); i++) {
                    sum += crawler(array.value.get(i));
                }
                return sum;
            }
            case Object -> {
                JsonObject object = (JsonObject) any;
                if (containsBlacklistedWord(object)) {
                    return 0;
                } else {
                    int sum = 0;
                    for (String key : object.value.keySet()) {
                        sum += crawler(object.value.get(key));
                    }
                    return sum;
                }
            }
        }
        return 0;
    }

    private boolean containsBlacklistedWord(JsonObject object) {
        for (String key : object.value.keySet()) {
            JsonAny any = object.value.get(key);
            if(any.type == Type.String && ((JsonString)any).value.equals(BLACKLISTED_WORD)) {
                return true;
            }
        }
        return false;
    }

}
