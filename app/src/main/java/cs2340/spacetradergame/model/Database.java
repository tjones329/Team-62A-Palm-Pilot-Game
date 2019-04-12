package cs2340.spacetradergame.model;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import cs2340.spacetradergame.views.MainActivity;

/**
 * database
 */
public class Database {
    public static final Database db = new Database();

    private final FirebaseFirestore database = FirebaseFirestore.getInstance();

    /**
     * saves game
     * @param game passed in game
     */
    public void saveGame(Game game) {
        database.collection("games").document("testgame").set(game);
    }

    /**
     * loads game with id and activity
     * @param gameID passed in gameId
     * @param activity passed id
     */
    public void loadGame(String gameID, final MainActivity activity) {
        Log.d("Load Game", "method started");
        database.collection("games").document(gameID).get().addOnSuccessListener(
                new OnSuccessListener<DocumentSnapshot>() {
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
