package rhombusOfStars;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int maxWidth = Integer.parseInt(in.nextLine());

        for (int i = 1; i < maxWidth; i++) {
            printRow(maxWidth, i);
        }
        System.out.println(repeatString("* ", maxWidth));
        for (int i = maxWidth - 1; i >= 1; i--) {
            printRow(maxWidth, i);
        }

    }

    private static void printRow(int maxWidth, int row) {
        System.out.print(repeatString(" ", maxWidth - row));
        System.out.print(repeatString("* ", row));
        System.out.println();
    }

    private static String repeatString(String sequence, int times) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < times; i++) {
            result.append(sequence);
        }

        return result.toString();
    }
}
