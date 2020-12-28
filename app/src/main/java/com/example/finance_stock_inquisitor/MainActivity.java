package com.example.finance_stock_inquisitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button predictStocks;
    private Button financeNews;

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

        financeNews = findViewById(R.id.finance_news);
        financeNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFinanceNewsActivity();
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

    /**
     * Changes activity from MainActivity to FinanceNews Activity.
     */
    public void openFinanceNewsActivity() {
        Intent intent = new Intent(this, FinanceNewsActivity.class);
        startActivity(intent);
    }
}
