package com.example.bbarbg.getfat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bbarbg.getfat.model.restaurant;

public class Einzelansicht_Restaurant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einzelansicht__restaurant);

        Intent intent = getIntent();
        String name = intent.getStringExtra("r_name");
        String type = intent.getStringExtra("r_type");
        Double x = intent.getDoubleExtra("r_x",0);
        Double y = intent.getDoubleExtra("r_y",0);
        Boolean opennow = intent.getBooleanExtra("r_opennow",false);


        final TextView text = findViewById(R.id.textView);
        text.setText("Name = " + name + "\nTyp = " + type +  "\nX = " + x  +  "\nY = " + y  +  "\nOpennow = " + opennow);


    }


    public void setImage(){


    }
}
