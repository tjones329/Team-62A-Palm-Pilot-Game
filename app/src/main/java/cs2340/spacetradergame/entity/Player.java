package cs2340.spacetradergame.entity;

import cs2340.spacetradergame.model.Game;

/**
 * player
 */
public class Player {

    private String name;
    private int credits = Game.STARTING_CREDITS;
    private int pilotPoints;
    private int fighterPoints;
    private int traderPoints;
    private int engineerPoints;
    private Spaceship ship = new Gnat();

    /**
     * constructor for player
     * @param name string
     * @param pilot int
     * @param fighter int
     * @param trader int
     * @param engineer int
     */
    public Player(String name, int pilot, int fighter, int trader, int engineer){
        this.name = name;
        pilotPoints = pilot;
        fighterPoints = fighter;
        traderPoints = trader;
        engineerPoints = engineer;
    }

    /**
     * constructor for player
     */
    public Player(){
        this("Player", Game.MAX_SKILL_POINTS, 0, 0, 0);
    }

    /**
     * decrements credits by amount
     * @param amount money bought
     */
    public void bought(int amount) {
        credits -= amount;
    }

    /**
     * increments by sold
     * @param amount money sold
     */
    public void sold(int amount) {
        credits += amount;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     *
     * @return pilotPoints
     */
    public int getPilotPoints() {
        return pilotPoints;
    }

    /**
     *
     * @return fighterPoints
     */
    public int getFighterPoints() {
        return fighterPoints;
    }

    /**
     *
     * @return traderPoints
     */
    public int getTraderPoints() {
        return traderPoints;
    }

    /**
     *
     * @return engineerPoints
     */
    public int getEngineerPoints() {
        return engineerPoints;
    }

    /**
     *
     * @return ship
     */
    public Spaceship getShip() {
        return ship;
    }

    /**
     *
     * @return cargohold
     */
    public CargoHold getHold() {
        return ship.getHold();
    }

    /**
     * sets the name
     * @param name string
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * set credits
     * @param credits int
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * set trader points
     * @param traderPoints int
     */
    public void setTraderPoints(int traderPoints) {
        this.traderPoints = traderPoints;
    }

    /**
     * setter
     * @param engineerPoints int
     */
    public void setEngineerPoints(int engineerPoints) {
        this.engineerPoints = engineerPoints;
    }

    /**
     * setter
     * @param pilotPoints int
     */
    public void setPilotPoints(int pilotPoints) {
        this.pilotPoints = pilotPoints;
    }

    /**
     * setter
     * @param fighterPoints int
     */
    public void setFighterPoints(int fighterPoints) {
        this.fighterPoints = fighterPoints;
    }

    /**
     * setter
     * @param ship string
     */
    public void setShip(Spaceship ship) {
        this.ship = ship;
    }

    /**
     *
     * @return spaceship
     */
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
