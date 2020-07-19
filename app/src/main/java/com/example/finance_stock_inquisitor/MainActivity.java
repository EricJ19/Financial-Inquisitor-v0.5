package com.example.finance_stock_inquisitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // API is Alpha Vantage, used from https://www.alphavantage.co/documentation/.
        //Gson gson = new Gson();
    }
}
