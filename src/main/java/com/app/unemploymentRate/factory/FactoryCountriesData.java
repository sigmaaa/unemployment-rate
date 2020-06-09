package com.app.unemploymentRate.factory;

import com.app.unemploymentRate.model.CountryRate;
import com.app.unemploymentRate.util.JSONObjectUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class FactoryCountriesData {

    private FactoryCountriesData() {}

    /**
     * creates a ArrayList of CountryRate objects
     * @param jsonStatUrlContent - String with content of URL
     * @return ArrayList of CountryRate objects
     * @throws JSONException in case json-stat content has not structure like this:
     * {"value": [], "dimension":{"year":{category:{index:{}}}}, "area":{category:{index:{}, label{}}}}
     */
    public static List<CountryRate> getCountriesData(String jsonStatUrlContent) throws JSONException {
        Double[] value;
        JSONObject obj = new JSONObject(jsonStatUrlContent);
        JSONArray arr = obj.getJSONArray("value");
        value = new Double[arr.length()];
        List<CountryRate> CountriesRate = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++)
        {
            value[i] = arr.getDouble(i);
        }

        JSONObject yearsObj = obj.getJSONObject("dimension").
                getJSONObject("year").
                getJSONObject("category").
                getJSONObject("index");

        String[] years = JSONObjectUtil.getKeysInJsonOrderFromJO(yearsObj);

        JSONObject shortNamesObj = obj.getJSONObject("dimension").
                getJSONObject("area").
                getJSONObject("category").
                getJSONObject("index");

        String[] shortNames = JSONObjectUtil.getKeysInJsonOrderFromJO(shortNamesObj);

        JSONObject countyLabels = obj.getJSONObject("dimension").
                getJSONObject("area").
                getJSONObject("category").
                getJSONObject("label");

        HashMap<String, String> countryLabelMap = new HashMap<>();
        JSONObjectUtil.convertJsonToHashMap(countyLabels, countryLabelMap);

        fulfillCountryRateList(CountriesRate, value, years, shortNames, countryLabelMap);

        return CountriesRate;
    }

    private static void fulfillCountryRateList(List<CountryRate> CountriesRate, Double[] value, String[] years,
                                               String[] shortNames, HashMap<String, String> countryLabelMap) {

        for (int i = 0; i < value.length; i++ ) {
            for (String shortName : shortNames) {
                for (String year : years) {
                    CountriesRate.add(new CountryRate(year,
                            countryLabelMap.get(shortName),
                            shortName, value[i]));
                    i += 1;
                }
            }
        }
    }

}
