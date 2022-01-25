package com.jarvis.lifecycledemo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private MyLocationListener myLocationListener;
    private TextView tvHello;

    private static void onLocateSuccess(String location) {
        //update UI
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        tvHello = findViewById(R.id.tv_hello);
//        myLocationListener = new MyLocationListener(this, location -> {
//            //update UI
//        });

        getLifecycle().addObserver(new MyLocationListener2(this, MainActivity::onLocateSuccess));
    }

    @Override
    protected void onStart() {
        super.onStart();
//        myLocationListener.start();
//        tvHello.postDelayed(() -> myLocationListener.start(), 1000 * 4);
//        tvHello.postDelayed(MainActivity.this::finish, 1000 * 2);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        myLocationListener.stop();
    }
}