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

        ((TextView) findViewById(R.id.textPlanetName)).setText(viewModel.getPlanetName());
        ((TextView) findViewById(R.id.textTechLevel)).setText(viewModel.getTechLevel());
        ((TextView) findViewById(R.id.textResources)).setText(viewModel.getResources());
        ((TextView) findViewById(R.id.textSystemName)).setText(viewModel.getSystemName());
        ((TextView) findViewById(R.id.textGovernment)).setText(viewModel.getGovernment());
        ((TextView) findViewById(R.id.textPolice)).setText(viewModel.getPolice());
        ((TextView) findViewById(R.id.textPirates)).setText(viewModel.getPirates());

        //to get the current planet, call the method in the viewModel getCurrentPlanet
        //also, to get the prices, do planet.getMarket, and then call method calculateprices
    }

    public void onTradePressed(View view) {
        Intent intent = new Intent(PlanetActivity.this, TradeActivity.class);
        startActivity(intent);
    }

    public void onPlayerPressed(View view) {
        Intent intent = new Intent(PlanetActivity.this, PlayerActivity.class);
        startActivity(intent);
    }

    public void onTravelPressed(View view) {
        Intent intent = new Intent(PlanetActivity.this, TradeActivity.class);
        startActivity(intent);
    }
}
