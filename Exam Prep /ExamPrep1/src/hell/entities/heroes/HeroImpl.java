package hell.entities.heroes;

import hell.entities.miscellaneous.HeroInventory;
import hell.interfaces.Hero;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

abstract class HeroImpl implements Hero {
    private String name;
    private long strength;
    private long agility;
    private long intelligence;
    private long hitPoints;
    private long damage;
    private HeroInventory heroInventory;

    protected HeroImpl(String name) {
        this.name = name;
        this.heroInventory = new HeroInventory();
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setStrength(int strength) {
        this.strength = strength;
    }

    protected void setAgility(int agility) {
        this.agility = agility;
    }

    protected void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    protected void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    protected void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getStrength() {
        return this.strength + this.heroInventory.getTotalStrengthBonus();
    }

    @Override
    public long getAgility() {
        return this.agility + this.heroInventory.getTotalAgilityBonus();
    }

    @Override
    public long getIntelligence() {
        return this.intelligence + this.heroInventory.getTotalIntelligenceBonus();
    }

    @Override
    public long getHitPoints() {
        return this.hitPoints + this.heroInventory.getTotalHitPointsBonus();
    }

    @Override
    public long getDamage() {
        return this.damage + this.heroInventory.getTotalDamageBonus();
    }

    @Override
    public Collection<Item> getItems() {
        try {
            Field commonItems = this.heroInventory.getClass().getDeclaredField("commonItems");
            commonItems.setAccessible(true);
            return ((Map<String, Item>) commonItems.get(heroInventory)).values();

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addItem(Item item) {
        this.heroInventory.addCommonItem(item);
    }

    @Override
    public void addRecipe(Recipe recipe) {
        this.heroInventory.addRecipeItem(recipe);
    }

    @Override
    public String toString() {
        String items = this.getItems().size() == 0 ? "None" : "\n";
        List<String> itemsStrings = this.getItems().stream().map(Object::toString).collect(Collectors.toList());
        items += String.join("\n", itemsStrings);

        return String.format("Hero: %s, Class: %s\n" +
                "HitPoints: %d, Damage: %d\n" +
                "Strength: %d\n" +
                "Agility: %d\n" +
                "Intelligence: %d\n" +
                "Items: %s", this.getName(), this.getClass().getSimpleName(),
                this.getHitPoints(), this.getDamage(),
                this.getStrength(),
                this.getAgility(),
                this.getIntelligence(), items);
    }
}
