package com.vivianaranha.hellocutout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


import static  android.view.WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER;
import static  android.view.WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT;
import static  android.view.WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button b = findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMode();
            }
        });

    }

    private void showMode() {

        WindowManager.LayoutParams params = getWindow().getAttributes();
        int cutoutMode = params.layoutInDisplayCutoutMode;
        String mode = null;
        switch (cutoutMode) {
            case LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS:
                mode = "ALWAYS";
                break;
            case LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT:
                mode = "DEFAULT";
                break;
            case LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER:
                mode = "NEVER";
                break;
        }
        Toast.makeText(this, "Cutout Mode: " + mode, Toast.LENGTH_LONG).show();

    }


}
