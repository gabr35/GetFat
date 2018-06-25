package com.example.bbarbg.getfat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bbarbg.getfat.helper.RestaurantListViewAdapter;
import com.example.bbarbg.getfat.helper.RestaurantParser;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Restaurant extends AppCompatActivity {
    private String temp = "";
    private ProgressBar progressBar;
    private static final String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=AIzaSyAOpJAjEDLjxZIVm3nKk_8wtW3cW3gPujM&";
    private ArrayList<String> checkedFood;
    private ListView restaurantList;
    private List<com.example.bbarbg.getfat.model.Restaurant> restaurants = new ArrayList<>();
    private RestaurantListViewAdapter restaurantAdapter;
    private int radius;
    private String location = "";
    private double lat;
    private double longitude;
    private RequestQueue queue;
    private int counter = 0;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        progressBar = (ProgressBar) findViewById(R.id.loading_restaurants_progress);
        progressBar.setVisibility(View.VISIBLE);

        this.restaurantList = (ListView) findViewById(R.id.restaurants);


        setTitle("Gefundene Restaurants");
        Intent intent = getIntent();
        this.checkedFood = intent.getStringArrayListExtra("checkedFood");
        switch (intent.getStringExtra("radius")) {
            case "Hier":
                radius = 500;
                break;
            case "2km entfernt":
                radius = 2000;
                break;
            case "10km entfernt":
                radius = 10000;
                break;
        }
        lat = intent.getDoubleExtra("lat", 0);
        longitude = intent.getDoubleExtra("long", 0);
        System.out.println("location " + location);
        System.out.println("chekced " + checkedFood.toString());



        queue = Volley.newRequestQueue(getApplicationContext());
        restaurantAdapter = new RestaurantListViewAdapter(this, this.restaurants);

        for (String food : checkedFood) {
            try {
                getRestaurants(url, food);
                //request counter
                counter++;
            } catch (JSONException e) {

            }
        }
        System.out.println("restaurant nach for: " + restaurants.toString());
        restaurantList.setAdapter(restaurantAdapter);
        AdapterView.OnItemClickListener mListClickedHandler = new
                AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView parent, View v, int position, long id){
                        Intent intent = new Intent(getApplicationContext(), Einzelansicht_Restaurant.class);
                        com.example.bbarbg.getfat.model.Restaurant selected = (com.example.bbarbg.getfat.model.Restaurant) parent.getItemAtPosition(position);
                        intent.putExtra("r_name", selected.getName());
                        intent.putExtra("r_type", selected.getType());
                        intent.putExtra("r_x", selected.getX());
                        intent.putExtra("r_y", selected.getY());
                        intent.putExtra("r_opennow", selected.isOpennow());
                        intent.putExtra("r_rating", (float) selected.getRating());
                        intent.putExtra("r_adresse", selected.getAdresse());
                        startActivity(intent);
                    }
                };
        restaurantList.setOnItemClickListener(mListClickedHandler);
    }

    public void getRestaurants(String url, final String type) throws JSONException {
        url += "location=" + String.valueOf(lat) + "," + String.valueOf(longitude) + "&radius=" + radius + "&type=Restaurant&keyword=" + type;

        System.out.println("url " + url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    restaurants.addAll(RestaurantParser.craeteRestaurantFromJsonToString(response, type));
                    restaurantAdapter.notifyDataSetChanged();
                    System.out.println("restaurants2: " + restaurants.toString());
                    if (counter > 1) {
                        counter--;
                    } else {
                        progressBar.setVisibility(View.GONE);
                        if (restaurants.size() == 0) {
                            Toast errorToast = Toast.makeText(Restaurant.this, "Keine Restaurants gefunden :(", Toast.LENGTH_LONG);
                            errorToast.show();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("onErrorResponse " + error.getMessage());
            }
        }); queue.add(stringRequest);
    }



}
