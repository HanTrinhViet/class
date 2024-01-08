package net.braniumacademy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "ON_CREATE");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "ON_START");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "ON_RESUME");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "ON_PAUSE");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "ON_RESTART");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "ON_STOP");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "ON_DESTROY");
    }
}