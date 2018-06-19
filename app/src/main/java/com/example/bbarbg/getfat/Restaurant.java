package com.example.bbarbg.getfat;

import android.app.ActionBar;
import android.app.LoaderManager;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bbarbg.getfat.helper.RestaurantParser;
import com.example.bbarbg.getfat.model.restaurant;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Restaurant extends AppCompatActivity {
    private String temp = "";
    private ProgressBar progressBar;
    private static final String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=AIzaSyAOpJAjEDLjxZIVm3nKk_8wtW3cW3gPujM&";
    private ArrayList<String> checkedFood;
    private ListView restaurantList;
    private ArrayAdapter<restaurant> restaurantAdapter;
    private int radius;
    private String location = "46.939667,7.398639";


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
        this.restaurantAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);


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
        System.out.println("chekced " + checkedFood.toString());




            for (String food : checkedFood) {
            try {
                getRestaurants(url, food);
                restaurantList.setAdapter(restaurantAdapter);

                AdapterView.OnItemClickListener mListClickedHandler = new
                        AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView parent, View v, int position, long id){
                                Intent intent = new Intent(getApplicationContext(), Einzelansicht_Restaurant.class);
                                restaurant selected = (restaurant) parent.getItemAtPosition(position);
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



                progressBar.setVisibility(View.GONE);
            } catch (JSONException e) {

            }
        }



    }


    public void getRestaurants(String url, final String type) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        url += "location=" + location + "&radius=" + radius + "&type=restaurant&keyword=" + type;
        System.out.println("url " + url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    List<restaurant> restaurants = RestaurantParser.craeteRestaurantFromJsonToString(response, type);
                    restaurantAdapter.addAll(restaurants);
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
