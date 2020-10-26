package classBoxDataValidation;

import java.security.InvalidParameterException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        double length = Double.parseDouble(in.nextLine());
        double width = Double.parseDouble(in.nextLine());
        double height = Double.parseDouble(in.nextLine());

        try {
            Box box = new Box(length, width, height);
            System.out.println(box.toString());
        } catch (InvalidParameterException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
