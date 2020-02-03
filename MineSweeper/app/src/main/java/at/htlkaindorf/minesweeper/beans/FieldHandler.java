package at.htlkaindorf.minesweeper.beans;

import android.widget.Button;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class FieldHandler {
    private Button[][] buttons;
    private Field[][] fields;

    public FieldHandler(Button[][] buttons) {
        this.buttons = buttons;
        this.fields = getField();
    }

    private Field[][] getField() {
        Field[][] fields = new Field[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fields[i][j] = new Field(i, j);
            }
        }
        return fields;
    }

    public void populateWithMines() {
        HashSet<int[]> coordinates = new HashSet<>();
        Random r1 = new Random();

        while (coordinates.size() < 10) {
            //generate random coordinates
            coordinates.add(new int[]{r1.nextInt(9), r1.nextInt(9)});
        }

        this.printCoordinatesDebug(coordinates);

        for (int[] item : coordinates) {
            this.fields[item[0]][item[1]].setType(FieldType.BOMB);
            this.fields[item[0]][item[1]].setNumberSurrounding(-1);
        }
    }

    public void mapToGUI() {
        //TODO responsible for showing the data on the gui
    }

    public void mapSingleToGUI(int x, int y) {
        //TODO responsible for showing the data of a single object on the gui
    }

    public void printFieldDebug() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(this.fields[i][j].toString() + " ");
            }
            System.out.println();
        }
    }

    private void printCoordinatesDebug(HashSet<int[]> coordinates) {
        for (int[] item : coordinates) {
            System.out.println(Arrays.toString(item));
        }
    }

    public static void main(String[] args) {
        FieldHandler f1 = new FieldHandler(null);
        f1.populateWithMines();
        f1.printFieldDebug();
    }
}
