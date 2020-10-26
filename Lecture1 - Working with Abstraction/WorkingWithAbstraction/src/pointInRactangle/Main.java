package pointInRactangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int[] rectangleCoordinates = readIntArray(in);

        Point topLeft = new Point(rectangleCoordinates[0], rectangleCoordinates[1]);
        Point bottomRight = new Point(rectangleCoordinates[2], rectangleCoordinates[3]);

        Rectangle rectangle = new Rectangle(topLeft, bottomRight);

        int count = Integer.parseInt(in.nextLine());

        for (int i = 0; i < count; i++) {
            int[] pointCoordinates = readIntArray(in);
            Point current = new Point(pointCoordinates[0], pointCoordinates[1]);
            System.out.println(rectangle.contains(current));
        }

    }

    private static int[] readIntArray(Scanner in) {
        return Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
