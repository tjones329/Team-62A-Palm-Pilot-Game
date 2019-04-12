package cs2340.spacetradergame.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cs2340.spacetradergame.R;
import cs2340.spacetradergame.model.Game;
import cs2340.spacetradergame.viewmodel.TradeViewModel;

/**
 * tradeactivity
 */
public class TradeActivity extends AppCompatActivity {
    private TradeViewModel viewModel;

    //Spaceship ship;
    //Player player;
    //Planet planet;
    //Market market;

    private TextView currentCreditsText;

    //Available display
    TextView[] availableTextViews;

    //Hold display
    TextView[] holdTextViews;

    //Quantity inputs
    private EditText[] quantityInputs;

    private ImageButton[] subtractButtons;
    private ImageButton[] addButtons;

    private Button transactButton;

    private List<Integer> prices;
    private List<Integer> quantities;

    /**
     * on create
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_goods);

        viewModel = ViewModelProviders.of(this).get(TradeViewModel.class);

        currentCreditsText = findViewById(R.id.textCurrentBalance);

        //Price displays
        TextView[] priceTextViews;
        priceTextViews = new TextView[Game.ITEM_NUM];
        priceTextViews[0] = findViewById(R.id.price0);
        priceTextViews[1] = findViewById(R.id.price1);
        priceTextViews[2] = findViewById(R.id.price2);
        priceTextViews[3] = findViewById(R.id.price3);
        priceTextViews[4] = findViewById(R.id.price4);
        priceTextViews[5] = findViewById(R.id.price5);
        priceTextViews[6] = findViewById(R.id.price6);
        priceTextViews[7] = findViewById(R.id.price7);
        priceTextViews[8] = findViewById(R.id.price8);
        priceTextViews[9] = findViewById(R.id.price9);

        availableTextViews = new TextView[Game.ITEM_NUM];
        availableTextViews[0] = findViewById(R.id.available0);
        availableTextViews[1] = findViewById(R.id.available1);
        availableTextViews[2] = findViewById(R.id.available2);
        availableTextViews[3] = findViewById(R.id.available3);
        availableTextViews[4] = findViewById(R.id.available4);
        availableTextViews[5] = findViewById(R.id.available5);
        availableTextViews[6] = findViewById(R.id.available6);
        availableTextViews[7] = findViewById(R.id.available7);
        availableTextViews[8] = findViewById(R.id.available8);
        availableTextViews[9] = findViewById(R.id.available9);

        holdTextViews = new TextView[Game.ITEM_NUM];
        holdTextViews[0] = findViewById(R.id.hold0);
        holdTextViews[1] = findViewById(R.id.hold1);
        holdTextViews[2] = findViewById(R.id.hold2);
        holdTextViews[3] = findViewById(R.id.hold3);
        holdTextViews[4] = findViewById(R.id.hold4);
        holdTextViews[5] = findViewById(R.id.hold5);
        holdTextViews[6] = findViewById(R.id.hold6);
        holdTextViews[7] = findViewById(R.id.hold7);
        holdTextViews[8] = findViewById(R.id.hold8);
        holdTextViews[9] = findViewById(R.id.hold9);

        quantityInputs = new EditText[Game.ITEM_NUM];
        quantityInputs[0] = findViewById(R.id.quantity0);
        quantityInputs[1] = findViewById(R.id.quantity1);
        quantityInputs[2] = findViewById(R.id.quantity2);
        quantityInputs[3] = findViewById(R.id.quantity3);
        quantityInputs[4] = findViewById(R.id.quantity4);
        quantityInputs[5] = findViewById(R.id.quantity5);
        quantityInputs[6] = findViewById(R.id.quantity6);
        quantityInputs[7] = findViewById(R.id.quantity7);
        quantityInputs[8] = findViewById(R.id.quantity8);
        quantityInputs[9] = findViewById(R.id.quantity9);

        subtractButtons = new ImageButton[Game.ITEM_NUM];
        subtractButtons[0] = findViewById(R.id.subtract0);
        subtractButtons[1] = findViewById(R.id.subtract1);
        subtractButtons[2] = findViewById(R.id.subtract2);
        subtractButtons[3] = findViewById(R.id.subtract3);
        subtractButtons[4] = findViewById(R.id.subtract4);
        subtractButtons[5] = findViewById(R.id.subtract5);
        subtractButtons[6] = findViewById(R.id.subtract6);
        subtractButtons[7] = findViewById(R.id.subtract7);
        subtractButtons[8] = findViewById(R.id.subtract8);
        subtractButtons[9] = findViewById(R.id.subtract9);

        addButtons = new ImageButton[Game.ITEM_NUM];
        addButtons[0] = findViewById(R.id.add0);
        addButtons[1] = findViewById(R.id.add1);
        addButtons[2] = findViewById(R.id.add2);
        addButtons[3] = findViewById(R.id.add3);
        addButtons[4] = findViewById(R.id.add4);
        addButtons[5] = findViewById(R.id.add5);
        addButtons[6] = findViewById(R.id.add6);
        addButtons[7] = findViewById(R.id.add7);
        addButtons[8] = findViewById(R.id.add8);
        addButtons[9] = findViewById(R.id.add9);

        prices = viewModel.getPrices();
        List<Integer> available = viewModel.getAvailable();
        quantities = new ArrayList<>(Game.ITEM_NUM);

        for (int i = 0; i < Game.ITEM_NUM; ++i) {
            quantities.add(0);
            priceTextViews[i].setText(String.valueOf(prices.get(i)));
            availableTextViews[i].setText(String.valueOf(available.get(i)));
            quantityInputs[i].setText("0");
            if (prices.get(i) == 0) {
                subtractButtons[i].setClickable(false);
                addButtons[i].setClickable(false);
                quantityInputs[i].setEnabled(false);
            } else {
                final int currentItem = i;
                quantityInputs[i].setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        Log.d("Focus status: ", String.valueOf(hasFocus));
                        if (!hasFocus) {
                            if (!isValidItemNum(currentItem,
                                    getNumber(quantityInputs[currentItem].getText().toString()))) {
                                Log.d("Input", "Invalid quantity");
                                reset(currentItem);
                            }
                        }
                    }
                });
                quantityInputs[i].addTextChangedListener(new TextWatcher() {
                    boolean wasReset;
                    @Override
                    public void afterTextChanged(Editable s) {}
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int before, int count) {
                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (wasReset) {
                            wasReset = false;
                            return;
                        }
                        if (quantityInputs[currentItem].isFocused()) {
                            return;
                        }
                        int newVal = getNumber(s);
                        if (isValidItemNum(currentItem, newVal)) {
                            quantities.set(currentItem, newVal);
                        } else {
                            wasReset = true;
                            reset(currentItem);
                        }
                    }
                });
            }
        }

        transactButton = findViewById(R.id.buttonTransact);

        updateCredits();
        updateHold();
    }

    /**
     * getter
     * @param s charsequence
     * @return number
     */
    private int getNumber(CharSequence s) {
        try {
            return Integer.valueOf(s.toString());
        } catch (NumberFormatException e) {
            Toast.makeText(TradeActivity.this,
                    "Invalid number", Toast.LENGTH_SHORT).show();
            return -1;
        }
    }

    /**
     * Determines if the integer in s is a valid number of MarketItem itemNum to buy or sell.
     * Populates newVal[0] with the value in s if it is valid
     * @param itemNum the number of the MarketItem
     * @param quantity the sequence representing the number of items
     * @return if the integer in s is valid
     */
    private boolean isValidItemNum(int itemNum, int quantity) {
        boolean invalid;
        if (invalid = (quantity < 0)) {
            Toast.makeText(TradeActivity.this,
                    "Cannot trade negative items", Toast.LENGTH_SHORT).show();
        } else if (viewModel.getBuySell()) { // Buy
            int cargo = 0;
            int total = 0;
            for (int j = 0; j < Game.ITEM_NUM; ++j) {
                if (itemNum == j) {
                    cargo += quantity;
                    total += quantity * prices.get(j);
                } else {
                    cargo += quantities.get(j);
                    total += quantities.get(j) * prices.get(j);
                }
            }
            if (invalid = !viewModel.canAdd(cargo)) {
                Toast.makeText(TradeActivity.this,
                        "You don't have enough cargo space", Toast.LENGTH_SHORT).show();
            } else if (invalid = !viewModel.canBuy(total)) {
                Toast.makeText(TradeActivity.this,
                        "You don't have enough credits", Toast.LENGTH_SHORT).show();
            }
        } else { // Sell
            if (invalid = !viewModel.canSell(itemNum, quantity)) {
                Toast.makeText(TradeActivity.this,
                        "You don't have enough of this item", Toast.LENGTH_SHORT).show();
            }
        }
        return !invalid;
    }

    /**
     * reset
     * @param itemId int
     */
    private void reset(int itemId) {
        Log.d("Action", "Reset");
        quantityInputs[itemId].setText(String.valueOf(quantities.get(itemId)));
    }

    /**
     * subtract
     * @param itemId int
     */
    public void subtract(int itemId) {
        quantityInputs[itemId].setText(String.valueOf(
                Integer.valueOf(quantityInputs[itemId].getText().toString()) - 1));
    }

    /**
     * add
     * @param itemId int
     */
    public void add(int itemId) {
        quantityInputs[itemId].setText(String.valueOf(
                Integer.valueOf(quantityInputs[itemId].getText().toString()) + 1));
    }

    /**
     * update cargo
     */
    private void updateHold() {
        int[] hold = viewModel.getHold();
        for (int i = 0; i < Game.ITEM_NUM; ++i) {
            holdTextViews[i].setText(String.valueOf(hold[i]));
        }
    }

    /**
     * update credits
     */
    public void updateCredits() {
        currentCreditsText.setText("Current Balance: " + viewModel.getCredits());
    }

    /**
     * subtract
     * @param view View
     */
    public void onSubtract0Pressed(View view) {
        subtract(0);
    }
    /**
     * subtract
     * @param view View
     */
    public void onSubtract1Pressed(View view) {
        subtract(1);
    }
    /**
     * subtract
     * @param view View
     */
    public void onSubtract2Pressed(View view) {
        subtract(2);
    }

    /**
     * subtract
     * @param view View
     */
    public void onSubtract3Pressed(View view) {
        subtract(3);
    }
    /**
     * subtract
     * @param view View
     */
    public void onSubtract4Pressed(View view) {
        subtract(4);
    }
    /**
     * subtract
     * @param view View
     */
    public void onSubtract5Pressed(View view) {
        subtract(5);
    }
    /**
     * subtract
     * @param view View
     */
    public void onSubtract6Pressed(View view) {
        subtract(6);
    }
    /**
     * subtract
     * @param view View
     */
    public void onSubtract7Pressed(View view) {
        subtract(7);
    }
    /**
     * subtract
     * @param view View
     */
    public void onSubtract8Pressed(View view) {
        subtract(8);
    }
    /**
     * subtract
     * @param view View
     */
    public void onSubtract9Pressed(View view) {
        subtract(9);
    }
    /**
     * subtract
     * @param view View
     */
    public void onAdd0Pressed(View view) {
        add(0);
    }
    /**
     * add
     * @param view View
     */
    public void onAdd1Pressed(View view) {
        add(1);
    }
    /**
     * add
     * @param view View
     */
    public void onAdd2Pressed(View view) {
        add(2);
    }
    /**
     * add
     * @param view View
     */
    public void onAdd3Pressed(View view) {
        add(3);
    }
    /**
     * add
     * @param view View
     */
    public void onAdd4Pressed(View view) {
        add(4);
    }
    /**
     * add
     * @param view View
     */
    public void onAdd5Pressed(View view) {
        add(5);
    }
    /**
     * add
     * @param view View
     */
    public void onAdd6Pressed(View view) {
        add(6);
    }
    /**
     * add
     * @param view View
     */
    public void onAdd7Pressed(View view) {
        add(7);
    }
    /**
     * add
     * @param view View
     */
    public void onAdd8Pressed(View view) {
        add(8);
    }
    /**
     * add
     * @param view View
     */
    public void onAdd9Pressed(View view) {
        add(9);
    }

    /**
     * transact
     * @param view View
     */
    public void onTransactPressed(View view) {
        viewModel.transact(quantities);
        clear();
        updateCredits();
        updateHold();
        /*
        boolean testAvailable = true;
        boolean testTechLevel = true;
        for (int i = 0; i < buy.length; i++) {
            if (prices[i] != 0 && (buy[i] > available[i])) {
                testAvailable = false;
            } else if (prices[i] == 0 && (buy[i] > available[i])) {
                testTechLevel = false;
            }
        }

        boolean testCapacity = true;
        int totalItems = 0;
        for (int i = 0; i < 10; i++) {
            totalItems += buy[i];
        }
        if (ship.getCargo().size() + totalItems > ship.getCapacity()) {
            testCapacity = false;
        }

        if (testAvailable && testTechLevel && testCapacity) {
            int totalPrice = 0;
            for (int i = 0; i < 10; i++) {
                totalPrice += (buy[i] * prices[i]);
            }
            if (totalPrice <= player.getCredits()) {
                player.setCredits(player.getCredits() - totalPrice);
                for (int i = 0; i < 10; i++) {
                    available[i] -= buy[i];
                }
                market.setQuantities(available);

                ship.addCargo(buy);
            } else {
                Toast.makeText(TradeActivity.this,
                        "You don't have enough money!", Toast.LENGTH_SHORT).show();
            }

        } else if (!testCapacity) {
            Toast.makeText(TradeActivity.this,
                    "You don't have enough cargo space", Toast.LENGTH_SHORT).show();
        } else if (!testAvailable) {
            Toast.makeText(TradeActivity.this,
                    "You don't have enough cargo space", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(TradeActivity.this,
                    "Planet does not produce this item", Toast.LENGTH_SHORT).show();
        }

        for (int i = 0; i < Game.ITEM_NUM; ++i) {
            availableTextViews[i].setText(available[0]);
        }*/
    }

    /*public void onSellPressed(View view) {
        int[] quantities = getQuantities();
        if (quantities != null) {
            if (!Market.sell(quantities)) {

            }
        }
    }*/

    /**
     * togglepress
     * @param view View
     */
    public void onTogglePressed(View view) {
        if (viewModel.toggle()) {
            transactButton.setText("Buy");
        } else {
            transactButton.setText("Sell");
        }
        clear();
    }

    /**
     * clear button
     * @param view View
     */
    public void onClearPressed(View view) {
        clear();
    }

    /**
     * clear
     */
    private void clear() {
        for (int i = 0; i < Game.ITEM_NUM; ++i) {
            quantityInputs[i].setText("0");
            quantities.set(i, 0);
        }
    }

    /**
     * back button
     * @param view View
     */
    public void onBackPressed(View view) {
        Intent intent = new Intent(TradeActivity.this, PlanetActivity.class);
        startActivity(intent);
    }
}
