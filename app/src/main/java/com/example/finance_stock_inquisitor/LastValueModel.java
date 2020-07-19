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
     * Based on Last Value Model, calculates forecasts stock prices over the next 6 hours.
     * @param finData - The response from API on stock price data.
     * @return List of predicted stock prices for the next 6 hours.
     */
    public ArrayList<Double> getNext6HourPredictedPrices(Gson finData) {
        return null;
    }

    /**
     * Based on Last Value Model, calculates forecasts stock prices over the next 12 hours.
     * @param finData - The response from API on stock price data.
     * @return List of predicted stock prices for the next 12 hours.
     */
    public ArrayList<Double> getNext12HourPredictedPrices(Gson finData) {
        return null;
    }

    /**
     * Based on Last Value Model, calculates forecasts stock prices over the next 1 day.
     * @param finData - The response from API on stock price data.
     * @return List of predicted stock prices for the next 1 day.
     */
    public ArrayList<Double> getNext1DayPredictedPrices(Gson finData) {
        return null;
    }

    /**
     * Based on Last Value Model, calculates forecasts stock prices over the next 1 week.
     * @param finData - The response from API on stock price data.
     * @return List of predicted stock prices for the next 1 week.
     */
    public ArrayList<Double> getNext1WeekPredictedPrices(Gson finData) {
        return null;
    }

    /**
     * Based on Last Value Model, calculates forecasts stock prices over the next 1 month.
     * @param finData - The response from API on stock price data.
     * @return List of predicted stock prices for the next 1 month.
     */
    public ArrayList<Double> getNext1MonthPredictedPrices(Gson finData) {
        return null;
    }

    /**
     * Based on Last Value Model, calculates forecasts stock prices over the of next 3 months.
     * @param finData - The response from API on stock price data.
     * @return List of predicted stock prices for the next 3 months.
     */
    public ArrayList<Double> getNext3MonthPredictedPrices(Gson finData) {
        return null;
    }

}