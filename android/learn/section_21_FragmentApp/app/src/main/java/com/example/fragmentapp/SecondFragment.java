package com.example.fragmentapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondFragment extends Fragment {

    TextView txtVwSecondFragment;
    Button btnSecondFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        txtVwSecondFragment = view.findViewById(R.id.txt_vw_second_fragment);
        btnSecondFragment = view.findViewById(R.id.btn_second_fragment);

        btnSecondFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Second Fragment", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}