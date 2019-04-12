package cs2340.spacetradergame.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import cs2340.spacetradergame.entity.Planet;
import cs2340.spacetradergame.entity.SolarSystem;
import cs2340.spacetradergame.entity.Spaceship;
import cs2340.spacetradergame.model.Database;
import cs2340.spacetradergame.model.Game;

/**
 * class
 */
public class PlanetViewModel extends AndroidViewModel {
    private final Planet currentPlanet;
    private final SolarSystem currentSystem;
    private final List<SolarSystem> systems;
    private final List<Planet> planets;
    private final Game game;
    private final Spaceship ship;

    /**
     * constructor
     * @param application Application
     */
    public PlanetViewModel(@NonNull Application application) {
        super(application);
        game = Game.getInstance();
        currentPlanet = game.getCurrentPlanet();
        currentSystem = game.getCurrentSystem();
        systems = game.getSystems();
        planets = game.getPlanets();
        ship = game.getShip();
    }

    /**
     *
     * @return wasattacked
     */
    public boolean wasAttacked() {
        return game.wasAttacked();
    }

    /**
     *
     * @return piratedamage
     */
    public int pirateDamage() {
        return ship.pirateDamage();
    }

    /**
     *
     * @return name
     */
    public String getPlanetName() {
        return currentPlanet.getName();
    }

    /**
     *
     * @return techlevel
     */
    public String getTechLevel() {
        return currentPlanet.getTechLevel().name();
    }

    /**
     *
     * @return resources
     */
    public String getResources() {
        return currentPlanet.getResources().name();
    }

    /**
     *
     * @return name
     */
    public String getSystemName() {
        return currentSystem.getName();
    }

    /**
     *
     * @return government name
     */
    public String getGovernment() {
        return currentSystem.getGovernment().name();
    }

    /**
     *
     * @return police
     */
    public String getPolice() {
        return currentSystem.getPolice().name();
    }

    /**
     *
     * @return pirates
     */
    public String getPirates() {
        return currentSystem.getPirates().name();
    }

    /**
     * savegame
     */
    public void saveGame() {
        Database.db.saveGame(game);
    }

}
