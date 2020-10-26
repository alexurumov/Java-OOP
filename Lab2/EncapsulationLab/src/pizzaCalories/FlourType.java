package pizzaCalories;

public enum FlourType {
    White (1.5),
    Wholegrain(1.0);

    double calories;

    FlourType(double calories) {
        this.calories = calories;
    }

    public double getCalories() {
        return calories;
    }
}
