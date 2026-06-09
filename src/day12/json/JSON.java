package day12.json;

/**
 * Any: Numerical, String, Boolean, Null, Array, Object
 *
 * 1) Number -1 3 3.1
 *
 * 2) String "Hi" ""
 *
 * 3) Boolean true false
 *
 * 4) Null null
 *
 * 5) Array: [Any, Any, ...] [] [-1, 3] ["A", "B", ""] [["A", "B"], ["C", "D"]]
 *
 * 6) Object: "key":Any {} {"name":"banana", "color":"yellow"}
 *
 *
 * @author permi
 */
public class JSON {

    public final JsonAny value;

    private final String input;
    private int pointer = 0;

    public JSON(String input) {
        this.input = input;
        value = createAny();
    }

    private JsonAny createAny() {
        JsonAny result;
        switch (input.charAt(pointer)) {
            // 1) Number
            case '-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' ->
                result = createNumber();
            // 2) String
            case '"' ->
                result = createString();
            // 3) Boolean (must be lowercase)
            case 't', 'f' ->
                result = createBoolean();
            // 4) Null (must be lowercase)
            case 'n' ->
                result = createNull();
            // 5) Array
            case '[' ->
                result = createArray();
            // 6) Object
            case '{' ->
                result = createObject();
            // Invalid JSON
            default ->
                result = null;
        }
        return result;
    }

    /**
     * This method does not completely check if the number is valid in 
     * regards of the dot (for floating point numbers)
     */
    private JsonNumber createNumber() {
        String numberCharSet = "-0123456789.";
        StringBuilder sb = new StringBuilder();
        while (!reachedEndOfInput() && numberCharSet.contains(String.valueOf(input.charAt(pointer)))) {
            if (!sb.isEmpty() && input.charAt(pointer) == '-') {
                // A minus can only be present at the beginning of the number
                return null;
            }
            sb.append(input.charAt(pointer));
            pointer++;
        }
        return new JsonNumber(Double.parseDouble(sb.toString()));
    }

    private JsonString createString() {
        StringBuilder sb = new StringBuilder();
        // Advancing pointer by 1 since it's currently pointing at the start double quote
        pointer++;
        while (!reachedEndOfInput() && input.charAt(pointer) != '"') {
            sb.append(input.charAt(pointer));
            pointer++;

            // Handle escape sequence using a backslash ( Ex: \" )
            if (!reachedEndOfInput() && input.charAt(pointer - 1) == '\\') {
                sb.append(input.charAt(pointer));
                pointer++;
            }
        }
        if (input.charAt(pointer) != '"') {
            // A string needs to end with a double quote
            return null;
        }
        // Advancing pointer by 1 since it's currently pointing at the end double quote
        pointer++;
        return new JsonString(sb.toString());
    }

    private JsonBoolean createBoolean() {
        if (pointer + 3 < input.length() && input.substring(pointer, pointer + 4).equals("true")) {
            pointer += 4;
            return new JsonBoolean(true);
        }

        if (pointer + 4 < input.length() && input.substring(pointer, pointer + 5).equals("false")) {
            pointer += 5;
            return new JsonBoolean(false);
        }
        // Read invalid boolean or reached EOF
        return null;
    }

    private JsonNull createNull() {
        if (pointer + 3 < input.length() && input.substring(pointer, pointer + 4).equals("null")) {
            pointer += 4;
            return new JsonNull();
        }
        // Read invalid null or reached EOF
        return null;
    }

    /**
     * This method does not completely check if the array is valid.
     * For instance, [,] or ["A",,,] would both be considered legal.
     */
    private JsonArray createArray() {
        JsonArray array = new JsonArray();

        // Advancing pointer by 1 since it's currently pointing at [
        pointer++;

        while (!reachedEndOfInput() && input.charAt(pointer) != ']') {
            // Remove spaces if available
            while (!reachedEndOfInput() && input.charAt(pointer) == ' ') {
                pointer++;
            }

            // Reached end of array
            if (!reachedEndOfInput() && input.charAt(pointer) == ']') {
                break;
            }

            if (!reachedEndOfInput() && input.charAt(pointer) == ',') {
                pointer++;
                continue;
            }

            array.value.add(createAny());
        }
        if (!reachedEndOfInput() && input.charAt(pointer) != ']') {
            // Invalid JSON
            return null;
        }
        // Advancing pointer by 1 since it's currently pointing at ]
        pointer++;
        return array;
    }

    private JsonObject createObject() {
        JsonObject object = new JsonObject();
        
        // Advancing pointer by 1 since it's currently pointing at {
        pointer++;
        while (!reachedEndOfInput() && input.charAt(pointer) != '}') {
            // Remove spaces if available
            while (!reachedEndOfInput() && input.charAt(pointer) == ' ') {
                pointer++;
            }
            
            // Reached end of array
            if (!reachedEndOfInput() && input.charAt(pointer) == '}') {
                break;
            }
            
            // Invalid JSON
            if(reachedEndOfInput() && input.charAt(pointer) != '"') {
                return null;
            }
            pointer++;
            // Start reading the key
            StringBuilder sb = new StringBuilder();
            while(!reachedEndOfInput() && input.charAt(pointer) != '"') {
                sb.append(input.charAt(pointer));
                pointer++;
            }
            pointer++;
            
            // Empty key is not allowed
            if(sb.isEmpty()) {
                return null;
            }
            
            String key = sb.toString();
            
            // Remove spaces if available
            while (!reachedEndOfInput() && input.charAt(pointer) == ' ') {
                pointer++;
            }
            
            // The next char has to be a colon
            if(reachedEndOfInput() || input.charAt(pointer) != ':') {
                return null;
            }
            pointer++;
            
            // Remove spaces if available
            while (!reachedEndOfInput() && input.charAt(pointer) == ' ') {
                pointer++;
            }
            
            object.value.put(key, createAny());
            
            if (!reachedEndOfInput() && input.charAt(pointer) == ',') {
                pointer++;
            }
        }
        
        if (input.charAt(pointer) != '}') {
            // Invalid JSON
            return null;
        }
        // Advancing pointer by 1 since it's currently pointing at }
        pointer++;
        return object;
    }

    private boolean reachedEndOfInput() {
        return pointer >= input.length();
    }

    @Override
    public String toString() {
        return value.toString();
    }
    
}
