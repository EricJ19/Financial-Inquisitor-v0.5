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

    /**
     * Based on Last Value Model, calculates forecasts stock prices over the next 1 day.
     * @param stockPrices - The response from API on stock price data.
     * @return List of predicted stock prices for the next 1 day.
     */
    public ArrayList<Double> getNext1DayPredictedPrices(ArrayList<Double> stockPrices) {
        ArrayList<Double> predictedPrices = new ArrayList<>();
        predictedPrices.add(getLastValue(stockPrices));
        return predictedPrices;
    }

    /**
     * Based on Last Value Model, calculates forecasts stock prices over the next 1 week.
     * @param stockPrices - The response from API on stock price data.
     * @return List of predicted stock prices for the next 1 week.
     */
    public ArrayList<Double> getNext1WeekPredictedPrices(ArrayList<Double> stockPrices) {
        ArrayList<Double> predictedPrices = new ArrayList<>();
        int oneWeek = 7;
        return addForecastValues(predictedPrices, oneWeek, getLastValue(stockPrices));
    }

    /**
     * Based on Last Value Model, calculates forecasts stock prices over the next 1 month.
     * @param stockPrices - The response from API on stock price data.
     * @return List of predicted stock prices for the next 1 month.
     */
    public ArrayList<Double> getNext1MonthPredictedPrices(ArrayList<Double> stockPrices) {
        ArrayList<Double> predictedPrices = new ArrayList<>();
        // Approximately 30 days for one month.
        int oneMonth = 30;
        return addForecastValues(predictedPrices, oneMonth, getLastValue(stockPrices));
    }

    /**
     * Based on Last Value Model, calculates forecasts stock prices over the of next 3 months.
     * @param stockPrices - The response from API on stock price data.
     * @return List of predicted stock prices for the next 3 months.
     */
    public ArrayList<Double> getNext3MonthsPredictedPrices(ArrayList<Double> stockPrices) {
        ArrayList<Double> predictedPrices = new ArrayList<>();
        // Approximately 30 days per month.
        int threeMonths = 90;
        return addForecastValues(predictedPrices, threeMonths, getLastValue(stockPrices));
    }

    private double getLastValue(ArrayList<Double> stockPrices) {
        return stockPrices.get(stockPrices.size() - 1);
    }

    private ArrayList<Double> addForecastValues(ArrayList<Double> predictedPrices,
                                                int days,
                                                double lastValue) {
        for (int i = 0; i < days; i++) {
            predictedPrices.add(lastValue);
        }
        return predictedPrices;
    }

}
