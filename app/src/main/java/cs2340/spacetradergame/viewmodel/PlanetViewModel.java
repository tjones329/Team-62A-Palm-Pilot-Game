package cs2340.spacetradergame.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import cs2340.spacetradergame.model.Game;

public class PlanetViewModel extends AndroidViewModel {
    Game game = Game.getInstance();

    public PlanetViewModel(@NonNull Application application) {
        super(application);
    }

    public String getName() {
        return game.getCurrentPlanet().getName();
    }

    public String getTechLevel() {
        return game.getCurrentPlanet().getTechLevel().name();
    }
}
