package onlineRadioDb;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InvalidSongException {

        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        SongDatabase database = new SongDatabase();

        while (n-- > 0) {
            String[] input = in.nextLine().split(";");
            String author = input[0];
            String name = input[1];
            String length = input[2];

            try {
                Song song = new Song(author, name, length);
                database.addSong(song);
                System.out.println("Song added.");
            } catch (InvalidSongException error) {
                System.out.println(error.getMessage());
            }
        }

        System.out.println(database.toString());
        System.out.println(database.getTotalLengthOfSongs());
    }
}
