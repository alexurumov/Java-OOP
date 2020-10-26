package BoatRaceSimulator.models.engine;

import BoatRaceSimulator.exceptions.ArgumentException;

public class SterndriveEngine extends Engine {

    public SterndriveEngine(String model, int horsepower, int displacement) throws ArgumentException {
        super(model, horsepower, displacement);
    }

    @Override
    double calculateOutput() {
        return (this.getHorsepower() * 7) + this.getDisplacement();
    }
}
