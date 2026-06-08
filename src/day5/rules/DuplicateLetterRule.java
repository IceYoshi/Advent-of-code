package day5.rules;

/**
 *
 * @author permi
 */
public class DuplicateLetterRule implements Rule {

    @Override
    public boolean check(String word) {
        int duplicateCount = 0;
        for (int i = 0; i < word.length() - 1; i++) {
            if (word.charAt(i) == word.charAt(i + 1)) {
                duplicateCount++;
            }
        }

        return duplicateCount > 0;
    }

}
