package com.example.finance_stock_inquisitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class FinanceNewsActivity extends AppCompatActivity {
    /**
     * EditText to search for news.
     */
    private EditText searchNews;
    /**
     * Main/Key News to show.
     */
    private ImageView mainNews;
    /**
     * Secondary news, smaller than main news.
     */
    private ImageView secondaryNews1;
    /**
     * * Secondary news, smaller than main news.
     */
    private ImageView secondaryNews2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_news);

        searchNews = findViewById(R.id.searchNews);
        mainNews = findViewById(R.id.MainNews);
        secondaryNews1 = findViewById(R.id.SecondaryNews1);
        secondaryNews2 = findViewById(R.id.SecondaryNews2);
    }
}
