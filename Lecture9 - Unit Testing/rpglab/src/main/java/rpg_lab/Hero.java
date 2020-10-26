package rpg_lab;

import java.util.ArrayList;

public class Hero {

    private String name;
    private int experience;
    private Weapon weapon;
    private Iterable<Weapon> inventory;

    public Hero(String name, Weapon weapon) {
        this.name = name;
        this.experience = 0;
        this.weapon = weapon;
        this.inventory = new ArrayList<Weapon>();
    }

    public Iterable<Weapon> getInventory() {
        return this.inventory;
    }

    public void setInventory(Iterable<Weapon> inventory) {
        this.inventory = inventory;
    }

    public String getName() {
        return this.name;
    }

    public int getExperience() {
        return this.experience;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public void attack(Target target) {
        this.weapon.attack(target);

        if (target.isDead()) {
            this.experience += target.giveExperience();
        }
    }
}
