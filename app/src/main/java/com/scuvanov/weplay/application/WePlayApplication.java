package com.scuvanov.weplay.application;

import android.app.Application;
import android.content.Context;

import com.scuvanov.weplay.util.APIUtil;

public class WePlayApplication extends Application {

    private static Context mContext;

    private static final String FIRST_OFFSET = "50";
    private static final String SECOND_OFFSET = "100";
    private static final String THIRD_OFFSET = "150";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        //Retrieve values from IGDB API
        APIUtil.getGenres(getApplicationContext());
        APIUtil.getEsrbs(getApplicationContext());
        APIUtil.getPlatforms(getApplicationContext(), null);
        APIUtil.getPlatforms(getApplicationContext(), FIRST_OFFSET);
        APIUtil.getPlatforms(getApplicationContext(), SECOND_OFFSET);
        APIUtil.getPlatforms(getApplicationContext(), THIRD_OFFSET);
        APIUtil.getGames(null);
    }

    public static Context getContext() {
        return mContext;
    }
}
