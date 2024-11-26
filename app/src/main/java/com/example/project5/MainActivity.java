package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton chicagoPizzaButton = findViewById(R.id.chicagoPizzaButton);
        ImageButton nyPizzaButton = findViewById(R.id.nyPizzaButton);
        Button currentOrderButton = findViewById(R.id.currentOrderButton);
        Button placedOrdersButton = findViewById(R.id.placedOrdersButton);

        chicagoPizzaButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChicagoPizzaActivity.class);
            startActivity(intent);
        });

        nyPizzaButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NYPizzaActivity.class);
            startActivity(intent);
        });

        currentOrderButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CurrentOrderActivity.class);
            startActivity(intent);
        });

        placedOrdersButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PlacedOrdersActivity.class);
            startActivity(intent);
        });
    }
}