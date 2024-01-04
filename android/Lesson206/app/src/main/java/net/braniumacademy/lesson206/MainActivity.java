package net.braniumacademy.lesson206;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText txtFullName = findViewById(R.id.edit_full_name);
        EditText txtAddress = findViewById(R.id.edit_address);
        Button btnRegister = findViewById(R.id.button_register);

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_register) {
            EditText txtFullName = findViewById(R.id.edit_full_name);
            EditText txtAddress = findViewById(R.id.edit_address);
            String fullName = txtFullName.getText().toString();
            String address = txtAddress.getText().toString();
            String msg = "Họ và tên: " + fullName + "\n" + "Địa chỉ: " + address;
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}