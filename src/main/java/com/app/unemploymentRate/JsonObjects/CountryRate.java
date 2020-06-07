package com.app.unemploymentRate.JsonObjects;

import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

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
