package cs2340.spacetradergame.entity;

import java.util.Random;

public class Market {
    private int techLevel;
    Random gen;
    int currentPrice;
    public Market(int currTechLevel) {
        gen = new Random();
        techLevel = currTechLevel;
    }

    /**
     * Calculates the prices of the items IN THE ENUM ORDER. If it is equal to 0, that means the
     * planet is unable to produce it. If it is not equal to 0, it tells you the price the planet
     * sells it at
     * @return int[] price array in the order of items listed in MarketItem enum file
     */
    public int[] calculatePrices() {
        int[] prices = new int[10]; //the order of prices is same as order in the enum MarketItem
        for (MarketItem m : MarketItem.values()) {
            //The price for water would be 30 (the base price) + 3*2 (the IPL * (Planet Tech Level - MTLP)) + (variance)
            //variance should range 0 to m.getVar() inclusive
            if (canProduce(m)) {
                currentPrice = m.getBasePrice() + (m.getIPL() * (techLevel - m.getMTLP()))
                        + gen.nextInt(m.getVar() + 1);
            } else {
                currentPrice = 0;
            }
            prices[m.ordinal()] = currentPrice;
        }
        return prices;
    }
    public String toString() {
        return techLevel + "";
    }
    /**
     * Determines whether a particular market item is even produced by the planet
     * @param item passed in enum item
     * @return boolean return whether the planete's tech level meets the requirement for item
     */
    private boolean canProduce(MarketItem item) {
        return item.getMTLP() <= techLevel;
    }
}
