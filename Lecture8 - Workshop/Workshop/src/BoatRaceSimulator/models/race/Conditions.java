package BoatRaceSimulator.models.race;

public class Conditions {
    private double windSpeed;
    private double currentSpeed;

    public Conditions(double windSpeed, double currentSpeed) {
        this.windSpeed = windSpeed;
        this.currentSpeed = currentSpeed;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }
}
