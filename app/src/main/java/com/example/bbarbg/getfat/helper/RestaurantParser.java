package com.example.bbarbg.getfat.helper;

import android.util.Log;

import com.example.bbarbg.getfat.Restaurant;
import com.example.bbarbg.getfat.model.restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RestaurantParser {

    public static List<restaurant> craeteRestaurantFromJsonToString(String jsonString, String type) throws JSONException {
        List<restaurant> restaurants = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonString);
        System.out.println("jsonString " + jsonString);
        JSONArray results = jsonObject.getJSONArray("results");
        System.out.println("jsonString sub resuluts " + results.toString());
        for (int i = 0; i < results.length(); i++) {
            String name = results.getJSONObject(i).getString("name");
            double x = results.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
            double y = results.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getDouble("lng");
            boolean isOpen = results.getJSONObject(i).getJSONObject("opening_hours").getBoolean("open_now");
            String foto = "";
            if(results.getJSONObject(i).has("photos")) {
                foto = "https://maps.googleapis.com/maps/api/place/photo?key=AIzaSyAOpJAjEDLjxZIVm3nKk_8wtW3cW3gPujM&photoreference=" +
                        results.getJSONObject(i).getJSONArray("photos").getJSONObject(0).getString("photo_reference");
            }
            restaurant restaurant = new restaurant(name, type, x, y, isOpen, foto);
            restaurants.add(restaurant);
        }
        return restaurants;
    }
}
