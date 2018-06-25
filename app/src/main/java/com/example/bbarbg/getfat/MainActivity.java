package com.example.bbarbg.getfat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
/*import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;*/
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

import static android.provider.UserDictionary.Words.APP_ID;
import static java.lang.System.in;

public class MainActivity extends AppCompatActivity {

    private List<String> checkedFood = new ArrayList<String>();
    private CheckBox pizza;
    private CheckBox burger;
    private CheckBox sandwich;
    private CheckBox asian;
    private CheckBox doener;
    private Location uLocation;
    private FusedLocationProviderClient mFusedLocationClient;
    private final int MY_PERMISSION_ACCESS_COURSE_LOCATION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //User location permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            this.requestPermissions(new String[] { Manifest.permission.ACCESS_COARSE_LOCATION },
                    MY_PERMISSION_ACCESS_COURSE_LOCATION);
        }

        //get User location
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        System.out.println("Location: " + location.getLatitude());
                        if (location != null) {
                            uLocation = location;
                        } else {
                            Toast errorToast = Toast.makeText(MainActivity.this, "Konnte die Userlocation nicht laden", Toast.LENGTH_SHORT);
                            errorToast.show();

                        }
                    }
                });

        setTitle("Auf was hast du Lust?");

        //Dropdown
        final Spinner dropdown = (Spinner) findViewById(R.id.radius);
        String[] items = new String[]{"Hier", "2km entfernt", "10km entfernt"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        this.pizza = (CheckBox) findViewById(R.id.cb_pizza);
        this.burger = (CheckBox) findViewById(R.id.cb_burger);
        this.doener = (CheckBox) findViewById(R.id.cb_doener);
        this.sandwich = (CheckBox) findViewById(R.id.cb_sandwich);
        this.asian = (CheckBox) findViewById(R.id.cb_asian);

         //button
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (pizza.isChecked() || burger.isChecked() || doener.isChecked() || sandwich.isChecked() || asian.isChecked()) {
                    System.out.println(checkedFood.toString());
                    Intent intent = new Intent(getApplicationContext(), Restaurant.class);
                    intent.putStringArrayListExtra("checkedFood", (ArrayList<String>) checkedFood);
                    intent.putExtra("radius", dropdown.getSelectedItem().toString());
                    //intent.putExtra("location", location);
                    intent.putExtra("lat", uLocation.getLatitude());
                    intent.putExtra("long", uLocation.getLongitude());
                    startActivity(intent);
                } else {
                    Toast errorToast = Toast.makeText(MainActivity.this, "Bitte eine Food-Kategorie auswählen!", Toast.LENGTH_SHORT);
                    errorToast.show();
                }
            }
        });
    }

    public void onCheckboxClicked(View v){

        boolean checked = ((CheckBox) v).isChecked();

           switch(v.getId()) {
            case R.id.cb_burger:
                if (checked){
                    //intent.putExtra("burger", "burger");
                    checkedFood.add("burger");
                }  else {
                    checkedFood.remove("burger");
                }
                break;
            case R.id.cb_asian:
                if (checked){
                    //intent.putExtra("asian", "asian");
                    checkedFood.add("asian");
                } else {
                    checkedFood.remove("asian");
                }
                break;
            case R.id.cb_doener:
                if (checked){
                    //intent.putExtra("doener", "doener");
                    checkedFood.add("doener");
                } else {
                    checkedFood.remove("doener");
                }
                break;
            case R.id.cb_pizza:
                if (checked){
                    //intent.putExtra("pizza", "pizza");
                    checkedFood.add("pizza");
                }  else {
                    checkedFood.remove("pizza");
                }
                break;
            case R.id.cb_sandwich:
                if (checked){
                    //intent.putExtra("sandwich", "sandwich");
                    checkedFood.add("sandwich");
                }  else {
                    checkedFood.remove("sandwich");
                }break;
            }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_ACCESS_COURSE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }
}
