package cs2340.spacetradergame.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import cs2340.spacetradergame.R;
import cs2340.spacetradergame.model.Game;

/**
 * class
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * button
     * @param view View
     */
    public void onStartPressed(View view) {
        boolean savedGame = false;
        File appDir = getApplicationContext().getFilesDir();
        for (String s : appDir.list()) {
            if ("gameid.txt".equals(s)) {
                Game.loadGame("testgame", MainActivity.this);
                savedGame = true;
                break;
            }
        }
        if (!savedGame) {
            File saveFile = new File(appDir, "gameid.txt");
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(saveFile);
                pw.println("testgame");
            } catch (FileNotFoundException e) {
                Log.d("Save Game", "Couldn't save game");
            } finally {
                if (pw != null) {
                    pw.close();
                }
            }
            Intent intent = new Intent(MainActivity.this, NewGameActivity.class);
            startActivity(intent);
        }
    }

    /**
     * launch activity
     */
    public void launchPlanetActivity() {
        Intent intent = new Intent(MainActivity.this, PlanetActivity.class);
        startActivity(intent);
    }
}
