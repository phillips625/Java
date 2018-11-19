package com.philco;

/**
 * Created by PhillipsDaramola on 31/10/2017.
 */
public class Planet extends HeavenlyBody{

    // We're not passing the body type because we know it is a planet.
    public Planet(String name, double orbitalPeriod) {
        // Passing in the enum for Planets
        super(name, orbitalPeriod, BodyTypes.PLANET);
    }

    @Override
    public boolean addSatellite(HeavenlyBody moon) {

        // Checking that to see if this is a moon
        if (moon.getKey().getBodyTypes() == BodyTypes.MOON) {
            return super.addSatellite(moon);
        }
        else {
            return false;
        }
    }
}
