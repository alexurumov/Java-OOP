package BoatRaceSimulator.models.boat;

import BoatRaceSimulator.exceptions.ArgumentException;
import BoatRaceSimulator.models.engine.Engine;

public abstract class Motorboat extends Boat {
    private Engine engine;

    public Motorboat(String model, double weight, Engine engine) throws ArgumentException {
        super(model, weight);
        this.engine = engine;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
