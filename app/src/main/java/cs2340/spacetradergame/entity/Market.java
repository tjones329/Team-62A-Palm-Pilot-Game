package cs2340.spacetradergame.entity;

import java.util.Random;

public class Market {
    private int techLevel;
    int[] prices;
    int[] quantities;
    Random gen;
    int currentPrice;
    public Market(int currTechLevel) {
        prices = new int[10];
        quantities = new int[10];
        gen = new Random();
        techLevel = currTechLevel;
    }

    /**
     * Calculates the prices of the items IN THE ENUM ORDER. If it is equal to 0, that means the
     * planet is unable to produce it. If it is not equal to 0, it tells you the price the planet
     * sells it at
     * @return int[] price array in the order of items listed in MarketItem enum file
     */
    public void calculatePrices() {
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
    }

    public void calculateQuantities() {
        int randomQuantity;
        for (MarketItem m : MarketItem.values()) {
            if (canProduce(m)) {
                randomQuantity = gen.nextInt(20) + 10;
            } else {
                randomQuantity = 0;
            }
            quantities[m.ordinal()] = randomQuantity;
        }
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
    public boolean canSell(MarketItem item) {return item.getMTLU() <= techLevel;}

    public int[] getQuantities() {return quantities;};
    public int[] getPrices() {return prices;};
}
