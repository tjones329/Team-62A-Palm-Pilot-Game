package cs2340.spacetradergame.entity;

import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import cs2340.spacetradergame.model.Point;
import cs2340.spacetradergame.model.RandomMethods;

public class SolarSystem {
    public enum Government { // If a solar system isn't governed on a system-wide level, not worth trading
        ANARCHY, TRIBAL, MONARCHY, DEMOCRACY, SOCIALIST, TOTALITARIAN;
        public static Government[] governments = Government.values();
    }
    public enum Police { // Solar systems are governed, and thus enforced on a system-wide level
        MINIMAL, FEW, SOME, MANY, SWARMS;
        public static Police[] police = Police.values();
    }
    public enum Pirates { // Pirates with spaceships will patrol systems
        MINIMAL, FEW, SOME, MANY, SWARMS;
        public static Pirates[] pirates = Pirates.values();
    }

    private String name;
    private Point pos;

    private Pirates pirates;
    private Police police;
    private Government government;
    public Set<Planet> planets;

    public SolarSystem(String name) {
        this.name = name;
        pos = new Point(RandomMethods.nextInt(Universe.universeWidth),
                RandomMethods.nextInt(Universe.universeHeight));
    }

    public void startSystem(String[] planetNames) {
        //we don't want techLevel to be system wide, it should vary for each planet. Commented out
        //techLevel = TechLevel.techLevels[gaussian(random, TechLevel.techLevels.length - 1)];
        pirates = Pirates.pirates[RandomMethods.gaussian(Pirates.pirates.length - 1)];
        police = Police.police[RandomMethods.gaussian(Police.police.length - 1)];
        government = Government.governments[RandomMethods.nextInt(Government.governments.length)];

        planets = new HashSet<>();
        Planet curr;
        for (String s : planetNames) {
            boolean added;
            do {
                curr = new Planet(s);
                added = planets.add(curr);
            } while (!added);
        }
    }

    public Planet getRandomPlanet() {
        int planetNum = RandomMethods.nextInt(planets.size());
        int i = 0;
        for (Planet p : planets) {
            if (i == planetNum) {
                return p;
            }
            ++i;
        }
        return planets.iterator().next();
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
                //+ " Tech Level: " + techLevel
                //+ " Resources: " + resources
                + " Government: " + government
                + " Police: " + police
                + " Pirates: " + pirates);
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
