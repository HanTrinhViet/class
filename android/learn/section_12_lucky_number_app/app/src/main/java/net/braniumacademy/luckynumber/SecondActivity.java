package net.braniumacademy.luckynumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    TextView welcomeTxt, luckyNumberTxt;
    Button share_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        welcomeTxt = findViewById(R.id.textView2);
        luckyNumberTxt = findViewById(R.id.text_view_lucky_number);
        share_btn = findViewById(R.id.share_btn);

        // Receiving data from Main Activity
        Intent i = getIntent();
        String userName = i.getStringExtra("name");

        // Generate random number
        int random_number = generateRandomNumber();
        luckyNumberTxt.setText(""+ random_number);
        share_btn.setOnClickListener(l -> {
            shareData(userName, random_number);
        });
    }

    private void shareData(String username, int randomNumber) {
        // Implicit Intent
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        // Addtional Info
        i.putExtra(Intent.EXTRA_SUBJECT, username + " got lucky today!");
        i.putExtra(Intent.EXTRA_TEXT, "His lucky number is: " + randomNumber);
        startActivity(Intent.createChooser(i, "Choose a platform"));
    }

    public int generateRandomNumber() {
        Random random = new Random();
        int upper_limit = 2;
        int randomNumberGenerated = random.nextInt(upper_limit);
        return randomNumberGenerated;
    }
}