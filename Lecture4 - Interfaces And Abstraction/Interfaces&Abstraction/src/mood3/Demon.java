package mood3;

public class Demon extends Character<Integer> {
    private static final String CHARACTER_TYPE = "Demon";

    public Demon(String username, int level, Double specalPoints) {
        super(username, CHARACTER_TYPE, level, specalPoints);
    }

    @Override
    public Integer getHashedPassword() {
        return this.getUsername().length() * 217;
    }

    @Override
    public String getSpecialPointsByLevel() {
        return String.format("%.1f", this.getSpecialPoints().doubleValue() * this.getLevel());
    }

    @Override
    public String getPasswordString() {
        return String.format("%d", this.getHashedPassword());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
