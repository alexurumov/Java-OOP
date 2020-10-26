package pizzaCalories;

public enum Toppings {
    Meat(1.2),
    Veggies(0.8),
    Cheese(1.1),
    Sauce(0.9);

    double calories;

    Toppings(double calories) {
        this.calories = calories;
    }

    public double getCalories() {
        return calories;
    }
}
