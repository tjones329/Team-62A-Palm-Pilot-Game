package cs2340.spacetradergame.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import cs2340.spacetradergame.entity.Player;
import cs2340.spacetradergame.entity.Spaceship;
import cs2340.spacetradergame.model.Game;

public class PlayerViewModel extends AndroidViewModel {
    private Player player;
    private Spaceship ship;

    public PlayerViewModel(@NonNull Application application) {
        super(application);
        Game game = Game.getInstance();
        player = game.getPlayer();
        ship = player.getShip();
    }

    public String getPlayerName() {
        return player.getName();
    }

    public int getCredits() {
        return player.getCredits();
    }

    public int getPilot() {
        return player.getPilotPoints();
    }

    public int getFighter() {
        return player.getFighterPoints();
    }

    public int getTrader() {
        return player.getTraderPoints();
    }

    public int getEngineer() {
        return player.getEngineerPoints();
    }

    public int getHealth() {
        return ship.getHealth();
    }

    public int getSpeed() {
        return ship.getSpeed();
    }

    public int getHyperdrive() {
        return ship.getHyperdrive();
    }

    public int getDamage() {
        return ship.getDamage();
    }

    public int getCapacity() {
        return ship.getCapacity();
    }

}
