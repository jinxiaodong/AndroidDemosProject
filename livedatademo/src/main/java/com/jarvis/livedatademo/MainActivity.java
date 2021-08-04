package com.jarvis.livedatademo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {

        int id = view.getId();
        if (id == R.id.btn) {
            Intent intent = new Intent(this, NameActivity.class);
            startActivity(intent);
        } else if (id == R.id.btn2) {
            Intent intent = new Intent(this, LiveDataBusActivity.class);
            new Thread(){
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        //livedata发消息通知所有的观察者数据变化了
                        LiveDataBusX.getInstance().with("Test",String.class).postValue("Hello"+i);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
            startActivity(intent);
        }
    }
}