package com.currencyexchange.currencyexchange.currency;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="currency")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "shorthand")
    private String shortHand;
    @ElementCollection
    @CollectionTable(name = "exchange_rates", joinColumns = @JoinColumn(name = "currency_id"))
    @MapKeyColumn(name= "currency_code")
    @Column(name = "exchange_rate")
    private Map<String,Double> exchangeRates;


    public Currency() {
        exchangeRates = new HashMap<>();
    }

    public Currency( String shortHand, Map<String, Double> exchangeRates) {
        this.shortHand = shortHand;
        this.exchangeRates = exchangeRates;
    }

    public Currency(long id, String shortHand, Map<String, Double> exchangeRates) {
        this.id = id;
        this.shortHand = shortHand;
        this.exchangeRates = exchangeRates;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getShortHand() {
        return shortHand;
    }

    public void setShortHand(String shortHand) {
        this.shortHand = shortHand;
    }

    public Map<String, Double> getExchangeRates() {
        return exchangeRates;
    }

    public void setExchangeRates(Map<String, Double> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    public void addExchangeRate(String targetShortHand, Double rate){
        this.exchangeRates.put(targetShortHand, rate);
    }

    public void deleteExchangeRate(String targetShortHand){
        this.exchangeRates.remove(targetShortHand);
    }

    public void clearExchangeRate(){
        this.exchangeRates.clear();
    }

    public Double getTargetRate(String targetShortHand) {
        return this.exchangeRates.get(targetShortHand);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", shortHand='" + shortHand + '\'' +
                ", exchangeRates=" + exchangeRates +
                '}';
    }
}
