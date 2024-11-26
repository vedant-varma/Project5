package com.example.project5;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import model.*;

/**
 * Activity for handling Chicago-style pizza ordering
 * Handles pizza customization, including topping and size select
 * @author Jimmy Mishan
 * @author Vedant Varma
 */
public class ChicagoPizzaActivity extends AppCompatActivity {
    private Spinner typeSpinner;
    private RadioGroup sizeGroup;
    private RadioButton smallButton, mediumButton, largeButton;
    private TextView crustLabel;
    private ListView availableToppingsListView;
    private ListView chosenToppingsListView;
    private Button selectToppingButton;
    private Button removeToppingButton;
    private Button addToOrderButton;
    private Pizza currentPizza;
    private final PizzaFactory pizzaFactory;
    private ArrayAdapter<Topping> availableToppingsAdapter;
    private ArrayAdapter<Topping> chosenToppingsAdapter;

    /**
     * Constructor initializes the pizza factory
     */
    public ChicagoPizzaActivity() {
        pizzaFactory = new ChicagoPizza();
    }

    /**
     * Gets the currently selected size from radio buttons
     * @return the selected Size enum value
     */
    private Size getSelectedSize() {
        if (smallButton.isSelected()) return Size.SMALL;
        if (mediumButton.isSelected()) return Size.MEDIUM;
        if (largeButton.isSelected()) return Size.LARGE;
        return Size.SMALL;
    }

    /**
     * Updates the price display based on current pizza configuration
     */
    private void updatePrice() {
        if (currentPizza != null) {
            double price = currentPizza.price();
            String priceText = String.format("$%.2f", price);
            Toast.makeText(this, "Price: " + priceText, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Initializes the activity, sets up UI components and event handlers
     * @param savedInstanceState saved state information
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicagopizza);

        initializeViews();
        setupSpinner();
        setupListViews();
        setupButtons();
        setupRadioGroup();
    }

    /**
     * Initializes all view references
     */
    private void initializeViews() {
        typeSpinner = findViewById(R.id.typeSpinner);
        sizeGroup = findViewById(R.id.radioGroup);
        smallButton = findViewById(R.id.smallButton);
        mediumButton = findViewById(R.id.mediumButton);
        largeButton = findViewById(R.id.largeButton);
        crustLabel = findViewById(R.id.crustLabel);
        availableToppingsListView = findViewById(R.id.availableToppingsListView);
        chosenToppingsListView = findViewById(R.id.chosenToppingsListView);
        selectToppingButton = findViewById(R.id.selectToppingButton);
        removeToppingButton = findViewById(R.id.removeToppingButton);
        addToOrderButton = findViewById(R.id.addToOrderButton);

        // Set initial state
        smallButton.setChecked(true);
        selectToppingButton.setEnabled(false);
        removeToppingButton.setEnabled(false);
        addToOrderButton.setEnabled(false);
    }

    /**
     * Sets up the pizza type spinner with available options
     */
    private void setupSpinner() {
        String[] pizzaTypes = {"Deluxe", "BBQ Chicken", "Meatzza", "Build Your Own"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, pizzaTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);

        typeSpinner.setOnItemSelectedListener(
                new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent,
                                       android.view.View view,
                                       int position,
                                       long id) {
                handlePizzaTypeSelection((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                // Do nothing if nothing selected
            }
        });
    }

    /**
     * Sets up the available and chosen toppings ListViews
     */
    private void setupListViews() {
        availableToppingsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, new ArrayList<>());
        chosenToppingsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, new ArrayList<>());

        availableToppingsListView.setAdapter(availableToppingsAdapter);
        chosenToppingsListView.setAdapter(chosenToppingsAdapter);
    }

    /**
     * Sets up the radio group for size selection
     */
    private void setupRadioGroup() {
        sizeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (currentPizza != null) {
                if (checkedId == R.id.smallButton) {
                    currentPizza.setSize(Size.SMALL);
                } else if (checkedId == R.id.mediumButton) {
                    currentPizza.setSize(Size.MEDIUM);
                } else if (checkedId == R.id.largeButton) {
                    currentPizza.setSize(Size.LARGE);
                }
                updatePrice();
            }
        });
    }

    /**
     * Sets up button click listeners
     */
    private void setupButtons() {
        selectToppingButton.setOnClickListener(v -> handleAddTopping());
        removeToppingButton.setOnClickListener(v -> handleRemoveTopping());
        addToOrderButton.setOnClickListener(v -> handleAddToOrder());
    }

    /**
     * Handles pizza type selection and updates the UI accordingly
     * @param selectedType the type of pizza selected
     */
    private void handlePizzaTypeSelection(String selectedType) {
        switch (selectedType) {
            case "Deluxe":
                currentPizza = pizzaFactory.createDeluxe();
                break;
            case "BBQ Chicken":
                currentPizza = pizzaFactory.createBBQChicken();
                break;
            case "Meatzza":
                currentPizza = pizzaFactory.createMeatzza();
                break;
            case "Build Your Own":
                currentPizza = pizzaFactory.createBuildYourOwn();
                selectToppingButton.setEnabled(true);
                removeToppingButton.setEnabled(true);
                break;
            default:
                currentPizza = null;
                return;
        }

        if (currentPizza != null) {
            currentPizza.setSize(getSelectedSize());
            crustLabel.setText("Crust: " + currentPizza.getCrust().toString());
            resetToppingsLists();
            addToOrderButton.setEnabled(true);
            updatePrice();
        }
    }

    /**
     * Handles adding selected topping to the pizza
     */
    private void handleAddTopping() {
        int position = availableToppingsListView.getCheckedItemPosition();
        if (position >= 0 && currentPizza != null) {
            Topping selectedTopping = availableToppingsAdapter.getItem(position);
            if (selectedTopping != null) {
                if (currentPizza.addToppingToPizza(selectedTopping)) {
                    availableToppingsAdapter.remove(selectedTopping);
                    chosenToppingsAdapter.add(selectedTopping);
                    updatePrice();
                    Toast.makeText(this, "Topping added", Toast.LENGTH_SHORT).show();
                } else {
                    showAlert("Maximum Toppings",
                            "Cannot add more than 7 toppings to a pizza.");
                }
            }
        }
    }

    /**
     * Handles removing a topping from the pizza
     */
    private void handleRemoveTopping() {
        int position = chosenToppingsListView.getCheckedItemPosition();
        if (position >= 0 && currentPizza != null) {
            Topping selectedTopping = chosenToppingsAdapter.getItem(position);
            if (selectedTopping != null) {
                currentPizza.removeToppingFromPizza(selectedTopping);
                chosenToppingsAdapter.remove(selectedTopping);
                availableToppingsAdapter.add(selectedTopping);
                updatePrice();
            }
        }
    }

    /**
     * Adds the current pizza to the current orders
     */
    private void handleAddToOrder() {
        if (currentPizza != null) {
            AllOrders.getInstance().getCurrentOrder().addPizzaToOrder(currentPizza);
            resetForm();
            Toast.makeText(this, "Pizza added to order", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Resets the topping lists based on current pizza
     */
    private void resetToppingsLists() {
        availableToppingsAdapter.clear();
        chosenToppingsAdapter.clear();

        // Add all toppings to available list
        for (Topping topping : Topping.values()) {
            availableToppingsAdapter.add(topping);
        }

        // Move pizza's toppings to chosen list
        if (currentPizza != null) {
            for (Topping topping : currentPizza.getToppings()) {
                availableToppingsAdapter.remove(topping);
                chosenToppingsAdapter.add(topping);
            }
        }
    }

    /**
     * Resets the form to its initial state
     */
    private void resetForm() {
        typeSpinner.setSelection(0);
        smallButton.setChecked(true);
        currentPizza = null;
        crustLabel.setText("Crust: ");
        resetToppingsLists();
        selectToppingButton.setEnabled(false);
        removeToppingButton.setEnabled(false);
        addToOrderButton.setEnabled(false);
    }

    /**
     * Shows an alert dialog with the given title and message
     * @param title the alert title
     * @param message the alert message
     */
    private void showAlert(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}