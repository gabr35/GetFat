package com.example.bbarbg.getfat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class Einzelansicht_Restaurant extends AppCompatActivity {

    private String name;
    private String type;
    private String adresse;
    private float rating;
    private double x;
    private double y;
    private boolean openNow;

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
        setContentView(R.layout.activity_einzelansicht__restaurant);

        Intent intent = getIntent();
        this.name = intent.getStringExtra("r_name");
        this.type = intent.getStringExtra("r_type");
        this.x = intent.getDoubleExtra("r_x",0);
        this.y = intent.getDoubleExtra("r_y",0);
        this.openNow = intent.getBooleanExtra("r_opennow",false);
        this.adresse = intent.getStringExtra("r_adresse");
        this.rating = intent.getFloatExtra("r_rating", 0);

        final TextView viewName = (TextView)findViewById(R.id.r_name);
        TextView adresse = (TextView)findViewById(R.id.r_adresse);
        TextView status = (TextView)findViewById(R.id.r_status);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        viewName.setText(this.name);
        adresse.setText(this.adresse);
        status.setText("Heute geöffnet");
        if (!openNow) {
            status.setText("Heute geschlossen");
        }
        ratingBar.setMax(5);
        if (rating == 0.00005) {
            ratingBar.setVisibility(View.GONE);
        }
        ratingBar.setRating(this.rating);



        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setTitle(this.name);
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
