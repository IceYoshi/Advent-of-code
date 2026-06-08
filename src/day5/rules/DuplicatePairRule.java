package day5.rules;

/**
 *
 * @author permi
 */
public class DuplicatePairRule implements Rule {

    @Override
    public boolean check(String word) {
        for (int i = 0; i < word.length() - 1; i++) {
            String letterPair = word.substring(i, i + 2);
            if (word.substring(i + 2, word.length()).contains(letterPair)) {
                return true;
            }
        }

        return false;
    }

}
