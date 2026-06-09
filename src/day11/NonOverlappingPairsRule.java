
package day11;

/**
 * Passwords must contain at least two different, non-overlapping 
 * pairs of letters, like aa, bb, or zz.
 * @author permi
 */
public class NonOverlappingPairsRule implements Rule {

    @Override
    public boolean isValid(String password) {
        int pairCount = 0;
        int i = 0;
        while(i < password.length() - 1 && pairCount < 2) {
            if(password.charAt(i) == password.charAt(i + 1)) {
                pairCount++;
                i += 2;
            } else {
                i++;
            }
        }
        return pairCount == 2;
    }
    
}
