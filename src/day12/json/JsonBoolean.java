
package day12.json;

/**
 *
 * @author permi
 */
public class JsonBoolean extends JsonAny {

    public final boolean value;
    
    public JsonBoolean(boolean value) {
        super(Type.Boolean);
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
    
}
