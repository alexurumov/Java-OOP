package farrari;

import java.lang.instrument.IllegalClassFormatException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IllegalClassFormatException {

        Scanner in = new Scanner(System.in);

        String driverName = in.nextLine();

        Ferrari ferrari = new Ferrari(driverName);

        System.out.println(ferrari.toString());

        String ferrariName = Ferrari.class.getSimpleName();
        String carInterface = Car.class.getSimpleName();
        boolean isCreated = Car.class.isInterface();
        if (!isCreated) {
            throw new IllegalClassFormatException("No interface created!");
        }

    }
}
