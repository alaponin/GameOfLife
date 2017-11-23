import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);  // Reading from System.in

        System.out.println("Enter a dimension for the grid: ");

        int dim = reader.nextInt();

        GameOfLife gol = new GameOfLife(dim);
        
        System.out.println("Enter initial number of alive cells: ");

        int cells = reader.nextInt();

        List<Cell> initialPattern = new ArrayList<Cell>();

        for (int i = 0; i < cells; i++) {

            System.out.println("Enter position x and y of cell " + i);

            int x = reader.nextInt();

            int y = reader.nextInt();

            Cell cell = new Cell(x, y, Status.ALIVE);

            initialPattern.add(cell);
        }

        reader.close();

        gol.randomInit(initialPattern, dim);

        gol.print();

        for ( ; ; ) {   // infinite tick

            gol.tick();

            gol.print();
        }
    }
}
