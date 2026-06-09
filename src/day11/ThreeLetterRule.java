package day11;

/**
 * Passwords must include one increasing straight of at least three letters,
 * like abc, bcd, cde, and so on, up to xyz. They cannot skip letters; abd
 * doesn't count.
 *
 * @author permi
 */
public class ThreeLetterRule implements Rule {

    @Override
    public boolean isValid(String password) {
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i) == password.charAt(i + 1) - 1
                    && password.charAt(i + 1) == password.charAt(i + 2) - 1) {
                return true;
            }
        }
        return false;
    }

}
