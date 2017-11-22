import java.util.ArrayList;
import java.util.List;

public class Grid {

    private static final int MAX_SIZE = 100;

    private int dimension;
    private List<Cell>[][] cells = new ArrayList[10][10];

    public Grid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = new Cell(i,j);
                cells[i][j] = new ArrayList<Cell>();

                if ((i == 3 || i == 4) && (j == 4 || j == 5)) {
                    cell.setCurrentStatus(Status.ALIVE);
                }
                cells[i][j].add(cell);
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
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
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
