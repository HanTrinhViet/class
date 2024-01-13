package net.braniumacademy.luckynumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button btn;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
        btn = findViewById(R.id.btn);
        txt = findViewById(R.id.textView);

        btn.setOnClickListener(l ->  {
            String userName = editText.getText().toString();

            // Explicit intent
            Intent i = new Intent(this, SecondActivity.class);

            // Passing the name to second activity
            i.putExtra("name", userName);

            startActivity(i);
        });
    }
}