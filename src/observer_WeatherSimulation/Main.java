package observer_WeatherSimulation;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WeatherStation station = new WeatherStation(-5, 35);

        Thread stationThread = new Thread(station, "WeatherStation-Thread");
        stationThread.start();

        WeatherObserver displayA = new ConsoleDisplay("Display-A");
        WeatherObserver displayB = new AlertDisplay(0, 30);

        station.register(displayA);
        station.register(displayB);

        // Let it run for 10 seconds
        Thread.sleep(10_000);

        // Remove one observer
        station.remove(displayA);
        System.out.println(">>> Removed Display-A");

        // Keep running for another 10 seconds
        Thread.sleep(10_000);

        // Stop the station
        station.stop();
        stationThread.join();

        System.out.println("Simulation finished.");
    }
}
