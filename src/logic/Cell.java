package logic;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private Status currentStatus;
    private Status futureStatus;

    private int positionX;
    private int positionY;
    private List<Cell> neighbors;

    public Cell(int positionX, int positionY) {
        this(positionX, positionY, Status.DEAD);
    }

    public Cell(int positionX, int positionY, Status status) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.currentStatus = status;
        this.neighbors = new ArrayList<Cell>();
    }

    void addNeighbor(Cell neighbor) {
        this.neighbors.add(neighbor);
    }

    /**
     * When the cell is alive and has more than 3 or less than 2 alive neighbors, it will die.
     * When the cell is dead and has 3 alive neighbors, it will come back to life.
     * In any other case it will keep the current status.
     */
    void evaluate() {

        if ( this.currentStatus.equals(Status.ALIVE) && (this.getNumberOfAliveNeighbors() > 3 || this.getNumberOfAliveNeighbors() < 2)) {
            this.die();
        } else if (this.currentStatus.equals(Status.DEAD) && this.getNumberOfAliveNeighbors() == 3) {
            this.comeToLife();
        } else {
            this.futureStatus = this.currentStatus;
        }

    }

    void comeToLife() {
        this.futureStatus = Status.ALIVE;
    }

    void die() {
        this.futureStatus = Status.DEAD;
    }

    int getNumberOfAliveNeighbors() {

        int aliveNeighbors = 0;

        for (Cell neighbor : neighbors) {

            if (neighbor.currentStatus.equals(Status.ALIVE)) {
                aliveNeighbors++;
            }

        }

        return aliveNeighbors;
    }

    void update() {
        this.currentStatus = this.futureStatus;
    }

    Status getCurrentStatus() {
        return currentStatus;
    }

    int getPositionX() {
        return positionX;
    }

    int getPositionY() {
        return positionY;
    }
}
