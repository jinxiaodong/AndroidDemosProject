package com.jarvis.androiddemosproject;

import android.app.Application;



/**
 * @author jinxiaodong
 * @description：
 * @date 2021/11/14
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initLeakCanary();
    }

    private void initLeakCanary() {

//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return;
//        }
//        RefWatcher install = LeakCanary.install(this);
    }
}
