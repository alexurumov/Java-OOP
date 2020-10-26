package pizzaCalories;

import java.security.InvalidParameterException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        try {

            String[] pizzaInput = in.nextLine().split("\\s+");
            String pizzaName = pizzaInput[1];
            int numberOfToppings = Integer.parseInt(pizzaInput[2]);

            Pizza pizza = new Pizza(pizzaName, numberOfToppings);

            String[] doughInput = in.nextLine().split("\\s+");
            String flourType = doughInput[1];
            String bakingTech = doughInput[2];
            double doughWeight = Double.parseDouble(doughInput[3]);

            Dough dough = new Dough(flourType, bakingTech, doughWeight);
            pizza.setDough(dough);

            while (numberOfToppings-- > 0) {
                String[] toppingsInput = in.nextLine().split("\\s+");
                String toppingName = toppingsInput[1];
                double toppingWeight = Double.parseDouble(toppingsInput[2]);

                Topping topping = new Topping(toppingName, toppingWeight);
                pizza.addTopping(topping);
            }

            System.out.println(pizza.toString());

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
