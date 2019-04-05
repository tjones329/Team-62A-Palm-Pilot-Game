package cs2340.spacetradergame.model;

import android.util.Log;

import com.google.firebase.firestore.Exclude;

import java.util.List;

import cs2340.spacetradergame.entity.Gnat;
import cs2340.spacetradergame.entity.Planet;
import cs2340.spacetradergame.entity.Player;
import cs2340.spacetradergame.entity.SolarSystem;
import cs2340.spacetradergame.entity.Spaceship;
import cs2340.spacetradergame.entity.Universe;
import cs2340.spacetradergame.views.MainActivity;

public class Game {
    public static final int MAX_SKILL_POINTS = 16;
    public static final int STARTING_CREDITS = 1000;
    public static final int ITEM_NUM = 10;


    private static Game instance = new Game();

    private int difficulty;
    private Player player;
    private Universe universe;
    private SolarSystem currentSystem;
    private Planet currentPlanet;

    private String saveCurrentSystem;
    private String saveCurrentPlanet;

    private boolean wasAttacked = false;

    public Game() {

    }

    public void newUniverse() {
        universe = new Universe();
        universe.logUniverse();

        currentSystem = universe.getRandomSystem();
        currentPlanet = currentSystem.getRandomPlanet();
    }

    public static void loadGame(String id, MainActivity activity) {
        Database.loadGame(id, activity);
    }
    public static void loadInstance(Game game) {
        instance = game;
        instance.loadGame();
    }
    public void loadGame() {
        currentSystem = universe.findSystem(saveCurrentSystem);
        currentPlanet = currentSystem.findPlanet(saveCurrentPlanet);
    }

    /**
     * Computes during travel if the player was attacked
     */
    public void travel() {
        wasAttacked = RandomMethods.nextInt(100) > 80;
    }
    public boolean wasAttacked() {
        boolean out = wasAttacked;
        wasAttacked = false;
        return out;
    }

    @Exclude
    public static Game getInstance() {
        return instance;
    }

    @Exclude
    public List<Planet> getPlanets() {
        return currentSystem.getPlanets();
    }

    @Exclude
    public List<SolarSystem> getSystems() {
        return universe.getSystems();
    }

    @Exclude
    public Spaceship getShip() {
        return player.getShip();
    }

    //getters
    public int getDifficulty() {
        return difficulty;
    }

    public Player getPlayer() {
        return player;
    }

    public Universe getUniverse() {
        return universe;
    }

    @Exclude
    public SolarSystem getCurrentSystem() {
        return currentSystem;
    }

    @Exclude
    public Planet getCurrentPlanet() {
        return currentPlanet;
    }

    public String getSaveCurrentSystem() {
        return currentSystem.getName();
    }

    public String getSaveCurrentPlanet() {
        return currentPlanet.getName();
    }

    //setters
    public void setCurrentSystem(SolarSystem system) {
        currentSystem = system;
    }

    public void setCurrentPlanet(Planet planet) {
        currentPlanet = planet;
    }

    public void setDifficulty(int difficulty) {
        Log.d("Difficulty set", String.valueOf(difficulty));
        this.difficulty = difficulty;
    }

    public void setPlayer(Player player) {
        Log.d("Player set", player.toString());
        this.player = player;
        player.setShip(new Gnat());
    }
}
