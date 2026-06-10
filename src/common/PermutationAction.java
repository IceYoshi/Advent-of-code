
package common;

import java.util.ArrayList;

/**
 *
 * @author permi
 */
public interface PermutationAction<T> {

    public void run(ArrayList<T> currentPermutation);
    
}
