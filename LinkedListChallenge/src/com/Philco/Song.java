package com.Philco;

/**
 * Created by PhillipsDaramola on 25/09/2017.
 */

// All classes inherits from the Object class.

public class Song {

    private String title;
    private double duration;

    public Song(String title, double duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    // Default toString method output: Philco.Song@60e53b93 -- Name of package,Name of Class, Address of where the Song class is in memory.
    // This is now overriden to print out something more useful

    // Returning a toString is a quick way to output the content of an actual object.
    // This is QUICK WAY to print out the values that an object of a class is set to.
    @Override
    public String toString() {
        // return super.toString();
        return this.title + ": " + this.duration;
    }
}
