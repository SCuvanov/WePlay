package com.scuvanov.weplay.application;

import android.app.Application;

import com.scuvanov.weplay.entity.Genre;
import com.scuvanov.weplay.util.APIUtil;

import java.util.List;

public class WePlayApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        APIUtil.getGenres(this);
        APIUtil.getPlatforms(this);
    }
}
