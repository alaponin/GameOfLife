import java.util.ArrayList;
import java.util.List;

public class Grid {

    private static final int MAX_SIZE = 100;
    private int dimension;
    private List<Cell>[][] cells;
    //private List<List<Cell>> cells = new ArrayList<List<Cell>>();

    public Grid(int gridDimension) {
        this.dimension = gridDimension;
        cells = new ArrayList[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Cell cell = new Cell(i,j);
                cells[i][j] = new ArrayList<Cell>();
                cells[i][j].add(cell);
            }
        }
    }

    public void setInitialPattern(List<Cell> initialPattern) {

        for(int i = 0; i < initialPattern.size(); i++)
        {
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
}
