package at.htlkaindorf.exa_108_numberpuzzlegame.beans;

import java.util.Random;

public class Coordinate {
    private int x;
    private int y;
    private final int MAX_X;
    private final int MAX_Y;

    private Random random;

    public Coordinate(int MAX_X, int MAX_Y) {
        this.MAX_X = MAX_X;
        this.MAX_Y = MAX_Y;
        this.random = new Random();

        this.setRandomCoordinates();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private void setRandomCoordinates() {
        this.x = random.nextInt(MAX_X) ;
        this.y = random.nextInt(MAX_Y);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
