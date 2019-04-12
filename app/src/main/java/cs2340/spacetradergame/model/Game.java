package cs2340.spacetradergame.model;

import com.google.firebase.firestore.Exclude;

import java.util.List;

import cs2340.spacetradergame.entity.Gnat;
import cs2340.spacetradergame.entity.Planet;
import cs2340.spacetradergame.entity.Player;
import cs2340.spacetradergame.entity.SolarSystem;
import cs2340.spacetradergame.entity.Spaceship;
import cs2340.spacetradergame.entity.Universe;
import cs2340.spacetradergame.views.MainActivity;

/**
 * game code
 */
public class Game {
    public static final int MAX_SKILL_POINTS = 16;
    public static final int STARTING_CREDITS = 1000;
    public static final int ITEM_NUM = 10;
    private static final int ATTACK_PROBABILITY = 20;


    private static Game instance = new Game();

    private int difficulty;
    private Player player;
    private Universe universe;
    private SolarSystem currentSystem;
    private Planet currentPlanet;

    private String saveCurrentSystem; // for Firebase
    private String saveCurrentPlanet; // for Firebase

    private boolean wasAttacked;

    /**
     * constructor for game, explicit for Firebase
     */
    public Game() {

    }

    /**
     * Makes a new universe
     * @param difficulty difficulty
     * @param player new player
     */
    public void newUniverse(int difficulty, Player player) {
        universe = new Universe();
        this.difficulty = difficulty;
        this.player = player;

        player.setShip(new Gnat());

        universe.logUniverse();

        currentSystem = universe.getRandomSystem();
        currentPlanet = currentSystem.getRandomPlanet();
    }

    /**
     * loads game with id and activity
     * @param id passed id
     * @param activity passed activity
     */
    public static void loadGame(String id, MainActivity activity) {
        Database.db.loadGame(id, activity);
    }

    /**
     * loads an instance of game
     * @param game instance
     */
    public static void loadInstance(Game game) {
        instance = game;
        instance.loadGame();
    }

    /**
     * loads a game
     */
    private void loadGame() {
        currentSystem = universe.findSystem(saveCurrentSystem);
        currentPlanet = currentSystem.findPlanet(saveCurrentPlanet);
    }

    /**
     * Computes during travel if the player was attacked
     */
    public void travel() {
        wasAttacked = RandomMethods.nextInt(100) < ATTACK_PROBABILITY;
    }

    /**
     * check if attacked
     * @return boolean
     */
    public boolean wasAttacked() {
        boolean out = wasAttacked;
        wasAttacked = false;
        return out;
    }

    /**
     *
     * @return instance
     */
    @Exclude
    public static Game getInstance() {
        return instance;
    }

    /**
     *
     * @return planets
     */
    @Exclude
    public List<Planet> getPlanets() {
        return currentSystem.getPlanets();
    }

    /**
     *
     * @return systems
     */
    @Exclude
    public List<SolarSystem> getSystems() {
        return universe.getSystems();
    }

    /**
     *
     * @return ship
     */
    @Exclude
    public Spaceship getShip() {
        return player.getShip();
    }

    /**
     *
     * @return player
     */
    @Exclude
    public Player getPlayer() {
        return player;
    }

    /**
     * For Firebase
     * @return universe
     */
    public Universe getUniverse() {
        return universe;
    }

    /**
     *
     * @return current system
     */
    @Exclude
    public SolarSystem getCurrentSystem() {
        return currentSystem;
    }

    /**
     *
     * @return planet current
     */
    @Exclude
    public Planet getCurrentPlanet() {
        return currentPlanet;
    }

    /**
     * For Firebase
     * @return current system
     */
    public String getSaveCurrentSystem() {
        return currentSystem.getName();
    }

    /**
     * For Firebase
     * @return name
     */
    public String getSaveCurrentPlanet() {
        return currentPlanet.getName();
    }

    //setters

    /**
     * setter
     * @param system system
     */
    public void setCurrentSystem(SolarSystem system) {
        currentSystem = system;
    }

    /**
     * setter
     * @param planet planet
     */
    public void setCurrentPlanet(Planet planet) {
        currentPlanet = planet;
    }
}
