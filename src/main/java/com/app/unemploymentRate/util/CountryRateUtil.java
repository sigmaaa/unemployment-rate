package com.app.unemploymentRate.util;

import com.app.unemploymentRate.JsonObjects.CountryRate;

import java.util.ArrayList;
import java.util.HashMap;

public final class CountryRateUtil {

    private CountryRateUtil(){};

    public static HashMap<String, Double> getTopCountriesWithBestRateInYear (ArrayList<CountryRate> countriesRate,
                                                                             int N, String year) {

        HashMap<String, Double> bestCountries = new HashMap<>();
        String countryName = "";
        for (int i = 0; i < N; i++) {
            Double lowestValue = 100.0;
            for (CountryRate country : countriesRate) {
                if(country.getYear().equals(year) && country.getUnemploymentRate() <= lowestValue
                        && !bestCountries.containsKey(country.getCountryName())) {
                    countryName = country.getCountryName();
                    lowestValue = country.getUnemploymentRate();
                }
            }
            bestCountries.put(countryName, lowestValue);
        }


        return  bestCountries;
    }

    public static HashMap<String, Double> getTopCountriesWithWorstRateInYear (ArrayList<CountryRate> countriesRate,
                                                                              int N, String year) {

        HashMap<String, Double> worstCountries = new HashMap<>();
        String countryName = "";
        for (int i = 0; i < N; i++) {
            Double highestValue = 0.0;
            for (CountryRate country : countriesRate) {
                if(country.getYear().equals(year) && country.getUnemploymentRate() >= highestValue
                        && !worstCountries.containsKey(country.getCountryName())) {
                    countryName = country.getCountryName();
                    highestValue = country.getUnemploymentRate();
                }
            }
            worstCountries.put(countryName, highestValue);
        }


        return  worstCountries;
    }

    public static HashMap<String, Double> getAllCountriesRateByYear (ArrayList<CountryRate> countriesRates, String year) {

        HashMap<String, Double> allCountriesRate = new HashMap<>();

        for (CountryRate country : countriesRates) {
            if(country.getYear().equals(year)) {
                allCountriesRate.put(country.getCountryName(), country.getUnemploymentRate());
            }
        }
        return allCountriesRate;
    }
}
