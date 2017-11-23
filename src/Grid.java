import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class Grid extends JPanel implements ComponentListener, MouseListener, MouseMotionListener, Runnable {

    private static final int MAX_SIZE = 100;
    private int dimension = 10;
    private List<Cell>[][] cells = new ArrayList[dimension][dimension];
    private static final int BLOCK_SIZE = 10;//for GUI
    private Dimension d_gameBoardSize = null; //for GUI
    private ArrayList<Point> point = new ArrayList<Point>(0);//for GUI

    public Grid() {
        // Add resizing listener
        addComponentListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Cell cell = new Cell(i, j);
                cells[i][j] = new ArrayList<Cell>();
                cells[i][j].add(cell);
            }
        }
    }

    public void setInitialPattern(List<Cell> initialPattern) {

        for (int i = 0; i < initialPattern.size(); i++) {
            Cell cell = initialPattern.get(i);
            cells[cell.getPositionX()][cell.getPositionY()] = new ArrayList<Cell>();
            cells[cell.getPositionX()][cell.getPositionY()].add(cell);
        }

        addTheNeighbors();
    }

    private void addTheNeighbors() {

        int dimension = this.dimension;
        for (int i = 0; i < dimension; i++) {

            for (int j = 0; j < dimension; j++) {

                if (i < dimension && j < dimension && i >= 1 && j >= 1) {
                    cells[i][j].get(0).addNeighbor(cells[i - 1][j - 1].get(0));
                }
                if (i < dimension && j < dimension - 1 && i >= 1 && j >= 0) {
                    cells[i][j].get(0).addNeighbor(cells[i - 1][j + 1].get(0));
                }
                if (i < dimension - 1 && j < dimension && i >= 0 && j >= 1) {
                    cells[i][j].get(0).addNeighbor(cells[i + 1][j - 1].get(0));
                }
                if (i < dimension - 1 && j < dimension - 1 && i >= 0 && j >= 0) {
                    cells[i][j].get(0).addNeighbor(cells[i + 1][j + 1].get(0));
                }
                if (i < dimension && j < dimension && i >= 1 && j >= 0) {
                    cells[i][j].get(0).addNeighbor(cells[i - 1][j].get(0));
                }
                if (i < dimension && j < dimension && i >= 0 && j >= 1) {
                    cells[i][j].get(0).addNeighbor(cells[i][j - 1].get(0));
                }
                if (i < dimension && j < dimension - 1 && i >= 0 && j >= 0) {
                    cells[i][j].get(0).addNeighbor(cells[i][j + 1].get(0));
                }
                if (i < dimension - 1 && j < dimension && i >= 0 && j >= 0) {
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

    public String print() {

        String result = "";

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                result += cells[i][j].get(0).getCurrentStatus().toString() + " ";
            }

            result += "\n";
        }

        return result;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        // Setup the game board size with proper boundries
        d_gameBoardSize = new Dimension(getWidth() / BLOCK_SIZE - 2, getHeight() / BLOCK_SIZE - 2);
        updateArraySize();
    }
    private void updateArraySize() {
        ArrayList<Point> removeList = new ArrayList<Point>(0);
        for (Point current : point) {
            if ((current.x > d_gameBoardSize.width-1) || (current.y > d_gameBoardSize.height-1)) {
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

    }

    public void addPoint(MouseEvent me) {
        int x = me.getPoint().x / BLOCK_SIZE - 1;
        int y = me.getPoint().y / BLOCK_SIZE - 1;
        if ((x >= 0) && (x < d_gameBoardSize.width) && (y >= 0) && (y < d_gameBoardSize.height)) {
            addPoint(x, y);
        }
    }

    public void addPoint(int x, int y) {
        if (!point.contains(new Point(x,y))) {
            point.add(new Point(x,y));
        }
        repaint();
    }
    public void removePoint(int x, int y) {
        point.remove(new Point(x,y));
    }
    public void resetBoard() {
        point.clear();
    }
    public void randomlyFillBoard(int percent) {
        for (int i=0; i<d_gameBoardSize.width; i++) {
            for (int j=0; j<d_gameBoardSize.height; j++) {
                if (Math.random()*100 < percent) {
                    addPoint(i,j);
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            for (Point newPoint : point) {
                // Draw new point
                g.setColor(Color.green);
                g.fillRect(BLOCK_SIZE + (BLOCK_SIZE*newPoint.x), BLOCK_SIZE + (BLOCK_SIZE*newPoint.y), BLOCK_SIZE, BLOCK_SIZE);
            }
        } catch (ConcurrentModificationException cme) {}
        // Setup grid
        g.setColor(Color.BLACK);
        for (int i=0; i<=d_gameBoardSize.width; i++) {
            g.drawLine(((i*BLOCK_SIZE)+BLOCK_SIZE), BLOCK_SIZE, (i*BLOCK_SIZE)+BLOCK_SIZE, BLOCK_SIZE + (BLOCK_SIZE*d_gameBoardSize.height));
        }
        for (int i=0; i<=d_gameBoardSize.height; i++) {
            g.drawLine(BLOCK_SIZE, ((i*BLOCK_SIZE)+BLOCK_SIZE), BLOCK_SIZE*(d_gameBoardSize.width+1), ((i*BLOCK_SIZE)+BLOCK_SIZE));
        }
    }

}
