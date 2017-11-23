import logic.Cell;
import logic.GameOfLife;
import tests.*;

public class Main {

    public static void main(String[] args) {

        TestFactory testFactory = new TestFactory();

        Test testScenario = testFactory.getTestScenario("toad");

        List<Cell> initialPattern = testScenario.obtainScenario();

        GameOfLife gol = new GameOfLife(testScenario.getGridDimension());

        gol.randomInit(initialPattern);

        gol.print();

        for ( ; ; ) {

            gol.tick();

            gol.print();
        }
    }
}
