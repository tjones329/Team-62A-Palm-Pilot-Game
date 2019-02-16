package cs2340.spacetradergame.entity;

public abstract class Spaceship {
    private int damage;
    private int speed;
    private int capacity;
    private int health;
    public Spaceship(int d, int s, int c, int h) {
        damage = d;
        speed = s;
        capacity = c;
        health = h;
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
}
