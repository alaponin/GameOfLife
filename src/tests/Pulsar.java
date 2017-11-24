package tests;

import logic.Cell;
import logic.Status;

import java.util.ArrayList;
import java.util.List;

public class Pulsar implements Test {

    private final int gridDimension             = 17;

    private final int numberAliveCells          = 48;

    private static final int[][] coordinates    = { {2,4}, {2,5}, {2,6},  {2,10}, {2,11}, {2,12}, {4,2}, {4,7}, {4,9},
                                                    {4,14}, {5,2}, {5,7}, {5,9}, {5,14}, {6,2}, {6,7}, {6,9}, {6,14},
                                                    {7,4}, {7,5}, {7,6}, {7,10}, {7,11}, {7,12}, {9,4}, {9,5}, {9,6},
                                                    {9,10}, {9,11}, {9,12}, {10,2}, {10,7}, {10,9}, {10,14}, {11,2},
                                                    {11,7}, {11,9}, {11,14}, {12,2}, {12,7}, {12,9}, {12,14}, {14,4},
                                                    {14,5}, {14,6}, {14,10}, {14,11}, {14,12} };

    public List<Cell> obtainScenario(){

        List<Cell> blinkerPattern = new ArrayList<Cell>();

        for (int i = 0; i < numberAliveCells; i++) {

            Cell cell = new Cell(coordinates[i][0], coordinates[i][1] , Status.ALIVE);

            blinkerPattern.add(cell);
        }

        return blinkerPattern;
    }

    public int getGridDimension() {
        return gridDimension;
    }
}
