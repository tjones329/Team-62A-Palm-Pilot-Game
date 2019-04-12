package cs2340.spacetradergame.entity;

import android.util.Log;

import java.util.List;

import cs2340.spacetradergame.model.Point;
import cs2340.spacetradergame.model.RandomMethods;

/**
 * spaceship
 */
public class Spaceship {
    private int health;
    private int speed;
    private int fuel;
    private int hyperdrive; // number of parsecs the ship can travel
    private int damage;
    private CargoHold hold;

    /**
     * spaceship constructor
     */
    public Spaceship() {

    }

    /**
     * alternative spaceship constructor
     * @param health int
     * @param speed int
     * @param fuel int
     * @param hyperdrive int
     * @param damage int
     * @param capacity int
     */
    public Spaceship(int health, int speed, int fuel, int hyperdrive, int damage, int capacity) {
        this.health = health;
        this.speed = speed;
        this.fuel = fuel;
        this.hyperdrive = hyperdrive;
        this.damage = damage;
        this.hold = new CargoHold(capacity);
    }

    /**
     * records pirate damage
     * @return damage
     */
    public int pirateDamage() {
        int damage = RandomMethods.gaussian(10) + 5;
        health -= damage;
        return damage;
    }

    /**
     *
     * @return health
     */
    public int getHealth() {
        return health;
    }

    /**
     *
     * @return speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     *
     * @return fuel
     */
    public int getFuel() {
        return fuel;
    }

    /**
     *
     * @return hyperdrive
     */
    public int getHyperdrive() {
        return hyperdrive;
    }

    /**
     *
     * @return damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     *
     * @return capacity of cargohold
     */
    public int getCapacity() {
        return hold.getCapacity();
    }

    /**
     * setter for damage
     * @param d int
     */
    public void setDamage(int d) {
        damage = d;
    }

    /**
     * decrement fuel and logs something
     */
    public void decrementFuel() {
        --fuel;
        Log.d("Fuel", String.valueOf(fuel));
    }

    /**
     * setter speed
     * @param s int
     */
    public void setSpeed(int s) {
        speed = s;
    }

    /**
     * setter health
     * @param h int
     */
    public void setHealth(int h) {
        health = h;
    }

    /**
     * determines if jump possible
     * @param a Point
     * @param b Point
     * @return boolean
     */
    public boolean canJump(Point a, Point b) {
        return hyperdrive > Point.distance(a, b);
    }
    //public LinkedList<MarketItem> getCargo() {return cargo;}

    /**
     *
     * @return hold
     */
    public CargoHold getHold() {
        return hold;
    }
    /*public boolean addCargo(int[] items) {
        return hold.addCargo(items);
        for (MarketItem m : MarketItem.values()) {
            for (int i = 0; i < 10; i++) {
                if (m.ordinal() == i && (items[i] > 0)) {
                    for (int j = 0; j < items[i]; j++) {
                        cargo.add(m);
                    }
                }
            }
        }
    }*/

    /**
     * removes from cargo certain items
     * @param items list of items
     */
    public void removeCargo(List<Integer> items) {
        hold.removeCargo(items);
    }
}
