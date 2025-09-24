package adapter.calendar;

public class Main {
    public static void main(String[] args) {
        NewDate date = new CalendarToNewDateAdapter();

        // Set an explicit date ((YYYY-MM-DD))
        date.setYear(2024);
        date.setMonth(2);     // February
        date.setDay(27);
        System.out.println("Start:  " + date);   // 2024-02-27

        // Advance forward across month boundary (leap year check)
        date.advanceDays(3);
        System.out.println("Plus 3: " + date);   // 2024-03-01

        // Go backwards 10 days
        date.advanceDays(-10);
        System.out.println("Minus10:" + date);   // 2024-02-20

        // Another example: end of year wrap
        date.setYear(2025);
        date.setMonth(12);
        date.setDay(31);
        date.advanceDays(1);
        System.out.println("NewYear:" + date);   // 2026-01-01
    }
}
