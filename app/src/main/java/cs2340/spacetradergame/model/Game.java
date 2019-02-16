package cs2340.spacetradergame.model;

import android.util.Log;

import cs2340.spacetradergame.entity.Player;

public class Game {
    public static final int MAX_SKILL_POINTS = 16;
    private static Game instance = new Game();

    private int difficulty;
    private Player player;

    public static Game getInstance() {
        return instance;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public Player getPlayer() {
        return player;
    }

    public void setDifficulty(int difficulty) {
        Log.d("Difficulty set", String.valueOf(difficulty));
        this.difficulty = difficulty;
    }

    public void setPlayer(Player player) {
        Log.d("Player set", player.toString());
        this.player = player;
    }
}
