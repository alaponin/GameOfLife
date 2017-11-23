import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameOfLife {

    private Grid grid;

    private int gridDimension;

    static JFrame frame         = new JFrame("Game of Life");

    static JTextArea tArea;

    public GameOfLife(int gridDimension) {
        this.grid = new Grid(gridDimension);
    }

    public void tick() {
        grid.evolve();
    }

    public void randomInit(List<Cell> initialPattern, int dimension) {

        tArea = new JTextArea(dimension, dimension);

        tArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(tArea, BorderLayout.CENTER);

        frame.setSize(500,300);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        this.grid.setInitialPattern(initialPattern);
    }

    public Grid getGrid() {
        return this.grid;
    }

    public void print() {

        tArea.setText( grid.print() );

        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        // TODO: uncomment if you want to see console output.

        //System.out.println(grid.print());
    }
}
