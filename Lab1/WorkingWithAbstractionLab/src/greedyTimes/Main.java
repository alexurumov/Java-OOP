package greedyTimes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        long capacity = Long.parseLong(in.nextLine());

        Bag bag = new Bag(capacity);

        String[] input = in.nextLine().split("\\s+");

        for (int i = 0; i < input.length; i+=2) {
            String item = input[i];
            long weight = Long.parseLong(input[i + 1]);

            if (item.length() == 3) {
                bag.addCash(item, weight);
            } else if (item.toLowerCase().endsWith("gem") && item.length() > 3) {
                bag.addGems(item, weight);
            } else if (item.equalsIgnoreCase("gold")) {
                bag.addGold(weight);
            }
        }

        System.out.println(bag.toString());

    }
}