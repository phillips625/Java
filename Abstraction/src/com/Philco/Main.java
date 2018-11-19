package com.Philco;

// Abstraction is like Interfaces - But this time, with CLASSES.
public class Main {

    public static void main(String[] args) {
        Dog dog = new Dog("Austin");
        dog.breathe();
        dog.eat();

        // This gives an error because you cannot instantiate an abstract class. You can't directly instantiate an abstract class.
        // Bird bird = new Bird("Hemming");

        Parrot parrot = new Parrot("Hemming");
        parrot.breathe();
        parrot.eat();
        parrot.fly();

        Penguin penguin = new Penguin("Papa Pengz");
        penguin.fly();
    }
}
