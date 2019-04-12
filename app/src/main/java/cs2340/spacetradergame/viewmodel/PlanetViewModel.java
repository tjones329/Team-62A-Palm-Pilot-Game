package cs2340.spacetradergame.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.widget.Space;

import java.util.List;
import java.util.Set;

import cs2340.spacetradergame.entity.Planet;
import cs2340.spacetradergame.entity.Player;
import cs2340.spacetradergame.entity.SolarSystem;
import cs2340.spacetradergame.entity.Spaceship;
import cs2340.spacetradergame.model.Database;
import cs2340.spacetradergame.model.Game;

/**
 * class
 */
public class PlanetViewModel extends AndroidViewModel {
    private Planet currentPlanet;
    private SolarSystem currentSystem;
    private List<SolarSystem> systems;
    private List<Planet> planets;
    private Game game;
    private Spaceship ship;

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
     *
     * @param system SolarSystem
     */
    public void setCurrentSystem(SolarSystem system) {
        game.setCurrentSystem(system);
    }

    /**
     *
     * @param planet Planet
     */
    public void setCurrentPlanet(Planet planet) {
        game.setCurrentPlanet(planet);
    }

    /**
     *
     * @return systems
     */
    public List<SolarSystem> getSystems() { return systems;}

    /**
     *
     * @return planets
     */
    public List<Planet> getPlanets() {return planets;}

    /**
     * savegame
     */
    public void saveGame() {
        Database.saveGame(game);
    }

}
