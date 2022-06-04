package me.hanhngo.mycurrencyconverter;

import java.util.HashMap;

public class Currency {
    private CurrencyName currencyName;
    private final HashMap<CurrencyName, Double> rates;

    public Currency(CurrencyName currencyName) {
        this.currencyName = currencyName;
        this.rates = generateRates();
    }

    public CurrencyName getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(CurrencyName currencyName) {
        this.currencyName = currencyName;
    }

    public HashMap<CurrencyName, Double> getRates() {
        return rates;
    }

    private HashMap<CurrencyName, Double> generateRates() {
        HashMap<CurrencyName, Double> rates = new HashMap<>();
        for (CurrencyName name : CurrencyName.values()) {
            if (this.currencyName == name) {
                rates.put(name, 1d);
            } else {
                rates.put(name, (double) Math.round(Math.random() * 3 * 100) / 100);
            }
        }
        return rates;
    }

    @Override
    public String toString() {
        return currencyName.toString();
    }
}