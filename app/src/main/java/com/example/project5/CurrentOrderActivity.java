package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import model.*;

/**
 * Activity for managing the current pizza order
 * Shows order details, allows removing pizzas and placing the order
 * @author Jimmy Mishan
 * @author Vedant Varma
 */
public class CurrentOrderActivity extends AppCompatActivity {
    private ListView pizzaListView;
    private TextView subtotalValue;
    private TextView salesTaxValue;
    private TextView orderTotalValue;
    private Button removePizzaButton;
    private Button placeOrderButton;
    private ArrayAdapter<Pizza> pizzaAdapter;
    private static final double SALES_TAX_RATE = 0.06625;
    private ImageButton homeButton;
    private AllOrders allOrders;
    private Order currentOrder;

    /**
     * Initializes the activity, sets up UI components and event handlers
     * @param savedInstanceState saved state information
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        allOrders = AllOrders.getInstance();
        currentOrder = allOrders.getCurrentOrder();

        initializeViews();
        setupListView();
        setupButtons();
        updateOrderTotals();
    }

    /**
     * Initializes all view references
     */
    private void initializeViews() {
        pizzaListView = findViewById(R.id.pizzaListView);
        subtotalValue = findViewById(R.id.subtotalValue);
        salesTaxValue = findViewById(R.id.salesTaxValue);
        orderTotalValue = findViewById(R.id.orderTotalValue);
        removePizzaButton = findViewById(R.id.removePizzaButton);
        placeOrderButton = findViewById(R.id.placeOrderButton);
        homeButton = findViewById(R.id.homeButton);

        // Disable buttons initially if order is empty
        boolean orderEmpty = AllOrders.getInstance().getCurrentOrder().getPizzas().isEmpty();
        removePizzaButton.setEnabled(!orderEmpty);
        placeOrderButton.setEnabled(!orderEmpty);
    }

    /**
     * Sets up the pizza ListView with its adapter
     */
    private void setupListView() {
        pizzaAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_single_choice,
                currentOrder.getPizzas());

        pizzaListView.setAdapter(pizzaAdapter);
        pizzaListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    /**
     * Sets up button click listeners
     */
    private void setupButtons() {
        removePizzaButton.setOnClickListener(v -> handleRemovePizza());
        placeOrderButton.setOnClickListener(v -> handlePlaceOrder());
        homeButton.setOnClickListener(v -> handleHomeButton());
    }

    /**
     * Handles removing selected pizza from the order
     */
    private void handleRemovePizza() {
        int position = pizzaListView.getCheckedItemPosition();
        if (position != ListView.INVALID_POSITION) {
            if (position < currentOrder.getPizzas().size()) {
                currentOrder.getPizzas().remove(position);
                pizzaAdapter.notifyDataSetChanged();
                updateOrderTotals();
                pizzaListView.clearChoices();
                Toast.makeText(this,
                        "Pizza removed from order",
                        Toast.LENGTH_SHORT).show();

                // Disable buttons if order becomes empty
                if (currentOrder.getPizzas().isEmpty()) {
                    removePizzaButton.setEnabled(false);
                    placeOrderButton.setEnabled(false);
                }
            }
        } else {
            showAlert("No Selection", "Please select a pizza to remove");
        }
    }

    /**
     * Handles placing the current order
     */
    private void handlePlaceOrder() {
        if (currentOrder.getPizzas().isEmpty()) {
            showAlert("Empty Order", "Cannot place an empty order");
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Order")
                .setMessage("Place this order?")
                .setPositiveButton("Yes", (dialog, id) -> {
                    // Place the order
                    allOrders.placeCurrentOrder();

                    // Update currentOrder reference
                    currentOrder = allOrders.getCurrentOrder();

                    // Reinitialize the adapter with the new currentOrder's pizzas list
                    pizzaAdapter = new ArrayAdapter<>(CurrentOrderActivity.this,
                            android.R.layout.simple_list_item_single_choice,
                            currentOrder.getPizzas());
                    pizzaListView.setAdapter(pizzaAdapter);

                    updateOrderTotals();

                    Toast.makeText(this,
                            "Order placed successfully",
                            Toast.LENGTH_SHORT).show();

                    removePizzaButton.setEnabled(false);
                    placeOrderButton.setEnabled(false);
                })
                .setNegativeButton("No", null)
                .show();
    }


    /**
     * Updates the display of order totals
     */
    private void updateOrderTotals() {
        double subtotal = currentOrder.getSubtotal();
        double tax = subtotal * SALES_TAX_RATE;
        double total = subtotal + tax;

        subtotalValue.setText(String.format("$%.2f", subtotal));
        salesTaxValue.setText(String.format("$%.2f", tax));
        orderTotalValue.setText(String.format("$%.2f", total));
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

    /**
     * Method called on Action for the home button, returns to home.
     */
    private void handleHomeButton() {
        Intent intent = new Intent(CurrentOrderActivity.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Updates the view when resuming the activity
     */
    @Override
    protected void onResume() {
        super.onResume();
        pizzaAdapter.notifyDataSetChanged();
        updateOrderTotals();

        // Update button states
        boolean orderEmpty = AllOrders.getInstance().getCurrentOrder().getPizzas().isEmpty();
        removePizzaButton.setEnabled(!orderEmpty);
        placeOrderButton.setEnabled(!orderEmpty);
    }
}