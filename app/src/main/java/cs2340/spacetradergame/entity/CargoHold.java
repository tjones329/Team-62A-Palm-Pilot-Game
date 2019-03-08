package cs2340.spacetradergame.entity;

import android.util.Log;

import cs2340.spacetradergame.model.Game;

public class CargoHold {
    private int capacity;
    private int currentFilled = 0;
    private int[] items = new int[Game.ITEM_NUM];

    public CargoHold(int capacity) {
        this.capacity = capacity;
    }

    public void addCargo(int[] items) {
        for (int i = 0; i < this.items.length; ++i) {
            this.items[i] += items[i];
        }
    }

    public void removeCargo(int[] items) {
        for (int i = 0; i < this.items.length; ++i) {
            this.items[i] -= items[i];
        }
    }

    public boolean hasItemNum(int itemId, int quantity) {
        return items[itemId] >= quantity;
    }

    public int[] getCargo() {
        return items;
    }
    public int getCapacity() {
        return capacity;
    }

    public boolean canAdd(int cargo) {
        Log.d("Current cargo", String.valueOf(currentFilled));
        return currentFilled + cargo <= capacity;
    }
}
