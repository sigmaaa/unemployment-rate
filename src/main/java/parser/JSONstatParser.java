package parser;

import com.app.unemploymentRate.util.JSONObjectUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Iterator;

public final class JSONstatParser {

    private JSONstatParser() {}

    /**
     * Method gets most recent year from jsot-stat content
     * @param jsonStatContent - String content from json-stat URL
     * @return String year like "2012"
     * @throws JSONException if cannot get to dimension -> year -> category -> index
     */
    public static String getMostRecentYear(String jsonStatContent) throws JSONException {

        JSONObject obj = new JSONObject(jsonStatContent);
        JSONObject yearsObj = obj.getJSONObject("dimension").
                getJSONObject("year").
                getJSONObject("category").
                getJSONObject("index");

        String[] years = JSONObjectUtil.getKeysInJsonOrderFromJO(yearsObj);
        String year = "";

        if (years.length != 0) {
            year = years[0];

            for (String y : years) {
                if (Integer.parseInt(y) > Integer.parseInt(year)) {
                    year = y;
                }
            }
        }
        return year;
    }

    /**
     * Method gets all names from json-stat content (dimension -> area -> category -> label)
     * @param jsonStatContent - String content from json-stat URL
     * @return HashSet<String> with country names list
     * @throws JSONException if cannot get to dimension -> year -> category -> label
     */
    public static HashSet<String> getAllCountryNames (String jsonStatContent) throws JSONException {

        HashSet<String> countryNames = new HashSet<>();
        JSONObject obj = new JSONObject(jsonStatContent);
        JSONObject countyLabels = obj.getJSONObject("dimension").
                getJSONObject("area").
                getJSONObject("category").
                getJSONObject("label");

        Iterator x = countyLabels.keys();
        while (x.hasNext()) {
            String key = (String) x.next();
            countryNames.add(countyLabels.getString(key));
        }

        return countryNames;
    }
}
