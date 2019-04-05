package cs2340.spacetradergame.entity;

import cs2340.spacetradergame.model.Game;
import cs2340.spacetradergame.model.RandomMethods;

public class Player {

    private String name;
    private int credits = Game.STARTING_CREDITS;
    private int pilotPoints;
    private int fighterPoints;
    private int traderPoints;
    private int engineerPoints;
    private Spaceship ship = new Gnat();

    public Player(String name, int pilot, int fighter, int trader, int engineer){
        this.name = name;
        pilotPoints = pilot;
        fighterPoints = fighter;
        traderPoints = trader;
        engineerPoints = engineer;
    }

    public Player(){
        this("Player", Game.MAX_SKILL_POINTS, 0, 0, 0);
    }

    public void bought(int amount) {
        credits -= amount;
    }
    public void sold(int amount) {
        credits += amount;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public int getPilotPoints() {
        return pilotPoints;
    }

    public int getFighterPoints() {
        return fighterPoints;
    }

    public int getTraderPoints() {
        return traderPoints;
    }

    public int getEngineerPoints() {
        return engineerPoints;
    }

    public Spaceship getShip() {
        return ship;
    }

    public CargoHold getHold() {
        return ship.getHold();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setTraderPoints(int traderPoints) {
        this.traderPoints = traderPoints;
    }

    public void setEngineerPoints(int engineerPoints) {
        this.engineerPoints = engineerPoints;
    }

    public void setPilotPoints(int pilotPoints) {
        this.pilotPoints = pilotPoints;
    }

    public void setFighterPoints(int fighterPoints) {
        this.fighterPoints = fighterPoints;
    }

    public void setShip(Spaceship ship) {
        this.ship = ship;
    }

    public Spaceship returnShip() {return ship;}

    @Override
    public String toString() {
        return "Name: " + name
                + " Pilot Points: " + String.valueOf(pilotPoints)
                + " Fighter Points: " + String.valueOf(fighterPoints)
                + " Trader Points: " + String.valueOf(traderPoints)
                + " Engineer Points: " + String.valueOf(engineerPoints);
    }
}
