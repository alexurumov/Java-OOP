package wildFarm;

abstract class Animal {
    String animalType;
    String animalName;
    Double animalWeight;
    Integer foodEaten;

    public Animal(String animalType, String animalName, Double animalWeight) {
        this.animalName = animalName;
        this.animalType = animalType;
        this.animalWeight = animalWeight;
        this.foodEaten = 0;
    }

    abstract void makeSound();

    protected void eat(Food food) {
        this.foodEaten += food.quantity;
    };

    public String getAnimalType() {
        return animalType;
    }

    public String getAnimalName() {
        return animalName;
    }

    public Double getAnimalWeight() {
        return animalWeight;
    }

    public Integer getFoodEaten() {
        return foodEaten;
    }
}
