package com.example.fragmentapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class FirstFragment extends Fragment {
    TextView txtVwFirstFragment;
    Button btnFirstFragment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        txtVwFirstFragment = view.findViewById(R.id.txt_vw_first_fragment);
        btnFirstFragment = view.findViewById(R.id.btn_first_fragment);

        btnFirstFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "First Fragment", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}