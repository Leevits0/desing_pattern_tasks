package observer.weathersimulation;

// WeatherObserver.java

public interface WeatherObserver {
    // Called by the subject whenever temperature changes
    void onTemperatureChanged(int newTemp);
}
