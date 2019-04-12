package cs2340.spacetradergame.entity;

import android.util.Log;

import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import cs2340.spacetradergame.model.Game;

/**
 * CargoHold
 */
public class CargoHold {
    private int capacity;
    private int currentFilled;
    private int[] items = new int[Game.ITEM_NUM];
    private List<Integer> saveItems = new ArrayList<>(Game.ITEM_NUM);

    /**
     * Default constructor for cargohold
     */
    public CargoHold() {

    }

    /**
     * Alternative constructor with capacity aprameter
     * @param capacity tells us how many items we can have total
     */
    public CargoHold(int capacity) {
        this.capacity = capacity;
    }

    /**
     * adds cargo based on some items
     * @param items list of ints
     */
    public void addCargo(List<Integer> items) {
        if (items == null) {
            throw new NoSuchElementException("Error, items is null!");
        }
        if (items.size() != Game.ITEM_NUM) {
            throw new IllegalArgumentException("Error, incorrect size!");
        }
        for (int i = 0; i < this.items.length; ++i) {
            if (items.get(i) < 0) {
                throw new IllegalArgumentException("Error, cannot have negative value!");
            }
            this.items[i] += items.get(i);
            currentFilled += items.get(i);
        }
    }

    /**
     * removes from our array of items given an array
     * @param items an array of items we want to remove
     * @throws NoSuchElementException if items is null
     * @throws IllegalArgumentException if incorrect size or neg value
     */
    public void removeCargo(List<Integer> items) {
        if (items == null) {
            throw new NoSuchElementException("Error, items is null!");
        }
        if (items.size() != Game.ITEM_NUM) {
            throw new IllegalArgumentException("Error, incorrect size!");
        }
        for (int i = 0; i < this.items.length; ++i) {
            if (items.get(i) < 0) {
                throw new IllegalArgumentException("Error, cannot have negative value!");
            }
            this.items[i] -= items.get(i);
            currentFilled -= items.get(i);
        }
    }

    /**
     * determines if enough of an item is in the cargohold
     * @param itemId index of item
     * @param quantity checks if greater than this quantity
     * @return whether there is enough items
     */
    public boolean hasItemNum(int itemId, int quantity) {
        return items[itemId] >= quantity;
    }

    /**
     * determines if you can add some cargo
     * @param cargo size of adding array
     * @return whether you can add
     */
    public boolean canAdd(int cargo) {
        Log.d("Current cargo", String.valueOf(currentFilled));
        return currentFilled + cargo <= capacity;
    }

    /**
     * getter for capacity
     * @return int for capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     *
     * @return currentFilled
     */
    public int getCurrentFilled() {
        return currentFilled;
    }

    /**
     *
     * @return items
     */
    public int[] getItems() {
        return items;
    }

    /**
     *
     * @return list of savevd items
     */
    public List<Integer> getSaveItems() {
        saveItems.clear();
        for (int i : items) {
            saveItems.add(i);
        }
        return saveItems;
    }
}
