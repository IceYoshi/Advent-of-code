package day5.rules;

import java.util.ArrayList;

/**
 *
 * @author permi
 */
public class ExclusionRule implements Rule {

    @Override
    public boolean check(String word) {
        ArrayList<String> excludeList = new ArrayList<>();
        excludeList.add("ab");
        excludeList.add("cd");
        excludeList.add("pq");
        excludeList.add("xy");

        for (int i = 0; i < word.length() - 1; i++) {
            String letterPair = word.substring(i, i + 2);
            if (excludeList.contains(letterPair)) {
                return false;
            }
        }

        return true;
    }

}
