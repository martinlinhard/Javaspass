package at.htlkaindorf.travelplanner.bl;

import java.time.LocalDate;

public class TripResult {
    private int totalDays;
    private int amountOfTrips;
    private LocalDate startDate;
    private LocalDate endDate;

    public TripResult(int totalDays, int amountOfTrips, LocalDate startDate, LocalDate endDate) {
        this.totalDays = totalDays;
        this.amountOfTrips = amountOfTrips;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public int getAmountOfTrips() {
        return amountOfTrips;
    }

    public void setAmountOfTrips(int amountOfTrips) {
        this.amountOfTrips = amountOfTrips;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
