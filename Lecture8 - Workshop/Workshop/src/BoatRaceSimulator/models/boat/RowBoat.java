package BoatRaceSimulator.models.boat;

import BoatRaceSimulator.exceptions.ArgumentException;
import BoatRaceSimulator.models.race.Conditions;

public class RowBoat extends Boat {
    private int oars;

    public RowBoat(String model, double weight, int oars) throws ArgumentException {
        super(model, weight);
        this.setOars(oars);
    }

    public int getOars() {
        return oars;
    }

    public void setOars(int oars) throws ArgumentException {
        if (oars <= 0) {
            throw new ArgumentException("Oars must be a positive integer.");
        }
        this.oars = oars;
    }

    @Override
    public double getSpeed(Conditions conditions) {
        double result =  (this.getOars() * 100) - this.getWeight() + conditions.getCurrentSpeed();
        return result > 0 ? result : -1f;
    }
}
