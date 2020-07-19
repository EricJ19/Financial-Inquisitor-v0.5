package com.example.finance_stock_inquisitor;

/**
 * This is a simple benchmark model which uses the last observed stock price as the
 * next predicted value.
 */
public class LastValueModel {
    /**
     *
     * @param latestPrice - The last observed price of a stock.
     * @return The predicted price of the stock in the next interval.
     */
    public double getNextPredictedPrice(double latestPrice) {
        return latestPrice;
    }
}
