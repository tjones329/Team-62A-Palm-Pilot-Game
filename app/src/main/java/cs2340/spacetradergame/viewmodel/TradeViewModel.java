package cs2340.spacetradergame.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import cs2340.spacetradergame.entity.CargoHold;
import cs2340.spacetradergame.entity.Planet;
import cs2340.spacetradergame.entity.Player;
import cs2340.spacetradergame.model.Game;

public class TradeViewModel extends AndroidViewModel {
    private Planet currentPlanet;
    private Player player;
    private CargoHold hold;
    private boolean buySell = true; // true = Buy, false = Sell

    private int[] prices;

    public TradeViewModel (@NonNull Application application) {
        super(application);
        Game game = Game.getInstance();
        currentPlanet = game.getCurrentPlanet();
        prices = currentPlanet.getPrices();
        player = game.getPlayer();
        hold = player.getHold();
    }

    public void transact(int[] items) {
        int total = 0;
        for (int i = 0; i < Game.ITEM_NUM; ++i) {
            total += items[i] * prices[i];
        }
        if (buySell) {
            hold.addCargo(items);
            player.bought(total);
        } else {
            hold.removeCargo(items);
            player.sold(total);
        }
    }
    public int[] getPrices() {
        return prices;
    }

    public int[] getAvailable() {
        return currentPlanet.getQuantities();
    }

    public int[] getHold() {
        return hold.getCargo();
    }

    /**
     * Toggles buySell
     * @return the new buySell value
     */
    public boolean toggle() {
        return (buySell = !buySell);
    }

    public boolean getBuySell() {
        return buySell;
    }

    public boolean canAdd(int cargo) {
        return hold.canAdd(cargo);
    }
    public boolean canBuy(int total) {
        return player.getCredits() >= total;
    }
    public boolean canSell(int itemId, int quantity) {
        return hold.hasItemNum(itemId, quantity);
    }

    public int getCredits() {
        return player.getCredits();
    }
}
