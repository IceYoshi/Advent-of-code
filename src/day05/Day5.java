package day05;

import common.Day;

/**
 *
 * @author permi
 */
public class Day5 extends Day {

    private Rule rulesPart1[] = {
        new VowelRule(),
        new DuplicateLetterRule(),
        new ExclusionRule()
    };

    private Rule rulesPart2[] = {
        new DuplicatePairRule(),
        new DuplicateLetterWithGapRule()
    };

    public Day5() {
        super(FileType.Input);

        int niceStringCount1 = 0;
        int niceStringCount2 = 0;

        String words[] = input.split("\n");
        System.out.println("Number of strings: " + words.length);
        for (String word : words) {
            word = word.toLowerCase(); // Make sure the word is in lowercase
            // Part One
            boolean isNiceString = true;
            for (Rule rule : rulesPart1) {
                if (!rule.check(word)) {
                    isNiceString = false;
                    break;
                }
            }
            if (isNiceString) {
                niceStringCount1++;
            }

            // Part Two
            isNiceString = true;
            for (Rule rule : rulesPart2) {
                if (!rule.check(word)) {
                    isNiceString = false;
                    break;
                }
            }
            if (isNiceString) {
                niceStringCount2++;
            }
        }

        System.out.println("Number of nice strings (Part One): " + niceStringCount1);
        System.out.println("Number of nice strings (Part Two): " + niceStringCount2);
    }

}
