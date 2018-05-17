package com.scuvanov.weplay.repository;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.scuvanov.weplay.dao.PlatformDao;
import com.scuvanov.weplay.database.AppDatabase;
import com.scuvanov.weplay.entity.Platform;

import java.util.List;

public class PlatformRepository extends BaseRepository {

    private PlatformDao platformDao;

    public PlatformRepository() {
        this.platformDao = AppDatabase.getAppDatabase().platformDao();
    }

    //Database Methods
    public void insertAll(Platform... platforms) {
        AsyncTask.execute(() -> { //Same as new Runnable()
            platformDao.insertAll(platforms);
        });
    }

    public void insertPlatform(Platform platform) {
        AsyncTask.execute(() -> {
            platformDao.insertPlatform(platform);
        });
    }

    public LiveData<List<Platform>> getAll() {
        return platformDao.getAll();
    }

    public LiveData<Platform> findByName(String name) {
        return platformDao.findByName(name);
    }

    public void delete(Platform platform) {
        platformDao.delete(platform);
    }

}
