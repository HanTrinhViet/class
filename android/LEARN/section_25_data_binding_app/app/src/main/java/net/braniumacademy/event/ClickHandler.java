package net.braniumacademy.event;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class ClickHandler {

    Context context;

    public ClickHandler(Context context) {
        this.context = context;
    }

    public ClickHandler() {
    }

    public void onButtonClickHandler(View view) {
        Toast.makeText(context, "pressing...", Toast.LENGTH_SHORT).show();
    }
}
