package com.alex.eko.paragon.receipt.domain.enums;

public enum Currency {
    PLN, UA, EUR;


    public static Currency getCurrencyFromString(String currencyStr) {
        if (currencyStr == null) {
            throw new IllegalArgumentException("Currency string cannot be null");
        }

        try {
            return Currency.valueOf(currencyStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("No enum constant for currency: " + currencyStr);
        }
    }

}
