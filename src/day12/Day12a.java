package day12;

import java.util.ArrayList;

/**
 *
 * @author permi
 */
public class Day12a {

    public Day12a(String input) {        
        IO.println("Sum of all numbers: " + getSum(retrieveNumbers(input)));
    }
    
    private ArrayList<Integer> retrieveNumbers(String input) {
        String numberCharSet = "-0123456789";
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> numbers = new ArrayList<>();
        int i = 0;
        while (i < input.length()) {
            if (numberCharSet.contains(String.valueOf(input.charAt(i)))) {
                sb.append(input.charAt(i));
            } else {
                if (!sb.isEmpty()) {
                    numbers.add(Integer.valueOf(sb.toString()));
                    sb.setLength(0);
                }
            }
            i++;
        }
        if (!sb.isEmpty()) {
            numbers.add(Integer.valueOf(sb.toString()));
        }

        return numbers;
    }

    private int getSum(ArrayList<Integer> numbers) {
        return numbers.stream().reduce(0, Integer::sum);
    }

}
