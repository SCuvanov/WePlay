package com.scuvanov.weplay.application;

import android.app.Application;

import com.scuvanov.weplay.util.APIUtil;

public class WePlayApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Retrieve values from Database
        APIUtil.getGenres(getApplicationContext());
        APIUtil.getPlatforms(getApplicationContext());
    }
}
