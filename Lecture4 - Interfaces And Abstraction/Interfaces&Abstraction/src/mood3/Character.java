package mood3;

public abstract class Character<T> implements GameObject<T> {
    private String username;
    private String characterType;
    private Number specialPoints;
    private int level;
    private T hashedPassword;

    Character(String username, String characterType,int level, Number specialPoints) {
        this.username = "\"" + username + "\"";
        this.characterType = characterType;
        this.level = level;
        this.specialPoints = specialPoints;
    }

    @Override
    public String getUsername() {
        return this.username.substring(1, this.username.length() -1);
    }

    @Override
    public String getCharacterType() {
        return this.characterType;
    }

    @Override
    public Number getSpecialPoints() {
        return this.specialPoints;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void setHashedPassword(T password) {
        this.hashedPassword = password;
    }

    @Override
    public String toString() {
        return String.format("\"%s\" | \"%s\" -> %s\n%s"
                , this.getUsername()
                , this.getPasswordString()
                , this.getCharacterType()
                , this.getSpecialPointsByLevel());
    }

    abstract String getSpecialPointsByLevel();

    abstract String getPasswordString();
}
