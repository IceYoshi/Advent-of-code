
package common.json;

/**
 *
 * @author permi
 */
public class JsonNumber extends JsonAny {

    public final double value;
    
    public JsonNumber(double value) {
        super(Type.Number);
        this.value = value;
    }

    @Override
    public String toString() {
        if((int)value == value) {
            return String.valueOf((int)value);
        } else {
            return String.valueOf(value);
        }
    }
    
}
