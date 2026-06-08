package day5.rules;

/**
 *
 * @author permi
 */
public class VowelRule implements Rule {

    @Override
    public boolean check(String word) {
        int vowelsCount = 0;
        String vowels = "aeiou";

        for (char letter : word.toCharArray()) {
            if (vowels.contains(String.valueOf(letter))) {
                vowelsCount++;
            }
        }

        return vowelsCount > 2;
    }

}
