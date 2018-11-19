package com.philco;

/**
 * Created by PhillipsDaramola on 31/10/2017.
 */
public class DwarfPlanet extends HeavenlyBody{

    public DwarfPlanet(String name, double orbitalPeriod) {
        super(name, orbitalPeriod, BodyTypes.DWARF_PLANET);
    }
}
