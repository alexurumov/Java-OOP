package pr0304Barracks.models.units;

import pr0304Barracks.contracts.Attacker;
import pr0304Barracks.contracts.Unit;

public class Archer extends AbstractUnit implements Attacker, Unit {
    private static final int ARCHER_HEALTH = 25;
    private static final int ARCHER_DAMAGE = 7;

    public Archer() {
        super(ARCHER_HEALTH, ARCHER_DAMAGE);
    }
}
