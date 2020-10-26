package BoatRaceSimulator.models.boat;

public class BoatResult {
    private Boat boat;
    private double raceTime;

    public BoatResult(Boat boat, double raceTime) {
        this.boat = boat;
        this.raceTime = raceTime;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public double getRaceTime() {
        return raceTime;
    }

    public void setRaceTime(double raceTime) {
        this.raceTime = raceTime;
    }
}
