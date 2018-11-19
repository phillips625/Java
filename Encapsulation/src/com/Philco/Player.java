package com.Philco;

/**
 * Created by PhillipsDaramola on 16/07/2017.
 */
public class Player {

    // public String name;
    // Problem without encapsulation: If you change a variable here, it affects other classes that
    // calls it directly - as opposed to just affecting the class it's in.
    public String fullName;
    public int health;
    public String weapon;

    public void loseHealth(int damage) {
        this.health = this.health - damage;

        if (this.health <= 0){
            System.out.println("Player knocked out");
        }
    }

    public int remaining(){
        return this.health;
    }

}
