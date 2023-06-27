package com.currencyexchange.currencyexchange;

import com.currencyexchange.currencyexchange.currency.Currency;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CurrencyTest {
    private Currency currency;

    @Before
    public void setUp() {
        currency = new Currency();
    }

    @Test
    public void testAddExchangeRate() {
        currency.addExchangeRate("USD", 1.0);
        currency.addExchangeRate("EUR", 0.9);

        assertEquals(2, currency.getExchangeRates().size());
        assertEquals(1.0, currency.getTargetRate("USD"), 0.0);
        assertEquals(0.9, currency.getTargetRate("EUR"), 0.0);
    }

    @Test
    public void testDeleteExchangeRate() {
        currency.addExchangeRate("USD", 1.0);
        currency.addExchangeRate("EUR", 0.9);
        currency.deleteExchangeRate("USD");

        assertNull(currency.getTargetRate("USD"));
        assertEquals(0.9, currency.getTargetRate("EUR"), 0.0);
    }

    @Test
    public void testClearExchangeRate() {
        currency.addExchangeRate("USD", 1.0);
        currency.addExchangeRate("EUR", 0.9);
        currency.clearExchangeRate();

        assertTrue(currency.getExchangeRates().isEmpty());
    }

    @Test
    public void testGetTargetRate() {
        currency.addExchangeRate("USD", 1.0);
        currency.addExchangeRate("EUR", 0.9);

        assertEquals(1.0, currency.getTargetRate("USD"), 0.0);
        assertEquals(0.9, currency.getTargetRate("EUR"), 0.0);
        assertNull(currency.getTargetRate("GBP"));
    }

    @Test
    public void testToString() {
        Map<String, Double> exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.9);

        currency = new Currency(1, "USD", exchangeRates);

        String expectedString = "Currency{id=1, shortHand='USD', exchangeRates={EUR=0.9, USD=1.0}}";
        assertEquals(expectedString, currency.toString());
    }
}