package vehicleExtended;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String[] carInput = in.nextLine().split("\\s+");
        Vehicle car = new Car(Double.valueOf(carInput[1]), Double.valueOf(carInput[2]), Double.valueOf(carInput[3]));

        String[] truckInput = in.nextLine().split("\\s+");
        Vehicle truck = new Truck(Double.valueOf(truckInput[1]), Double.valueOf(truckInput[2]), Double.valueOf(truckInput[3]));

        String[] busInput = in.nextLine().split("\\s+");
        Vehicle bus = new Bus(Double.valueOf(busInput[1]), Double.valueOf(busInput[2]), Double.valueOf(busInput[3]));

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
                    } else if (object.equals("Truck")){
                        System.out.println(truck.drive(variable));
                    } else {
                        System.out.println(bus.drive(variable));
                    }
                    break;
                case "Refuel":
                    try {
                        if (object.equals("Car")) {
                            car.refuel(variable);
                        } else if (object.equals("Truck")){
                            truck.refuel(variable);
                        } else {
                            bus.refuel(variable);
                        }
                    } catch (IllegalArgumentException error) {
                        System.out.println(error.getMessage());
                    }
                    break;
                case "DriveEmpty":
                    System.out.println(((Bus) bus).driveEmpty(variable));
                    break;
                default:
                    break;
            }
        }

        System.out.println(car.toString());
        System.out.println(truck.toString());
        System.out.println(bus.toString());
    }
}
