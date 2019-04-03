package cs2340.spacetradergame.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.Set;

import cs2340.spacetradergame.entity.Planet;
import cs2340.spacetradergame.entity.SolarSystem;
import cs2340.spacetradergame.entity.Spaceship;
import cs2340.spacetradergame.model.Game;

public class TravelSolarSystemViewModel extends AndroidViewModel {
    private Set<SolarSystem> systems;
    private Set<Planet> planets;
    private Spaceship ship;
    private Game game;

    public TravelSolarSystemViewModel(@NonNull Application application) {
        super(application);
        game = Game.getInstance();
        systems = game.getSystems();
        planets = game.getPlanets();
        ship = game.getPlayer().getShip();
    }

    public void setCurrentSystem(SolarSystem system) {
        game.setCurrentSystem(system);
    }

    public void setCurrentPlanet(Planet planet) {
        game.setCurrentPlanet(planet);
    }

    public Set<SolarSystem> getSystems() { return systems;}

    public Set<Planet> getPlanets() {return planets;}

    public boolean hasFuel() {
        return ship.getFuel() > 0;
    }

    public void travel() {
        ship.decrementFuel();
    }
}
