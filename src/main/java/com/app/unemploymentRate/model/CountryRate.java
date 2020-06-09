package com.app.unemploymentRate.model;
import lombok.Data;

/**
 * Represents unemployment rate for the country in some year
 */
@Data
public class CountryRate {

    private String year;
    private String countryName;
    private String shortName;
    private Double unemploymentRate;

    public CountryRate(String year, String countryName, String shortName, Double unemploymentRate) {

       this.year = year;
       this.countryName = countryName;
       this.shortName = shortName;
       this.unemploymentRate = unemploymentRate;
    }

}
