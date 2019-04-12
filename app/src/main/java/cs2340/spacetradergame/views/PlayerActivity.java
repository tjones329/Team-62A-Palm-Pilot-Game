package cs2340.spacetradergame.views;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cs2340.spacetradergame.R;
import cs2340.spacetradergame.viewmodel.PlayerViewModel;

/**
 * class
 */
public class PlayerActivity extends AppCompatActivity {
    private PlayerViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        viewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((TextView) findViewById(R.id.textPlayerName)).setText(viewModel.getPlayerName());
        ((TextView) findViewById(R.id.textCredits)).setText(String.valueOf(viewModel.getCredits()));
        ((TextView) findViewById(R.id.textPilot)).setText(String.valueOf(viewModel.getPilot()));
        ((TextView) findViewById(R.id.textFighter)).setText(String.valueOf(viewModel.getFighter()));
        ((TextView) findViewById(R.id.textTrader)).setText(String.valueOf(viewModel.getTrader()));
        ((TextView) findViewById(R.id.textEngineer)).setText(String.valueOf(
                viewModel.getEngineer()));

        ((TextView) findViewById(R.id.textHealth)).setText(String.valueOf(viewModel.getHealth()));
        ((TextView) findViewById(R.id.textSpeed)).setText(String.valueOf(viewModel.getSpeed()));
        ((TextView) findViewById(R.id.textHyperdrive)).setText(String.valueOf(
                viewModel.getHyperdrive()));
        ((TextView) findViewById(R.id.textDamage)).setText(String.valueOf(viewModel.getDamage()));
        ((TextView) findViewById(R.id.textCapacity)).setText(String.valueOf(
                viewModel.getCapacity()));
    }
}
