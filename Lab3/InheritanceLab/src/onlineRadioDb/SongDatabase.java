package onlineRadioDb;

import java.util.ArrayList;
import java.util.List;

public class SongDatabase {
    private List<Song> songs;

    public SongDatabase() {
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }
    public String getTotalLengthOfSongs () {
        int totalSeconds = 0;
        for (Song song : songs) {
            totalSeconds += Integer.valueOf(song.getLength());
        }

        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds - (hours * 3600)) / 60;
        int seconds = (totalSeconds - (hours * 3600)) % 60;

        return String.format("Playlist length: %dh %dm %ds", hours, minutes, seconds);
    }

    @Override
    public String toString() {
        return "Songs added: " + songs.size();
    }
}
