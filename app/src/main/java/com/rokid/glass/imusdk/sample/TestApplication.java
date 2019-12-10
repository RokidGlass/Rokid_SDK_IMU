package com.rokid.glass.imusdk.sample;

import android.app.Application;

import com.rokid.glass.imusdk.IMUSdk;

public class TestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        IMUSdk.init(this,true);
        IMUSdk.setVoiceWakeupState(true);
    }
}
