package cs2340.spacetradergame.entity;

import cs2340.spacetradergame.model.Point;

public abstract class Spaceship {
    private int health;
    private int speed;
    private int hyperdrive; // number of parsecs the ship can travel
    private int damage;
    private CargoHold hold;
    public Spaceship(int health, int speed, int hyperdrive, int damage, int capacity) {
        this.health = health;
        this.speed = speed;
        this.hyperdrive = hyperdrive;
        this.damage = damage;
        this.hold = new CargoHold(capacity);
    }
    public int getHealth() {
        return health;
    }
    public int getSpeed() {
        return speed;
    }
    public int getHyperdrive() {
        return hyperdrive;
    }
    public int getDamage() {
        return damage;
    }
    public int getCapacity() {
        return hold.getCapacity();
    }
    public void setDamage(int d) {
        damage = d;
    }
    public void setSpeed(int s) {
        speed = s;
    }
    public void setHealth(int h) {
        health = h;
    }
    public boolean canJump(Point a, Point b) {
        return hyperdrive > Point.distance(a, b);
    }
    //public LinkedList<MarketItem> getCargo() {return cargo;}
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
    public void removeCargo(int[] items) {
        hold.removeCargo(items);
    }
}
