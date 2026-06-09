
package day12.json;

/**
 *
 * @author permi
 */
public abstract class JsonAny {

    public static enum Type {
        Number,
        String,
        Boolean,
        Null,
        Array,
        Object
    }
    
    public final Type type;

    public JsonAny(Type type) {
        this.type = type;
    }
    
    
    
}
