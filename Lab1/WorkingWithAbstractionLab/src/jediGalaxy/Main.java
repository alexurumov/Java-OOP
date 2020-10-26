package jediGalaxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] dimensions = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        Galaxy galaxy = new Galaxy(dimensions[0], dimensions[1]);

        StarsManipulator starsManipulator = new StarsManipulator(galaxy);

        String input = reader.readLine();

        long sumOfStars = 0;

        while (!input.equals("Let the Force be with you")) {

            int[] playerPositions = Arrays.stream(input.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int[] enemyPositions = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

            starsManipulator.destroyStars(enemyPositions);
            sumOfStars = starsManipulator.sumOfStars(playerPositions);

            input = reader.readLine();
        }

        System.out.println(sumOfStars);
    }
}
