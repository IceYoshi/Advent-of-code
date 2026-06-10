
package common.json;

/**
 *
 * @author permi
 */
public class JsonString  extends JsonAny  {

    public final String value;
    
    public JsonString(String value) {
        super(Type.String);
        this.value = value;
    }

    @Override
    public String toString() {
        return "\"" + value + "\"";
    }
    
    

}
