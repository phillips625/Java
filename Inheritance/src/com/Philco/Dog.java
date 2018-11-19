package com.Philco;

/**
 * Created by PhillipsDaramola on 25/06/2017.
 */
// 'extends' inherits from the 'Animal' class. Now we can extend the functionality of the Animal class. E.g here we have
    // states like 'eyes' and 'legs' which are unique to the Dog class because not all animals have 'eyes' and 'legs'.
public class Dog extends Animal{

    private int eyes;
    private int legs;
    private int tail;
    private int teeth;
    private String coat;

    // You now have to create a constructor that calls the constructor of the Animal class
    public Dog(String name, int size, int weight, int eyes, int legs, int tail, int teeth, String coat) {
        // 'super' calls the class (Animal class) we're extending from.
        super(name, 1, 1, size, weight);
        this.eyes = eyes;
        this.legs = legs;
        this.tail = tail;
        this.teeth = teeth;
        this.coat = coat;
    }

    private void chew(){
        System.out.println("Dog.chew() called");
    }

    // Overrides the eat method in the Animal super class
    @Override
    public void eat() {
        System.out.println("Dog.eat() called");
        chew();
        // Calls eat method in the Animal class
        super.eat();
    }

    // Overrides move method from the Animal class
    @Override
    public void move(int speed) {
        System.out.println("Dog().move called.");
        moveLegs(speed);
        super.move(speed);
    }

    private void moveLegs(int speed) {
        System.out.println("Dog.moveLegs called " + speed);
    }

    public void walk(){
        System.out.println("Dog.walk() called");

        // This explicitly calls move method from Animal class
        super.move(5);
    }

    public void run(){
        System.out.println("Dog.run() called");

        // Calls move method from Animal class if there is no move method in the Dog class
        move(10);
    }
}
