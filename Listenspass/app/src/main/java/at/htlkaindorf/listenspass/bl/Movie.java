package at.htlkaindorf.listenspass.bl;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Movie {
    private String name;
    private YearMonth releaseDate;
    private int duration; //in minuten
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMMM - yyyy");

    public Movie(String name, YearMonth releaseDate, int duration) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public YearMonth getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(YearMonth releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getReleaseDateString() {
        return DATE_TIME_FORMATTER.format(this.releaseDate);
    }

    public String getDurationString() {
        return String.format("%02d:%02d", duration / 60, duration%60);
    }
}
