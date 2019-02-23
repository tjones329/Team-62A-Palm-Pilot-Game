package cs2340.spacetradergame.entity;

import java.util.Random;

public class Planet {
    public static final int MAX_RADIUS = 10;
    public static final int MIN_RADIUS = 2;
    private String name;
    private int orbitRadius;

    public Planet(String name, Random random) {
        this.name = name;
        orbitRadius = random.nextInt(MAX_RADIUS - MIN_RADIUS) + MIN_RADIUS;
    }

    public String getName() {
        return name;
    }

    public int getOrbitRadius() {
        return orbitRadius;
    }

    @Override
    public int hashCode() {
        return orbitRadius;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Planet) {
            Planet p = (Planet) obj;
            return p.orbitRadius == orbitRadius;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return name + " with orbit radius " + orbitRadius;
    }
}
