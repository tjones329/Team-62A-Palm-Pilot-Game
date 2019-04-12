package cs2340.spacetradergame.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import cs2340.spacetradergame.entity.Planet;
import cs2340.spacetradergame.entity.SolarSystem;
import cs2340.spacetradergame.entity.Spaceship;
import cs2340.spacetradergame.model.Game;

/**
 * travelsolarsystem
 */
public class TravelSolarSystemViewModel extends AndroidViewModel {
    private final List<SolarSystem> systems;
    private final List<Planet> planets;
    private final Spaceship ship;
    private final Game game;

    /**
     * constructor
     * @param application Application
     */
    public TravelSolarSystemViewModel(@NonNull Application application) {
        super(application);
        game = Game.getInstance();
        systems = game.getSystems();
        planets = game.getPlanets();
        ship = game.getPlayer().getShip();
    }

    /**
     * setter
     * @param system SolarSystem
     */
    public void setCurrentSystem(SolarSystem system) {
        game.setCurrentSystem(system);
    }

    /**
     * setter
     * @param planet Planet
     */
    public void setCurrentPlanet(Planet planet) {
        game.setCurrentPlanet(planet);
    }

    /**
     * getter
     * @return systems
     */
    public List<SolarSystem> getSystems() { return systems;}

    /**
     *
     * @return planets
     */
    public List<Planet> getPlanets() {return planets;}

    /**
     * determines fuel
     * @return boolean
     */
    public boolean hasFuel() {
        return ship.getFuel() > 0;
    }

    /**
     * travels
     */
    public void travel() {
        ship.decrementFuel();
    }
}
