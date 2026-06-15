package common;

import java.util.ArrayList;

/*
 * Allows the generation of all possible permutations of the list.
 *
 * @author permi
 */
public class PermutationList<T> extends ArrayList<T> {

    private ArrayList<T> poolOfValues;
    private ArrayList<T> currentPermutation;

    /**
     * Let n be the number of values in the list. We first have n values to
     * choose from. After that, this method calls itself recursively, where it
     * has n-1 values to choose from. This goes down to 0, where finally the
     * permutation list is completed.
     *
     * @param action The run method is called for each permutation of the list
     */
    public void forEachPermutation(PermutationAction<T> action) {
        if (poolOfValues == null) {
            // Add all values to the pool
            poolOfValues = new ArrayList<>();
            for (T location : this) {
                poolOfValues.add(location);
            }
            currentPermutation = new ArrayList<>();
        }

        if (poolOfValues.isEmpty()) {
            action.run(currentPermutation);
        }

        for (int i = 0; i < poolOfValues.size(); i++) {
            T value = poolOfValues.remove(i);
            currentPermutation.add(value);
            forEachPermutation(action);
            // Insert value back into its previous position
            poolOfValues.add(i, value);
        }

        if (!currentPermutation.isEmpty()) {
            currentPermutation.removeLast();
        }
    }

}
