import logic.Cell;
import logic.GameOfLife;
import tests.*;
import java.util.List;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);  // Reading from System.in

        System.out.println("Enter the desired test scenario: ");
        System.out.println("- Blinker");
        System.out.println("- Toad");
        System.out.println("- Beacon");
        System.out.println("- Pulsar");
        System.out.print("\nOption: ");

        String testCase = reader.nextLine();

        TestFactory testFactory = new TestFactory();

        Test testScenario = testFactory.getTestScenario(testCase);

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
