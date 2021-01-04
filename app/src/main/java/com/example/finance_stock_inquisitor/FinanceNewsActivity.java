package com.example.finance_stock_inquisitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    /**
     * Goes Back to main.
     */
    private Button newsToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_news);

        searchNews = findViewById(R.id.search_news);
        mainNews = findViewById(R.id.main_news);
        secondaryNews1 = findViewById(R.id.secondary_news_1);
        secondaryNews2 = findViewById(R.id.secondary_news_2);
        newsToMain = findViewById(R.id.news_to_main);
        newsToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
