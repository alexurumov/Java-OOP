package rpg_tests;

import org.junit.Before;
import org.junit.Test;
import rpg_lab.Axe;
import rpg_lab.Dummy;

import static org.junit.Assert.*;
import static rpg_tests.Constants.*;

public class AxeTests {
    private Axe axe;
    private Dummy dummy;

    @Before
    public void initializeTestObjects() {
        this.axe = new Axe(AXE_ATTACK, AXE_DURABILITY);
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_XP);
    }

    @Test
    public void axeShouldLoseDurabilityAfterAttack() {

        this.axe.attack(this.dummy);

        assertEquals("Wrong durability, ", EXPECTED_DURABILITY, this.axe.getDurabilityPoints());
    }

    @Test (expected = IllegalStateException.class)
    public void shouldNotBeAbleToAttackWithBrockenAxe() {

        this.axe.attack(this.dummy);
        this.axe.attack(this.dummy);
    }
}
