package common;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author permi
 */
public abstract class Day {

    protected String input;
    private static final String DIR = "input/";

    public enum FileType {
        Input,
        Debug
    }

    public Day() {
        readInput(FileType.Input);
    }
    
    public Day(FileType type) {
        readInput(type);
    }

    /**
     * Reads the input file for the given advent day challenge.
     * @param type If set to <code>FileType.Debug</code>, uses an alternative
     * input file that can be customized by the user, to make debugging easier.
     */
    public void readInput(FileType type) {
        // Retrieve the day from the class name.
        int day = Integer.parseInt(getClass().getSimpleName().substring(3, getClass().getSimpleName().length()));
        System.out.println("================================ Solution to day " + day + " ================================");
        input = "";

        File inputFile;
        switch (type) {
            case Input ->
                inputFile = new File(DIR + day + ".txt");
            case Debug ->
                inputFile = new File(DIR + "debug.txt");
            default -> {
                return;
            }
        }

        try (Scanner reader = new Scanner(inputFile)) {
            boolean isFirstLine = true;
            while (reader.hasNextLine()) {
                if (isFirstLine) {
                    isFirstLine = false;
                } else {
                    input += '\n';
                }

                input += reader.nextLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("IOException: File not found: " + inputFile.getName());
        }
    }
}
