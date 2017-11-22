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
        this.neighbors = new ArrayList<Cell>();
    }

    public void addNeighbor(Cell neighbor) {
        this.neighbors.add(neighbor);
    }

    public void evaluate() {
        if (this.currentStatus.equals(Status.ALIVE) && (this.getNumberOfAliveNeighbors() > 3 || this.getNumberOfAliveNeighbors() < 2)) {
            this.die();
        } else if (this.currentStatus.equals(Status.DEAD) && this.getNumberOfAliveNeighbors() == 3) {
            this.comeToLife();
        } else {
            this.futureStatus = this.currentStatus;
        }
    }

    private void comeToLife() {
        this.futureStatus = Status.ALIVE;
    }

    private void die() {
        this.futureStatus = Status.DEAD;
    }

    public int getNumberOfAliveNeighbors() {
        int aliveNeighbors = 0;
        for (Cell neighbor : neighbors) {
            if (neighbor.currentStatus.equals(Status.ALIVE)) {
                aliveNeighbors++;
            }
        }
        return aliveNeighbors;
    }

    public void update() {
        this.currentStatus = this.futureStatus;
    }

    public Status getCurrentStatus() {
        return currentStatus;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "currentStatus=" + currentStatus +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                '}';
    }

    //TODO: hack delete later
    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }
}
