package cs2340.spacetradergame.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Set;

import cs2340.spacetradergame.R;
import cs2340.spacetradergame.entity.Planet;
import cs2340.spacetradergame.entity.SolarSystem;
import cs2340.spacetradergame.viewmodel.PlanetViewModel;

public class TravelSolarSystemActivity extends AppCompatActivity {

    private Spinner solarSpinner;
    private PlanetViewModel viewModel;
    private Set<SolarSystem> systems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_solar_system);

        viewModel = ViewModelProviders.of(this).get(PlanetViewModel.class);

        solarSpinner = findViewById(R.id.solarSpinner);

        systems = viewModel.getSystems();
        String[] systemArray = new String[systems.size()];

        int i = 0;
        for (SolarSystem system : systems) {
            systemArray[i++] = system.getName();
        }

        ArrayAdapter<String> solarAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, systemArray);

        solarSpinner.setAdapter(solarAdapter);

        solarSpinner.setSelection(0);
    }

    public void onTravelSolarSystem(View view) {
        String selectedSystem = (String) solarSpinner.getSelectedItem();
        for (SolarSystem system : systems) {
            if (system.getName().equals(selectedSystem)) {
                viewModel.setCurrentSystem(system);
                break;
            }
        }
        Intent intent = new Intent(TravelSolarSystemActivity.this, TravelPlanetActivity.class);
        startActivity(intent);
    }
}