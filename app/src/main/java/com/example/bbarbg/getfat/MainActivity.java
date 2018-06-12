package com.example.bbarbg.getfat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import static java.lang.System.in;

public class MainActivity extends AppCompatActivity {
    String temp = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setTitle("Auf was hast du Lust?");

        Spinner dropdown = findViewById(R.id.radius);
        String[] items = new String[]{"Hier", "~300m entfernt", "Kanton"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


    }


    public void suchen(View v){

        Intent intent = new Intent(getApplicationContext(), Restaurant.class);

        boolean checked = ((CheckBox) v).isChecked();


       switch(v.getId()) {
            case R.id.cb_burger:
                if (checked){
                    intent.putExtra("burger", "burger");
                    temp += "burger";
                }
            case R.id.cb_asian:
                if (checked){
                    intent.putExtra("asian", "asian");
                }
            case R.id.cb_doener:
                if (checked){
                    intent.putExtra("doener", "doener");
                }
            case R.id.cb_pizza:
                if (checked){
                    intent.putExtra("pizza", "pizza");
                    temp += "pizza";
                }
            case R.id.cb_sandwich:
                if (checked){
                    intent.putExtra("sandwich", "sandwich");
                }

        }
        this.setTitle(temp);





        //startActivity(intent);

    }

}
