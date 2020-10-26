package foodShortage;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());

        Map<String, Buyer> buyers = new LinkedHashMap<>();

        while (n-- > 0) {

            String[] input = in.nextLine().split("\\s+");

            Buyer buyer;
            if (input.length == 4) {
                buyer = new Citizen(input[0], Integer.parseInt(input[1]), input[2], input[3]);
            } else {
                buyer = new Rebel(input[0], Integer.parseInt(input[1]), input[2]);
            }

            buyers.put(((Person) buyer).getName(), buyer);
        }


        String line = in.nextLine();

        while (!line.equals("End")) {

            for (String buyerName : buyers.keySet()) {
                if (buyerName.equals(line)) {
                    buyers.get(buyerName).buyFood();
                }
            }

            line = in.nextLine();
        }

        int totalFoodBought = Buyer.START_FOOD;
        for (Buyer buyer : buyers.values()) {
            totalFoodBought += buyer.getFood();
        }


        System.out.println(totalFoodBought);
    }
}
