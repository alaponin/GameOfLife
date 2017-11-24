package logic;

import java.util.ArrayList;
import java.util.List;

class Grid {

    private int dimension;

    private List<Cell>[][] cells;

    Grid(int gridDimension) {
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

    void setInitialPattern(List<Cell> initialPattern) {

        for (Cell cell : initialPattern) {
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
                if (i < dimension && j < dimension - 1 && i >= 1) {
                    cells[i][j].get(0).addNeighbor(cells[i - 1][j + 1].get(0));
                }
                if (i < dimension - 1 && j < dimension && j >= 1) {
                    cells[i][j].get(0).addNeighbor(cells[i + 1][j - 1].get(0));
                }
                if (i < dimension - 1 && j < dimension - 1) {
                    cells[i][j].get(0).addNeighbor(cells[i + 1][j + 1].get(0));
                }
                if (i < dimension && j < dimension && i >= 1) {
                    cells[i][j].get(0).addNeighbor(cells[i - 1][j].get(0));
                }
                if (i < dimension && j < dimension && j >= 1) {
                    cells[i][j].get(0).addNeighbor(cells[i][j - 1].get(0));
                }
                if (i < dimension && j < dimension - 1 ) {
                    cells[i][j].get(0).addNeighbor(cells[i][j + 1].get(0));
                }
                if (i < dimension - 1 && j < dimension) {
                    cells[i][j].get(0).addNeighbor(cells[i + 1][j].get(0));
                }
            }

        }

    }

    void evolve() {

        for (List<Cell>[] cellList : cells) {
            for (List<Cell> cell : cellList) {
                cell.get(0).evaluate();
            }
        }

        for (List<Cell>[] cellList : cells) {
            for (List<Cell> cell : cellList) {
                cell.get(0).update();
            }
        }
    }

    String print() {

        StringBuilder result = new StringBuilder();

        for (List<Cell>[] cell : cells) {
            for (List<Cell> aCell : cell) {
                result.append(aCell.get(0).getCurrentStatus().toString()).append(" ");
            }

            result.append("\n");
        }

        return result.toString();
    }

    public int getDimension() {
        return dimension;
    }
}
