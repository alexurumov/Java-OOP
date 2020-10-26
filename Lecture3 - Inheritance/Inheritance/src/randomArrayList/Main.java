package randomArrayList;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        RandomArrayList randomArrayList = new RandomArrayList();

        randomArrayList.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        System.out.println(randomArrayList.getRandomElement());

    }
}
