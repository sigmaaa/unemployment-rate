package com.app.unemploymentRate.util;

import com.app.unemploymentRate.model.CountryRate;

import java.util.ArrayList;
import java.util.HashMap;

public final class CountryRateUtil {

    private CountryRateUtil(){}

    /**
     * Method gets top N countries with lowest unemployment rate in a certain year
     * @param countriesRate an ArrayList of CountryRate objects
     * @param N - is a int number of top countries we want to get in result hashMap
     * @param year - String with year, like "2012"
     * @return HashMap<String, Double> which contains country name  and unemployment rate of the country
     */
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

    /**
     * Method gets top N countries with highest unemployment rate in a certain year
     * @param countriesRate an ArrayList of CountryRate objects
     * @param N is a number of top countries
     * @param year - String with year, like "2012"
     * @return HashMap<String, Double> which contains country name  and unemployment rate of the country
     */
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

    /**
     * Method gets all country rates in a certain year
     * @param countriesRates an ArrayList of CountryRate objects
     * @param year - String with year, like "2012"
     * @return HashMap<String, Double> which contains country name  and unemployment rate of the country
     */
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
