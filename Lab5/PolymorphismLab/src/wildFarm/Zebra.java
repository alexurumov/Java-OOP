package wildFarm;

public class Zebra extends Mammal {
    public Zebra(String animalType, String animalName, Double animalWeight, String livingRegion) {
        super(animalType, animalName, animalWeight, livingRegion);
    }

    @Override
    protected void eat(Food food) {
        if (food instanceof Vegetable) {
            super.eat(food);
            return;
        }
        throw new IllegalArgumentException("Zebras are not eating that type of food!");
    }

    @Override
    void makeSound() {
        System.out.println("Zs");
    }
}
