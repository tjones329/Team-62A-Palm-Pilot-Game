package cs2340.spacetradergame.entity;

public class Player {

    private String name;
    private int credits = 1000;
    private int traderPoints;
    private int engineerPoints;
    private int pilotPoints;
    private int fighterPoints;
    private Spaceship ship = new Gnat();

    public void player(String name, int trader, int engineer, int pilot, int fighter){
        this.name = name;
        traderPoints = trader;
        engineerPoints = engineer;
        pilotPoints = pilot;
        fighterPoints = fighter;
    }

    public void player(){
        this.player("Player", 0, 0, 0, 0);
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public int getTraderPoints() {
        return traderPoints;
    }

    public int getEngineerPoints() {
        return engineerPoints;
    }

    public int getPilotPoints() {
        return pilotPoints;
    }

    public int getFighterPoints() {
        return fighterPoints;
    }

    public Spaceship getShip() {
        return ship;
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
}
