package wildFarm;

public class Tiger extends Felime {
    public Tiger(String animalType, String animalName, Double animalWeight, String livingRegion) {
        super(animalType, animalName, animalWeight, livingRegion);
    }

    @Override
    protected void eat(Food food) {
        if (food.getClass().getSimpleName().equals("Meat")) {
            super.eat(food);
            return;
        }
        throw new IllegalArgumentException("Tigers are not eating that type of food!");
    }

    @Override
    void makeSound() {
        System.out.println("ROAAR!!!");
    }
}
