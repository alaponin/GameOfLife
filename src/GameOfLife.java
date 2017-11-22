import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameOfLife {

    private Grid grid;

    static JFrame frame         = new JFrame("Game of Life");
    static JTextArea tArea      = new JTextArea(10,20);
    static JScrollPane pane     = new JScrollPane(tArea);
    static Container container;

    public GameOfLife() {

        this.grid = new Grid();

        // TODO: make this less hardcoded and beautiful

        container = frame.getContentPane();
        container.add(pane);

        frame.setSize(300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }


    public void tick() {
        grid.evolve();
    }

    public void randomInit(List<Cell> initialPattern) {

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
