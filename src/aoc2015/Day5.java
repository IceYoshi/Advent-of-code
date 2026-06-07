package aoc2015;

import common.Day;
import java.util.ArrayList;


/**
 *
 * @author permi
 */
public class Day5 extends Day {

    private interface Rule {
        public boolean check(String word);
    }
    
    private class VowelRule implements Rule {

        @Override
        public boolean check(String word) {
            int vowelsCount = 0;
            String vowels = "aeiou";
            
            for(char letter: word.toCharArray()) {
                if(vowels.contains(String.valueOf(letter))) vowelsCount++;
            }
            
            return vowelsCount > 2;
        }
        
    }
    
    private class DuplicateLetterRule implements Rule {

        @Override
        public boolean check(String word) {
            int duplicateCount = 0;
            for(int i = 0; i < word.length() - 1; i++) {
                if(word.charAt(i) == word.charAt(i+1)) duplicateCount++;
            }
            
            return duplicateCount > 0;
        }
        
    }
    
    private class ExclusionRule implements Rule {
        
        @Override
        public boolean check(String word) {
            ArrayList<String> excludeList = new ArrayList<>();
            excludeList.add("ab");
            excludeList.add("cd");
            excludeList.add("pq");
            excludeList.add("xy");
            
            for(int i = 0; i < word.length() - 1; i++) {
                String letterPair = word.substring(i, i+2);
                if(excludeList.contains(letterPair)) return false;
            }
            
            return true;
        }
        
    }
    
    private class DuplicatePairRule implements Rule {
        
        @Override
        public boolean check(String word) {
            for(int i = 0; i < word.length() - 1; i++) {
                String letterPair = word.substring(i, i+2);
                if(word.substring(i+2, word.length()).contains(letterPair)) return true;
            }
            
            return false;
        }
        
    }
    
    private class DuplicateLetterWithGapRule implements Rule {
        
        @Override
        public boolean check(String word) {
            for(int i = 0; i < word.length() - 2; i++) {
                if(word.charAt(i) == word.charAt(i+2)) return true;
            }
            
            return false;
        }
        
    }
    
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
        for(String word: words) {
            word = word.toLowerCase(); // Make sure the word is in lowercase
            // Part One
            boolean isNiceString = true;
            for(Rule rule: rulesPart1) {
                if(!rule.check(word)) {
                    isNiceString = false;
                    break;
                }
            }
            if(isNiceString) niceStringCount1++;
            
            // Part Two
            isNiceString = true;
            for(Rule rule: rulesPart2) {
                if(!rule.check(word)) {
                    isNiceString = false;
                    break;
                }
            }
            if(isNiceString) niceStringCount2++;
        }
        
        System.out.println("Number of nice strings (Part One): " + niceStringCount1);
        System.out.println("Number of nice strings (Part Two): " + niceStringCount2);
    }

}
