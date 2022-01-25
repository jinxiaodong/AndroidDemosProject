package com.jarvis.livedatademo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn);
        btn.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

            }
        });
    }

    public void click(View view) {

        int id = view.getId();
        if (id == R.id.btn) {
            Intent intent = new Intent(this, NameActivity.class);
            startActivity(intent);
        } else if (id == R.id.btn2) {
            Intent intent = new Intent(this, LiveDataBusActivity.class);

            startActivity(intent);
        }
    }
}