package hell.entities.heroes;

public class Assassin extends HeroImpl {
    public Assassin(String name) {
        super(name);
        this.setStrength(25);
        this.setAgility(100);
        this.setIntelligence(15);
        this.setHitPoints(150);
        this.setDamage(300);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
