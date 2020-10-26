package BoatRaceSimulator.models.boat;

import BoatRaceSimulator.exceptions.ArgumentException;
import BoatRaceSimulator.models.engine.Engine;
import BoatRaceSimulator.models.race.Conditions;

public class Yacht extends Motorboat {
    private double cargo;

    public Yacht(String model, double weight, Engine engine, double cargo) throws ArgumentException {
        super(model, weight, engine);
        this.setCargo(cargo);
    }

    public double getCargo() {
        return cargo;
    }

    public void setCargo(double cargo) throws ArgumentException {
        if (cargo <= 0) {
            throw new ArgumentException("Cargo Weight must be a positive integer.");
        }
        this.cargo = cargo;
    }

    @Override
    public double getSpeed(Conditions conditions) {
        double result = this.getEngine().getOutput() - (this.getWeight() + this.getCargo()) + (conditions.getCurrentSpeed() / 2f);
        return result > 0 ? result : -1f;
    }
}
