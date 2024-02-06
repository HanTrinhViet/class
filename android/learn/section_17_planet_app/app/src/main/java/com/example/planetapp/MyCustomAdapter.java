package com.example.planetapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyCustomAdapter extends ArrayAdapter<Planet> {
    private ArrayList<Planet> planets;
    Context context;

    public MyCustomAdapter(ArrayList<Planet> planets, Context context) {
        super(context, R.layout.planet_item_layout, planets);
        this.planets = planets;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // 1 - get the planet object for the current position
        Planet planet = getItem(position);

        // 2 - inflate layout
        ViewHolder holder;
        final View result;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(
                    R.layout.planet_item_layout,
                    parent,
                    false
            );
            holder.imgVwPlanet = (ImageView) convertView.findViewById(R.id.img_vw_planet);
            holder.txtVwPlanetName = (TextView) convertView.findViewById(R.id.txt_vw_planet_name);
            holder.txtVwMoonCount = (TextView) convertView.findViewById(R.id.txt_vw_moon_count);
            result = convertView;
            convertView.setTag(holder);
        } else {
            // the view is recycled
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        holder.txtVwPlanetName.setText(planet.getPlanetName());
        holder.txtVwMoonCount.setText(planet.getMoonCount());
        holder.imgVwPlanet.setImageResource(planet.getPlanetImage());

        return result;
    }

    private static class ViewHolder {
        ImageView imgVwPlanet;
        TextView txtVwPlanetName;
        TextView txtVwMoonCount;
    }
}
