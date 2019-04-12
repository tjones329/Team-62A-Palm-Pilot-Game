package cs2340.spacetradergame.entity;

import android.util.Log;

import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;
import java.util.List;

import cs2340.spacetradergame.model.Game;

public class CargoHold {
    private int capacity;
    private int currentFilled;
    private int[] items = new int[Game.ITEM_NUM];
    private List<Integer> saveItems = new ArrayList<>(Game.ITEM_NUM);

    public CargoHold() {

    }

    public CargoHold(int capacity) {
        this.capacity = capacity;
    }

    public void addCargo(List<Integer> items) {
        for (int i = 0; i < this.items.length; ++i) {
            this.items[i] += items.get(i);
            currentFilled += items.get(i);
        }
    }

    public void removeCargo(List<Integer> items) {
        for (int i = 0; i < this.items.length; ++i) {
            this.items[i] -= items.get(i);
            currentFilled -= items.get(i);
        }
    }

    public boolean hasItemNum(int itemId, int quantity) {
        return items[itemId] >= quantity;
    }

    public boolean canAdd(int cargo) {
        Log.d("Current cargo", String.valueOf(currentFilled));
        return currentFilled + cargo <= capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    @Exclude
    public int getCurrentFilled() {
        return currentFilled;
    }

    @Exclude
    public int[] getItems() {
        return items;
    }

    public List<Integer> getSaveItems() {
        saveItems.clear();
        for (int i : items) {
            saveItems.add(i);
        }
        return saveItems;
    }
}
