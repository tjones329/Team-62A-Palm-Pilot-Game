package cs2340.spacetradergame.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import cs2340.spacetradergame.entity.Player;
import cs2340.spacetradergame.entity.Spaceship;
import cs2340.spacetradergame.model.Game;

/**
 * class
 */
public class PlayerViewModel extends AndroidViewModel {
    private Player player;
    private Spaceship ship;

    /**
     * constructor
     * @param application Application
     */
    public PlayerViewModel(@NonNull Application application) {
        super(application);
        Game game = Game.getInstance();
        player = game.getPlayer();
        ship = player.getShip();
    }

    /**
     *
     * @return name
     */
    public String getPlayerName() {
        return player.getName();
    }

    /**
     *
     * @return creds
     */
    public int getCredits() {
        return player.getCredits();
    }

    /**
     *
     * @return pp
     */
    public int getPilot() {
        return player.getPilotPoints();
    }

    /**
     *
     * @return fp
     */
    public int getFighter() {
        return player.getFighterPoints();
    }

    /**
     *
     * @return tp
     */
    public int getTrader() {
        return player.getTraderPoints();
    }

    /**
     *
     * @return ep
     */
    public int getEngineer() {
        return player.getEngineerPoints();
    }

    /**
     *
     * @return h
     */
    public int getHealth() {
        return ship.getHealth();
    }

    /**
     *
     * @return s
     */
    public int getSpeed() {
        return ship.getSpeed();
    }

    /**
     *
     * @return hd
     */
    public int getHyperdrive() {
        return ship.getHyperdrive();
    }

    /**
     *
     * @return d
     */
    public int getDamage() {
        return ship.getDamage();
    }

    /**
     *
     * @return c
     */
    public int getCapacity() {
        return ship.getCapacity();
    }

}
