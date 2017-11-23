package tests;

import logic.Cell;
import logic.Status;
import java.util.ArrayList;
import java.util.List;

public class Beacon implements Test {

    private int numberAliveCells                = 6;

    private int gridDimension                   = 6;

    private static final int[][] coordinates    = { {1,1}, {1,2}, {2,1}, {3,4}, {4,3}, {4,4} };

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
