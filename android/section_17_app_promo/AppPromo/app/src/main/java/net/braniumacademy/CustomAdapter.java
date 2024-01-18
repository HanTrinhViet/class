package net.braniumacademy;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Planet> {
    private List<Planet> planets;
    Context context;

    public CustomAdapter(Context context, List<Planet> planets) {
        super(context, R.layout.item_list_layout, planets);
        this.planets = planets;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // 1 - Get the planet object for the current position
        Planet planet = this.getItem(position);

        // 2 - Inflate the layout
        MyViewHolder myViewHolder;
        final View result;

        
    }

    private static class MyViewHolder {
        ImageView planetImg;
        TextView planetName;
        TextView moonCount;
    }
}
