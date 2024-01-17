package net.braniumacademy;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnBlack, btnYellow, btnRed, btnPurple, btnGreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBlack = findViewById(R.id.btn_black);
        btnYellow = findViewById(R.id.btn_yellow);
        btnRed = findViewById(R.id.btn_red);
        btnPurple = findViewById(R.id.btn_purple);
        btnGreen = findViewById(R.id.btn_green);

        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.mck);
                mediaPlayer.start();
            }
        });
    }
}