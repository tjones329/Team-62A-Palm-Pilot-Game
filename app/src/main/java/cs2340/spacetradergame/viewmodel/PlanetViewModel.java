package cs2340.spacetradergame.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.Set;

import cs2340.spacetradergame.entity.Planet;
import cs2340.spacetradergame.entity.SolarSystem;
import cs2340.spacetradergame.model.Game;

public class PlanetViewModel extends AndroidViewModel {
    private Planet currentPlanet;
    private SolarSystem currentSystem;
    private Set<SolarSystem> systems;
    private Set<Planet> planets;
    private Game game;

    public PlanetViewModel(@NonNull Application application) {
        super(application);
        game = Game.getInstance();
        currentPlanet = game.getCurrentPlanet();
        currentSystem = game.getCurrentSystem();
        systems = game.getSystems();
        planets = game.getPlanets();
    }

    public String getPlanetName() {
        return currentPlanet.getName();
    }

    public String getTechLevel() {
        return currentPlanet.getTechLevel().name();
    }

    public String getResources() {
        return currentPlanet.getResources().name();
    }

    public String getSystemName() {
        return currentSystem.getName();
    }

    public String getGovernment() {
        return currentSystem.getGovernment().name();
    }

    public String getPolice() {
        return currentSystem.getPolice().name();
    }

    public String getPirates() {
        return currentSystem.getPirates().name();
    }

    public void setCurrentSystem(SolarSystem system) {
        game.setCurrentSystem(system);
    }

    public void setCurrentPlanet(Planet planet) {
        game.setCurrentPlanet(planet);
    }

    public Set<SolarSystem> getSystems() { return systems;}

    public Set<Planet> getPlanets() {return planets;}

}
