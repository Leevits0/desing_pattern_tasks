package observer.weathersimulation;



public class ConsoleDisplay implements WeatherObserver {
    private final String name;

    public ConsoleDisplay(String name) {
        this.name = name;
    }

    @Override
    public void onTemperatureChanged(int newTemp) {
        System.out.printf("[%s] Temperature update: %d°C%n", name, newTemp);
    }
}

