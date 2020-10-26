package bookShop;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        try {
            Scanner in = new Scanner(System.in);
            String author = in.nextLine();
            String title = in.nextLine();
            double price = Double.valueOf(in.nextLine());

            Book book = new Book(author, title, price);
            GoldenEditionBook goldenEditionBook = new GoldenEditionBook(author, title, price);

            System.out.println(book.toString());
            System.out.println(goldenEditionBook.toString());

        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
        }
    }
}
