package observer_WeatherSimulation;
// AlertDisplay.java

public class AlertDisplay implements WeatherObserver {
    private final int lowThreshold;
    private final int highThreshold;

    public AlertDisplay(int lowThreshold, int highThreshold) {
        this.lowThreshold = lowThreshold;
        this.highThreshold = highThreshold;
    }

    @Override
    public void onTemperatureChanged(int newTemp) {
        if (newTemp <= lowThreshold) {
            System.out.printf("⚠️ ALERT: It's getting cold! (%d°C)%n", newTemp);
        } else if (newTemp >= highThreshold) {
            System.out.printf("🔥 ALERT: It's getting hot! (%d°C)%n", newTemp);
        } else {
            System.out.printf("AlertDisplay: Temperature is fine (%d°C)%n", newTemp);
        }
    }
}

