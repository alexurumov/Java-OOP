package militaryElite.classImpls;

import militaryElite.Mission;
import militaryElite.interfaces.Commando;

import java.util.List;
import java.util.Set;

public class ComandoImpl extends SpecialisedSoldierImpl implements Commando {
    private List<Mission> missions;

    public ComandoImpl(String id, String firstName, String lastName, double salary, String corps, List<Mission> missions) {
        super(id, firstName, lastName, salary, corps);
        this.missions = missions;
    }

    @Override
    public List<Mission> getMissions() {
        return this.missions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator()).append("Missions: ");

        if (!missions.isEmpty()) {
            this.missions.stream().forEach(mission -> sb.append(System.lineSeparator()).append("  ").append(mission.toString()));
        }

        return super.toString() + sb.toString();
    }
}
