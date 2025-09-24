package adapter.calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarToNewDateAdapter implements NewDate {
    private final Calendar cal;

    public CalendarToNewDateAdapter() {
        // GregorianCalendar implementation
        this.cal = new GregorianCalendar();
        // Strictness about invalid dates (e.g., Feb 30 -> throws)
        this.cal.setLenient(false);
    }

    // --- setters  ---
    @Override
    public void setDay(int day) {
        cal.set(Calendar.DAY_OF_MONTH, day);
        // touch to trigger validation
        cal.getTime();
    }

    @Override
    public void setMonth(int month) {
        cal.set(Calendar.MONTH, month - 1); // translate 1..12 -> 0..11
        cal.getTime();
    }

    @Override
    public void setYear(int year) {
        cal.set(Calendar.YEAR, year);
        cal.getTime();
    }

    // --- getters (translate back to 1..12) ---
    @Override
    public int getDay() {
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public int getMonth() {
        return cal.get(Calendar.MONTH) + 1;
    }

    @Override
    public int getYear() {
        return cal.get(Calendar.YEAR);
    }

    // --- behavior ---
    @Override
    public void advanceDays(int days) {
        cal.add(Calendar.DAY_OF_MONTH, days);
    }

    // Convenience for printing
    @Override
    public String toString() {
        return "%04d-%02d-%02d".formatted(getYear(), getMonth(), getDay());
    }
}
