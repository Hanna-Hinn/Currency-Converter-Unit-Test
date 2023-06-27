package com.currencyexchange.currencyexchange.currency;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class CurrencyService {
    /*
    *   Each Service related to the currency is saved here
    *   This class identifies as a Service(component)
    * */

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }


    // getCurrencies : takes no params
    // return List with all currencies in the DB.
    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }

    // getCurrenciesShortHand: takes no params
    // returns List of strings containing the ShortHand of all the currencies in the DB.
    public List<String> getCurrenciesShortHand() {
        List<Currency> currencies = currencyRepository.findAll();
        List<String> shortHandList = new ArrayList<>();

        for (Currency i: currencies){
            shortHandList.add(i.getShortHand());
        }

        return shortHandList;

    }

    // getExchangeRate:
    // params: targetCurrency(string) --> The Want to convert to Currency
    //         sourceCurrency(string) --> The want to convert from currency
    // returns: a double value containing the rate from DB.
    public Double getExchangeRate(String rateSource,String targetCurrency,String sourceCurrency) throws Exception {

        if(rateSource == "DB"){
            Currency currency = currencyRepository.findByShortHand(sourceCurrency);
            if (currency.getExchangeRates().get(targetCurrency) != null){
                return currency.getExchangeRates().get(targetCurrency);
            }else {
                throw new Exception("Please Check the Target Currency");
            }
        }else {
            if (callAPI(sourceCurrency).get(targetCurrency) != null){
                return callAPI(sourceCurrency).get(targetCurrency);
            }else {
                throw new Exception("Please Check the Target Currency");
            }
        }
    }


    //API Related Methods

    //Call External API
    public HashMap<String,Double> callAPI(String sourceCurrencyCode) throws Exception {

            URL url = new URL("https://v6.exchangerate-api.com/v6/8a8617f7cf53c224d4fceeb7/latest/"+sourceCurrencyCode); // Replace with your API URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String jsonResponse = response.toString();
                JSONObject jsonObject = new JSONObject(jsonResponse);

                Gson gson = new Gson();
                JsonElement jsonElement = gson.fromJson(String.valueOf(jsonObject.getJSONObject("conversion_rates")), JsonElement.class);
                HashMap<String, Double> hashMap = gson.fromJson(jsonElement, HashMap.class);
                connection.disconnect();

                return hashMap;
            } else {
                connection.disconnect();
                throw new Exception("Error Code " + responseCode + " When Calling API" );
            }
    }


    //Save the Data from the APIs into the Database.
    public String saveAPItoDB(){
//        currencyRepository.deleteAll();
        try {
            HashMap<String,Double> map = callAPI("USD");

            List<String> list = new ArrayList<>();
            List<Currency> currencies = new ArrayList<>();

            for (String i : map.keySet()) {
                list.add(i);
            }
            for (String i: list) {
                Currency currency = new Currency(i, callAPI(i));
                currencies.add(currency);
            }


            currencyRepository.saveAll(currencies);
            currencyRepository.flush();
           return "DataBase Updated Successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Helper method to open URL connection
    public BufferedReader openUrlConnection(URL url) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            return new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            throw new Exception("Error Code " + responseCode + " When Calling API");
        }
    }

}
