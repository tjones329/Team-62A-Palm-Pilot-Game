package cs2340.spacetradergame.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cs2340.spacetradergame.R;
import cs2340.spacetradergame.viewmodel.PlanetViewModel;

public class PlanetActivity extends AppCompatActivity {
    PlanetViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_info);
        viewModel = ViewModelProviders.of(this).get(PlanetViewModel.class);

        TextView textPlanetName = findViewById(R.id.textDisplayPlanetName);
        textPlanetName.setText(viewModel.getName());

        TextView textTechLevel = findViewById(R.id.textDisplayTechLevel);
        textTechLevel.setText(viewModel.getTechLevel());

        //to get the current planet, call the method in the viewModel getCurrentPlanet
        //also, to get the prices, do planet.getMarket, and then call method calculateprices
    }

    public void onMarketPressed(View view) {
            Intent intent = new Intent(PlanetActivity.this, TradeActivity.class);
            startActivity(intent);
    }
}
