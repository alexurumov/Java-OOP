package BoatRaceSimulator.models.boat;

import BoatRaceSimulator.exceptions.ArgumentException;
import BoatRaceSimulator.models.race.Conditions;

public class SailBoat extends Boat {
    private int sailEfficiency;

    public SailBoat(String model, double weight, int sailEfficiency) throws ArgumentException {
        super(model, weight);
        this.setSailEfficiency(sailEfficiency);
    }

    public int getSailEfficiency() {
        return sailEfficiency;
    }

    public void setSailEfficiency(int sailEfficiency) throws ArgumentException {
        if (sailEfficiency < 1 || sailEfficiency > 100) {
            throw new ArgumentException("Sail Effectiveness must be between [1...100].");
        }
        this.sailEfficiency = sailEfficiency;
    }

    @Override
    public double getSpeed(Conditions conditions) {
        double result = (conditions.getWindSpeed() * (this.getSailEfficiency() / 100f)) - this.getWeight() + (conditions.getCurrentSpeed() / 2f);
        return result > 0 ? result : -1f;
    }
}
