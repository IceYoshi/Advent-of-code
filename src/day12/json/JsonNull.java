
package day12.json;

/**
 *
 * @author permi
 */
public class JsonNull extends JsonAny {

    public final Object value = null;
    
    public JsonNull() {
        super(Type.Null);
    }

    @Override
    public String toString() {
        return "null";
    }
    
}
