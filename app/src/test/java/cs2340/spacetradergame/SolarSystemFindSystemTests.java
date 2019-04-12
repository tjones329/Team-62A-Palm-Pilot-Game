package cs2340.spacetradergame;


import org.junit.Test;

import cs2340.spacetradergame.entity.SolarSystem;
import cs2340.spacetradergame.entity.Universe;

import static org.junit.Assert.*;


public class SolarSystemFindSystemTests {

    private Universe normalUniverse = new Universe();


    @Test
    public void findExisting () {
        SolarSystem copy = new SolarSystem();
        copy = normalUniverse.getSystems().get(5);
        assertEquals(copy, normalUniverse.findSystem(copy.getName()));
    }

    @Test
    public void findNotInUniverse() {
        assertNull(normalUniverse.findSystem("fake"));
    }

    @Test
    public void findWithEmptyString() {
        assertNull(normalUniverse.findSystem(""));
    }

    @Test
    public void findFirst() {
        SolarSystem copy = new SolarSystem();
        copy = normalUniverse.getSystems().get(0);
        assertEquals(copy, normalUniverse.findSystem(copy.getName()));
    }

    @Test
    public void findLast() {
        SolarSystem copy = new SolarSystem();
        copy = normalUniverse.getSystems().get(normalUniverse.getSystems().size() - 1);
        assertEquals(copy, normalUniverse.findSystem(copy.getName()));
    }

    @Test
    public void findWithNull() {
        assertNull(normalUniverse.findSystem(null));
    }
}
