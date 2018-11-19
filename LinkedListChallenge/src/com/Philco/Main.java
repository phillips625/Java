package com.Philco;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

    // List of Album objects.
    // THis is static because we are calling 'albums' in a 'static' method (aka main method).
    private static ArrayList<Album> albums = new ArrayList<Album>();

    public static void main(String[] args) {

        // This is the 1st album.
        Album album = new Album("Test Album 1", "Maitre Gims");
        album.addSong("Test 1", 2.45);
        album.addSong("Test 2", 3.45);
        album.addSong("Test 3", 4.45);
        album.addSong("Test 4", 5.45);
        album.addSong("Test 5", 6.45);
        album.addSong("Test 6", 7.45);
        album.addSong("Test 7", 8.45);
        album.addSong("Test 8", 9.45);
        album.addSong("Test 9", 10.45);
        albums.add(album);

        // This is the 2nd album.
        album = new Album("Test Album 1", "Jay Z");
        album.addSong("Test 11", 2.5);
        album.addSong("Test 12", 3.5);
        album.addSong("Test 13", 4.5);
        album.addSong("Test 14", 5.5);
        album.addSong("Test 15", 6.5);
        album.addSong("Test 16", 7.5);
        album.addSong("Test 17", 8.5);
        album.addSong("Test 18", 9.5);
        album.addSong("Test 19", 10.5);
        albums.add(album);

        // The playlist is going to be a linked list and of course the albums are stored in an array list.
        // Rem: A playlist can get songs from any album.
        LinkedList<Song> playList = new LinkedList<Song>();
        // albums.get(0) gets the first item (an album in this case) in the 'albums' array list aka 'Maitre Gims' album.
        // ("Song from Maitre Gim's album", playList you want to add the song to)
        albums.get(0).addToPlayList("Test 1", playList);
        albums.get(0).addToPlayList("Test 2", playList);
        albums.get(0).addToPlayList("Test 52", playList); // Song doesn't exist.
        //Using the track number in this case.
        albums.get(0).addToPlayList(8, playList); // Adding the song, Test 8, using the track number instead of the title to the playlist.

        // Adding songs from the Jay Z track to the album.
        albums.get(1).addToPlayList(1, playList); // Song Test 11
        albums.get(1).addToPlayList(2, playList); // Song Test 12
        albums.get(1).addToPlayList(3, playList); // SOng Test 13
        albums.get(1).addToPlayList(24, playList); // Doesn't exist

        play(playList);
    }

    private static void play(LinkedList<Song> playList){
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        // Tells us which direction we're going in.
        boolean forward = true;

        // listIterator contains a song.
        ListIterator<Song> listIterator = playList.listIterator();
        // If the play list is empty.
        if (playList.size() == 0){
            System.out.println("No songs in the playlist");
            return;
        }
        else {
            // toString() is an overriden method in the 'Song' class.
            System.out.println("Now playing " + listIterator.next().toString()); // next() is an inbuilt method of linkedlists.
        }

        // 'while (!quit)' - because in a playlist, you can continue until you exit out.
        while (!quit){
            // Input by the user. Remember 'scanner' has been set up in the first line of this function.
            int action = scanner.nextInt();
            // Takes the terminal to the next line.
            scanner.nextLine();

            switch (action){
                case 0:
                    // Allows you to exit.
                    System.out.println("Playlist complete.");
                    quit = true;
                    break;

                    // Going forward
                case 1:

                    // If we're not going forward, we're going to change direction and set it to going forward so that the next line
                    // (aka next if statement) actually works (aka takes you forward without any breaks).
                    // aka if (forward == false is true), which in this case, it is.
                    if (!forward){
                        if (listIterator.hasNext()){
                            listIterator.next();
                        }
                        forward = true;
                    }

                    // The above if statement is needed for this if statement to work.
                    if (listIterator.hasNext()){
                        System.out.println("Now Playing: " + listIterator.next().toString());
                    }
                    else {
                        System.out.println("We've reached the end of the playlist");
                        forward = false;
                    }
                    break;

                    // Going backwards
                case 2:

                    if (forward){
                        if (listIterator.hasPrevious()){
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if (listIterator.hasPrevious()){
                        System.out.println("Now Playing: " + listIterator.previous().toString());
                    }
                    else {
                        System.out.println("We're at the start of the playlist");
                        forward = true;
                    }
                    break;
                case 3:
                    break;
                case 4:
                    //printList(playList);
                    break;
                case 5:
                    //printMenu();
                    break;
            }
        }
    }
}


// If you use the '.remove()' function, you have to use the '.next()' or '.previous()' immediately after.




















