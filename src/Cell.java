import java.util.ArrayList;
import java.util.List;

public class Cell {
    private Status currentStatus;
    private Status futureStatus;
    private int positionX;
    private int positionY;
    private List<Cell> neighbors;

    public Cell(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.currentStatus = Status.DEAD;
    }

    public void addNeighbor(Cell neighbor) {
        this.neighbors.add(neighbor);
    }

    public void evaluate() {

    }

    private void comeToLife() {

    }

    private void die() {

    }

    private int getNumberOfAliveNeighbors() {
        int aliveNeighbors = 0;
        for (Cell neighbor : neighbors) {
            if (neighbor.currentStatus.equals(Status.ALIVE)) {
                aliveNeighbors++;
            }
        }
        return aliveNeighbors;
    }

    public void update() {

    }

    @Override
    public String toString() {
        return "Cell{" +
                "currentStatus=" + currentStatus +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                '}';
    }
}
