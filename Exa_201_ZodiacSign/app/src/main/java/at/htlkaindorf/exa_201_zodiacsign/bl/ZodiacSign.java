package at.htlkaindorf.exa_201_zodiacsign.bl;

import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ZodiacSign {
    private String name;
    private MonthDay startDate;
    private int drawableID;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd. MMMM");


    public ZodiacSign(String name, MonthDay startDate, int drawableID) {
        this.name = name;
        this.startDate = startDate;
        this.drawableID = drawableID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MonthDay getStartDate() {
        return startDate;
    }

    public void setStartDate(MonthDay startDate) {
        this.startDate = startDate;
    }

    public int getDrawableID() {
        return drawableID;
    }

    public void setDrawableID(int drawableID) {
        this.drawableID = drawableID;
    }

    public String getFormattedDate(ZodiacSign other) {
        return String.format("%s bis %s", ZodiacSign.dtf.format(this.startDate), ZodiacSign.dtf.format(other.getStartDate().withDayOfMonth(other.getStartDate().getDayOfMonth() - 1)));
    }
}
