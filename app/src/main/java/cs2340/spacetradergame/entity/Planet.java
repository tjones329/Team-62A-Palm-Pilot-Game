package cs2340.spacetradergame.entity;

import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;
import java.util.List;

import cs2340.spacetradergame.model.Game;
import cs2340.spacetradergame.model.RandomMethods;

import static cs2340.spacetradergame.model.RandomMethods.gaussian;

public class Planet {
    public enum TechLevel {
        PREAGRICULTURE, AGRICULTURE, MIDIEVAL, RENAISSANCE, EARLYINDUSTRIAL, INDUSTRIAL,
        POSTINDUSTRIAL, HITECH;
        public static TechLevel[] techLevels = TechLevel.values();
    }
    public enum Resources {
        NOSPECIALRESOURCES, MINERALRICH, MINERALPOOR, DESERT, LOTSOFWATER, RICHSOIL, POORSOIL,
        RICHFAUNA, LIFELESS, WEIRDMUSHROOMS, LOTSOFHERBS, ARTISTIC, WARLIKE;
        public static Resources[] resources = Resources.values();
    }

    public static final int MAX_RADIUS = 10;
    public static final int MIN_RADIUS = 2;

    private String name;
    private int orbitRadius;

    private TechLevel techLevel;
    private Resources resources;

    private List<Integer> prices;
    private List<Integer> quantities;

    public Planet() {

    }

    public Planet(String name) {
        this.name = name;
        orbitRadius = RandomMethods.nextInt(MAX_RADIUS + 1 - MIN_RADIUS) + MIN_RADIUS;
    }

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

    /**
     * Determines whether a particular market item is even produced by the planet
     * @param item passed in enum item
     * @return boolean return whether the planete's tech level meets the requirement for item
     */
    /*private boolean canProduce(MarketItem item) {
        return item.getMTLP() <= techLevel.ordinal();
    }

    public boolean canSell(MarketItem item) {
        return item.getMTLU() <= techLevel.ordinal();
    }*/

    /**
     * Gives the planet a unique tech level. For purposes of M7, although we have a variety
     * of different attributes such as "resources" or "pirates", we will ignore them for now. We're
     * still passing them in, but in order to calculate the prices for the market, techLevel is
     * the only necessary variable.
     * @param techLevel passed in techLevel for the creation of the market, prices determined in
     * market
     */
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
        double angle = RandomMethods.nextInt(360) * 180 / Math.PI;
        double coord;
        double[] returnValue = new double[2];
        coord = Math.cos(angle) * orbitRadius * 100;
        returnValue[0] = Math.round(coord) / 10;
        coord = Math.sin(angle) * orbitRadius * 100;
        returnValue[1] = Math.round(coord) / 10;
        return returnValue;
    }

    public String getName() {
        return name;
    }

    public int getOrbitRadius() {
        return orbitRadius;
    }

    public TechLevel getTechLevel() {
        startPlanet();
        return techLevel;
    }

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

    public List<Integer> getPrices() {
        startPlanet();
        return prices;
    }

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
