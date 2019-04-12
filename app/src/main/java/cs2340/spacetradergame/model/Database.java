package cs2340.spacetradergame.model;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import cs2340.spacetradergame.views.MainActivity;
import cs2340.spacetradergame.views.PlanetActivity;

/**
 * database
 */
public class Database {
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    /**
     * saves game
     * @param game passed in game
     */
    public static void saveGame(Game game) {
        db.collection("games").document("testgame").set(game);
    }

    /**
     * loads game with id and activity
     * @param gameID passed in gameId
     * @param activity passed id
     */
    public static void loadGame(String gameID, final MainActivity activity) {
        Log.d("Load Game", "method started");
        db.collection("games").document(gameID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("Load Game", "Success");
                Game savedGame = documentSnapshot.toObject(Game.class);
                if (savedGame == null) {
                    Log.d("Load Game", "game was null");
                } else {
                    Game.loadInstance(savedGame);
                    activity.launchPlanetActivity();
                }
            }
        });
        Log.d("Load Game", "method finished");
    }
}
