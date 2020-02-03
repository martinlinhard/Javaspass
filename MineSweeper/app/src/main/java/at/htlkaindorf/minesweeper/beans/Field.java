package at.htlkaindorf.minesweeper.beans;

public class Field {
    private FieldType type;
    //-1 when FieldType equals "BOMB"
    private int numberSurrounding;
    private int x;
    private int y;
    private boolean tagged;
    private boolean hidden;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
        this.numberSurrounding = -1;
        this.type = FieldType.CLEAR;
        this.tagged = false;
        this.hidden = true;
    }

    public FieldType getType() {
        return type;
    }

    public int getNumberSurrounding() {
        return numberSurrounding;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isTagged() {
        return tagged;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    public void setNumberSurrounding(int numberSurrounding) {
        this.numberSurrounding = numberSurrounding;
    }

    public void setTagged(boolean tagged) {
        this.tagged = tagged;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @Override
    public String toString() {
        return this.type == FieldType.BOMB ? "BOMB" : (this.numberSurrounding == -1 ? "nothing" : (this.numberSurrounding + ""));
    }
}