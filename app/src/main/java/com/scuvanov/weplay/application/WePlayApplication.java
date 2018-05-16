package com.scuvanov.weplay.application;

import android.app.Application;

import com.scuvanov.weplay.util.APIUtil;

public class WePlayApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //TODO: Perhaps verify if values exist in DB, if they do.. don't make another call.
        //Retrieve values from IGDB API
        APIUtil.getGenres(getApplicationContext());
        APIUtil.getPlatforms(getApplicationContext());
        APIUtil.getEsrbs(getApplicationContext());
    }
}
