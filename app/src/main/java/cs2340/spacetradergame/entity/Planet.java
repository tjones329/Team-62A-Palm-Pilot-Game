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

    /**
     * Gives a random xy value pair that is radius distance away from the sun(assuming the sun is at
     *  (0,0))
     *
     * @return a double[] with double[0] = x and double[1] = y
     */
    public double[] getCoords(){
        Random rand = new Random();
        double angle = rand.nextInt(360) * 180 / Math.PI;
        double coord;
        double[] returnValue = new double[2];
        coord = Math.cos(angle) * this.orbitRadius * 100;
        returnValue[0] = Math.round(coord) / 10;
        coord = Math.sin(angle) * this.orbitRadius * 100;
        returnValue[1] = Math.round(coord) / 10;
        return returnValue;
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
