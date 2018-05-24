package com.scuvanov.weplay.repository;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.scuvanov.weplay.dao.PlatformDao;
import com.scuvanov.weplay.database.AppDatabase;
import com.scuvanov.weplay.entity.Genre;
import com.scuvanov.weplay.entity.Platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<Integer, Platform> getAllAsMap() {
        Map<Integer, Platform> platformMap = new HashMap<Integer, Platform>();
        for (Platform p : platformDao.getAllAsList()) {
            platformMap.put(p.getId(), p);
        }
        return platformMap;
    }

    public List<String> getAllPlatformNames(int[] platformIds) {
        if(platformIds == null) return null;
        List<String> platformNames = new ArrayList<String>();
        for(Platform p: platformDao.loadAllByIds(platformIds)) {
            platformNames.add(p.getName());
        }
        return platformNames;
    }

    public Platform findByName(String name) {
        return platformDao.findByName(name);
    }

    public Platform findById(int id) {
        return platformDao.findById(id);
    }

    public void delete(Platform platform) {
        platformDao.delete(platform);
    }

}
