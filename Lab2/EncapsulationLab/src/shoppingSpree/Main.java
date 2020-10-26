package shoppingSpree;

import java.security.InvalidParameterException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Map<String, Person> people = new LinkedHashMap<>();
        Map<String, Product> products = new LinkedHashMap<>();

        try {

            String[] peopleInput = in.nextLine().split(";");

            for (int i = 0; i < peopleInput.length; i++) {
                String[] personDetails = peopleInput[i].split("=");
                String personName = personDetails[0];
                double money = Double.parseDouble(personDetails[1]);

                Person currentPerson = new Person(personName, money);
                people.put(personName, currentPerson);
            }

            String[] productInput = in.nextLine().split(";");

            for (int j = 0; j < productInput.length; j++) {
                String[] productDetails = productInput[j].split("=");
                String productName = productDetails[0];
                double cost = Double.parseDouble(productDetails[1]);

                Product currentProduct = new Product(productName, cost);
                products.put(productName, currentProduct);

            }

            String line = in.nextLine();

            while (!line.equalsIgnoreCase("END")) {

                String[] command = line.split("\\s+");
                String person = command[0];
                String product = command[1];

                people.get(person).buyProduct(products.get(product));

                line = in.nextLine();
            }

            people.values().forEach(p -> System.out.println(p.toString()));

        } catch (InvalidParameterException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
