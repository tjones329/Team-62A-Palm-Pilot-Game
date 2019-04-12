package cs2340.spacetradergame.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import cs2340.spacetradergame.entity.CargoHold;
import cs2340.spacetradergame.entity.Planet;
import cs2340.spacetradergame.entity.Player;
import cs2340.spacetradergame.model.Game;

/**
 * class
 */
public class TradeViewModel extends AndroidViewModel {
    private Planet currentPlanet;
    private Player player;
    private CargoHold hold;
    private boolean buySell = true; // true = Buy, false = Sell

    private List<Integer> prices;

    /**
     * constructor
     * @param application Application
     */
    public TradeViewModel (@NonNull Application application) {
        super(application);
        Game game = Game.getInstance();
        currentPlanet = game.getCurrentPlanet();
        prices = currentPlanet.getPrices();
        player = game.getPlayer();
        hold = player.getHold();
    }

    /**
     * transact
     * @param items List<Integer></Integer>
     */
    public void transact(List<Integer> items) {
        int total = 0;
        for (int i = 0; i < Game.ITEM_NUM; ++i) {
            total += items.get(i) * prices.get(i);
        }
        //@TODO change planet values
        if (buySell) {
            hold.addCargo(items);
            player.bought(total);
        } else {
            hold.removeCargo(items);
            player.sold(total);
        }
    }

    /**
     *
     * @return prices
     */
    public List<Integer> getPrices() {
        return prices;
    }

    /**
     *
     * @return quantities
     */
    public List<Integer> getAvailable() {
        return currentPlanet.getQuantities();
    }

    /**
     *
     * @return hold
     */
    public int[] getHold() {
        return hold.getItems();
    }

    /**
     * Toggles buySell
     * @return the new buySell value
     */
    public boolean toggle() {
        return (buySell = !buySell);
    }

    /**
     *
     * @return busell
     */
    public boolean getBuySell() {
        return buySell;
    }

    /**
     *
     * @param cargo int
     * @return boolean
     */
    public boolean canAdd(int cargo) {
        return hold.canAdd(cargo);
    }

    /**
     *
     * @param total int
     * @return boolean
     */
    public boolean canBuy(int total) {
        return player.getCredits() >= total;
    }

    /**
     *
     * @param itemId int
     * @param quantity int
     * @return boolean
     */
    public boolean canSell(int itemId, int quantity) {
        return hold.hasItemNum(itemId, quantity);
    }

    /**
     *
     * @return credits
     */
    public int getCredits() {
        return player.getCredits();
    }
}
