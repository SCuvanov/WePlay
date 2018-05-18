package com.scuvanov.weplay.application;

import android.app.Application;
import android.content.Context;

import com.scuvanov.weplay.util.APIUtil;

public class WePlayApplication extends Application {

    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        //TODO: Perhaps verify if values exist in DB, if they do.. don't make another call.
        //Retrieve values from IGDB API
        APIUtil.getGenres(getApplicationContext());
        APIUtil.getPlatforms(getApplicationContext(), null);
        APIUtil.getPlatforms(getApplicationContext(), "50");
        APIUtil.getPlatforms(getApplicationContext(), "100");
        APIUtil.getEsrbs(getApplicationContext());
    }

    public static Context getContext() {
        return mContext;
    }
}
