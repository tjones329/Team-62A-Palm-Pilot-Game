package cs2340.spacetradergame.entity;

import android.util.Log;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import cs2340.spacetradergame.model.Point;

public class SolarSystem {
    enum TechLevel {
        PREAGRICULTURE, AGRICULTURE, MIDIEVAL, RENAISSANCE, EARLYINDUSTRIAL, INDUSTRIAL,
        POSTINDUSTRIAL, HITECH;
        public static TechLevel[] techLevels = TechLevel.values();
    }
    enum Resources {
        NOSPECIALRESOURCES, MINERALRICH, MINERALPOOR, DESERT, LOTSOFWATER, RICHSOIL, POORSOIL,
        RICHFAUNA, LIFELESS, WEIRDMUSHROOMS, LOTSOFHERBS, ARTISTIC, WARLIKE;
        public static Resources[] resources = Resources.values();
    }
    enum Pirates {
        MINIMAL, FEW, SOME, MANY, SWARMS;
        public static Pirates[] pirates = Pirates.values();
    }
    enum Police {
        MINIMAL, FEW, SOME, MANY, SWARMS;
        public static Police[] police = Police.values();
    }
    enum Government {
        ANARCHY, TRIBAL, MONARCHY, DEMOCRACY, SOCIALIST, TOTALITARIAN;
        public static Government[] governments = Government.values();
    }

    private String name;
    private Point pos;
    private TechLevel techLevel;
    private Resources resources;
    private Pirates pirates;
    private Police police;
    private Government government;
    public Set<Planet> planets;

    public SolarSystem(String name, Random random) {
        this.name = name;
        pos = new Point(random.nextInt(Universe.universeWidth),
                random.nextInt(Universe.universeHeight));
    }

    /**
     * Returns a number between 0 and max, inclusive, with a gaussian distribution
     * @param random the random number object
     * @param max the maximum number
     * @return the random number
     */
    private int gaussian(Random random, int max) {
        int i = (int) (((random.nextGaussian() + 3) / 6) * max);
        if (i < 0) {
            return 0;
        } else if (i > max) {
            return max;
        } else {
            return i;
        }
    }

    public void startSystem(String[] planetNames, Random random) {
        techLevel = TechLevel.techLevels[gaussian(random, TechLevel.techLevels.length - 1)];
        int resourcesInt = random.nextInt(Resources.resources.length + 3);
        if (resourcesInt < 3) {
            resources = Resources.NOSPECIALRESOURCES;
        } else {
            resources = Resources.resources[resourcesInt - 3];
        }
        pirates = Pirates.pirates[gaussian(random, Pirates.pirates.length - 1)];
        police = Police.police[gaussian(random, Police.police.length - 1)];
        government = Government.governments[random.nextInt(Government.governments.length)];

        planets = new HashSet<>();
        Planet curr;
        for (String s : planetNames) {
            boolean added;
            do {
                curr = new Planet(s, random);
                added = planets.add(curr);
            } while (!added);
        }
    }

    /**
     * the logic for getting planet positions in xy value pairs when the solarsystem is zoomed in on
     * @return a set of double[] with double[0] = x and double[1] = y
     */
    public Set<double[]> onZoom() {
        HashSet<double[]> locations = new HashSet<>(this.planets.size());
        for (Planet p : this.planets) {
            boolean done = false;
            while (!done) {
                done = true;
                double[] xyValue = p.getCoords();
                for (double[] planetLocation : locations) {
                    if (xyValue[0] == planetLocation[0] && xyValue[1] == planetLocation[1]) {
                        done = false;
                    }
                }
                if (done) {
                    locations.add(xyValue);
                }
            }
        }
        return locations;
    }

    public String getName() {
        return name;
    }

    public Point getPos() {
        return pos;
    }

    public void logSystem() {
        Log.d("Solar System", "Name: " + name
                + " Position: " + pos.toString()
                + " Tech Level: " + techLevel
                + " Resources: " + resources
                + " Pirates: " + pirates
                + " Police: " + police
                + " Government: " + government);
        for (Planet p : planets) {
            Log.d("Planet", p.toString());
        }
    }

    @Override
    public int hashCode() {
        return pos.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SolarSystem) {
            SolarSystem s = (SolarSystem) obj;
            return pos.equals(s.getPos());
        } else {
            return false;
        }
    }
}
