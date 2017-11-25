import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class GameOfLife extends JFrame implements ActionListener {

    private Grid grid;

    private JMenuBar mb_menu;
    private JMenu m_help;
    private JMenuItem mi_help_about, mi_help_source;
    JButton startGameBtn = new JButton("Start");
    JButton stopGameBtn = new JButton("Stop");
    JButton resetGameBtn = new JButton("Reset");
    JButton randomFillBtn = new JButton("Random Pattern");
    JButton newGame = new JButton("New Game");
    private Thread game;


    public GameOfLife() {

        // Setup menu
        mb_menu = new JMenuBar();
        setJMenuBar(mb_menu);
        m_help = new JMenu("Help");
        mb_menu.add(m_help);
        mi_help_about = new JMenuItem("About");
        mi_help_about.addActionListener(this);
        mi_help_source = new JMenuItem("Source");
        mi_help_source.addActionListener(this);
        m_help.add(mi_help_about);
        m_help.add(mi_help_source);

        setLayout(new BorderLayout(3, 3));  // BorderLayout with 3-pixel gaps.
        // Setup game board
        this.grid = new Grid();
        add(grid, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();  // The subpanel that holds the buttons.
        add(buttonPanel, BorderLayout.SOUTH);

        startGameBtn.addActionListener(this);   // The CardPanel listens for events.
        buttonPanel.add(startGameBtn);

        stopGameBtn.addActionListener(this);
        buttonPanel.add(stopGameBtn);

        resetGameBtn.addActionListener(this);
        buttonPanel.add(resetGameBtn);

        randomFillBtn.addActionListener(this);
        buttonPanel.add(randomFillBtn);

        newGame.addActionListener(this);
        buttonPanel.add(newGame);
    }

    public void tick() {
        grid.evolve();
    }

    public void randomInit(List<Cell> initialPattern, int dimension) {
        this.grid.setInitialPattern(initialPattern);
    }

    public Grid getGrid() {
        return this.grid;
    }

    public void print() {
        try {
            Thread.sleep(1000);
            grid.run();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        // TODO: uncomment if you want to see console output.
        //System.out.println(grid.print());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(startGameBtn)) {
            grid.setGameBeingPlayed(true);
        } else if (e.getSource().equals(resetGameBtn)) {
            grid.resetBoard();
        } else if (e.getSource().equals(stopGameBtn)) {
            grid.setGameBeingPlayed(false);
        } else if (e.getSource().equals(randomFillBtn)) {
            grid.resetBoard();
            grid.randomlyFillBoard();
        } else if (e.getSource().equals(mi_help_about)) {
            JOptionPane.showMessageDialog(null, "Conway's game of life was a cellular animation devised by the mathematician John Conway.\nThis Java, swing based implementation was created by OOTI-2017.\n\n");
        }
    }
}
