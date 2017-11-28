package logic;

import logic.Cell;
import tests.Test;
import tests.TestFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class Grid extends JPanel implements ComponentListener, MouseListener, MouseMotionListener, Runnable {

    private List<Cell>[][] cells;
    private static final int BLOCK_SIZE = 10;//for GUI
    private Dimension d_gameBoardSize = null; //for GUI
    private ArrayList<Point> point = new ArrayList<Point>(0);//for GUI
    private Thread game;

    public Grid() {
        // Add resizing listener
        addComponentListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void setInitialPattern(List<Cell> initialPattern) {
        for (int i = 0; i < initialPattern.size(); i++) {
            Cell cell = initialPattern.get(i);
            cells[cell.getPositionX()][cell.getPositionY()].get(0).setCurrentStatus(Status.ALIVE);
        }
        addTheNeighbors();
    }

    private void addTheNeighbors() {
        int dimensionX = d_gameBoardSize.width - 1;
        int dimensionY = d_gameBoardSize.height - 1;
        for (int i = 0; i < dimensionX; i++) {
            for (int j = 0; j < dimensionY; j++) {
                if (i >= 1 && j >= 1) {
                    cells[i][j].get(0).addNeighbor(cells[i - 1][j - 1].get(0));
                }
                if (j < dimensionY - 1 && i >= 1) {
                    cells[i][j].get(0).addNeighbor(cells[i - 1][j + 1].get(0));
                }
                if (i < dimensionX - 1 && j >= 1) {
                    cells[i][j].get(0).addNeighbor(cells[i + 1][j - 1].get(0));
                }
                if (i < dimensionX - 1 && j < dimensionY - 1) {
                    cells[i][j].get(0).addNeighbor(cells[i + 1][j + 1].get(0));
                }
                if (i >= 1) {
                    cells[i][j].get(0).addNeighbor(cells[i - 1][j].get(0));
                }
                if (j >= 1) {
                    cells[i][j].get(0).addNeighbor(cells[i][j - 1].get(0));
                }
                if (j < dimensionY - 1) {
                    cells[i][j].get(0).addNeighbor(cells[i][j + 1].get(0));
                }
                if (i < dimensionX - 1) {
                    cells[i][j].get(0).addNeighbor(cells[i + 1][j].get(0));
                }
            }
        }
    }

    public void evolve() {
        for (List<Cell>[] cell : cells) {
            for (List<Cell> aCell : cell) {
                aCell.get(0).evaluate();
            }
        }

        for (List<Cell>[] cell : cells) {
            for (List<Cell> aCell : cell) {
                aCell.get(0).update();
            }
        }
    }


    @Override
    public void componentResized(ComponentEvent e) {
        // Setup the game board size with proper boundaries
        d_gameBoardSize = new Dimension(getWidth() / BLOCK_SIZE - 2, getHeight() / BLOCK_SIZE - 2);
        updateArraySize();
    }

    private void updateArraySize() {
        ArrayList<Point> removeList = new ArrayList<Point>(0);
        for (Point current : point) {
            if ((current.x > d_gameBoardSize.width - 1) || (current.y > d_gameBoardSize.height - 1)) {
                removeList.add(current);
            }
        }
        point.removeAll(removeList);
        repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
// Mouse was released (user clicked)
        addPoint(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Mouse is being dragged, user wants multiple selections
        addPoint(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void run() {

        List<Cell> initialPattern = new ArrayList<Cell>();

        cells = new ArrayList[d_gameBoardSize.width][d_gameBoardSize.height];
        for (int i = 0; i < d_gameBoardSize.width; i++) {
            for (int j = 0; j < d_gameBoardSize.height; j++) {
                Cell cell = new Cell(i, j);
                cells[i][j] = new ArrayList<Cell>();
                cells[i][j].add(cell);
            }
        }
        for (Point current : point) {
            Cell initialPatternCell = new Cell(current.x, current.y, Status.ALIVE);
            initialPattern.add(initialPatternCell);
        }
        setInitialPattern(initialPattern);
        evolve();
        resetBoard();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].get(0).getCurrentStatus().equals(Status.ALIVE)) {
                    Point p = new Point();
                    p.x = i;
                    p.y = j;
                    point.add(p);
                }
            }
        }
        repaint();
        try {
            Thread.sleep(800);
            run();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt(); // restore interrupted status
        }
    }

    public void setGameBeingPlayed(boolean isBeingPlayed) {
        if (isBeingPlayed) {
            game = new Thread(this);
            game.start();
        }
        if (!isBeingPlayed) {
            game.interrupt();
            try {
                game.join();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void addPoint(MouseEvent me) {
        int x = me.getPoint().x / BLOCK_SIZE - 1;
        int y = me.getPoint().y / BLOCK_SIZE - 1;
        if ((x >= 0) && (x < d_gameBoardSize.width) && (y >= 0) && (y < d_gameBoardSize.height)) {
            addPoint(x, y);
        }
    }

    public void addPoint(int x, int y) {

        if (!point.contains(new Point(x, y))) {
            point.add(new Point(x, y));
        }
        repaint();
    }

    public void resetBoard() {
        point.clear();
        repaint();
    }

    public void randomlyFillBoard() {
        int percent = (int) Math.floor(Math.random() * 100) + 1;
        for (int i = 0; i < d_gameBoardSize.width; i++) {
            for (int j = 0; j < d_gameBoardSize.height; j++) {
                if (Math.random() * 100 < percent) {
                    addPoint(i, j);
                }
            }
        }
    }

    public void loadSelectedPatternFillBoard(String selectedPattern) {

        if (selectedPattern.equalsIgnoreCase("Blinker")) {

            assignCellsToGrid("blinker");
        } else if (selectedPattern.equalsIgnoreCase("Beacon")) {

            assignCellsToGrid("beacon");
        } else if (selectedPattern.equalsIgnoreCase("Toad")) {

            assignCellsToGrid("toad");
        } else if (selectedPattern.equalsIgnoreCase("Pulsar")) {

            assignCellsToGrid("pulsar");
        }

    }

    private void assignCellsToGrid(String patternName) {
        TestFactory testFactory = new TestFactory();
        Test testScenario = testFactory.getTestScenario(patternName);
        List<Cell> aliveCells = testScenario.obtainScenario();

        for (Cell cell : aliveCells) {
            Point point = new Point(cell.getPositionX(), cell.getPositionY());
            addPoint(point.x, point.y);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics localGraphics = g;
        super.paintComponent(localGraphics);
        try {
            for (Point newPoint : point) {
                // Draw new point
                localGraphics.setColor(Color.black);
                localGraphics.fillRect(BLOCK_SIZE + (BLOCK_SIZE * newPoint.x), BLOCK_SIZE + (BLOCK_SIZE * newPoint.y), BLOCK_SIZE, BLOCK_SIZE);
            }
        } catch (ConcurrentModificationException cme) {
            cme.printStackTrace();
        }
        // Setup grid
        localGraphics.setColor(Color.BLACK);
        for (int i = 0; i <= d_gameBoardSize.width; i++) {
            localGraphics.drawLine(((i * BLOCK_SIZE) + BLOCK_SIZE), BLOCK_SIZE, (i * BLOCK_SIZE) + BLOCK_SIZE, BLOCK_SIZE + (BLOCK_SIZE * d_gameBoardSize.height));
        }
        for (int i = 0; i <= d_gameBoardSize.height; i++) {
            localGraphics.drawLine(BLOCK_SIZE, ((i * BLOCK_SIZE) + BLOCK_SIZE), BLOCK_SIZE * (d_gameBoardSize.width + 1), ((i * BLOCK_SIZE) + BLOCK_SIZE));
        }
        g = localGraphics;
    }

}
