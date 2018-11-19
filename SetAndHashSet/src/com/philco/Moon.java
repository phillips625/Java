package com.philco;

/**
 * Created by PhillipsDaramola on 31/10/2017.
 */
public class Moon extends HeavenlyBody {
    public Moon(String name, double orbitalPeriod) {
        super(name, orbitalPeriod, BodyTypes.MOON);
    }
}
