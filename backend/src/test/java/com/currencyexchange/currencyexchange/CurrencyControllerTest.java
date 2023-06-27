package com.currencyexchange.currencyexchange;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CurrencyControllerTest {

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    void testGetAllCurrencies() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/v1/currency/", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().indexOf("["));
    }

    @Test
    void testGetAllCurrencyAbbreviations() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/v1/currency/shorthand", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().indexOf("["));
    }

    @Test
    void testGetExchangeRateFromAPI() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/v1/currency/exchange?targetCurrency=ILS&sourceCurrency=USD&rateSource=API", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(Double.parseDouble(response.getBody()) > 0);
    }

    @Test
    void testGetExchangeRateFromDB() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/v1/currency/exchange?targetCurrency=ILS&sourceCurrency=USD&rateSource=DB", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(Double.parseDouble(response.getBody()) > 0);
    }

    @Test
    void testUpdateDatabase() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/v1/currency/updateDB", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("DataBase Updated Successfully", response.getBody());
    }
}
