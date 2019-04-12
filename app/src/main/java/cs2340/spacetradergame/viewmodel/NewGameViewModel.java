package cs2340.spacetradergame.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import cs2340.spacetradergame.entity.Planet;
import cs2340.spacetradergame.entity.Player;
import cs2340.spacetradergame.model.Game;

/**
 * class
 */
public class NewGameViewModel extends AndroidViewModel {
    static Game game = Game.getInstance();

    /**
     *
     * @param application Application
     */
    public NewGameViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     *
     * @param name str
     * @param difficulty int
     * @param pilot int
     * @param fighter int
     * @param trader int
     * @param engineer int
     */
    public void createGame(String name, int difficulty, int pilot, int fighter, int trader, int engineer) {
        game.setDifficulty(difficulty);
        game.setPlayer(new Player(name, pilot, fighter, trader, engineer));
        game.newUniverse();
    }

    /**
     *
     * @return planet
     */
    public static Planet getCurrentPlanet() {
        return game.getCurrentPlanet();
    }

    /**
     *
     * @return player
     */
    public static Player getPlayer() {
        return game.getPlayer();
    }
}
