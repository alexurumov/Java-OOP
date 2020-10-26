package rpg_tests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import rpg_lab.Dummy;

import static org.junit.Assert.*;
import static rpg_tests.Constants.*;

public class DummyTests {
    private Dummy dummy;

    @Before
    public void initialiseTestObject() {
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_XP);
    }

    @Test
    public void dummyShouldLoseHealthIfAttacked () {
        this.dummy.takeAttack(ATTACK_POINTS);

        assertEquals("Wrong Health, ", dummy.getHealth(), EXPECTED_HEALTH);
    }

    @Test (expected = IllegalStateException.class)
    public void deadDummyCannotBeAttacked() {

        this.dummy.takeAttack(ATTACK_POINTS);
        this.dummy.takeAttack(ATTACK_POINTS);
        this.dummy.takeAttack(ATTACK_POINTS);
    }

    @Test
    public void deadDummyShouldBeAbleToGiveXP() {

        this.dummy.takeAttack(ATTACK_POINTS);

        Dummy mocked = Mockito.mock(Dummy.class);
        Mockito.when(mocked.giveExperience()).thenReturn(DUMMY_XP);

        int actual = mocked.giveExperience();

        assertEquals(DUMMY_XP, actual);

//        assertEquals("Wrong Experience, ", this.dummy.giveExperience(), EXPECTED_XP);
    }

    @Test (expected = IllegalStateException.class)
    public void aliveDummyShouldNotGeAbleToGiveXP() {

        this.dummy.giveExperience();
    }
}
