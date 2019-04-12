package cs2340.spacetradergame;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import cs2340.spacetradergame.entity.Planet;
import cs2340.spacetradergame.entity.SolarSystem;

public class TestSolarSystem {
    @Test
    public void testStartSystem() {
        // Test randomized pirates, police, government
        String[] emptyPlanetNames = {};
        SolarSystem systems[] = new SolarSystem[5000];
        for (int i = 0; i < systems.length; ++i) {
            systems[i] = new SolarSystem();
            systems[i].startSystem(emptyPlanetNames);
        }
        int[] governmentCoverage = new int[SolarSystem.Government.values().length];
        int[] policeCoverage = new int[SolarSystem.RandomEncounter.values().length];
        int[] piratesCoverage = new int[SolarSystem.RandomEncounter.values().length];

        for (int i = 0; i < systems.length; ++i) {
            ++governmentCoverage[systems[i].getGovernment().ordinal()];
            ++policeCoverage[systems[i].getPolice().ordinal()];
            ++piratesCoverage[systems[i].getPirates().ordinal()];
        }
        System.out.println("Government coverage should be evenly distributed");
        for (SolarSystem.Government g : SolarSystem.Government.values()) {
            System.out.println(g.name() + ": " + governmentCoverage[g.ordinal()]);
        }
        System.out.println("Police coverage should follow a gaussian distribution");
        for (SolarSystem.RandomEncounter p : SolarSystem.RandomEncounter.values()) {
            System.out.println(p.name() + ": " + policeCoverage[p.ordinal()]);
        }
        System.out.println("Pirate coverage should follow a gaussian distribution");
        for (SolarSystem.RandomEncounter p : SolarSystem.RandomEncounter.values()) {
            System.out.println(p.name() + ": " + piratesCoverage[p.ordinal()]);
        }

        SolarSystem systemFull = new SolarSystem();
        String[] planetNamesFull = new String[Planet.MAX_RADIUS - Planet.MIN_RADIUS + 1];
        for (int i = 0; i < planetNamesFull.length; ++i) {
            planetNamesFull[i] = String.valueOf(i);
        }
        systemFull.startSystem(planetNamesFull);
        List<Planet> planetList = systemFull.getPlanets();
        boolean[] orbitCoverage = new boolean[Planet.MAX_RADIUS - Planet.MIN_RADIUS + 1];
        for (Planet p : planetList) {
            Assert.assertTrue("Orbit not in range",
                    (p.getOrbitRadius() >= Planet.MIN_RADIUS)
                            && (p.getOrbitRadius()<= Planet.MAX_RADIUS));
            orbitCoverage[p.getOrbitRadius() - 2] = true;
        }
        for (int i = 0; i < orbitCoverage.length; ++i) {
            Assert.assertTrue("Orbit " + (i + 2) + " not covered", orbitCoverage[i]);
        }

        SolarSystem systemPartial = new SolarSystem();
        String[] planetNamesPartial = new String[planetNamesFull.length - 1];
        for (int i = 0; i < planetNamesPartial.length; ++i) {
            planetNamesFull[i] = String.valueOf(i);
        }
        systemPartial.startSystem(planetNamesPartial);
        planetList = systemPartial.getPlanets();
        orbitCoverage = new boolean[Planet.MAX_RADIUS - Planet.MIN_RADIUS + 1];
        for (Planet p : planetList) {
            Assert.assertTrue("Orbit not in range",
                    (p.getOrbitRadius() >= Planet.MIN_RADIUS)
                            && (p.getOrbitRadius() <= Planet.MAX_RADIUS));
            orbitCoverage[p.getOrbitRadius() - 2] = true;
        }
        boolean allCovered = true;
        for (int i = 0; i < orbitCoverage.length; ++i) {
            if (orbitCoverage[i] == false) {
                System.out.println(i + " not covered");
                allCovered = false;
            }
        }
        Assert.assertFalse("All obits covered when max planet names not inputted", allCovered);
    }
}