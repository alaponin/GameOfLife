package logic;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameOfLife {

    private Grid grid;

    private int gridDimension;

    static JFrame frame         = new JFrame("Game of Life");

    static JTextArea tArea;

    public GameOfLife(int gridDimension) {

        this.gridDimension = gridDimension;

        this.grid = new Grid(this.gridDimension);

    }

    public void tick() {
        grid.evolve();
    }

    public void randomInit(List<Cell> initialPattern) {

        tArea = new JTextArea(this.gridDimension, this.gridDimension);

        tArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(tArea, BorderLayout.CENTER);

        frame.setSize(700,800);

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

    }
}
