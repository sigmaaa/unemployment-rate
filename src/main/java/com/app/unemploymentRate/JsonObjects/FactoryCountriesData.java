package com.app.unemploymentRate.JsonObjects;

import com.app.unemploymentRate.util.JSONObjectUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public final class FactoryCountriesData {

    private FactoryCountriesData() {}

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

        ArrayList<String> years = JSONObjectUtil.getKeysFromJsonObject(yearsObj);
        Collections.sort(years);

        JSONObject shortNamesObj = obj.getJSONObject("dimension").
                getJSONObject("area").
                getJSONObject("category").
                getJSONObject("index");

        String[] shortNames = JSONObjectUtil.getKeysSortedByValueFromJO(shortNamesObj);

        JSONObject countyLabels = obj.getJSONObject("dimension").
                getJSONObject("area").
                getJSONObject("category").
                getJSONObject("label");


        HashMap<String, String> countryLabelMap = new HashMap<>();
        JSONObjectUtil.convertJsonToHashMap(countyLabels, countryLabelMap);

        fulfillCountryRateList(CountriesRate, value, years, shortNames, countryLabelMap);

        return CountriesRate;
    }

    private static void fulfillCountryRateList(List<CountryRate> CountriesRate, Double[] value, ArrayList<String> years,
                                               String[] shortNames, HashMap<String, String> countryLabelMap) {

        for (int i = 0; i < value.length; i++ ) {
            for (int j = 0; j < shortNames.length; j++) {
                String shortName = shortNames[j];
                for (int k = 0; k < years.size(); k++) {
                    CountriesRate.add(new CountryRate(years.get(k),
                            countryLabelMap.get(shortName),
                            shortName, value[i]));
                    i += 1;
                }
            }
        }
    }

}
