package com.Philco;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by PhillipsDaramola on 25/09/2017.
 */
public class Album {
    private String name;
    private String artist;
    // The album contains an arraylist of songs.
    private ArrayList<Song> songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        // Need to initialise the array list in order to use it without getting any errors.
        this.songs = new ArrayList<Song>();
    }

    // Method to add song.
    public boolean addSong(String title, Double duration){

        // This if statement validates that we are not duplicating songs.
        if (findSong(title) == null){
            this.songs.add(new Song(title, duration));
            return true;
        }
        // This means that the song already exists.
        return false;
    }

    // This checks for songs in the arraylist.
    private Song findSong(String title){

        // Easier way to loop through an arraylist.
        // This is the foreach command in Java.
        // Instead of doing " for(int i = 0; i < songs.size(); i++) "
                // Instead
        // What this does is it goes through to create a variable called 'checkedSong' for every entry in our 'songs' arraylist.
        for (Song checkedSong : this.songs){
            if (checkedSong.getTitle().equals(title)){
                return checkedSong;
            }
        }
        return null;
    }
                // 2 ways of adding song to the play list.
    // Passing the track number that we want to add to our playlist and also adding our playlist.
    // We're using the track number as a reference for the song.
    public boolean addToPlayList(int trackNumber, LinkedList<Song> playList){

        // '- 1' because track numbers start at 1 (for human) and 0 for computers.
        int index = trackNumber - 1;
        // Checking if there is a song in that entry.
        if ((index > 0) && (index <= this.songs.size())){
            // If the song exists, then we'll had that to our playlist.
            playList.add(this.songs.get(index));
            return true;
        }
        System.out.println("This album does not have a track " + trackNumber);
        return false;
    }

    // Overloading the addToPlayList function. This will allow you to add songs to the 'playlist'
    // using the title as opposed to the 'tracknumber'.
    public boolean addToPlayList(String title, LinkedList<Song> playList){

        Song checkedSong = findSong(title);
        // If the song can be found.
        if (checkedSong != null) {
            playList.add(checkedSong);
            return true;
        }
        System.out.println("The song " + title + " is not in this album");
        return false;
    }
}

































