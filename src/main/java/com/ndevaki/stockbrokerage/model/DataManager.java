package com.ndevaki.stockbrokerage.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class DataManager {

    public static HashMap<String, HashMap<String, Security>> processData(JSONObject portfolioObject, JSONObject trendObject) {
        HashMap<String, HashMap<String, Security>> result = new HashMap<String, HashMap<String, Security>>();
        //process security qty api
        JSONArray array = (JSONArray) portfolioObject.get("data");
        for (Object obj : array) {
            if (obj instanceof JSONObject) {
                JSONObject instance = (JSONObject) obj;
                String date = instance.get("date").toString();
                String company = instance.get("security").toString();
                double qty = Double.parseDouble(instance.get("quantity").toString());
                if (!result.containsKey(date)) {
                    result.put(date, new HashMap<String, Security>());
                }
                if (!result.get(date).containsKey(company)) {
                    result.get(date).put(company, new Security());
                }
                Security security = result.get(date).get(company);
                security.setQty(qty);
            }
        }
        //process security price api;
        JSONArray trends = (JSONArray) trendObject.get("data");
        for (Object obj : trends) {
            if (obj instanceof JSONObject) {
                JSONObject instance = (JSONObject) obj;
                String date = instance.get("date").toString();
                String company = instance.get("security").toString();
                double price = Double.parseDouble(instance.get("price").toString());
                if (!result.containsKey(date)) {
                    result.put(date, new HashMap<String, Security>());
                }
                if (!result.get(date).containsKey(company)) {
                    (result.get(date)).put(company, new Security());
                }
                result.get(date).get(company).setPrice(price);
            }
        }
        return result;
    }
}
