package com.example.finance_stock_inquisitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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
     * The free API key used to retrieve data using Alpha Vantage from:
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
    /**
     * User selects forecast model.
     */
    private Spinner modelSelector;
    /**
     * User selects time period for forecast.
     */
    private Spinner timeSelector;
    /**
     * Button to predict stock prices based on past stock price data.
     */
    private Button predictStockPrices;
    /**
     * GraphView to display actual stock prices and forecast stock prices.
     */
    private GraphView stockGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_predicting);
        openingPrices = new ArrayList<>();
        stockSymbol = findViewById(R.id.stock_symbol);
        stockGraph = findViewById(R.id.stock_graph);

        setGraphDimensions();

        getStockData = findViewById(R.id.get_stock_data);
        getStockData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Solve known bug->graph only shows half the time.
                getStockData();
                drawStockGraph();

                // Clears old prices to allow new prices to fill it.
                // For some reason the data is retained when the graph is shown (half the time).
                openingPrices.clear();
            }
        });

        // Set up modelSelector spinner.
        modelSelector = findViewById(R.id.model_select);

        ArrayAdapter<CharSequence> modelsAdapter
                = ArrayAdapter.createFromResource(
                        this, R.array.models, android.R.layout.simple_spinner_item);

        modelsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelSelector.setAdapter(modelsAdapter);


        // Set up timeSelector spinner.
        timeSelector = findViewById(R.id.time_select);

        ArrayAdapter<CharSequence> timesAdapter
                = ArrayAdapter.createFromResource(
                        this, R.array.times, android.R.layout.simple_spinner_item);

        timesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSelector.setAdapter(timesAdapter);

        predictStockPrices = findViewById(R.id.predict_stocks);
        predictStockPrices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStockPrediction();
            }
        });
    }

    /**
     * Sets the scale of the graph.
     * Called in onCreate.
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

                           // The dates of the last 100 days are moving
                           // so iterate over the unknown dates.
                           for (int i = 0; i < dates.length(); i++) {
                               String dateKey = dates.getString (i);

                               JSONObject stockPricesOnDay
                                       = dailyStockPrices.getJSONObject(dateKey);

                               double openingPriceDouble
                                       = stockPricesOnDay.getDouble(OPEN_PRICE_KEY);

                               // Add the stock prices from back to front, most recent to oldest.
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
     * @return - the capitalized version of the user input in stockSymbol EditText.
     */
    private String getFormattedUserInputTicker() {
        return stockSymbol.getText().toString().toUpperCase();
    }

    /**
     * Draws the graph with stock data for actual and predicted stock prices.
     */
    public void drawStockGraph() {

        // Finds max and min price to scale graph.
        double openingPricesMax = 0.0;
        double openingPricesMin = 0.0;
        for (int i = 0; i < openingPrices.size(); i++) {
            if (openingPricesMax < openingPrices.get(i)) {
                openingPricesMax = openingPrices.get(i);
            }
            if (openingPricesMin > openingPrices.get(i)) {
                openingPricesMin = openingPrices.get(i);
            }
        }

        stockGraph.getViewport().setMaxY(openingPricesMax + 50.0);

        // If value is less than zero, set y-axis to zero because stocks cannot be negative.
        if (openingPricesMin - 50.0 < 0.0) {
            stockGraph.getViewport().setMinY(0.0);
        } else {
            stockGraph.getViewport().setMinY(openingPricesMin - 50.0);
        }

        // Remove previous plots.
        stockGraph.removeAllSeries();

        // x - time in days.
        // y - stock price in USD.
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

    /**
     * Uses the user selected model and time period to show the forecast of prices on the graph.
     */
    public void showStockPrediction() {
        String modelSelected = modelSelector.getSelectedItem().toString();
        String timeSelected = timeSelector.getSelectedItem().toString();
        drawPredictedGraph(modelSelected, timeSelected);
    }

    /**
     * Draws the predicted stock prices based on model and time period on the graph.
     * @param modelSelected the forecasting model selected by the user from modelSelector spinner.
     * @param timeSelected the forecasting time selected by the user from timeSelector spinner.
     */
    public void drawPredictedGraph(String modelSelected, String timeSelected) {
        PredictionModel model = getModel(modelSelected);
        // Forecasts are based on the model and time selected.
        ArrayList<Double> forecastedPrices = getForecastedPrices(model, timeSelected);

        // x - time in days.
        // y - stock price in USD.
        double x, y;
        // 101 is the last x position not filled from the stock data.
        x = 101;
        series = new LineGraphSeries<>();

        for (int i = 0; i < forecastedPrices.size(); i++) {
            // 1 unit horizontally represents 1 day.
            double oneDayInterval = 1.0;
            x = x + oneDayInterval;
            y = forecastedPrices.get(i);

            series.appendData(new DataPoint(x, y), true, forecastedPrices.size());
        }
        stockGraph.addSeries(series);
    }

    private PredictionModel getModel(String modelSelected) {
        switch (modelSelected) {
            case "Last Value":
                return new LastValueModel();
            case "Linear Regression":
                return new LinearRegressionModel();
            default:
                // Default is the Last Value Model which is used as a baseline for other models.
                return new LastValueModel();
        }

    }

    private ArrayList<Double> getForecastedPrices(PredictionModel model, String timeSelected) {
        switch (timeSelected) {
            case "1 Day":
                return model.getNext1DayPredictedPrices(openingPrices);
            case "1 Week":
                return model.getNext1WeekPredictedPrices(openingPrices);
            case "1 Month":
                return model.getNext1MonthPredictedPrices(openingPrices);
            case "3 Months":
                return model.getNext3MonthsPredictedPrices(openingPrices);
            default:
                // Default is maximum forecast of prices.
                return model.getNext3MonthsPredictedPrices(openingPrices);
        }
    }
}
