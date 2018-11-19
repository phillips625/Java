package com.Philco;

public class Main {

    public static void main(String[] args) {

        Animal animal = new Animal("Animal",1,1,5,5);

        Dog dog = new Dog("Yorkie", 8, 20, 2, 4, 1, 20, "Silk");

        // We're able to use the eat method thanks to inheritance
        // This method was called before the use of the override method in the Dog class. The eat method here was called straight from the Animal
        // class which the Dog method is inheriting from.
        // dog.eat();

        // The eat method here corresponds to the override eat method in the dog class
        //dog.eat();

        System.out.println("----------------------");
         dog.walk();
        //dog.run();
    }
}
