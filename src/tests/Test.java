package tests;

import logic.Cell;
import java.util.List;

public interface Test {
    List<Cell> obtainScenario();
    int getGridDimension();
}
