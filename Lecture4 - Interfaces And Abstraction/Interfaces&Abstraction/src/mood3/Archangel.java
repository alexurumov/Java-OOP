package mood3;

public class Archangel extends Character<String> {
    private static final String CHARACTER_TYPE = "Archangel";
    public Archangel(String username, int level, Integer specalPoints) {
        super(username, CHARACTER_TYPE, level, specalPoints);
    }

    @Override
    public String getHashedPassword() {
        StringBuilder sb = new StringBuilder(this.getUsername());
        return sb.reverse().append(this.getUsername().length() * 21).toString();
    }

    @Override
    public String getSpecialPointsByLevel() {
        return String.format("%d", this.getSpecialPoints().intValue() * this.getLevel());
    }

    @Override
    public String getPasswordString() {
        return String.format("%s", this.getHashedPassword());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
