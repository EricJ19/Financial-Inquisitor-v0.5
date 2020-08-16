package com.example.finance_stock_inquisitor;

import com.google.gson.Gson;

import java.util.ArrayList;

public class LinearRegressionModel implements PredictionModel {
    //TODO: Write implementations for following methods.

    /**
     * Based on Linear Regression, calculates forecasts stock prices over the next 1 day.
     * @param stockPrices - The response from API on stock price data.
     * @return List of predicted stock prices for the next 1 day.
     */
    public ArrayList<Double> getNext1DayPredictedPrices(ArrayList<Double> stockPrices) {
        return null;
    }

    /**
     * Based on Linear Regression, calculates forecasts stock prices over the next 1 week.
     * @param stockPrices - The response from API on stock price data.
     * @return List of predicted stock prices for the next 1 week.
     */
    public ArrayList<Double> getNext1WeekPredictedPrices(ArrayList<Double> stockPrices) {
        return null;
    }

    /**
     * Based on Linear Regression, calculates forecasts stock prices over the next 1 month.
     * @param stockPrices - The response from API on stock price data.
     * @return List of predicted stock prices for the next 1 month.
     */
    public ArrayList<Double> getNext1MonthPredictedPrices(ArrayList<Double> stockPrices) {
        return null;
    }

    /**
     * Based on Linear Regression, calculates forecasts stock prices over the of next 3 months.
     * @param stockPrices - The response from API on stock price data.
     * @return List of predicted stock prices for the next 3 months.
     */
    public ArrayList<Double> getNext3MonthsPredictedPrices(ArrayList<Double> stockPrices) {
        return null;
    }
}
