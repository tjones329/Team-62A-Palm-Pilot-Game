package cs2340.spacetradergame.entity;

public class Gnat extends Spaceship {
    public Gnat() {super(2,5,5,3);}
    public String toString() {
        return "Gnat ship with following stats \n Damage: " + getDamage() + "\n Speed: "
                + getSpeed() + "\n Health: " + getHealth() + "\n Capacity: " + getCapacity();
    }
}
