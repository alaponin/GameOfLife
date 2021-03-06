package logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class GameOfLife extends JFrame implements ActionListener {

    private Grid grid;
    private JMenuBar mb_menu;
    private JMenu m_help;
    private JMenuItem mi_help_about, mi_help_source;

    final JButton startGameBtn = new JButton("Start");
    final JButton stopGameBtn = new JButton("Stop");
    final JButton resetGameBtn = new JButton("Reset");
    JButton randomFillBtn = new JButton("Random Pattern");
    JButton choosePatternBtn = new JButton("Choose From Patterns");

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
        stopGameBtn.setEnabled(false);

        resetGameBtn.addActionListener(this);
        buttonPanel.add(resetGameBtn);

        randomFillBtn.addActionListener(this);
        buttonPanel.add(randomFillBtn);

        choosePatternBtn.addActionListener(this);
        buttonPanel.add(choosePatternBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(startGameBtn)) {
            grid.setGameBeingPlayed(true);
            startGameBtn.setEnabled(false);
            resetGameBtn.setEnabled(false);
            randomFillBtn.setEnabled(false);
            choosePatternBtn.setEnabled(false);
            stopGameBtn.setEnabled(true);
        } else if (e.getSource().equals(resetGameBtn)) {
            grid.resetBoard();
        } else if (e.getSource().equals(stopGameBtn)) {
            grid.setGameBeingPlayed(false);
            startGameBtn.setEnabled(true);
            resetGameBtn.setEnabled(true);
            randomFillBtn.setEnabled(true);
            choosePatternBtn.setEnabled(true);
            stopGameBtn.setEnabled(false);
        } else if (e.getSource().equals(randomFillBtn)) {
            grid.resetBoard();
            grid.randomlyFillBoard();
        } else if (e.getSource().equals(choosePatternBtn)) {
            final JFrame f_autoFill = new JFrame();
            f_autoFill.setTitle("Patterns");
            f_autoFill.setSize(360, 80);
            f_autoFill.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - f_autoFill.getWidth()) / 2,
                    (Toolkit.getDefaultToolkit().getScreenSize().height - f_autoFill.getHeight()) / 2);
            f_autoFill.setResizable(false);
            JPanel p_patterns = new JPanel();
            p_patterns.setOpaque(false);
            f_autoFill.add(p_patterns);
            p_patterns.add(new JLabel("What pattern do you like to load? "));
            String[] patternOptions = {"Select", "Beacon", "Blinker", "Pulsar", "Toad"};
            final JComboBox cb_patterns = new JComboBox(patternOptions);
            p_patterns.add(cb_patterns);
            cb_patterns.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cb_patterns.getSelectedIndex() > 0) {
                        grid.resetBoard();
                        grid.loadSelectedPatternFillBoard((String) cb_patterns.getSelectedItem());
                        f_autoFill.dispose();
                    }
                }
            });
            f_autoFill.setVisible(true);
        } else if (e.getSource().equals(mi_help_about)) {
            JOptionPane.showMessageDialog(null, "Conway's game of life was a cellular animation devised by the mathematician John Conway.\nThis Java, swing based implementation was created by OOTI-2017.\n\n");
        }else if (e.getSource().equals(mi_help_source)) {
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            try {
                desktop.browse(new URI("https://github.com/alaponin/GameOfLife"));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Source is available on GitHub at:\nhttps://github.com/alaponin/GameOfLife", "Source", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
