package cs2340.spacetradergame.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.Set;

import cs2340.spacetradergame.entity.Planet;
import cs2340.spacetradergame.entity.SolarSystem;
import cs2340.spacetradergame.entity.Spaceship;
import cs2340.spacetradergame.model.Game;

public class TravelPlanetViewModel extends AndroidViewModel {
    private Game game;
    private Set<Planet> planets;

    public TravelPlanetViewModel(@NonNull Application application) {
        super(application);
        game = Game.getInstance();
        planets = game.getPlanets();
    }

    public void setCurrentPlanet(Planet planet) {
        game.setCurrentPlanet(planet);
    }

    public Set<Planet> getPlanets() {return planets;}
}
