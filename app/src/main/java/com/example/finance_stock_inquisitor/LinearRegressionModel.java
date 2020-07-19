package com.example.finance_stock_inquisitor;

import com.google.gson.Gson;

import java.util.ArrayList;

public class LinearRegressionModel implements PredictionModel {
    //TODO: Write implementations for following methods.
    /**
     * Based on Linear Regression, calculates forecasts stock prices over the next 6 hours.
     * @param finData - The response from API on stock price data.
     * @return List of predicted stock prices for the next 6 hours.
     */
    public ArrayList<Double> getNext6HourPredictedPrices(Gson finData) {
        return null;
    }

    /**
     * Based on Linear Regression, calculates forecasts stock prices over the next 12 hours.
     * @param finData - The response from API on stock price data.
     * @return List of predicted stock prices for the next 12 hours.
     */
    public ArrayList<Double> getNext12HourPredictedPrices(Gson finData) {
        return null;
    }

    /**
     * Based on Linear Regression, calculates forecasts stock prices over the next 1 day.
     * @param finData - The response from API on stock price data.
     * @return List of predicted stock prices for the next 1 day.
     */
    public ArrayList<Double> getNext1DayPredictedPrices(Gson finData) {
        return null;
    }

    /**
     * Based on Linear Regression, calculates forecasts stock prices over the next 1 week.
     * @param finData - The response from API on stock price data.
     * @return List of predicted stock prices for the next 1 week.
     */
    public ArrayList<Double> getNext1WeekPredictedPrices(Gson finData) {
        return null;
    }

    /**
     * Based on Linear Regression, calculates forecasts stock prices over the next 1 month.
     * @param finData - The response from API on stock price data.
     * @return List of predicted stock prices for the next 1 month.
     */
    public ArrayList<Double> getNext1MonthPredictedPrices(Gson finData) {
        return null;
    }

    /**
     * Based on Linear Regression, calculates forecasts stock prices over the of next 3 months.
     * @param finData - The response from API on stock price data.
     * @return List of predicted stock prices for the next 3 months.
     */
    public ArrayList<Double> getNext3MonthPredictedPrices(Gson finData) {
        return null;
    }
}
