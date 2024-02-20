package com.example.ngay1_15_2024;

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

public class MyCustomAdapter extends ArrayAdapter<Contact> {
    private ArrayList<Contact> arrayList;
    Context context;

    public MyCustomAdapter(ArrayList<Contact> arrayList, Context context) {
        super(context, R.layout.item_layout, arrayList);
        this.arrayList = arrayList;
        this.context = context;
    }

    private static class MyViewHolder {
        TextView name, phone;
        ImageView imageView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // 1. Get the Contact object for the current position
        Contact contact = getItem(position);

        // 2. Inflating Layout
        MyViewHolder myViewHolder;

        if(convertView == null) {
            myViewHolder = new MyViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());

            convertView = layoutInflater.inflate(
                    R.layout.item_layout,
                    parent,
                    false
            );

            // finding view
            myViewHolder.name =(TextView) convertView.findViewById(R.id.name);
            myViewHolder.phone =(TextView) convertView.findViewById(R.id.phone);
            myViewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);

        }else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        myViewHolder.name.setText(contact.getName());
        myViewHolder.phone.setText(contact.getPhone());
        myViewHolder.imageView.setImageResource(contact.getImages());


        return convertView;
    }
}
