package BoatRaceSimulator.models.boat;

import BoatRaceSimulator.exceptions.ArgumentException;
import BoatRaceSimulator.models.engine.Engine;
import BoatRaceSimulator.models.race.Conditions;

public class PowerBoat extends Motorboat {

    private Engine second;

    public PowerBoat(String model, double weight, Engine engine, Engine second) throws ArgumentException {
        super(model, weight, engine);
        this.second = second;
    }

    public Engine getSecond() {
        return second;
    }

    public void setSecond(Engine second) {
        this.second = second;
    }

    @Override
    public double getSpeed(Conditions conditions) {
        double result = (this.getEngine().getOutput() + this.getSecond().getOutput()) - this.getWeight() + (conditions.getCurrentSpeed() / 5f);
        return result > 0 ? result : -1f;
    }
}
