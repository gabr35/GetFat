package com.example.bbarbg.getfat.helper;

import com.example.bbarbg.getfat.model.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RestaurantParser {

    public static List<Restaurant> craeteRestaurantFromJsonToString(String jsonString, String type) throws JSONException {
        List<Restaurant> restaurants = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonString);
        System.out.println("jsonString " + jsonString);
        JSONArray results = jsonObject.getJSONArray("results");
        System.out.println("jsonString sub resuluts " + results.toString());
        for (int i = 0; i < results.length(); i++) {
            String name = results.getJSONObject(i).getString("name");
            double x = results.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
            double y = results.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getDouble("lng");
            String adresse = results.getJSONObject(i).getString("vicinity");
            double rating = 0.00005;
            if (results.getJSONObject(i).has("rating")) {
                rating = results.getJSONObject(i).getDouble("rating");
            }
            boolean isOpen = false;
            if(results.getJSONObject(i).has("opening_hours")) {
                isOpen = results.getJSONObject(i).getJSONObject("opening_hours").getBoolean("open_now");
            }
            String foto = "";
            if(results.getJSONObject(i).has("photos")) {
                foto = "https://maps.googleapis.com/maps/api/place/photo?key=AIzaSyAOpJAjEDLjxZIVm3nKk_8wtW3cW3gPujM&photoreference=" +
                        results.getJSONObject(i).getJSONArray("photos").getJSONObject(0).getString("photo_reference");
            }
            Restaurant restaurant = new Restaurant(name, type, x, y, isOpen, foto, adresse, rating);
            restaurants.add(restaurant);
        }
        return restaurants;
    }
}
