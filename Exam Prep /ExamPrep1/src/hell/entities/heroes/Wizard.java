package hell.entities.heroes;

public class Wizard extends HeroImpl {
    public Wizard(String name) {
        super(name);
        this.setStrength(25);
        this.setAgility(25);
        this.setIntelligence(100);
        this.setHitPoints(100);
        this.setDamage(250);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
