package collectionHierarchy;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String[] insertStrings = in.nextLine().split("\\s+");

        AddCollection addCollection = new AddCollection();
        AddRemoveCollection addRemoveCollection = new AddRemoveCollection();
        MyListImpl myListCollection = new MyListImpl();

        insertInto(addCollection, insertStrings).forEach(Main::printAddResult);
        System.out.println();
        insertInto(addRemoveCollection, insertStrings).forEach(Main::printAddResult);
        System.out.println();
        insertInto(myListCollection, insertStrings).forEach(Main::printAddResult);
        System.out.println();

        int removeCount = Integer.parseInt(in.nextLine());

        String[] results = removeTimes(addRemoveCollection, removeCount);
        printArray(results);
        System.out.println();
        results = removeTimes(myListCollection, removeCount);
        printArray(results);
    }

    private static void printArray(String[] results) {
        for (String result : results) {
            System.out.print(result + " ");
        }
    }

    private static String[] removeTimes(AddRemovable collection, int removeCount) {
        String[] result = new String[removeCount];

        for (int i = 0; i < removeCount; i++) {
            result[i] = collection.remove();
        }

        return result;
    }

    private static void insertIntoAndPrint(Addable collection, String[] items) {
        Arrays.stream(items).map(collection::add).forEach(Main::printAddResult);
    }

    private static Stream<Integer> insertInto(Addable collection, String[] items) {
        return Arrays.stream(items).map(collection::add);
    }

    private static void printAddResult(Integer result) {
        System.out.print(result + " ");
    }
}
