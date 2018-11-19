package com.philco.model;

/**
 * Created by PhillipsDaramola on 09/01/2018.
 */

// We're creating a class for each of the tables so that the users will not know about the internal workings of the DataSource class.
public class Artist {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
