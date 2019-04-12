package cs2340.spacetradergame.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.Set;

import cs2340.spacetradergame.entity.Planet;
import cs2340.spacetradergame.entity.SolarSystem;
import cs2340.spacetradergame.entity.Spaceship;
import cs2340.spacetradergame.model.Game;

/**
 * travel
 */
public class TravelPlanetViewModel extends AndroidViewModel {
    private Game game;
    private List<Planet> planets;

    /**
     * constructor
     * @param application Application
     */
    public TravelPlanetViewModel(@NonNull Application application) {
        super(application);
        game = Game.getInstance();
        planets = game.getPlanets();
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
     * @return planets
     */
    public List<Planet> getPlanets() {return planets;}

    /**
     * travels
     */
    public void travel() {
        game.travel();
    }
}
