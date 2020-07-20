package com.example.finance_stock_inquisitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static java.lang.Double.parseDouble;

public class StockPredictingActivity extends AppCompatActivity {
    /**
     * API key used to retrieve data using Alpha Vantage from:
     * https://www.alphavantage.co/documentation/.
     */
    private final String API_KEY = "5X0MB8C04PRUD01A";
    /**
     * Key for API response to get the daily prices.
     */
    private final String DAILY_KEY = "Time Series (Daily)";
    /**
     * Key for API response to get opening prices.
     */
    private final String OPEN_PRICE_KEY = "1. open";
    /**
     * Stock prices to graph.
     */
    private LineGraphSeries<DataPoint> series;
    /**
     * Contains the daily opening prices of a particular stock, from oldest to most recent.
     */
    private ArrayList<Double> openingPrices;
    /**
     * Button to call API and put stock data into openingPrices.
     */
    private Button getStockData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_predicting);
        openingPrices = new ArrayList<>();
        getStockData = findViewById(R.id.get_stock_data);

        getStockData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStockData();
                drawStockGraph();

                // Clears old prices to allow new prices to fill it.
                openingPrices.clear();
            }
        });
    }

    /**
     * Gets the stock data to be used for forecasting.
     */
    private void getStockData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url
                = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=IBM&apikey="
                + API_KEY;

       JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
               new Response.Listener<JSONObject>() {
                   @Override
                   public void onResponse(JSONObject response) {
                       try {
                           JSONObject dailyStockPrices = response.getJSONObject(DAILY_KEY);
                           JSONArray dates = dailyStockPrices.names();

                           for (int i = 0; i < dates.length(); i++) {
                               String dateKey = dates.getString (i);

                               JSONObject stockPricesOnDay
                                       = dailyStockPrices.getJSONObject(dateKey);

                               double openingPriceDouble
                                       = stockPricesOnDay.getDouble(OPEN_PRICE_KEY);

                               openingPrices.add(0, openingPriceDouble);
                           }
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }
               }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               error.printStackTrace();
           }
       });

       queue.add(request);
    }

    /**
     * Draws the graph for actual and predicted stock prices.
     */
    public void drawStockGraph() {
        GraphView stockGraph = findViewById(R.id.stock_graph);

        stockGraph.getViewport().setMinX(0.0);
        stockGraph.getViewport().setMaxX(100.0);
        stockGraph.getViewport().setMinY(0.0);
        stockGraph.getViewport().setMaxY(500.0);

        stockGraph.getViewport().setYAxisBoundsManual(true);
        stockGraph.getViewport().setXAxisBoundsManual(true);

        // x - time
        // y - stock price in dollars
        double y, x;
        x = 0.0;
        series = new LineGraphSeries<>();

        for (int i = 0; i < openingPrices.size(); i++) {
            // 0.8 represents a time interval of 1 day.
            x = x + 0.8;
            y = openingPrices.get(i);

            series.appendData(new DataPoint(x, y), true, openingPrices.size());
        }
        stockGraph.addSeries(series);
    }
}
