package com.example.bbarbg.getfat;

import android.app.ActionBar;
import android.app.LoaderManager;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Restaurant extends AppCompatActivity {
    private String temp = "";
    private ProgressBar progressBar;
    private static final String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        progressBar = findViewById(R.id.loading_restaurants_progress);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setX(184);

        setTitle("Gefundene Restaurants");
        Intent intent = getIntent();
        ArrayList<String> checkedFood = intent.getStringArrayListExtra("checkedFood");
        System.out.println("chekced " + checkedFood.toString());


        progressBar = findViewById(R.id.loading_restaurants_progress);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setX(184);

        try {
            getRestaurants(url);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getRestaurants(String url) throws JSONException{
        JSONObject jsonObj = new JSONObject(url);

        //jsonObj.getString("temp");



    }
}
