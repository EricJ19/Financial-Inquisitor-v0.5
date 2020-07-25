package com.example.finance_stock_inquisitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
import org.w3c.dom.Text;

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
    /**
     * User inputs for specific stock.
     */
    private EditText stockSymbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_predicting);
        openingPrices = new ArrayList<>();
        getStockData = findViewById(R.id.get_stock_data);
        stockSymbol = findViewById(R.id.stock_symbol);

        setGraphDimensions();

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
     * Sets the scale of the graph.
     * Called onCreate.
     */
    private void setGraphDimensions() {
        GraphView stockGraph = findViewById(R.id.stock_graph);

        stockGraph.getViewport().setMinX(0.0);
        stockGraph.getViewport().setMaxX(160.0);
        stockGraph.getViewport().setMinY(0.0);
        stockGraph.getViewport().setMaxY(100.0);

        stockGraph.getViewport().setYAxisBoundsManual(true);
        stockGraph.getViewport().setXAxisBoundsManual(true);
    }

    /**
     * Gets the opening prices of a particular stock for the last n days to be used for forecasting.
     * Depending on the call type n can be 100 or much more.
     */
    private void getStockData() {
        String userInputTicker = getFormattedUserInputTicker();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url
                = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="
                + userInputTicker + "&apikey=" + API_KEY;

       JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
               new Response.Listener<JSONObject>() {
                   @Override
                   public void onResponse(JSONObject response) {
                       try {
                           JSONObject dailyStockPrices = response.getJSONObject(DAILY_KEY);

                           // Dates from today till last n days for stock prices.
                           JSONArray dates = dailyStockPrices.names();

                           for (int i = 0; i < dates.length(); i++) {
                               // Dates are moving so iterate over the unknown dates.
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
     * Retrieves the input of ticker. Used to unique API calls.
     * @return - the user input in stockSymbol EditText.
     */
    private String getFormattedUserInputTicker() {
        return stockSymbol.getText().toString().toUpperCase();
    }

    /**
     * Draws the graph with stock data for actual and predicted stock prices.
     */
    public void drawStockGraph() {
        GraphView stockGraph = findViewById(R.id.stock_graph);

        // Finds max and min price to scale graph.
        double openingPricesMax = 0.0;
        for (int i = 0; i < openingPrices.size(); i++) {
            if (openingPricesMax < openingPrices.get(i)) {
                openingPricesMax = openingPrices.get(i);
            }
        }

        stockGraph.getViewport().setMaxY(openingPricesMax + 50.0);
        stockGraph.getViewport().setMinY(openingPricesMax - 50.0);

        // Remove previous plots.
        stockGraph.removeAllSeries();

        // x - time in days
        // y - stock price in dollars
        double x, y;
        x = 0.0;
        series = new LineGraphSeries<>();

        for (int i = 0; i < openingPrices.size(); i++) {
            // 1 unit horizontally represents 1 day.
            double oneDayInterval = 1.0;
            x = x + oneDayInterval;
            y = openingPrices.get(i);

            series.appendData(new DataPoint(x, y), true, openingPrices.size());
        }
        stockGraph.addSeries(series);
    }
}
