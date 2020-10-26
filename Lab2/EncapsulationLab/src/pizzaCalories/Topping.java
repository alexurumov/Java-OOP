package pizzaCalories;

import java.security.InvalidParameterException;

public class Topping {
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    private void setToppingType(String toppingType) {
        try {
            Toppings.valueOf(toppingType);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Cannot place " + toppingType + " on top of your pizza.");
        }
        this.toppingType = toppingType;
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50) {
            throw new InvalidParameterException(this.toppingType + " weight should be in the range [1..50].");
        }
        this.weight = weight;
    }

    protected double calculateCalories() {
        return (2 * this.weight) * Toppings.valueOf(toppingType).getCalories();
    }
}
