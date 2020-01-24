package at.htlkaindorf.travelplanner.bl;

import android.content.Intent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Trip implements Serializable {
    private String city;
    private String country;
    private String countryCode;
    private transient LocalDate startDate;
    private int duration;

    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M.yyyy");

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        startDate = LocalDate.parse(ois.readUTF(), dtf);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeUTF(dtf.format(startDate));
    }

    public Trip(String city, String country, String countryCode, LocalDate startDate, int duration) {
        this.city = city;
        this.country = country;
        this.countryCode = countryCode;
        this.startDate = startDate;
        this.duration = duration;
    }

    public static Trip fromLine(String line) {
        //// city - country - country_code - startDate - duration in days
        //Picos - Brazil - BR - 11.5.2024 - 9
        //Takub - Philippines - PH - 13.9.2022 - 1
        String[] tokens = line.split(" - ");
        String city = tokens[0];
        String country = tokens[1];
        String countryCode = tokens[2];
        LocalDate startDate = LocalDate.parse(tokens[3], Trip.dtf);
        int duration = Integer.parseInt(tokens[4]);
        return new Trip(city, country, countryCode, startDate, duration);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("%s: %s - %s", this.city, TripAdapter.dtf.format(this.startDate), TripAdapter.dtf.format(this.getEndDate()));
    }

    public static TripResult compute(List<Trip> tripList) {
        int totalDays = tripList.get(0).getDuration();
        LocalDate minDate = tripList.get(0).startDate;
        LocalDate maxDate = tripList.get(0).getEndDate();

        for (int i = 1; i < tripList.size(); i++) {
            Trip curr = tripList.get(i);
            totalDays += curr.getDuration();
            if (curr.getStartDate().isBefore(minDate)) {
                minDate = curr.getStartDate();
            }

            if (curr.getEndDate().isAfter(maxDate)) {
                maxDate = curr.getEndDate();
            }
        }

        return new TripResult(totalDays, tripList.size(), minDate, maxDate);
    }

    public LocalDate getEndDate() {
        return this.startDate.plusDays(duration);
    }
}