package cs2340.spacetradergame.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import cs2340.spacetradergame.entity.Planet;
import cs2340.spacetradergame.entity.Player;
import cs2340.spacetradergame.model.Game;

public class NewGameViewModel extends AndroidViewModel {
    static Game game = Game.getInstance();

    public NewGameViewModel(@NonNull Application application) {
        super(application);
    }

    public void createGame(String name, int difficulty, int pilot, int fighter, int trader, int engineer) {
        game.setDifficulty(difficulty);
        game.setPlayer(new Player(name, pilot, fighter, trader, engineer));
        game.newUniverse();
    }

    public static Planet getCurrentPlanet() {
        return game.getCurrentPlanet();
    }

    public static Player getPlayer() {
        return game.getPlayer();
    }
}
