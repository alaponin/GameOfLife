package tests;

import logic.Cell;
import logic.Status;

import java.util.ArrayList;
import java.util.List;

public class Toad implements Test {

    private final int numberAliveCells                = 6;

    private final int gridDimension                   = 6;

    private static final int[][] coordinates    = { {2,2}, {2,3}, {2,4}, {3,1}, {3,2}, {3,3} };

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
