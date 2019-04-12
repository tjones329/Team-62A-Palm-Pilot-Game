package cs2340.spacetradergame.entity;

import android.util.Log;

import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cs2340.spacetradergame.model.Point;
import cs2340.spacetradergame.model.RandomMethods;

public class SolarSystem {
    public enum Government { // If a solar system isn't governed on a system-wide level, not worth trading
        ANARCHY, TRIBAL, MONARCHY, DEMOCRACY, SOCIALIST, TOTALITARIAN;
        public static Government[] governments = Government.values();
    }
    public enum RandomEncounter { // Solar systems are governed, and thus enforced on a system-wide level
        MINIMAL, FEW, SOME, MANY, SWARMS;
        public static RandomEncounter[] values = RandomEncounter.values();
    }

    private String name;
    private Point pos;

    private Government government;
    private RandomEncounter police;
    private RandomEncounter pirates;
    private List<Planet> planets;

    public SolarSystem() {

    }

    public SolarSystem(String name) {
        this.name = name;
        pos = new Point(RandomMethods.nextInt(Universe.universeWidth),
                RandomMethods.nextInt(Universe.universeHeight));
    }

    /**
     * Initializes the values for the system and the planets in the system
     * @param planetNames a non-null array of Strings with the names of the planets, which should
     * not contain duplicate strings or have a length greater than
     * Planet.MAX_ORBIT - Planet.MIN_ORBIT + 1 (number of orbits)
     */
    public void startSystem(String[] planetNames) {
        government = Government.governments[RandomMethods.nextInt(Government.governments.length)];
        police = RandomEncounter.values[RandomMethods.gaussian(RandomEncounter.values.length - 1)];
        pirates = RandomEncounter.values[RandomMethods.gaussian(RandomEncounter.values.length - 1)];

        this.planets = new ArrayList<>(planetNames.length);
        Set<Planet> planets = new HashSet<>();
        Planet curr;
        for (String s : planetNames) {
            boolean added;
            do {
                curr = new Planet(s);
                added = planets.add(curr);
            } while (!added);
            this.planets.add(curr);
        }
    }

    @Exclude
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

    public Planet findPlanet(String planetName) {
        for (Planet p : planets) {
            if (p.getName().equals(planetName)) {
                return p;
            }
        }
        return null;
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

    public String getName() {
        return name;
    }

    public Point getPos() {
        return pos;
    }

    public Government getGovernment() {
        return government;
    }

    public RandomEncounter getPolice() {
        return police;
    }

    public RandomEncounter getPirates() {
        return pirates;
    }

    public List<Planet> getPlanets() {
        return planets;
    }
}
