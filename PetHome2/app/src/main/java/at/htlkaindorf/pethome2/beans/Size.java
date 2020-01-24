package at.htlkaindorf.pethome2.beans;

public enum Size {
    SMALL,
    MEDIUM,
    LARGE;

    public static Size parse(String s) {
        switch (s) {
            case "S":
                return Size.SMALL;
            case "M":
                return Size.MEDIUM;
            case "L":
                return Size.LARGE;
            default:
                return null;
        }
    }
}
