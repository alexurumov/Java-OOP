package BoatRaceSimulator.models.engine;

import BoatRaceSimulator.exceptions.ArgumentException;

public class JetEngine extends Engine {
    public JetEngine(String model, int horsepower, int displacement) throws ArgumentException {
        super(model, horsepower, displacement);
    }

    @Override
    double calculateOutput() {
        return (this.getHorsepower() * 5) + this.getDisplacement();
    }
}
