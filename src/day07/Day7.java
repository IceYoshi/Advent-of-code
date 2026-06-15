package day07;

import common.Day;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author permi
 */
public class Day7 extends Day {

    private Map<String, Integer> wireMap = new HashMap<>();
    
    private enum Direction {
        Left,
        Right
    }

    public Day7() {
        super(FileType.Input);

        runCircuit();

        //printWireMap();
        if (wireMap.containsKey("a")) {
            int a = wireMap.get("a");
            System.out.println("Signal of wire a (Part One): " + getShortComponentOfInteger(a));
            // Part Two
            wireMap.clear();
            wireMap.put("b", a);
            runCircuit();
            if (wireMap.containsKey("a")) {
                a = wireMap.get("a");
                System.out.println("Signal of wire a (Part Two): " + getShortComponentOfInteger(a));
            } else {
                System.out.println("Wire a has not received a signal (Part Two).");
            }

        } else {
            System.out.println("Wire a has not received a signal (Part One).");
        }

    }

    /**
     * Since Java does not have an unsigned short type, we'll store the wire
     * signal value as an integer and later call this method to retrieve only
     * the last 4 bytes.
     */
    private int getShortComponentOfInteger(int value) {
        return value & 0xffff;
    }

    /**
     * Example of command: NOT gs -> gt
     */
    private boolean gateNOT(String tokens[]) {
        try {
            int wireSignal = getTokenValue(tokens[1]);

            String outputKey = tokens[3];

            // Only set value if the output wire hasn't a signal value yet.
            if (!wireMap.containsKey(outputKey)) {
                wireMap.put(outputKey, ~wireSignal);
            } else {
                return false;
            }
            return true;
        } catch (WireWithoutSignalException e) {
            // Skip command
            return false;
        }
    }

    /**
     * Example of command: gn AND gp -> gq
     */
    private boolean gateAND(String tokens[]) {
        try {
            int wireSignal1 = getTokenValue(tokens[0]);
            int wireSignal2 = getTokenValue(tokens[2]);

            String outputKey = tokens[4];

            if (!wireMap.containsKey(outputKey)) {
                wireMap.put(outputKey, wireSignal1 & wireSignal2);
            } else {
                return false;
            }
            return true;
        } catch (WireWithoutSignalException e) {
            // Skip command
            return false;
        }
    }

    /**
     * Example of command: ck OR cl -> cm
     */
    private boolean gateOR(String tokens[]) {
        try {
            int wireSignal1 = getTokenValue(tokens[0]);
            int wireSignal2 = getTokenValue(tokens[2]);

            String outputKey = tokens[4];

            if (!wireMap.containsKey(outputKey)) {
                wireMap.put(outputKey, wireSignal1 | wireSignal2);
            } else {
                return false;
            }
            return true;
        } catch (WireWithoutSignalException e) {
            // Skip command
            return false;
        }
    }

    

    /**
     * Examples of commands: cd LSHIFT 15 -> ch | x RSHIFT 5 -> aa
     */
    private boolean shift(Direction direction, String tokens[]) {
        try {
            int wireSignal1 = getTokenValue(tokens[0]);
            int wireSignal2 = getTokenValue(tokens[2]);

            String outputKey = tokens[4];

            if (!wireMap.containsKey(outputKey)) {
                switch (direction) {
                    case Left ->
                        wireMap.put(outputKey, wireSignal1 << wireSignal2);
                    case Right ->
                        wireMap.put(outputKey, wireSignal1 >> wireSignal2);
                }
            } else {
                return false;
            }
            return true;
        } catch (WireWithoutSignalException e) {
            // Skip command
            return false;
        }
    }

    /**
     * Examples of commands: 44430 -> b | lx -> a
     */
    private boolean assignment(String tokens[]) {
        try {
            int input = getTokenValue(tokens[0]);

            String outputKey = tokens[2];

            if (!wireMap.containsKey(outputKey)) {
                wireMap.put(outputKey, input);
            } else {
                return false;
            }
            return true;
        } catch (WireWithoutSignalException e) {
            // Skip command
            return false;
        }
    }

    private int getTokenValue(String token) throws WireWithoutSignalException {
        // Case 1: Token is a numerical value, not a wire
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            // Integer converson failed, therefore it is a wire.

            if (wireMap.containsKey(token)) {
                // Case 2: Wire has a signal value
                return wireMap.get(token);
            } else {
                // Case 3: Wire has NO signal value
                throw new WireWithoutSignalException();
            }
        }
    }

    @SuppressWarnings("unused")
    private void printWireMap() {
        System.out.println("==== WireMap ====");
        wireMap.forEach((key, value) -> {
            System.out.println(key + ": " + getShortComponentOfInteger(value));
        });
        System.out.println("=================");
    }

    /**
     * The circuit is run multiple times because some commands depend on others.
     * It finishes once no change has been made to the WireMap. Perhaps as an
     * optimization, one could try to sort the list beforehand such that no
     * incomplete gate calls are being made.
     */
    private void runCircuit() {
        boolean circuitChanged;
        do {
            circuitChanged = false;
            String commands[] = input.split("\n");
            for (String command : commands) {
                String tokens[] = command.split(" ");

                if (tokens[0].equals("NOT")) {
                    if (gateNOT(tokens)) {
                        circuitChanged = true;
                    }
                } else if (tokens[1].equals("AND")) {
                    if (gateAND(tokens)) {
                        circuitChanged = true;
                    }
                } else if (tokens[1].equals("OR")) {
                    if (gateOR(tokens)) {
                        circuitChanged = true;
                    }
                } else if (tokens[1].equals("LSHIFT")) {
                    if (shift(Direction.Left, tokens)) {
                        circuitChanged = true;
                    }
                } else if (tokens[1].equals("RSHIFT")) {
                    if (shift(Direction.Right, tokens)) {
                        circuitChanged = true;
                    }
                } else if (tokens[1].equals("->")) {
                    if (assignment(tokens)) {
                        circuitChanged = true;
                    }
                }
            }
        } while (circuitChanged);
    }

}
