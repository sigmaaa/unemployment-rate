package com.app.unemploymentRate.JsonObjects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FactoryCountriesData {

    private HashMap<String, Double> countriesWithLowestUR;
    private HashMap<String, Double> countriesWithHighestUR;

    public CountriesData getCountriesData(String jsonStatUrlContent) throws JSONException {
        Double[] value;
        JSONObject obj = new JSONObject(jsonStatUrlContent);
        JSONArray arr = obj.getJSONArray("value");
        value = new Double[arr.length()];
        for (int i = 0; i < arr.length(); i++)
        {
            value[i] = arr.getDouble(i);
        }

        String yearInterval = obj.getJSONObject("dimension").getJSONObject("year").getString("label");
        int stepForGetActualVal = getStepFromInterval(yearInterval);

        JSONObject countryIndex = obj.getJSONObject("dimension").getJSONObject("area").getJSONObject("category").getJSONObject("index");
        HashMap<String, Integer> countryIndexMap = new HashMap<>();
        convertJsonToHashMap(countryIndex, countryIndexMap);

        JSONObject countyLabels = obj.getJSONObject("dimension").getJSONObject("area").getJSONObject("category").getJSONObject("label");
        HashMap<String, String> countryLabelMap = new HashMap<>();
        convertJsonToHashMap(countyLabels, countryLabelMap);
        String[] sortedByIndexLabels = new String[countyLabels.length()];

        for (String label : countryIndexMap.keySet()) {
            sortedByIndexLabels[countryIndexMap.get(label)] = countryLabelMap.get(label);
        }
        HashMap<String, Double> allCountriesRate = new HashMap<>();
        int iterator = 0;
        for (int i = stepForGetActualVal; i < value.length; i += stepForGetActualVal+1) {

            allCountriesRate.put(sortedByIndexLabels[iterator], value[i]);
            iterator += 1;
        }


        setNcountriesWithLowAndHighUR(allCountriesRate, 3);
        return new CountriesData(yearInterval, countriesWithLowestUR, countriesWithHighestUR, allCountriesRate);
    }


    private void convertJsonToHashMap(JSONObject obj, HashMap hm) throws JSONException {
        for (Iterator it = obj.keys(); it.hasNext(); ) {
            String key = it.next().toString();
            hm.put(key, obj.get(key));
        }
    }

    private int getStepFromInterval(String interval) {

        List<String> allMatches = new ArrayList<String>();
        Matcher m = Pattern.compile("(\\d+)")
                .matcher(interval);
        while (m.find()) {
            allMatches.add(m.group());
        }

        if (allMatches.size() == 1) {
            return 0;
        } else {
            return Integer.parseInt(allMatches.get(1)) - Integer.parseInt(allMatches.get(0));
        }

    }

    private void setNcountriesWithLowAndHighUR(HashMap<String, Double> allCountriesRate, int N) {
        HashMap<String, Double> countriesWithLowestUR = new HashMap<>();
        HashMap<String, Double> countriesWithHighestUR = new HashMap<>();
        for(int i = 0; i < N; i++) {
            Double highestValue = null;
            Double lowestValue = null;
            String hvLabel = "";
            String lvLabel = "";
            for (String key : allCountriesRate.keySet()) {
                highestValue = highestValue == null ? allCountriesRate.get(key) : highestValue;
                lowestValue = lowestValue == null ? allCountriesRate.get(key) : lowestValue;

                if (allCountriesRate.get(key) >= highestValue && !countriesWithHighestUR.containsKey(key)) {
                    highestValue = allCountriesRate.get(key);
                    hvLabel = key;
                }

                if (allCountriesRate.get(key) <= lowestValue && !countriesWithLowestUR.containsKey(key)) {
                    lowestValue = allCountriesRate.get(key);
                    lvLabel = key;
                }

            }
            countriesWithHighestUR.put(hvLabel, highestValue);
            countriesWithLowestUR.put(lvLabel, lowestValue);
        }
        this.countriesWithHighestUR = countriesWithHighestUR;
        this.countriesWithLowestUR = countriesWithLowestUR;
    }

}
