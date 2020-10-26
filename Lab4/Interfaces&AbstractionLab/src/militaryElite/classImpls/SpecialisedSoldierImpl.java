package militaryElite.classImpls;

import militaryElite.interfaces.SpecialisedSoldier;

public class SpecialisedSoldierImpl extends PrivateImpl implements SpecialisedSoldier {
    private String corps;

    public SpecialisedSoldierImpl(String id, String firstName, String lastName, double salary, String corps) {
        super(id, firstName, lastName, salary);
        this.setCorps(corps);
    }

    public void setCorps(String corps) {
        if (corps.equals("Airforces") || corps.equals("Marines")) {
            this.corps = corps;
        } else {
            throw new IllegalArgumentException("Invalid corps!");
        }
    }

    @Override
    public String getCorps() {
        return this.corps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator()).append("Corps: ").append(this.getCorps());

        return super.toString() + sb.toString();
    }
}
