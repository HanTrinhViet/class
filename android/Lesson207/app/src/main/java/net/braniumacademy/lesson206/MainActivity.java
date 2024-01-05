package net.braniumacademy.lesson206;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private  EditText editTextFullName;
    private  EditText editTextAddress;
    private  Button btnLogin;
    private  Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // đăng ký sự kiện cho các nút
        editTextFullName = findViewById(R.id.edit_full_name);
        editTextAddress = findViewById(R.id.edit_full_name);

        btnLogin = findViewById(R.id.button_login);
        btnRegister = findViewById(R.id.button_register);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_login) {
            login();
        } else if (view.getId() == R.id.button_register) {
            register();
        }
    }

    private void register() {
        // Kiểm tra các thông tin đã chuẩn hoá
        // Các hành động khác nhau
        // Cho phép đăng nhập
        String msg = "Đăng ký thành công!\nHọ và tên: " + editTextFullName.getText().toString()
                + "\nĐịa chỉ: " + editTextAddress.getText().toString();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void login() {
        // Ktr dữ liệu chuẩn hoá chưa
        // cho đăng nhập
        // trả về trạng thái đăng nhập
        String msg = "Đăng nhập thành công!\nHọ và tên: " + editTextFullName.getText().toString()
                + "\nĐịa chỉ: " + editTextAddress.getText().toString();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}