package wildFarm;

public class Mouse extends Mammal {
    public Mouse(String animalType, String animalName, Double animalWeight, String livingRegion) {
        super(animalType, animalName, animalWeight, livingRegion);
    }

    @Override
    protected void eat(Food food) {
        if (food.getClass().getSimpleName().equals("Vegetable")) {
            super.eat(food);
            return;
        }
        throw new IllegalArgumentException("Mice are not eating that type of food!");
    }

    @Override
    void makeSound() {
        System.out.println("SQUEEEAAAK!");
    }
}
