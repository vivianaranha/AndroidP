package com.vivianaranha.imagedecoderapp;

import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {

    AnimatedImageDrawable winnieThePooh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView);

        Button startButton = findViewById(R.id.start_button);
        Button stopButton = findViewById(R.id.stop_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimation();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAnimation();
            }
        });


        try (InputStream inputStream = getAssets().open("winniw.webp");){
            int size = inputStream.available();
            byte[] bytes = new byte[size];
            inputStream.read(bytes);

            ByteBuffer buffer = ByteBuffer.wrap(bytes);
            ImageDecoder.Source source = ImageDecoder.createSource(buffer);

            Drawable drawable = ImageDecoder.decodeDrawable(source);
            imageView.setImageDrawable(drawable);

            if(drawable instanceof AnimatedImageDrawable) {
                winnieThePooh = (AnimatedImageDrawable) drawable;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void stopAnimation() {
        if(winnieThePooh != null){
            winnieThePooh.stop();
        }
    }

    private void startAnimation() {
        if(winnieThePooh != null){
            winnieThePooh.start();
        }
    }
}
