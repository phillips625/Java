package com.Philco;

public class Main {

    public static void main(String[] args) {

        // Output: Initial health is: 100
        EnhancedPlayer player = new EnhancedPlayer("Tim", 200, "Sword");
        System.out.println("Initial health is: " + player.getHealth());

        /*

        Player player = new Player();
        player.name = "Tim";
        // Problem without encapsulation: Without a constructor, a compulsory field might not
        // be initialised. Without encapsulation, you can't check if the initialisation is the right
        // type (string, object, etc).
      //  player.health = 20;
        player.health = 20;
        player.weapon = "sword";

        int damage = 8;
        player.loseHealth(damage);
        System.out.println("Remaining health: " + player.remaining());

        damage = 13;
        /// Problem without encapsulation: anyone can change the health points at any time.
        player.health = 200;

        player.loseHealth(damage);
        System.out.println("Remaining health: " + player.remaining());

        */
    }
}
