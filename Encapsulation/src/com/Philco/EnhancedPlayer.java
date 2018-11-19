package com.Philco;

/**
 * Created by PhillipsDaramola on 16/07/2017.
 */
public class EnhancedPlayer {

    // This is encapsulation as these fields are not available for other classes.
    private String name;

    //// 'hitPoints' was formally 'health'. The benefit of encapsulation is that you can change field names without affecting the main class
    // or other classes calling this class.
    // private int health = 100;
    // Setting default value for hitPoints. (Right click on 'health', click Refactor, click Rename, follow the steps.)
    private int hitPoints = 100;


    private String weapon;

    public EnhancedPlayer(String name, int health, String weapon) {
        this.name = name;

        // Basic validation: only assigns hitPoints if it is between 1 and 100
        if (health > 0 && health <= 100 ){
            this.hitPoints = health;
        }
        this.weapon = weapon;
    }

    public void loseHealth(int damage) {
        this.hitPoints = this.hitPoints - damage;

        if (this.hitPoints <= 0){
            System.out.println("Player knocked out");
        }
    }

    public int getHealth() {
        return hitPoints;
    }
}
