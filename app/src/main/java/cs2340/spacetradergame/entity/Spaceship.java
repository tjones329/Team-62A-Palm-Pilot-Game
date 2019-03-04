package cs2340.spacetradergame.entity;

import java.util.LinkedList;

public abstract class Spaceship {
    private int damage;
    private int speed;
    private int capacity;
    private int health;
    private LinkedList<MarketItem> cargo;
    public Spaceship(int d, int s, int c, int h) {
        damage = d;
        speed = s;
        capacity = c;
        health = h;
        cargo = new LinkedList<>();
    }
    public int getDamage() {
        return damage;
    }
    public int getSpeed() {
        return speed;
    }
    public int getCapacity() {
        return capacity;
    }
    public int getHealth() {
        return health;
    }
    public void setDamage(int d) {
        damage = d;
    }
    public void setSpeed(int s) {
        speed = s;
    }
    public void setCapacity(int c) {
        capacity = c;
    }
    public void setHealth(int h) {
        health = h;
    }
    public LinkedList<MarketItem> getCargo() {return cargo;}
    public void addCargo(MarketItem item) {cargo.add(item);}
    public boolean removeCargo(MarketItem item) {return cargo.remove(item);}

}
