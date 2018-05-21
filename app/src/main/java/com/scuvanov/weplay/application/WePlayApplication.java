package com.scuvanov.weplay.application;

import android.app.Application;
import android.content.Context;

import com.scuvanov.weplay.util.APIUtil;

public class WePlayApplication extends Application {

    private static Context mContext;

    private static final String FIRST_OFFSET = "50";
    private static final String SECOND_OFFSET = "100";


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        //TODO: Perhaps verify if values exist in DB, if they do.. don't make another call.
        //Retrieve values from IGDB API
        APIUtil.getGenres(getApplicationContext());
        APIUtil.getPlatforms(getApplicationContext(), null);
        APIUtil.getPlatforms(getApplicationContext(), FIRST_OFFSET);
        APIUtil.getPlatforms(getApplicationContext(), SECOND_OFFSET);
        APIUtil.getEsrbs(getApplicationContext());
    }

    public static Context getContext() {
        return mContext;
    }
}
