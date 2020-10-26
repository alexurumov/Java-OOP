package mordirsCrueltyPlan;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String[] foods = in.nextLine().split("\\s+");
        Gandalf gandalf = new Gandalf();
        Arrays.stream(foods).forEach(gandalf::eatFood);
        System.out.println(gandalf.getFoodPoints());
        System.out.println(gandalf.getMood());
    }
}
