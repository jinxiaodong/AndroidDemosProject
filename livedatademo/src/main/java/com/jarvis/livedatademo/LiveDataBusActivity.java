package com.jarvis.livedatademo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LiveDataBusActivity extends AppCompatActivity {

    private TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data_bus);
        tvTest = findViewById(R.id.tv_test);

        LiveDataBusX.getInstance().with("Test", String.class)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {

                        Toast.makeText(LiveDataBusActivity.this, s, Toast.LENGTH_SHORT).show();
                        tvTest.setText(s);
                    }
                });
    }
}