package animals;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Animal cat = new Cat("Pesho", "Whiskas");
        Animal dog = new Dog("Gosho", "Meat");

        System.out.println(cat.explainSelf());
        System.out.println(dog.explainSelf());
    }
}