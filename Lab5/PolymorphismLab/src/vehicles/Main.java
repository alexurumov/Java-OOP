package vehicles;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String[] carInput = in.nextLine().split("\\s+");
        Vehicle car = new Car(Double.valueOf(carInput[1]), Double.valueOf(carInput[2]));

        String[] truckInput = in.nextLine().split("\\s+");
        Vehicle truck = new Truck(Double.valueOf(truckInput[1]), Double.valueOf(truckInput[2]));

        int n = Integer.parseInt(in.nextLine());

        while (n-- > 0) {

            String[] cmdInput = in.nextLine().split("\\s+");

            String action = cmdInput[0];
            String object = cmdInput[1];
            double variable = Double.valueOf(cmdInput[2]);

            switch (action) {
                case "Drive":
                    if (object.equals("Car")) {
                        System.out.println(car.drive(variable));
                    } else {
                        System.out.println(truck.drive(variable));
                    }
                    break;
                case "Refuel":
                    if (object.equals("Car")) {
                        car.refuel(variable);
                    } else {
                        truck.refuel(variable);
                    }
                    break;
                default:
                    break;
            }
        }

        System.out.println(car.toString());
        System.out.println(truck.toString());
    }
}
