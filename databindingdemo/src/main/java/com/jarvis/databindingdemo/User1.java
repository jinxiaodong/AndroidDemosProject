package com.jarvis.databindingdemo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * @author jinxiaodong
 * @description：
 * @date 2021/8/5
 */
public class User1 extends BaseObservable {

    private String name ;
    private String pwd;

    public User1(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }


    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
        notifyPropertyChanged(BR.pwd);
    }
}
