package day18;

import common.*;

/**
 * A # means "on", and a . means "off".
 *
 * A light which is on stays on when 2 or 3 neighbors are on, and turns off
 * otherwise. A light which is off turns on if exactly 3 neighbors are on, and
 * stays off otherwise.
 *
 * @author permi
 */
public class Day18 extends Day {

    private boolean[][] ledGrid;
    private boolean[][] ledGridTemp;
    private int rowCount;
    private int colCount;

    private static final int STEP_COUNT = 100;

    public Day18() {
        super(FileType.Input);

        String[] lines = input.split("\n");
        if (lines.length == 0) {
            IO.println("Abort: Empty input.");
            return;
        }
        rowCount = lines[0].length();
        colCount = lines.length;
        ledGrid = new boolean[rowCount][colCount];
        ledGridTemp = new boolean[rowCount][colCount];

        for (int col = 0; col < colCount; col++) {
            for (int row = 0; row < rowCount; row++) {
                ledGrid[row][col] = lines[col].charAt(row) == '#';
            }
        }
        applyStuckLights();

        //printGrid();
        for (int i = 0; i < STEP_COUNT; i++) {
            performStep();
            //printGrid();
        }

        IO.println("Number of lights that are on after " + STEP_COUNT + " steps: " + getNumberOfLitLEDs());
    }

    private void performStep() {
        // Read from the original grid and write onto the temporal grid
        for (int col = 0; col < colCount; col++) {
            for (int row = 0; row < rowCount; row++) {
                int numberOfLitNeighbors = getNumberOfLitNeighbors(row, col);
                if (isOn(row, col)) {
                    // A light which is on stays on when 2 or 3 neighbors are on, and turns off otherwise.
                    ledGridTemp[row][col] = numberOfLitNeighbors == 2 || numberOfLitNeighbors == 3;
                } else {
                    // A light which is off turns on if exactly 3 neighbors are on, and stays off otherwise.
                    ledGridTemp[row][col] = numberOfLitNeighbors == 3;
                }
            }
        }

        // Copy the temporal grid into the original grid.
        // We cannot simply change the reference since both grids 
        // would then be pointing to the same memory.
        for (int row = 0; row < rowCount; row++) {
            System.arraycopy(ledGridTemp[row], 0, ledGrid[row], 0, colCount);
        }
        applyStuckLights();
    }

    private int getNumberOfLitNeighbors(int row, int col) {
        int result = 0;

        int neighborDiameter = 3; //3x3
        int offset = neighborDiameter / 2;

        for (int i = -offset; i <= offset; i++) {
            for (int j = -offset; j <= offset; j++) {
                if (i == 0 && j == 0) {
                    // Skip the reference LED
                    continue;
                }
                if (isOn(row + i, col + j)) {
                    result++;
                }
            }
        }

        return result;
    }

    /**
     * In addition to checking the value inside the grid, it also verifies that
     * the coordinates are inbound.
     *
     * @return true if inbound and value is true for the specified coordinates.
     */
    private boolean isOn(int row, int col) {
        return row >= 0 && row < rowCount && col >= 0 && col < colCount && ledGrid[row][col];
    }

    @SuppressWarnings("unused")
    private void printGrid() {
        for (int col = 0; col < colCount; col++) {
            for (int row = 0; row < rowCount; row++) {
                IO.print(ledGrid[row][col] ? '#' : '.');
            }
            IO.println();
        }
        IO.println("==========================");
    }

    private int getNumberOfLitLEDs() {
        int result = 0;

        for (int col = 0; col < colCount; col++) {
            for (int row = 0; row < rowCount; row++) {
                if (ledGrid[row][col]) {
                    result++;
                }
            }
        }

        return result;
    }

    // Four lights, one in each corner, are stuck on and can't be turned off.
    private void applyStuckLights() {
        ledGrid[0][0] = true;
        ledGrid[0][colCount - 1] = true;
        ledGrid[rowCount - 1][0] = true;
        ledGrid[rowCount - 1][colCount - 1] = true;
    }

}
