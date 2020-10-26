package BoatRaceSimulator.models.engine;

import BoatRaceSimulator.exceptions.ArgumentException;
import BoatRaceSimulator.models.Model;

public abstract class Engine implements Model {
    private String model;
    private double output;
    private int horsepower;
    private int displacement;

    public Engine(String model, int horsepower, int displacement) throws ArgumentException {
        this.setModel(model);
        this.setHorsepower(horsepower);
        this.setDisplacement(displacement);

        this.output = this.calculateOutput();
    }

    abstract double calculateOutput();

    @Override
    public String getModel() {
        return model;
    }

    public void setModel(String model) throws ArgumentException {
        if (model.length() < 3) {
            throw new ArgumentException("Model's name must be at least 3 symbols long.");
        }
        this.model = model;
    }

    public double getOutput() {
        return output;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) throws ArgumentException {
        if (horsepower <= 0) {
            throw new ArgumentException("Horsepower must be a positive integer.");
        }
        this.horsepower = horsepower;
    }

    public int getDisplacement() {
        return displacement;
    }

    public void setDisplacement(int displacement) throws ArgumentException {
        if (displacement <= 0) {
            throw new ArgumentException("Displacement must be a positive integer.");
        }
        this.displacement = displacement;
    }
}
