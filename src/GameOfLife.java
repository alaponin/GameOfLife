public class GameOfLife {

    private Grid grid;

    public GameOfLife() {
        this.grid = new Grid();
    }

    public void tick() {
        grid.evolve();

        System.out.println(grid.print());
    }
}
