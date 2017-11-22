import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grid {

    private List<Cell>[][] table = new ArrayList[10][10];

    public Grid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = new Cell(i,j);
                table[i][j] = new ArrayList<Cell>();
                table[i][j].add(cell);
            }
        }
    }

    public void evolve() {

    }
}
