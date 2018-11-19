//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.Philco;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Album {
    private String name;
    private String artist;
    private ArrayList<com.Philco.Song> songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList();
    }

    public boolean addSong(String title, double duration) {
        if(this.findSong(title) == null) {
            this.songs.add(new com.Philco.Song(title, duration));
            return true;
        } else {
            return false;
        }
    }

    private com.Philco.Song findSong(String title) {
        Iterator var2 = this.songs.iterator();

        com.Philco.Song checkedSong;
        do {
            if(!var2.hasNext()) {
                return null;
            }

            checkedSong = (com.Philco.Song)var2.next();
        } while(!checkedSong.getTitle().equals(title));

        return checkedSong;
    }

    public boolean addToPlayList(int trackNumber, List<com.Philco.Song> playList) {
        int index = trackNumber - 1;
        if(index > 0 && index <= this.songs.size()) {
            playList.add(this.songs.get(index));
            return true;
        } else {
            System.out.println("This album does not have a track " + trackNumber);
            return false;
        }
    }

    public boolean addToPlayList(String title, List<com.Philco.Song> playList) {
        com.Philco.Song checkedSong = this.findSong(title);
        if(checkedSong != null) {
            playList.add(checkedSong);
            return true;
        } else {
            System.out.println("The song " + title + " is not in this album");
            return false;
        }
    }
}
