package com.example.bbarbg.getfat.helper;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bbarbg.getfat.MainActivity;
import com.example.bbarbg.getfat.R;
import com.example.bbarbg.getfat.model.Restaurant;

import java.util.List;

public class RestaurantListViewAdapter extends ArrayAdapter<Restaurant> {

    private final Activity context;
    private final List<Restaurant> restaurants;
    private String name;
    private String isOpen;
    private String icon;

    public RestaurantListViewAdapter(Activity context, List<Restaurant> restaurants) {
        super(context, R.layout.reastaurant_list, restaurants);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.restaurants = restaurants;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.reastaurant_list, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(restaurants.get(position).getName());
        extratxt.setText("Heute geschlossen");
        if (restaurants.get(position).isOpennow()) {
            extratxt.setText("Heute geöffnet");
        }
        switch(restaurants.get(position).getType()){
            case "burger":

                imageView.setImageDrawable(context.getDrawable(R.drawable.ic_hamburger));

                break;
            case "sandwich":
                imageView.setImageDrawable(context.getDrawable(R.drawable.ic_sandwich));
                break;
            case "pizza":
                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_pizza));

                break;
            case "doener":
                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_donerkebab));

                break;
            case "asian":
                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_asia));
                break;

        }
        return rowView;

    };
}
