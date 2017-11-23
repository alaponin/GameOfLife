import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(800, 600);
    private static final Dimension MINIMUM_WINDOW_SIZE = new Dimension(400, 400);
    public static void main(String[] args) {
/*
        GameOfLife gol = new GameOfLife();

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter a dimension for the grid: ");
        int dim = reader.nextInt();
        gol.getGrid().setDimension(dim);
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
        for (; ; ) {   // infinite tick
            gol.tick();
            gol.print();
        }*/
        // Setup the swing specifics
        JFrame game = new GameOfLife();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setTitle("Conway's Game of Life");
        game.setSize(DEFAULT_WINDOW_SIZE);
        game.setMinimumSize(MINIMUM_WINDOW_SIZE);
        game.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - game.getWidth())/2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - game.getHeight())/2);
        game.setVisible(true);
    }
}
