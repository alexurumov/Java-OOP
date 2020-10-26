package mordirsCrueltyPlan;

public class Gandalf {
    private static final int DEFAULT_POINTS = 0;
    private int foodPoints;

    public Gandalf() {
        this.foodPoints = DEFAULT_POINTS;
    }

    public void eatFood(String food) {
        try {
            this.foodPoints += Foods.valueOf(food.toUpperCase()).getPoints();
        } catch (IllegalArgumentException error) {
            this.foodPoints--;
        }
    }

    public int getFoodPoints() {
        return foodPoints;
    }

    public String getMood() {
        String mood = "JavaScript";
        if (this.getFoodPoints() < -5) {
            mood = "Angry";
        } else if (this.getFoodPoints() < 1) {
            mood = "Sad";
        } else if (this.getFoodPoints() < 16) {
            mood = "Happy";
        }
        return mood;
    }
}
