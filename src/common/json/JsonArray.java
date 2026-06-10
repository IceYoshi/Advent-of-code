
package common.json;

import java.util.ArrayList;

/**
 *
 * @author permi
 */
public class JsonArray extends JsonAny {

    public final ArrayList<JsonAny> value = new ArrayList<>();
    
    public JsonArray() {
        super(Type.Array);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < value.size(); i++) {
            sb.append(value.get(i).toString());
            if(i < value.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
}
