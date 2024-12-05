package com.example.project5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.AllOrders;
import model.Order;
import model.Pizza;

/**
 * Controller class for the PlacedOrders page
 * @author Vedant Varma
 * @author Jimmy Mishan
 */
public class PlacedOrdersActivity extends Activity {
    /**
     * Spinner for selecting an order by its ID
     */
    private Spinner orderSpinner;

    /**
     * ListView to display the list of pizzas in the selected order
     */
    private ListView pizzasListView;

    /**
     * TextView to display the total cost of the selected order
     */
    private TextView totalLabel;

    /**
     * ImageButton to navigate back to the home screen
     */
    private ImageButton homeButton;

    /**
     * Button to cancel the selected order
     */
    private Button cancelOrderButton;

    /**
     * Adapter for managing the list of pizzas displayed in the ListView
     */
    private ArrayAdapter<Pizza> pizzaAdapter;

    /**
     * Singleton instance of AllOrders, which manages all placed orders
     */
    private AllOrders allOrders;

    /**
     * Initializes the activity, sets up the layout, and then populates views
     * @param savedInstanceState the saved state of the activity, if there is any
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_orders);
        allOrders = AllOrders.getInstance();
        initializeViews();
        setupListView();
        populateOrderSpinner();
        setupButtons();
        updatePizzasListView();
        updateOrderTotals();

    }

    /**
     * Updates the total cost label for the currently selected order
     */
    private void updateOrderTotals() {
        Integer selectedOrderNumber = (Integer) orderSpinner.getSelectedItem();
        if (selectedOrderNumber != null) {
            Order selectedOrder = allOrders.getOrderByNumber(selectedOrderNumber);
            if (selectedOrder != null) {
                double total = selectedOrder.getTotal(); // Assuming getTotal() includes tax
                totalLabel.setText(String.format("Total: $%.2f", total));
            } else {
                totalLabel.setText("Total: $0.00");
            }
        } else {
            totalLabel.setText("Total: $0.00");
        }
    }

    /**
     * Sets up event listeners for the home and cancel order buttons.
     */
    private void setupButtons() {
        homeButton.setOnClickListener(v -> handleHomeButton());
        cancelOrderButton.setOnClickListener(v -> handleCancelButton());
    }

    /**
     * Handles the action of the home button to navigate to the MainActivity.
     */
    private void handleHomeButton() {
        Intent intent = new Intent(PlacedOrdersActivity.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Handles the action of the cancel button to remove the selected order
     */
    private void handleCancelButton() {
        Integer selectedOrderNumber = (Integer) orderSpinner.getSelectedItem();
        if (selectedOrderNumber != null) {
            Order toCancel = allOrders.getOrderByNumber(selectedOrderNumber);
            if (toCancel != null) {
                boolean removed = allOrders.cancelOrder(toCancel);
                if (removed) {
                    Toast.makeText(this, "Order canceled!", Toast.LENGTH_SHORT).show();
                    populateOrderSpinner(); // Refresh the Spinner
                    updatePizzasListView(); // Refresh the ListView
                    updateOrderTotals();    // Update the total label

                    // Disable cancel button if no orders remain
                    cancelOrderButton.setEnabled(!allOrders.getOrders().isEmpty());
                }
            }
        }
    }


    /**
     * Sets up the ListView for displaying pizzas, with an empty adapter initially.
     */
    private void setupListView() {
        // Initialize the pizzaAdapter with an empty list
        pizzaAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                new ArrayList<>());

        // Set the adapter to the ListView
        pizzasListView.setAdapter(pizzaAdapter);
    }

    /**
     * Updates the ListView with pizzas from the currently selected order.
     */
    private void updatePizzasListView() {
        Integer selectedOrderNumber = (Integer) orderSpinner.getSelectedItem();
        if (selectedOrderNumber != null) {
            Order selectedOrder = allOrders.getOrderByNumber(selectedOrderNumber);
            if (selectedOrder != null) {
                pizzaAdapter.clear();
                pizzaAdapter.addAll(selectedOrder.getPizzas());
                pizzaAdapter.notifyDataSetChanged();
            } else {
                pizzaAdapter.clear();
                pizzaAdapter.notifyDataSetChanged();
            }
        } else {
            pizzaAdapter.clear();
            pizzaAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Initializes the views and sets the initial state for UI elements
     */
    private void initializeViews() {
        orderSpinner = findViewById(R.id.orderSpinner);
        pizzasListView = findViewById(R.id.pizzasListView);
        totalLabel = findViewById(R.id.totalLabel);
        homeButton = findViewById(R.id.homeButton);
        cancelOrderButton = findViewById(R.id.cancelOrderButton);

        boolean isEmpty = allOrders.getOrders().isEmpty();
        cancelOrderButton.setEnabled(!isEmpty);
    }

    /**
     * Populates the Spinner with the IDs of all placed orders.
     */
    private void populateOrderSpinner() {
        // Get order IDs from AllOrders
        List<Integer> orderIds = new ArrayList<>();
        for (Order order : allOrders.getOrders()) {
            orderIds.add(order.getOrderNumber());
        }

        // Create an ArrayAdapter for the Spinner
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, orderIds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter to the Spinner
        orderSpinner.setAdapter(adapter);

        // Set listener to update the pizzas list when selected order changes
        orderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updatePizzasListView();
                updateOrderTotals();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pizzaAdapter.clear();
                totalLabel.setText("Total: $0.00");
            }
        });
    }


}
