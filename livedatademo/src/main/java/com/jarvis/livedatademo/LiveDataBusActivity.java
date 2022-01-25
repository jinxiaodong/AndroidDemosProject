package com.jarvis.livedatademo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
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

                        Log.i("LiveDataBusX", s);
                        Toast.makeText(LiveDataBusActivity.this, s, Toast.LENGTH_SHORT).show();
                        tvTest.setText(s);
                    }
                });
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    //livedata发消息通知所有的观察者数据变化了
                    LiveDataBusX.getInstance().with("Test",String.class).postValue("Hello"+i);
//                        try {
//                            Thread.sleep(4000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                }
            }
        }.start();
    }
}