package cs2340.spacetradergame.entity;

/**
 * gnat variant of spaaceship
 */
public class Gnat extends Spaceship {
    /**
     * constructor for gnat
     */
    public Gnat() {
        super(30,5,8, 30,2, 5);
    }

    @Override
    public String toString() {
        return "Gnat ship with following stats \n Damage: " + getDamage() + "\n Speed: "
                + getSpeed() + "\n Health: " + getHealth() + "\n Capacity: " + getCapacity();
    }
}
