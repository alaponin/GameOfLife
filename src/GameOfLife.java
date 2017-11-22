import java.util.List;

public class GameOfLife {

    private Grid grid;

    public GameOfLife() {
        this.grid = new Grid();
    }


    public void tick() {
        //grid.evolve();

        System.out.println(grid.print());
    }

    public void randomInit(List<Cell> initialPattern) {
        // TODO: define
        this.grid.setInitialPattern(initialPattern);
    }

    public Grid getGrid() {
        return this.grid;
    }
}
