package day13;

import common.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Idea for optimization: Implement a selective brute-force that avoids some of
 * the duplicate permutations by giving the first guest a fixed seat.
 *
 * @author permi
 */
public class Day13 extends Day {

    private record GuestHappinessPair(String guest, int happiness) {

    }

    private final Map<String, List<GuestHappinessPair>> happinessMap = new HashMap<>();

    private int maxHappiness;

    public Day13() {
        super(FileType.Input);

        PermutationList<String> guests = new PermutationList<>();
        
        String[] lines = input.split("\n");
        for (String line : lines) {
            String[] tokens = line.split(" ");
            String guest1 = tokens[0];
            String guest2 = tokens[10];
            // Remove the dot at the end of the name
            guest2 = guest2.substring(0, guest2.length() - 1);
            int happiness = Integer.parseInt(tokens[3]);
            // Check if the happiness is negative
            if (tokens[2].equals("lose")) {
                happiness *= -1;
            }

            // Check if person is already inside the map
            if (!happinessMap.containsKey(guest1)) {
                happinessMap.put(guest1, new ArrayList<>());
                guests.add(guest1);
            }
            happinessMap.get(guest1).add(new GuestHappinessPair(guest2, happiness));
        }
        
        // Part Two: Add Santa to the guest list. Since the happinessMap does
        // not include him, every neighbor will be neutral around him (net happiness
        // change of 0).
        guests.add("Santa");

        // Calculate baseline for max happiness
        maxHappiness = getHappiness(guests);

        guests.forEachPermutation((currentPermutation) -> {
            maxHappiness = Math.max(maxHappiness, getHappiness(currentPermutation));
        });

        IO.println("Max happiness for the optimal seating arrangement: " + maxHappiness);
    }

    private int getHappiness(ArrayList<String> guests) {
        //printGuestList(guests);
        int happiness = 0;
        for (int i = 0; i < guests.size(); i++) {
            String guest = guests.get(i);
            
            if(!happinessMap.containsKey(guest)) {
                // Guest does not have an entry in the happinessMap.
                // Skipping him will result in a net happiness change of 0.
                continue;
            }
            
            String neighbor1 = guests.get((i + 1) % guests.size());
            String neighbor2 = guests.get((i - 1 + guests.size()) % guests.size());
            
            for (GuestHappinessPair phPair : happinessMap.get(guest)) {
                if (phPair.guest.equals(neighbor1) || phPair.guest.equals(neighbor2)) {
                    happiness += phPair.happiness;
                }
            }
        }
        return happiness;
    }

    @SuppressWarnings("unused")
    private void printGuestList(ArrayList<String> guests) {
        IO.print("[");
        for (int i = 0; i < guests.size(); i++) {
            IO.print(guests.get(i));
            if (i < guests.size() - 1) {
                IO.print(", ");
            }
        }
        IO.println("]");
    }

}
