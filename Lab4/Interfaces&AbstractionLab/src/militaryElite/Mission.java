package militaryElite;

public class Mission {
    private String codeName;
    private String state;

    public Mission(String codeName, String state) {
        this.codeName = codeName;
        this.setState(state);
    }

    public String getCodeName() {
        return codeName;
    }

    public String getState() {
        return state;
    }

    public void completeMission() {
        this.state = "Finished";
    }

    public void setState(String state) {
        if (state.equals("inProgress") || state.equals("Finished")) {
            this.state = state;
        } else {
            throw new IllegalArgumentException("Ivnalid state!");
        }
    }

    @Override
    public String toString() {
        return String.format("Code Name: %s State: %s", this.getCodeName(), this.getState());
    }
}
