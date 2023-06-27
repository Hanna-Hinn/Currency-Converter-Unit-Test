package com.currencyexchange.currencyexchange;

import com.currencyexchange.currencyexchange.currency.Currency;
import com.currencyexchange.currencyexchange.currency.CurrencyRepository;
import com.currencyexchange.currencyexchange.currency.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;


    private CurrencyService currencyService;

    @Mock
    private CurrencyService mockService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        currencyService = new CurrencyService(currencyRepository);
    }

    @Test
    public void testGetCurrencies() {

        List<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency("USD", new HashMap<>()));
        currencies.add(new Currency("EUR", new HashMap<>()));
        when(currencyRepository.findAll()).thenReturn(currencies);


        List<Currency> result = currencyService.getCurrencies();


        assertEquals(currencies, result);
        verify(currencyRepository, times(1)).findAll();
    }

    @Test
    public void testGetCurrenciesShortHand() {

        List<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency("USD", new HashMap<>()));
        currencies.add(new Currency("EUR", new HashMap<>()));
        when(currencyRepository.findAll()).thenReturn(currencies);


        List<String> result = currencyService.getCurrenciesShortHand();


        assertEquals(2, result.size());
        assertEquals("USD", result.get(0));
        assertEquals("EUR", result.get(1));
        verify(currencyRepository, times(1)).findAll();
    }

    @Test
    public void testGetExchangeRate_FromDB() throws Exception {

        Currency currency = new Currency("USD", new HashMap<>());
        currency.getExchangeRates().put("EUR", 1.5);
        when(currencyRepository.findByShortHand("USD")).thenReturn(currency);


        Double result = currencyService.getExchangeRate("DB", "EUR", "USD");


        assertEquals(1.5, result);
        verify(currencyRepository, times(1)).findByShortHand("USD");
    }


    @Test
    public void TestGetExchangeRate() throws Exception {
        // Arrange
        String rateSource = "DB";
        String targetCurrency = "EUR";
        String sourceCurrency = "USD";
        Currency currency = new Currency();
        currency.setShortHand(sourceCurrency);
        HashMap<String, Double> exchangeRates = new HashMap<>();
        exchangeRates.put(targetCurrency, 0.9242);
        currency.setExchangeRates(exchangeRates);

        // Stub the currencyRepository.findByShortHand() method to return the expected Currency
        Mockito.when(currencyRepository.findByShortHand(sourceCurrency)).thenReturn(currency);

        // Act
        Double actualDouble = currencyService.getExchangeRate(rateSource, targetCurrency, sourceCurrency);

        // Assert
        assertEquals(0.9242, actualDouble);
    }


    @Test
    public void testGetExchangeRate_InvalidTargetCurrency() {
        Currency currency = new Currency("USD", new HashMap<>());
        currency.getExchangeRates().put("EUR", 1.5);
        when(currencyRepository.findByShortHand("USD")).thenReturn(currency);


        Exception exception = assertThrows(Exception.class, () ->
                currencyService.getExchangeRate("DB", "GBP", "USD")
        );
        assertEquals("Please Check the Target Currency", exception.getMessage());
        verify(currencyRepository, times(1)).findByShortHand("USD");
    }


    @Test
    public void testCallAPI() throws Exception {
        // Arrange
        String sourceCurrencyCode = "USD";

        HashMap<String, Double> expectedHashMap = new HashMap<>();
        expectedHashMap.put("USD", 1d);
        expectedHashMap.put("AED", 3.6725);
        expectedHashMap.put("KZT", 447.1801);


        // Stub the callAPI() method to return the expected HashMap
        Mockito.when(mockService.callAPI(sourceCurrencyCode)).thenReturn(expectedHashMap);

        HashMap<String, Double> actualHashMap = currencyService.callAPI(sourceCurrencyCode);
        // Assert
        assertEquals(expectedHashMap.get("USD"), actualHashMap.get("USD"));
        assertEquals(expectedHashMap.get("AED"), actualHashMap.get("AED"));
        assertEquals(expectedHashMap.get("KZT"), actualHashMap.get("KZT"));
    }



    @Test
    public void testSaveAPItoDB() throws Exception {
        // Arrange
        HashMap<String, Double> map = new HashMap<>();
        map.put("EUR", 0.9242);
        map.put("GBP", 0.8417);

        List<String> list = new ArrayList<>();
        list.add("EUR");
        list.add("GBP");

        List<Currency> currencies = new ArrayList<>();
        for (String i : list) {
            Currency currency = new Currency();
            currency.setShortHand(i);
            HashMap<String, Double> exchangeRates = new HashMap<>();
            exchangeRates.put(i, 0.9242);
            currency.setExchangeRates(exchangeRates);
            currencies.add(currency);
        }

        // Stub the currencyRepository.saveAll() method to return the expected List
        Mockito.when(currencyRepository.saveAll(currencies)).thenReturn(currencies);

        // Act
        String actualString = currencyService.saveAPItoDB();

        // Assert
        assertEquals("DataBase Updated Successfully", actualString);
    }

}
