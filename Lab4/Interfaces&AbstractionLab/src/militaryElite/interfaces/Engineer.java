package militaryElite.interfaces;

import militaryElite.Repair;

import java.util.List;
import java.util.Set;

public interface Engineer extends SpecialisedSoldier {
    List<Repair> getRepairs();
}
