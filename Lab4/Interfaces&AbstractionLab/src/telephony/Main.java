package telephony;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String[] numbers = in.nextLine().split("\\s+");
        String[] urls = in.nextLine().split("\\s+");

        Smartphone smartphone = new Smartphone(Arrays.asList(numbers), Arrays.asList(urls));

        System.out.print(smartphone.call());
        System.out.print(smartphone.browse());
    }
}
