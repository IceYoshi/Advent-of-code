package day12.json;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author permi
 */
public class JsonObject extends JsonAny {

    public final Map<String, JsonAny> value = new HashMap();

    public JsonObject() {
        super(Type.Object);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        sb.append("{");
        for (String key : value.keySet()) {
            sb.append("\"")
                    .append(key)
                    .append("\": ")
                    .append(value.get(key).toString());
            if (index < value.size() - 1) {
                sb.append(", ");
            }
            index++;
        }
        sb.append("}");
        return sb.toString();
    }

}
