package day05;

/**
 *
 * @author permi
 */
public class DuplicateLetterWithGapRule implements Rule {

    @Override
    public boolean check(String word) {
        for (int i = 0; i < word.length() - 2; i++) {
            if (word.charAt(i) == word.charAt(i + 2)) {
                return true;
            }
        }

        return false;
    }

}
