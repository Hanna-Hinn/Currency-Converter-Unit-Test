package com.currencyexchange.currencyexchange.currency;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.LongAdder;

/*
*
*   Currency Controller Class
*
* */

@RestController
@RequestMapping(path = "api/v1/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @CrossOrigin
    @GetMapping("/")
    public List<Currency> getCurrencies() {
        return currencyService.getCurrencies();
    }

    @CrossOrigin
    @GetMapping("/shorthand")
    public List<String> getCurrenciesShortHand() { return currencyService.getCurrenciesShortHand();}

    @CrossOrigin
    @GetMapping("/exchange")
    public Double getExchangeRate(@RequestParam(name = "targetCurrency", required = true, defaultValue = "USD") String targetCurrency,
                                  @RequestParam(name = "sourceCurrency", required = true, defaultValue = "USD") String sourceCurrency,
                                  @RequestParam(name = "rateSource",required = true, defaultValue = "DB") String rateSource
                                  ) {
        try {
            return currencyService.getExchangeRate(rateSource.toUpperCase(),targetCurrency,sourceCurrency);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @CrossOrigin
    @GetMapping("/updateDB")
    public String updateDB(){
        try {
            return currencyService.saveAPItoDB();
        } catch (Exception e){
            throw e;
        }
    }

}
