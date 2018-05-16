package com.scuvanov.weplay.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.scuvanov.weplay.database.AppDatabase;
import com.scuvanov.weplay.entity.Platform;

import java.util.List;

public class PlatformRepository {

    //Database Methods
    public void insertAll(Context context, Platform... platforms) {
        AsyncTask.execute(() -> { //Same as new Runnable()
            AppDatabase db = AppDatabase.getAppDatabase(context.getApplicationContext());
            db.platformDao().insertAll(platforms);
        });
    }

    public void insertPlatform(Context context, Platform platform) {
        AsyncTask.execute(() -> {
            AppDatabase db = AppDatabase.getAppDatabase(context.getApplicationContext());
            db.platformDao().insertAll(platform);
        });
    }

    public LiveData<List<Platform>> getAll(Context context) {
        AppDatabase db = AppDatabase.getAppDatabase(context.getApplicationContext());
        return db.platformDao().getAll();
    }

    public LiveData<Platform> findByName(Context context, String name) {
        AppDatabase db = AppDatabase.getAppDatabase(context.getApplicationContext());
        return db.platformDao().findByName(name);
    }

    public void delete(Context context, Platform platform) {
        AppDatabase db = AppDatabase.getAppDatabase(context.getApplicationContext());
        db.platformDao().delete(platform);
    }

}
