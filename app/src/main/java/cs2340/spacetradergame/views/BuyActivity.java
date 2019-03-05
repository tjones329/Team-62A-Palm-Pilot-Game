package cs2340.spacetradergame.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cs2340.spacetradergame.R;
import cs2340.spacetradergame.entity.Market;
import cs2340.spacetradergame.entity.Planet;
import cs2340.spacetradergame.entity.Player;
import cs2340.spacetradergame.viewmodel.NewGameViewModel;

public class BuyActivity extends AppCompatActivity {
    Player player;
    Planet planet;
    Market market;
    int[] prices;
    int[] available;

    //Price displays
    private TextView p1;
    private TextView p2;
    private TextView p3;
    private TextView p4;
    private TextView p5;
    private TextView p6;
    private TextView p7;
    private TextView p8;
    private TextView p9;
    private TextView p10;

    //#Available display
    private TextView a1;
    private TextView a2;
    private TextView a3;
    private TextView a4;
    private TextView a5;
    private TextView a6;
    private TextView a7;
    private TextView a8;
    private TextView a9;
    private TextView a10;

    //Quantity inputs
    private EditText q1;
    private EditText q2;
    private EditText q3;
    private EditText q4;
    private EditText q5;
    private EditText q6;
    private EditText q7;
    private EditText q8;
    private EditText q9;
    private EditText q10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_goods);

        player = NewGameViewModel.getPlayer();
        planet = NewGameViewModel.getCurrentPlanet();
        market = planet.getMarket();

        p1 = findViewById((R.id.p1));
        p2 = findViewById((R.id.p2));
        p3 = findViewById((R.id.p3));
        p4 = findViewById((R.id.p4));
        p5 = findViewById((R.id.p5));
        p6 = findViewById((R.id.p6));
        p7 = findViewById((R.id.p7));
        p8 = findViewById((R.id.p8));
        p9 = findViewById((R.id.p9));
        p10 = findViewById((R.id.p10));

        a1 = findViewById((R.id.a1));
        a2 = findViewById((R.id.a2));
        a3 = findViewById((R.id.a3));
        a4 = findViewById((R.id.a4));
        a5 = findViewById((R.id.a5));
        a6 = findViewById((R.id.a6));
        a7 = findViewById((R.id.a7));
        a8 = findViewById((R.id.a8));
        a9 = findViewById((R.id.a9));
        a10 = findViewById((R.id.a10));

        q1 = findViewById((R.id.q1));
        q2 = findViewById((R.id.q2));
        q3 = findViewById((R.id.q3));
        q4 = findViewById((R.id.q4));
        q5 = findViewById((R.id.q5));
        q6 = findViewById((R.id.q6));
        q7 = findViewById((R.id.q7));
        q8 = findViewById((R.id.q8));
        q9 = findViewById((R.id.q9));
        q10 = findViewById((R.id.q10));

        available = market.getQuantities();
        prices = market.getPrices();

        p1.setText(prices[0] + "");
        a1.setText(available[0] + "");

        p2.setText(prices[1] + "");
        a2.setText(available[1] + "");

        p3.setText(prices[2] + "");
        a3.setText(available[2] + "");

        p4.setText(prices[3] + "");
        a4.setText(available[3] + "");

        p5.setText(prices[4] + "");
        a5.setText(available[4] + "");

        p6.setText(prices[5] + "");
        a6.setText(available[5] + "");

        p7.setText(prices[6] + "");
        a7.setText(available[6] + "");

        p8.setText(prices[7] + "");
        a8.setText(available[7] + "");

        p9.setText(prices[8] + "");
        a9.setText(available[8] + "");

        p10.setText(prices[9] + "");
        a10.setText(available[9] + "");

        q1.setText(0 + "");
        q2.setText(0 + "");
        q3.setText(0 + "");
        q4.setText(0 + "");
        q5.setText(0 + "");
        q6.setText(0 + "");
        q7.setText(0 + "");
        q8.setText(0 + "");
        q9.setText(0 + "");
        q10.setText(0 + "");


        //to get the current planet, call the method in the viewModel getCurrentPlanet
        //also, to get the prices, do planet.getMarket, and then call method calculateprices
    }

    public void onBuyPressed(View view) {
        int[] buy = new int[10];

        buy[0] = Integer.parseInt(q1.getText().toString());
        buy[1] = Integer.parseInt(q2.getText().toString());
        buy[2] = Integer.parseInt(q3.getText().toString());
        buy[3] = Integer.parseInt(q4.getText().toString());
        buy[4] = Integer.parseInt(q5.getText().toString());
        buy[5] = Integer.parseInt(q6.getText().toString());
        buy[6] = Integer.parseInt(q7.getText().toString());
        buy[7] = Integer.parseInt(q8.getText().toString());
        buy[8] = Integer.parseInt(q9.getText().toString());
        buy[9] = Integer.parseInt(q10.getText().toString());

        boolean testAvailable = true;
        for (int i = 0; i < buy.length; i++) {
            if (buy[i] > available[i]) {
                testAvailable = false;
            }
        }

        if (testAvailable) {
            int totalPrice = 0;
            for (int i = 0; i < 10; i++) {
                totalPrice += (buy[i] * prices[i]);
            }
            if (totalPrice <= player.getCredits()) {
                player.setCredits(player.getCredits() - totalPrice);
                for (int i = 0; i < 10; i++) {
                    available[i]-= buy[i];
                }

            } else {
                Toast.makeText(BuyActivity.this,
                        "Cannot buy more than available", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(BuyActivity.this,
                    "Tech level not high enough, or cannot buy more than available", Toast.LENGTH_SHORT).show();
        }

        a1.setText(available[0] + "");
        a2.setText(available[1] + "");
        a3.setText(available[2] + "");
        a4.setText(available[3] + "");
        a5.setText(available[4] + "");
        a6.setText(available[5] + "");
        a7.setText(available[6] + "");
        a8.setText(available[7] + "");
        a9.setText(available[8] + "");
        a10.setText(available[9] + "");
    }

    public void onBackPressed(View view) {
        Intent intent = new Intent(BuyActivity.this, PlanetActivity.class);
        startActivity(intent);
    }
}
