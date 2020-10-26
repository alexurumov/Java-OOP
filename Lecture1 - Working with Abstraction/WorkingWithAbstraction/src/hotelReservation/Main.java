package hotelReservation;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String[] inputArgs = in.nextLine().split(" ");

        double pricePerDay = Double.parseDouble(inputArgs[0]);

        int numberOfDays = Integer.parseInt(inputArgs[1]);

        Season season = Season.valueOf(inputArgs[2]);

        DiscountType type = DiscountType.valueOf(inputArgs[3]);

        ReservationDetails details = new ReservationDetails(pricePerDay, numberOfDays, season, type);

        System.out.printf("%.2f%n", PriceCalculator.calculate(details));
    }
}
