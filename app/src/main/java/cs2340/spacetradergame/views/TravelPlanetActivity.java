package cs2340.spacetradergame.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import cs2340.spacetradergame.R;
import cs2340.spacetradergame.entity.Planet;
import cs2340.spacetradergame.viewmodel.TravelPlanetViewModel;

/**
 * class
 */
public class TravelPlanetActivity extends AppCompatActivity {

    private Spinner planetSpinner;
    private TravelPlanetViewModel viewModel;
    private List<Planet> planets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_planet);

        viewModel = ViewModelProviders.of(this).get(TravelPlanetViewModel.class);

        planetSpinner = findViewById(R.id.planetSpinner);

        planets = viewModel.getPlanets();
        String[] planetArray = new String[planets.size()];

        int i = 0;
        for (Planet planet : planets) {
            planetArray[i++] = planet.getName();
        }

        ArrayAdapter<String> planetAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, planetArray);

        planetSpinner.setAdapter(planetAdapter);

        planetSpinner.setSelection(0);
    }

    /**
     * button
     * @param view View
     */
    public void onTravelPlanet(View view) {
        String selectedPlanet = (String) planetSpinner.getSelectedItem();
        for (Planet planet : planets) {
            if (planet.getName().equals(selectedPlanet)) {
                viewModel.setCurrentPlanet(planet);
                break;
            }
        }
        viewModel.travel();
        Intent intent = new Intent(TravelPlanetActivity.this, PlanetActivity.class);
        startActivity(intent);
    }
}
