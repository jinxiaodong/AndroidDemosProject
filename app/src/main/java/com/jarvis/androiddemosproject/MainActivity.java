package com.jarvis.androiddemosproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;


import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends Activity {


    AtomicInteger atomicInteger = new AtomicInteger();
    private static int count = 0;

    private static volatile Boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv = findViewById(R.id.iv);
        Object object = new Object();
        MyThread t1 = new MyThread(object);
        MyThread t2 = new MyThread(object);
        t1.start();
        t2.start();

    }



    static class MyThread extends Thread {

        private final Object object;

        public MyThread(Object object) {
            this.object = object;
        }

        @Override
        public void run() {
            super.run();
            synchronized (object) {
                while (true) {
                    try {
                        Log.i("TAG", Thread.currentThread().getName() + count++);
                        object.notifyAll();
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
