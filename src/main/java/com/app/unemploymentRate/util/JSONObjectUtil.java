package com.app.unemploymentRate.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public final class JSONObjectUtil {

    /**
     * Some JSON-stat objects contains structures with indexes, for example:
     * {"index" : "AU" : 0, "AT" : 1, "BE" : 2 }
     * Method gets String[] with keys from JSONObject in the right order,
     * which means key with value 0 will be in [0] pos.
     * @param obj - JSONObject like {"index" : "AU" : 0, "AT" : 1, "BE" : 2 }
     * @return String[] with keys from JSONObject obj like ["AU", "AT", "BE"]
     * @throws JSONException in case JSONObject not in format like {"index" : "AU" : 0, "AT" : 1, "BE" : 2 }
     */
    public static String[] getKeysInJsonOrderFromJO(JSONObject obj) throws JSONException {
        Iterator x = obj.keys();
        String[] array = new String[obj.length()];
        while(x.hasNext()) {
            String key = (String) x.next();
            array[obj.getInt(key)] = key;
        }
        return array;
    }

    /**
     * Fulfill hashmap with key-values from JSONObject
     * @param obj - JSONObject like {"DE": "Germany", "NO": Norway}
     * @param hm - HashMap for the fulfilling
     * @throws JSONException in case JSONObject not in format like {"DE": "Germany", "NO": Norway}
     */
    public static void convertJsonToHashMap(JSONObject obj, HashMap<String, String> hm) throws JSONException {
        for (Iterator it = obj.keys(); it.hasNext(); ) {
            String key = it.next().toString();
            hm.put(key, obj.getString(key));
        }
    }
}
