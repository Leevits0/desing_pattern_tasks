package observer.weathersimulation;

// WeatherSubject.java

public interface WeatherSubject {
    void register(WeatherObserver observer);
    void remove(WeatherObserver observer);
    void notifyObservers(); // subject calls this internally after updates
}
