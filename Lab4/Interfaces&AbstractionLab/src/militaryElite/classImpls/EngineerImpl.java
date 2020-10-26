package militaryElite.classImpls;

import militaryElite.Repair;
import militaryElite.interfaces.Engineer;

import java.util.List;
import java.util.Set;

public class EngineerImpl extends SpecialisedSoldierImpl implements Engineer {
    private List<Repair> repairs;

    public EngineerImpl(String id, String firstName, String lastName, double salary, String corps, List<Repair> repairs) {
        super(id, firstName, lastName, salary, corps);
        this.repairs = repairs;
    }

    @Override
    public List<Repair> getRepairs() {
        return this.repairs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator()).append("Repairs: ");

        repairs.stream().forEach(repair -> sb.append(System.lineSeparator()).append("  ").append(repair.toString()));

        return super.toString() + sb.toString();
    }
}
