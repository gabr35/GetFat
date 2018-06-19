package com.example.bbarbg.getfat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.in;

public class MainActivity extends AppCompatActivity {

    private List<String> checkedFood = new ArrayList<String>();
    private CheckBox pizza;
    private CheckBox burger;
    private CheckBox sandwich;
    private CheckBox asian;
    private CheckBox doener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setTitle("Auf was hast du Lust?");

        final Spinner dropdown = findViewById(R.id.radius);
        String[] items = new String[]{"Hier", "~2km entfernt", "~10km entfernt"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        this.pizza = findViewById(R.id.cb_pizza);
        this.burger = findViewById(R.id.cb_burger);
        this.doener = findViewById(R.id.cb_doener);
        this.sandwich = findViewById(R.id.cb_sandwich);
        this.asian = findViewById(R.id.cb_asian);

         //button
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (pizza.isChecked() || burger.isChecked() || doener.isChecked() || sandwich.isChecked() || asian.isChecked()) {
                    System.out.println(checkedFood.toString());
                    Intent intent = new Intent(getApplicationContext(), Restaurant.class);
                    intent.putStringArrayListExtra("checkedFood", (ArrayList<String>) checkedFood);
                    intent.putExtra("radius", dropdown.getSelectedItem().toString());
                    startActivity(intent);
                } else {
                    Toast errorToast = Toast.makeText(MainActivity.this, "Bitte eine Food-Kategorie auswählen!", Toast.LENGTH_SHORT);
                    errorToast.show();
                }

            }
        });
    }


    public void onCheckboxClicked(View v){

        //Intent intent = new Intent(getApplicationContext(), Restaurant.class);

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
                }
                break;
        }
    }
}
