package mordirsCrueltyPlan;

public enum Foods {
    CRAM(2),
    LEMBAS(3),
    APPLE(1),
    MELON(1),
    HONEYCAKE(5),
    MUSHROOMS(-10);

    int points;

    Foods(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}
