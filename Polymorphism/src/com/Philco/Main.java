package com.Philco;


class Movie {

    private String name;

    public Movie(String name) {
        this.name = name;
    }

    // This is displayed automatically when an extended class doesn't have a generic plot method
    public String plot(){
        return "No plot here";
    }

    public String getName() {
        return name;
    }
}

class jaws extends Movie{

    public jaws() {
        super("Jaws");
    }

    public String plot(){
        return "A shark eats a lot of people";
    }
}

class IndependenceDay extends Movie{

    public IndependenceDay() {
        super("Independence Day");
    }

    @Override
    public String plot() {
        return "Aliens attempt to take over";
    }
}

class MazeRunner extends Movie{

    public MazeRunner() {
        super("Maze Runner");
    }

    @Override
    public String plot() {
        return "Kids escape a maze";
    }
}

class StarWars extends Movie{

    public StarWars() {
        super("Star Wars");
    }

    @Override
    public String plot() {
        return "Imperial Forces taking over";
    }
}

class Forgetable extends Movie{

    public Forgetable() {
        super("Forgetable");

    }

    // No 'plot' method override.. This will show how polymorphism works.
    // When the 'plot' method is called by the main function, "Plot: No plot here" (from the parent class, 'Movie') is displayed as the default.
}

public class Main {


    public static void main(String[] args) {

        //  movie.plot() explains Polymorphism. We're assigning different functionality depending on the object that we've created.
        // movie.getName() is possible due to inheritance. Forgetable, StarWars, etc has access to 'getName()' because of inheritance.
        for (int i = 1; i<11; i++) {

           // Movie movie = randomMovie(); could be Movie movie = new jaws();
            Movie movie = randomMovie();
            System.out.println("Movie #" + i +
                            " : " + movie.getName() + "\n" +
                            "Plot: " + movie.plot() + "\n");
        }
    }

    // This function returns a random Movie (Movie class) from the above list.
    public static Movie randomMovie() {

        // (Math.random() * 5) returns a number between 0-4
        int randomNumber = (int) (Math.random() * 5) +1;
        System.out.println("Random number generated is: " + randomNumber);

        // We're returning a Movie class depending on the value of randomNumber
        switch (randomNumber) {
            case 1:
                return new jaws();
            case 2:
                return new IndependenceDay();
            case 3:
                return new MazeRunner();
            case 4:
                return new StarWars();
            case 5:
                return new Forgetable();
        }

        return null;
    }
}
