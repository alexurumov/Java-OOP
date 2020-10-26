package militaryElite.interfaces;

import militaryElite.Mission;

import java.util.List;
import java.util.Set;

public interface Commando extends SpecialisedSoldier {
    List<Mission> getMissions();
}
