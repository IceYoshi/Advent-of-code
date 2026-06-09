package day11;

/**
 * Passwords may not contain the letters i, o, or l, as these letters can be
 * mistaken for other characters and are therefore confusing.
 *
 * @author permi
 */
public class ForbiddenLettersRule implements Rule {

    private static final String FORBIDDEN_LETTERS = "iol";

    @Override
    public boolean isValid(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (FORBIDDEN_LETTERS.contains(String.valueOf(password.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

}
