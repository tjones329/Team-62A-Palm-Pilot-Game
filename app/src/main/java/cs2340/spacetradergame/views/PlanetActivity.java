package cs2340.spacetradergame.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cs2340.spacetradergame.R;

public class PlanetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);

        //to get the current planet, call the method in the viewModel getCurrentPlanet
        //also, to get the prices, do planet.getMarket, and then call method calculateprices
    }
}
