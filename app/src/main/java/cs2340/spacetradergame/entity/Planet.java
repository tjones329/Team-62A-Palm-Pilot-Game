package cs2340.spacetradergame.entity;

import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;
import java.util.List;

import cs2340.spacetradergame.model.Game;
import cs2340.spacetradergame.model.RandomMethods;

import static cs2340.spacetradergame.model.RandomMethods.gaussian;

/**
 * planet
 */
public class Planet {
    public enum TechLevel {
        PREAGRICULTURE, AGRICULTURE, MIDIEVAL, RENAISSANCE, EARLYINDUSTRIAL, INDUSTRIAL,
        POSTINDUSTRIAL, HITECH;
        static final TechLevel[] techLevels = TechLevel.values();
    }
    public enum Resources {
        NOSPECIALRESOURCES, MINERALRICH, MINERALPOOR, DESERT, LOTSOFWATER, RICHSOIL, POORSOIL,
        RICHFAUNA, LIFELESS, WEIRDMUSHROOMS, LOTSOFHERBS, ARTISTIC, WARLIKE;
        static final Resources[] resources = Resources.values();
    }

    public static final int MAX_RADIUS = 10;
    public static final int MIN_RADIUS = 2;

    private String name;
    private int orbitRadius;

    private TechLevel techLevel;
    private Resources resources;

    private List<Integer> prices;
    private List<Integer> quantities;

    /**
     * Explicit for Firebase
     */
    public Planet() {

    }

    /**
     * constructor with name
     * @param name string
     */
    public Planet(String name) {
        this.name = name;
        orbitRadius = RandomMethods.nextInt(MAX_RADIUS - MIN_RADIUS - 1) + MIN_RADIUS;
    }

    /**
     * initialize planet
     */
    private void startPlanet() {
        if (techLevel != null) {
            return;
        }

        techLevel = TechLevel.techLevels[gaussian(TechLevel.techLevels.length - 1)];
        int resourcesInt = RandomMethods.nextInt(Resources.resources.length + 3);
        if (resourcesInt < 3) {
            resources = Resources.NOSPECIALRESOURCES;
        } else {
            resources = Resources.resources[resourcesInt - 3];
        }

        prices = new ArrayList<>(Game.ITEM_NUM);
        quantities = new ArrayList<>(Game.ITEM_NUM);
        for (MarketItem m : MarketItem.values()) { // calculate prices and quantities of each item
            // Ex. the price for water would be 30 (the base price)
            // + 3*2 (the IPL * (Planet Tech Level - MTLP)) + (variance)
            //variance should range 0 to m.getVar() inclusive
            int price;
            int quantity;

            if (m.canProduce(techLevel)) {
                price = m.getBasePrice() + (m.getIPL() * (techLevel.ordinal() - m.getMTLP()))
                        + RandomMethods.nextInt(m.getVar() + 1);
                quantity = RandomMethods.nextInt(20) + 10;
            } else {
                price = 0;
                quantity = 0; // 0 indicates that this planet cannot produce this item
            }
            prices.add(price);
            quantities.add(quantity);
        }
    }

    /*private boolean canProduce(MarketItem item) {
        return item.getMTLP() <= techLevel.ordinal();
    }

    public boolean canSell(MarketItem item) {
        return item.getMTLU() <= techLevel.ordinal();
    }*/

    /*public void initializeMarket(int techLevel) {
        this.techLevel = techLevel;
        market = new Market(techLevel);
    }*/

    /**
     * Gives a random xy value pair that is radius distance away from the sun(assuming the sun is at
     *  (0,0))
     *
     * @return a double[] with double[0] = x and double[1] = y
     */
    @Exclude
    public double[] getCoords(){
        double angle = (RandomMethods.nextInt(360) * 180) / Math.PI;
        double coord;
        double[] returnValue = new double[2];
        coord = Math.cos(angle) * orbitRadius * 100;
        returnValue[0] = coord / 10;
        coord = Math.sin(angle) * orbitRadius * 100;
        returnValue[1] = coord / 10;
        return returnValue;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return orbitRadius
     */
    public int getOrbitRadius() {
        return orbitRadius;
    }

    /**
     *
     * @return techLevel
     */
    public TechLevel getTechLevel() {
        startPlanet();
        return techLevel;
    }

    /**
     *
     * @return resources
     */
    public Resources getResources() {
        startPlanet();
        return resources;
    }

    /*
    @Exclude
    public int[] getPrices() {
        startPlanet();
        return prices;
    }

    @Exclude
    public int[] getQuantities() {
        startPlanet();
        return quantities;
    }
    */

    /**
     *
     * @return prices
     */
    public List<Integer> getPrices() {
        startPlanet();
        return prices;
    }

    /**
     *
     * @return quantities
     */
    public List<Integer> getQuantities() {
        startPlanet();
        return quantities;
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
        return "Name: " + name
                + " Orbit Radius " + orbitRadius
                + " Tech Level " + techLevel
                + " Resources " + resources;
    }

}
