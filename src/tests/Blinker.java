package tests;

import logic.Cell;
import logic.Status;
import java.util.ArrayList;
import java.util.List;


public class Blinker implements Test {

    private final int numberAliveCells                = 3;

    private static final int[][] coordinates    = { {2,1}, {2,2}, {2,3} };

    public List<Cell> obtainScenario(){

        List<Cell> blinkerPattern = new ArrayList<Cell>();

        for (int i = 0; i < numberAliveCells; i++) {

            Cell cell = new Cell(coordinates[i][0], coordinates[i][1] , Status.ALIVE);

            blinkerPattern.add(cell);
        }

        return blinkerPattern;
    }

}
