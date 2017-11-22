import java.util.List;

public class GameOfLife {

    private Grid grid;

    public GameOfLife() {
        this.grid = new Grid();
    }


    public void tick() {
        grid.evolve();
    }

    public void randomInit(List<Cell> initialPattern) {
        // TODO: define
        this.grid.setInitialPattern(initialPattern);
    }

    public Grid getGrid() {
        return this.grid;
    }

    public void print() {
        System.out.println(grid.print());
    }
}
