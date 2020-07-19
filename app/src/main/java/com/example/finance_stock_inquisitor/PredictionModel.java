package com.example.finance_stock_inquisitor;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * This is the interface for every model. This is so models can be easily swapped out while
 * guaranteeing general range of time periods to model stock prices.
 */
public interface PredictionModel {
    /**
     * Each method below returns a list of predicted stock prices based on input. This list varies
     * in size based on what time period of prediction is called. Each element represents a
     * prediction for an increment of time (eg. every 30 minutes).
     */

    /**
     * Based on model, calculates forecasts stock prices over the next 6 hours.
     * @param finData - The response from API on stock price data.
     * @return List of predicted stock prices for the next 6 hours.
     */
    ArrayList<Double> getNext6HourPredictedPrices(Gson finData);

    /**
     * Based on model, calculates forecasts stock prices over the next 12 hours.
     * @param finData - The response from API on stock price data.
     * @return List of predicted stock prices for the next 12 hours.
     */
    ArrayList<Double> getNext12HourPredictedPrices(Gson finData);

    /**
     * Based on model, calculates forecasts stock prices over the next 1 day.
     * @param finData - The response from API on stock price data.
     * @return List of predicted stock prices for the next 1 day.
     */
    ArrayList<Double> getNext1DayPredictedPrices(Gson finData);

    /**
     * Based on model, calculates forecasts stock prices over the next 1 week.
     * @param finData - The response from API on stock price data.
     * @return List of predicted stock prices for the next 1 week.
     */
    ArrayList<Double> getNext1WeekPredictedPrices(Gson finData);

    /**
     * Based on model, calculates forecasts stock prices over the next 1 month.
     * @param finData - The response from API on stock price data.
     * @return List of predicted stock prices for the next 1 month.
     */
    ArrayList<Double> getNext1MonthPredictedPrices(Gson finData);

    /**
     * Based on model, calculates forecasts stock prices over the of next 3 months.
     * @param finData - The response from API on stock price data.
     * @return List of predicted stock prices for the next 3 months.
     */
    ArrayList<Double> getNext3MonthPredictedPrices(Gson finData);
}
