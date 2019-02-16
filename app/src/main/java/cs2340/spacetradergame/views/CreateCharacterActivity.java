package cs2340.spacetradergame.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import cs2340.spacetradergame.R;

public class CreateCharacterActivity extends AppCompatActivity {
    public static final String[] LEVEL_MAP = {"Beginner", "Easy", "Normal", "Hard", "Impossible"};

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
        setContentView(R.layout.activity_create_character);

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
    }

    private int getSum() {
        return Integer.parseInt(textPilot.getText().toString())
                + Integer.parseInt(textFighter.getText().toString())
                + Integer.parseInt(textTrader.getText().toString())
                + Integer.parseInt(textEngineer.getText().toString());
    }

    private void subtract(TextView t) {
        int currentVal = Integer.parseInt(t.getText().toString());
        if (currentVal > 0) {
            t.setText(Integer.toString(currentVal - 1));
            textSkill.setText(Integer.toString(Integer.parseInt(textSkill.getText().toString()) + 1));
        } else {
            Toast.makeText(CreateCharacterActivity.this,
                    "Cannot have negative skill points", Toast.LENGTH_SHORT).show();
        }
    }
    private void add(TextView t) {
        int currentVal = Integer.parseInt(t.getText().toString());
        if (getSum() < cs2340.spacetradergame.model.Game.MAX_SKILL_POINTS) {
            t.setText(Integer.toString(currentVal + 1));
            textSkill.setText(Integer.toString(Integer.parseInt(textSkill.getText().toString()) - 1));
        } else {
            Toast.makeText(CreateCharacterActivity.this,
                    "No skill points to allocate", Toast.LENGTH_SHORT).show();
        }
    }

    public void onSubtractPilotPressed(View view) {
        subtract(textPilot);
    }
    public void onAddPilotPressed(View view) {
        add(textPilot);
    }
    public void onSubtractFighterPressed(View view) {
        subtract(textFighter);
    }
    public void onAddFighterPressed(View view) {
        add(textFighter);
    }
    public void onSubtractTraderPressed(View view) {
        subtract(textTrader);
    }
    public void onAddTraderPressed(View view) {
        add(textTrader);
    }
    public void onSubtractEngineerPressed(View view) {
        subtract(textEngineer);
    }
    public void onAddEngineerPressed(View view) {
        add(textEngineer);
    }

    public void onStartPressed(View view) {
        Toast.makeText(CreateCharacterActivity.this, "Start Pressed",
                Toast.LENGTH_SHORT).show();
    }

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
