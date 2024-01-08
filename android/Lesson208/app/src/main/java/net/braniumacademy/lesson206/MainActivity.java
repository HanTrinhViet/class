package net.braniumacademy.lesson206;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = findViewById(R.id.btn_login);
        Button btnRegister = findViewById(R.id.btn_register);
        editTextEmail= findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);
        editTextPassword.setTransformationMethod(new PasswordMaskTransformation());

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login) {
            login();
        } else if (view.getId() == R.id.btn_register) {
            register();
        }
    }

    private void register() {
        String message = "Đăng ký thành công!\nXin chào bạn: "
                + editTextEmail.getText() + "!";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void login() {
        String message = "Đăng nhập thành công!\nEmail: "
                + editTextEmail.getText() + "!";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}