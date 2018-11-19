package com.Philco;

/**
 * Created by PhillipsDaramola on 07/10/2017.
 */

// This is a base class - so we're not extending anything.
public abstract class Player {

    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
