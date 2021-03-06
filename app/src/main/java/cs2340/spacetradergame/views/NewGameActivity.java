package cs2340.spacetradergame.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import cs2340.spacetradergame.R;
import cs2340.spacetradergame.model.Game;
import cs2340.spacetradergame.viewmodel.NewGameViewModel;

/**
 * class
 */
public class NewGameActivity extends AppCompatActivity {
    private static final String[] LEVEL_MAP = {"Beginner", "Easy", "Normal", "Hard", "Impossible"};

    private NewGameViewModel viewModel;

    private EditText textName;
    private SeekBar levelBar;
    private TextView textPilot;
    private TextView textFighter;
    private TextView textTrader;
    private TextView textEngineer;
    private TextView textSkill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        textName = findViewById(R.id.textName);
        levelBar = findViewById(R.id.levelBar);
        textPilot = findViewById(R.id.currentPilot);
        textFighter = findViewById(R.id.currentFighter);
        textTrader = findViewById(R.id.currentTrader);
        textEngineer = findViewById(R.id.currentEngineer);
        textSkill = findViewById(R.id.currentSkill);

        levelBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ((TextView) findViewById(R.id.currentLevel)).setText(LEVEL_MAP[progress]);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        viewModel = ViewModelProviders.of(this).get(NewGameViewModel.class);
    }

    /**
     * getter for sum
     * @return sum of points
     */
    private int getSum() {
        return Integer.parseInt(textPilot.getText().toString())
                + Integer.parseInt(textFighter.getText().toString())
                + Integer.parseInt(textTrader.getText().toString())
                + Integer.parseInt(textEngineer.getText().toString());
    }

    /**
     * text tto int
     * @param t TextView
     * @return text
     */
    private int textToInt(TextView t) {
        return Integer.parseInt(t.getText().toString());
    }

    /**
     * subtract
     * @param t TextView
     */
    private void subtract(TextView t) {
        int currentVal = textToInt(t);
        if (currentVal > 0) {
            t.setText(Integer.toString(currentVal - 1));
            textSkill.setText(Integer.toString(textToInt(textSkill) + 1));
        } else {
            Toast.makeText(NewGameActivity.this,
                    "Cannot have negative skill points", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * add to point
     * @param t Textview
     */
    private void add(TextView t) {
        int currentVal = textToInt(t);
        if (getSum() < cs2340.spacetradergame.model.Game.MAX_SKILL_POINTS) {
            t.setText(Integer.toString(currentVal + 1));
            textSkill.setText(Integer.toString(textToInt(textSkill) - 1));
        } else {
            Toast.makeText(NewGameActivity.this,
                    "No skill points to allocate", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * button
     * @param view View
     */
    public void onSubtractPilotPressed(View view) {
        subtract(textPilot);
    }

    /**
     *
     * @param view View
     */
    public void onAddPilotPressed(View view) {
        add(textPilot);
    }
    /**
     *
     * @param view View
     */
    public void onSubtractFighterPressed(View view) {
        subtract(textFighter);
    }
    /**
     *
     * @param view View
     */
    public void onAddFighterPressed(View view) {
        add(textFighter);
    }
    /**
     *
     * @param view View
     */
    public void onSubtractTraderPressed(View view) {
        subtract(textTrader);
    }
    /**
     *
     * @param view View
     */
    public void onAddTraderPressed(View view) {
        add(textTrader);
    }
    /**
     *
     * @param view View
     */
    public void onSubtractEngineerPressed(View view) {
        subtract(textEngineer);
    }
    /**
     *
     * @param view View
     */
    public void onAddEngineerPressed(View view) {
        add(textEngineer);
    }
    /**
     *
     * @param view View
     */
    public void onStartPressed(View view) {
        if(getSum() == Game.MAX_SKILL_POINTS) {
            viewModel.createGame(textName.getText().toString(), levelBar.getProgress(),
                    textToInt(textPilot), textToInt(textFighter), textToInt(textTrader),
                    textToInt(textEngineer));
            Intent intent = new Intent(NewGameActivity.this, PlanetActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(NewGameActivity.this, "Unallocated skill points",
                    Toast.LENGTH_SHORT).show();
        }
    }
    /**
     *
     * @param view View
     */
    public void onClearPressed(View view) {
        textName.setText("");
        levelBar.setProgress(2, true);
        textPilot.setText("0");
        textFighter.setText("0");
        textTrader.setText("0");
        textEngineer.setText("0");
        textSkill.setText("16");
    }
}
