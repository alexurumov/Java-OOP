package BoatRaceSimulator.models.boat;

import BoatRaceSimulator.exceptions.ArgumentException;
import BoatRaceSimulator.models.Model;
import BoatRaceSimulator.models.race.Conditions;

import java.util.Objects;

public abstract class Boat implements Model {
    private String model;
    private double weight;

    public Boat(String model, double weight) throws ArgumentException {
        this.setModel(model);
        this.setWeight(weight);
    }

    @Override
    public String getModel() {
        return model;
    }

    public void setModel(String model) throws ArgumentException {
        if (model.length() < 5) {
            throw new ArgumentException("Model's name must be at least 5 symbols long.");
        }
        this.model = model;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) throws ArgumentException {
        if (weight <= 0d) {
            throw new ArgumentException("Weight must be a positive integer.");
        }
        this.weight = weight;
    }

    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof  Boat)) return false;
        Boat boat = (Boat) o;
        return Objects.equals(model, boat.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }

    public abstract double getSpeed(Conditions conditions);
}
