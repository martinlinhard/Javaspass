package at.htlkaindorf.exa_205_game2048;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import at.htlkaindorf.exa_205_game2048.beans.Direction;

public class FieldHandler {
    private int[][] field;
    private List<Integer> emptyIndexes;
    private Random random;

    public FieldHandler() {
        int[][] field = new int[4][4];
        this.field = field;
        this.random = new Random();
        this.emptyIndexes = new ArrayList<>();

        this.initializeField();
    }

    private void initializeField() {
        for (int i = 0; i < 16; i++) this.emptyIndexes.add(i);

        this.addRandomNumber();
        this.addRandomNumber();
    }

    private void addRandomNumber() {
        if (this.emptyIndexes.size() < 1) return;

        int randomNumber = random.nextInt(3);
        int randomIndex = this.getRandomIndexFromAvailable();
        int index_y = randomIndex / 4;
        int index_x = randomIndex % 4;

        if (randomNumber < 2) {
            //add 2 to random index
            this.field[index_y][index_x] = 2;
        } else {
            // add 4 to random index
            this.field[index_y][index_x] = 4;
        }
    }

    private int getRandomIndexFromAvailable() {
        int index = this.random.nextInt(this.emptyIndexes.size());
        int val = this.emptyIndexes.get(index);
        this.emptyIndexes.remove(index);
        return val;
    }

    public void printForDebug() {
        for (int[] line : this.field) {
            System.out.println(Arrays.toString(line));
        }
    }

    private void makeMove(Direction direction) {
        switch (direction) {
            case TOP:
                this.moveToTheTop();
                break;
            case BOTTOM:
                this.moveToTheBottom();
                break;
            case LEFT:
                this.moveToTheLeft();
                break;
            case RIGHT:
                this.moveToTheRight();
                break;
        }
    }

    private void moveToTheLeft() {

    }

    private void moveToTheRight() {
    }

    private void moveToTheTop() {
    }

    private void moveToTheBottom() {
    }

    public static void main(String[] args) {
        FieldHandler f = new FieldHandler();
        f.printForDebug();
    }
}
