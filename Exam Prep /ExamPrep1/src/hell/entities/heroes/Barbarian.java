package hell.entities.heroes;

public class Barbarian extends HeroImpl {

    public Barbarian(String name) {
        super(name);
        this.setStrength(90);
        this.setAgility(25);
        this.setIntelligence(10);
        this.setHitPoints(350);
        this.setDamage(150);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
