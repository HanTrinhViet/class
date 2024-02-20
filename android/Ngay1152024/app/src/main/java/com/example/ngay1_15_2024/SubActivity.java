package com.example.ngay1_15_2024;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity {
    Button btnCancel;
    Button btnAdd;
    EditText etFullName;
    EditText etPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        btnCancel = findViewById(R.id.btn_cancel);
        btnAdd = findViewById(R.id.btn_add);
        etFullName = findViewById(R.id.ed_full_name);
        etPhoneNumber = findViewById(R.id.et_phone_number);


        btnCancel.setOnClickListener(
                view -> {
                    finish();
                }
        );

        btnAdd.setOnClickListener(
                view -> {
                    String name = etFullName.getText().toString();
                    String phoneNumber = etPhoneNumber.getText().toString();
                    Intent intent = new Intent();
                    Bundle b = new Bundle();
                    b.putString("Name", name);
                    b.putString("Phone", phoneNumber);
                    intent.putExtras(b);
                    setResult(150, intent);
                    finish();
                }
        );
    }
}