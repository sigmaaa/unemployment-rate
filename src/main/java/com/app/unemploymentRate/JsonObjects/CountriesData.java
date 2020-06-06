package com.app.unemploymentRate.JsonObjects;

import lombok.Data;

import java.util.HashMap;

@Data
public class CountriesData {

    private String latestYear;

    HashMap<String, Double> countriesWithLowestUR;
    HashMap<String, Double> countriesWithHighestUR;
    HashMap<String, Double> allCountriesStat;

    public CountriesData(String latestYear, HashMap<String, Double> countriesWithLowestUR,
                         HashMap<String, Double> countriesWithHighestUR, HashMap<String, Double> allCountriesStat) {

       this.countriesWithHighestUR = countriesWithHighestUR;
       this.countriesWithLowestUR = countriesWithLowestUR;
       this.allCountriesStat = allCountriesStat;
       this.latestYear = latestYear;
    }

}
