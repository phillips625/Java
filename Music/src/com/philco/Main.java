package com.philco;

import com.philco.model.Artist;
import com.philco.model.DataSource;
import com.philco.model.SongArtist;

import java.util.List;
import java.util.Scanner;

// Make sure to add the SQLite JDBC driver and the DATABASE CLASSES to the project.
public class Main {

    public static void main(String[] args) {

        DataSource dataSource = new DataSource();
        if (!dataSource.open()){
            System.out.println("Can't open data source");
            return;
        }

        List<Artist> artists = dataSource.queryArtists(DataSource.ORDER_BY_DESC);
        if (artists == null){
            System.out.println("No artists");
            return;
        }

        for (Artist artist: artists){
            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
        }

        List<String> albumsForArtist =
                dataSource.queryAlbumsForArtist("Iron Maiden", DataSource.ORDER_BY_ASC);

        for(String album : albumsForArtist) {
            System.out.println(album);
        }

        List<SongArtist> songArtists = dataSource.queryArtistsForSong("Go Your Own Way", DataSource.ORDER_BY_ASC);
        if(songArtists == null) {
            System.out.println("Couldn't find the artist for the song");
            // If the compiler gets here, the return code below will terminate the execution of the code.
            return;
        }

        for(SongArtist artist : songArtists) {
            System.out.println("Artist name = " + artist.getArtistName() +
                    " Album name = " + artist.getAlbumName() +
                    " Track = " + artist.getTrack());
        }


        dataSource.querySongsMetadata();

        int count = dataSource.getCount(DataSource.TABLE_SONGS);
        System.out.println("Number of songs is: " + count);

        dataSource.createViewForSongArtists();


                            // For SQL Injection Lesson
        // The statement entered into the command line, Go Your Own Way" or 1+1 or " , returned all the rows in the 'artist_list_view' table.
        // This is a SQL injection attack. We were susceptible because we were blindly concatenating whatever the user typed in. Since 1=1 is
        // true for the records, every record in the artist_list_view table will be returned.

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a song title here: ");
        String title = scanner.nextLine();


        songArtists = dataSource.querySongInfoView(title);
        // isEmpty is a much better way of checking if the List is empty.
        if(songArtists.isEmpty()) {
            System.out.println("Couldn't find the artist for the song");
            return;
        }

        // Tells you the album that a song is on.
        for(SongArtist artist : songArtists) {
            System.out.println("FROM VIEW - Artist name = " + artist.getArtistName() +
                    " Album name = " + artist.getAlbumName() +
                    " Track number = " + artist.getTrack());
        }


        dataSource.insertSong("Touch of Grey", "Grateful Dead", "In The Dark", 1);

        dataSource.close();
    }
}
