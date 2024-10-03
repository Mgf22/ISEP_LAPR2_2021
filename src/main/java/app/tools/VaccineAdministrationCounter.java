package app.tools;

import java.time.LocalDate;

public class VaccineAdministrationCounter {
    private LocalDate date;
    private int count;

    public VaccineAdministrationCounter(LocalDate date, int count) {
        this.date = date;
        this.count = count;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getCount() {
        return count;
    }
}
