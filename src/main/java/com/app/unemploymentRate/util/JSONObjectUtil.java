package com.app.unemploymentRate.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public final class JSONObjectUtil {

    public static String[] getKeysSortedByValueFromJO (JSONObject obj) throws JSONException {
        Iterator x = obj.keys();
        String[] array = new String[obj.length()];
        while(x.hasNext()) {
            String key = (String) x.next();
            array[obj.getInt(key)] = key;
        }
        return array;
    }

    public static ArrayList<String> getKeysFromJsonObject (JSONObject obj) throws JSONException {
        Iterator x = obj.keys();
        ArrayList<String> array = new ArrayList<>();
        while (x.hasNext()) {
            String key = (String) x.next();
            array.add(key);
        }
        return array;
    }

    public static void convertJsonToHashMap(JSONObject obj, HashMap hm) throws JSONException {
        for (Iterator it = obj.keys(); it.hasNext(); ) {
            String key = it.next().toString();
            hm.put(key, obj.get(key));
        }
    }
}
