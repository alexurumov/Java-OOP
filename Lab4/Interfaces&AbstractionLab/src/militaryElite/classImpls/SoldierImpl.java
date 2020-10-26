package militaryElite.classImpls;

import militaryElite.interfaces.Soldier;

public class SoldierImpl implements Soldier {
    private String id;
    private String firstName;
    private String lastName;

    public SoldierImpl(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(this.getFirstName()).append(" ").append(this.getLastName())
                .append(" ").append("Id: ").append(this.getId()).append(" ");

        return sb.toString();
    }
}
