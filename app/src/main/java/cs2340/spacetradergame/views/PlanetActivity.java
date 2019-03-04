package cs2340.spacetradergame.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cs2340.spacetradergame.R;
import cs2340.spacetradergame.model.Game;
import cs2340.spacetradergame.viewmodel.NewGameViewModel;

public class PlanetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_info);

        TextView textPlanetName = findViewById(R.id.textDisplayPlanetName);
        textPlanetName.setText(NewGameViewModel.getCurrentPlanet().getName());

        TextView textTechLevel = findViewById(R.id.textDisplayTechLevel);
        textTechLevel.setText(NewGameViewModel.getCurrentPlanet().getMarket().toString());

        //to get the current planet, call the method in the viewModel getCurrentPlanet
        //also, to get the prices, do planet.getMarket, and then call method calculateprices
    }

    public void onStartPressed(View view) {
            Intent intent = new Intent(PlanetActivity.this, BuyActivity.class);
            startActivity(intent);
    }
}
