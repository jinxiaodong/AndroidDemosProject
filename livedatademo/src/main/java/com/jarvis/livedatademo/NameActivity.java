package com.jarvis.livedatademo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class NameActivity extends AppCompatActivity {

    private TextView tvName;
    private Button change;
    private NameViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        Log.i("NameActivity：", "onCreate()");

        tvName = findViewById(R.id.tv_name);
        change = findViewById(R.id.change);


        model = new ViewModelProvider(this).get(NameViewModel.class);
        final Observer<String> observer = s -> {
            Log.i("Jarvis:",s);
            tvName.setText(s);
        };
        model.getCurrentName().observe(this, observer);

        change.setOnClickListener(v -> {
            String name = "Hello" + model.getI();
            model.getCurrentName().setValue(name);
            model.getCurrentName().setValue(name + "1");


        });

    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("NameActivity：", "onDestroy()");
    }
}