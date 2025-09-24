package adapter.calendar;

public interface NewDate {
    void setDay(int day);
    void setMonth(int month);    // 1..12 in THIS interface
    void setYear(int year);

    int getDay();
    int getMonth();              // 1..12 in THIS interface
    int getYear();

    void advanceDays(int days);  // can be negative to go backwards
}
