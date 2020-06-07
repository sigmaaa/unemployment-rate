package parser;

import com.app.unemploymentRate.util.JSONObjectUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class JSONstatParser {

    private JSONstatParser() {};

    public static String getMostRecentYear(String jsonStatUrlContent) throws JSONException {


        JSONObject obj = new JSONObject(jsonStatUrlContent);
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
}
