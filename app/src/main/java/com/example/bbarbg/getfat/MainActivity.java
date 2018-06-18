package com.example.bbarbg.getfat;

import android.content.Intent;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setTitle("Auf was hast du Lust?");

        Spinner dropdown = findViewById(R.id.radius);
        String[] items = new String[]{"Hier", "~300m entfernt", "Kanton"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
         //button
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println(checkedFood.toString());
                Intent intent = new Intent(getApplicationContext(), Restaurant.class);
                intent.putStringArrayListExtra("checkedFood", (ArrayList<String>) checkedFood);
                startActivity(intent);
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
