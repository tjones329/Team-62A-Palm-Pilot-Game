package cs2340.spacetradergame.entity;

public abstract class Spaceship {
    private int damage;
    private int speed;
    private int health;
    //private LinkedList<MarketItem> cargo;
    private CargoHold hold;
    public Spaceship(int damage, int speed, int capacity, int health) {
        this.damage = damage;
        this.speed = speed;
        this.health = health;
        //cargo = new LinkedList<>();
        this.hold = new CargoHold(capacity);
    }
    public int getDamage() {
        return damage;
    }
    public int getSpeed() {
        return speed;
    }
    public int getCapacity() {
        return hold.getCapacity();
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
    public void setHealth(int h) {
        health = h;
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
    public boolean removeCargo(int[] items) {
        return hold.removeCargo(items);
    }
}
