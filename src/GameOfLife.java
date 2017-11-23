import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.List;

public class GameOfLife extends JFrame implements ActionListener {

    private Grid grid;
    private JMenuBar mb_menu;
    private JMenu m_file, m_help;
    private JMenuItem mi_file_exit;
    private JMenuItem mi_help_about, mi_help_source;
//    private static JFrame gameFrame = new JFrame("Game of Life");
//    static JTextArea tArea;
    private Thread game;

    public GameOfLife() {
        // Setup menu
        mb_menu = new JMenuBar();
        setJMenuBar(mb_menu);
        m_file = new JMenu("File");
        mb_menu.add(m_file);
        m_help = new JMenu("Help");
        mb_menu.add(m_help);
        mi_file_exit = new JMenuItem("Exit");
        mi_file_exit.addActionListener(this);
        m_file.add(new JSeparator());
        m_file.add(mi_file_exit);

        mi_help_about = new JMenuItem("About");
        mi_help_about.addActionListener(this);
        mi_help_source = new JMenuItem("Source");
        mi_help_source.addActionListener(this);
        m_help.add(mi_help_about);
        m_help.add(mi_help_source);
        // Setup game board
        this.grid = new Grid();
        add(grid);
    }

    public void tick() {
        grid.evolve();
    }

    public void randomInit(List<Cell> initialPattern, int dimension) {
//        tArea = new JTextArea(dimension, dimension);
//        tArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
//        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        gameFrame.getContentPane().add(tArea, BorderLayout.CENTER);
//        gameFrame.setSize(500, 300);
//        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        gameFrame.setVisible(true);
        this.grid.setInitialPattern(initialPattern);
    }

    public Grid getGrid() {
        return this.grid;
    }

    public void print() {
       // tArea.setText(grid.print());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        // TODO: uncomment if you want to see console output.
        //System.out.println(grid.print());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(mi_file_exit)) {
            // Exit the game
            System.exit(0);
        }  /*else if (e.getSource().equals(mi_game_autofill)) {//TODO
            final JFrame f_autoFill = new JFrame();
            f_autoFill.setTitle("Autofill");
            f_autoFill.setSize(360, 60);
            f_autoFill.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - f_autoFill.getWidth()) / 2,
                    (Toolkit.getDefaultToolkit().getScreenSize().height - f_autoFill.getHeight()) / 2);
            f_autoFill.setResizable(false);
            JPanel p_autoFill = new JPanel();
            p_autoFill.setOpaque(false);
            f_autoFill.add(p_autoFill);
            p_autoFill.add(new JLabel("What percentage should be filled? "));
            Object[] percentageOptions = {"Select", 5, 10, 15, 20, 25, 30, 40, 50, 60, 70, 80, 90, 95};
            final JComboBox cb_percent = new JComboBox(percentageOptions);
            p_autoFill.add(cb_percent);
            cb_percent.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cb_percent.getSelectedIndex() > 0) {
                        grid.resetBoard();
                        grid.randomlyFillBoard((Integer) cb_percent.getSelectedItem());
                        f_autoFill.dispose();
                    }
                }
            });
            f_autoFill.setVisible(true);
        }*/ else if (e.getSource().equals(mi_help_about)) {
            JOptionPane.showMessageDialog(null, "Conway's game of life was a cellular animation devised by the mathematician John Conway.\nThis Java, swing based implementation was created by OOTI-2017.\n\n");
        }
    }
}
