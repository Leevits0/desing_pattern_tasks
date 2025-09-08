package observer.weathersimulation;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class WeatherStation implements WeatherSubject, Runnable {

    private final List<WeatherObserver> observers = new CopyOnWriteArrayList<>();
    private final Random random = new Random();

    private final int minTemp;
    private final int maxTemp;
    private volatile int temperature;   // shared across threads
    private volatile boolean running = true;

    public WeatherStation(int minTemp, int maxTemp) {
        if (minTemp > maxTemp) throw new IllegalArgumentException("min > max");
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;

        // Initial random temperature within [minTemp, maxTemp]
        temperature = minTemp + random.nextInt(maxTemp - minTemp + 1);
    }

    // --- WeatherSubject API ---
    @Override
    public void register(WeatherObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void remove(WeatherObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (WeatherObserver observer : observers) {
            observer.onTemperatureChanged(temperature);
        }
    }

    // --- Thread logic ---
    @Override
    public void run() {
        while (running) {
            try {
                // Sleep random 1–5 seconds
                int sleepTime = 1000 + random.nextInt(5000 - 1000 + 1);
                Thread.sleep(sleepTime);

                // Temperature change by -1 or +1
                int delta = random.nextBoolean() ? 1 : -1;
                int next = clamp(temperature + delta, minTemp, maxTemp);

                if (next != temperature) {
                    temperature = next;
                    notifyObservers();
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // preserve interrupt flag
                break;
            }
        }
    }

    public void stop() {
        running = false;
    }

    public int getTemperature() {
        return temperature;
    }

    // Helper:
    private static int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }
}
