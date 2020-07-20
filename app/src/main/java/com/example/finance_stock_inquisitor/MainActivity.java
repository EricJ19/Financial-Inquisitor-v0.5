package com.example.finance_stock_inquisitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.*;

public class MainActivity extends AppCompatActivity {
    private Button predictStocks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        predictStocks = findViewById(R.id.pred_stocks);
        predictStocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStockPredictingActivity();
            }
        });
    }

    /**
     * Changes activity from MainActivity to StockPredicting Activity.
     */
    public void openStockPredictingActivity() {
        Intent intent = new Intent(this, StockPredictingActivity.class);
        startActivity(intent);
    }
}
