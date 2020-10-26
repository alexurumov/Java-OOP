package militaryElite.classImpls;

import militaryElite.interfaces.LeutenantGeneral;
import militaryElite.interfaces.Private;

import java.util.List;
import java.util.Set;

public class LeutenantGeneralImpl extends PrivateImpl implements LeutenantGeneral {
    private List<Private> privates;

    public LeutenantGeneralImpl(String id, String firstName, String lastName, double salary, List<Private> privates) {
        super(id, firstName, lastName, salary);
        this.privates = privates;
    }

    @Override
    public List<Private> getPrivates() {
        return this.privates;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator()).append("Privates:");

        this.privates.stream()
                .sorted((p1, p2) -> p2.getId().compareTo(p1.getId()))
                .forEach(p -> sb.append(System.lineSeparator()).append("  ").append(p.toString()));

        return super.toString() + sb.toString();
    }
}
