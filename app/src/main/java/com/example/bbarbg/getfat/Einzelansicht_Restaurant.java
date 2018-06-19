package com.example.bbarbg.getfat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bbarbg.getfat.model.restaurant;

import java.util.ArrayList;

public class Einzelansicht_Restaurant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einzelansicht__restaurant);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("r_name");
        String type = intent.getStringExtra("r_type");
        final Double x = intent.getDoubleExtra("r_x",0);
        final Double y = intent.getDoubleExtra("r_y",0);
        Boolean opennow = intent.getBooleanExtra("r_opennow",false);

        setTitle(name);


        final TextView text = (TextView) findViewById(R.id.textViewName);
        text.setText();

        setImage(type);

        //button
        final Button button = (Button) findViewById(R.id.map_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Map.class);
                intent.putExtra("lat", x);
                intent.putExtra("lng", y);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

    }


    public void setImage(String type){
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        switch(type){
            case "burger":

                imageView.setImageDrawable(getResources().getDrawable(R.drawable.i_burger));

                break;
            case "sandwich":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.i_sandwich));
                break;
            case "pizza":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.i_pizza));

                break;
            case "doener":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.i_doener));

                break;
            case "asian":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.i_asia));
                break;

        }

    }
}
