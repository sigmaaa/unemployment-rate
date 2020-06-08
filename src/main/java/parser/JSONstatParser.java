package parser;

import com.app.unemploymentRate.util.JSONObjectUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public final class JSONstatParser {

    private JSONstatParser() {};

    public static String getMostRecentYear(String jsonStatContent) throws JSONException {


        JSONObject obj = new JSONObject(jsonStatContent);
        JSONObject yearsObj = obj.getJSONObject("dimension").
                getJSONObject("year").
                getJSONObject("category").
                getJSONObject("index");

        ArrayList<String> years = JSONObjectUtil.getKeysFromJsonObject(yearsObj);
        String year = "";

        if (!years.isEmpty()) {
            year = years.get(0);

            for (String y : years) {
                if (Integer.parseInt(y) > Integer.parseInt(year)) {
                    year = y;
                }
            }
        }
        return year;
    }

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
