package day11;

import common.*;

/**
 *
 * @author permi
 */
public class Day11 extends Day {

    private Rule[] rules = {
        new ThreeLetterRule(),
        new ForbiddenLettersRule(),
        new NonOverlappingPairsRule()
    };

    public Day11() {
        super(FileType.Input);
        
        //IO.println(isValid("abcdffaa"));

        String newPassword = findNextValidPassword(input);
        String newerPassword = findNextValidPassword(newPassword);
        
        IO.println("The next password after " + input + " is: " + newPassword);
        IO.println("The next password after " + newPassword + " is: " + newerPassword);
    }
    
    private String findNextValidPassword(String password) {
        do {
            password = increment(password);
        } while (!isValid(password));
        return password;
    }

    private String increment(String oldPassword) {
        String newPassword = "";
        int carryOver = 1;
        for(int i = oldPassword.length()-1; i >= 0; i--) {
            char letter = oldPassword.charAt(i);
            letter += carryOver;
            carryOver = 0;
            if(letter > 'z') {
                letter = 'a';
                carryOver = 1;
            }
            newPassword = letter + newPassword;
        }
        return newPassword;
    }
    
    private boolean isValid(String passoword) {
        for (Rule rule : rules) {
            if (!rule.isValid(passoword)) {
                //IO.println("Rule " + rule.getClass().getSimpleName() + " failed.");
                return false;
            }
        }
        return true;
    }

}
