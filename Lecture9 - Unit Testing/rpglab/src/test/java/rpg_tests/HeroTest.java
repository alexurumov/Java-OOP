package rpg_tests;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import rpg_lab.Hero;
import rpg_lab.Target;
import rpg_lab.Weapon;

import static org.junit.Assert.*;
import static rpg_tests.Constants.*;

public class HeroTest {

    @Test
    public void attackGainsXPIfTargetIsDead() {

        Target fakeTarget = new Target() {
            public void takeAttack(int attackPoints) { }

            public int getHealth() { return 0;}

            public int giveExperience() { return DUMMY_XP;}

            public boolean isDead() { return true; }
        };
        Weapon fakeWeapon = new Weapon() {
            public void attack(Target target) { }

            public int getAttackPoints() { return ATTACK_POINTS; }

            public int getDurabilityPoints() { return 0; }
        };

        Hero hero = new Hero(HERO_NAME, fakeWeapon);
        hero.attack(fakeTarget);

        assertEquals("Wrong XP, ", EXPECTED_XP, hero.getExperience());

    }

    @Test
    public void shouldGetLootAfterTargetKilled() {

        Weapon weaponMock = Mockito.mock(Weapon.class);
        Target targetMock = Mockito.mock(Target.class);

        Mockito.when(targetMock.isDead()).thenReturn(true);
        Mockito.when(targetMock.giveExperience()).thenReturn(DUMMY_XP);

        Hero hero = new Hero(HERO_NAME, weaponMock);

        hero.attack(targetMock);

        assertEquals("Wrong experience, ", DUMMY_XP, hero.getExperience());

    }
}
