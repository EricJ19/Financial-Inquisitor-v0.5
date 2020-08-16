package com.example.finance_stock_inquisitor;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * This is a simple benchmark model which uses the last observed stock price as the
 * next predicted values.
 */
public class LastValueModel implements PredictionModel {
    /**
     * ALl methods use the last observed value from finData as the next predicted values.
     */

    //TODO: Write implementations for following methods.

    /**
     * Based on Last Value Model, calculates forecasts stock prices over the next 1 day.
     * @param stockPrices - The response from API on stock price data.
     * @return List of predicted stock prices for the next 1 day.
     */
    public ArrayList<Double> getNext1DayPredictedPrices(ArrayList<Double> stockPrices) {
        return null;
    }

    /**
     * Based on Last Value Model, calculates forecasts stock prices over the next 1 week.
     * @param stockPrices - The response from API on stock price data.
     * @return List of predicted stock prices for the next 1 week.
     */
    public ArrayList<Double> getNext1WeekPredictedPrices(ArrayList<Double> stockPrices) {
        return null;
    }

    /**
     * Based on Last Value Model, calculates forecasts stock prices over the next 1 month.
     * @param stockPrices - The response from API on stock price data.
     * @return List of predicted stock prices for the next 1 month.
     */
    public ArrayList<Double> getNext1MonthPredictedPrices(ArrayList<Double> stockPrices) {
        return null;
    }

    /**
     * Based on Last Value Model, calculates forecasts stock prices over the of next 3 months.
     * @param stockPrices - The response from API on stock price data.
     * @return List of predicted stock prices for the next 3 months.
     */
    public ArrayList<Double> getNext3MonthsPredictedPrices(ArrayList<Double> stockPrices) {
        return null;
    }

    /**
     * Helper method to get the last value (most recent) of stock price.
     * @param stockPrices - The response from API on stock price data.
     * @return most recent actual stock price.
     */
    private double getLastValue(ArrayList<Double> stockPrices) {
        return stockPrices.get(stockPrices.size() - 1);
    }

}
