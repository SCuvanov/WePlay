package com.scuvanov.weplay.repository;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.scuvanov.weplay.dao.EsrbDao;
import com.scuvanov.weplay.database.AppDatabase;
import com.scuvanov.weplay.entity.Esrb;
import com.scuvanov.weplay.entity.Genre;
import com.scuvanov.weplay.entity.Platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EsrbRepository extends BaseRepository {

    private final EsrbDao esrbDao;

    public EsrbRepository() {
        this.esrbDao = AppDatabase.getAppDatabase().esrbDao();
    }

    //Database Methods
    public void insertAll(Esrb... esrbs) {
        AsyncTask.execute(() -> { //Same as new Runnable()
            esrbDao.insertAll(esrbs);
        });
    }

    public void insertEsrb(Esrb esrb) {
        AsyncTask.execute(() -> {
            esrbDao.insertEsrb(esrb);
        });
    }

    public LiveData<List<Esrb>> getAll() {
        return esrbDao.getAll();
    }

    public Map<Integer, Esrb> getAllAsMap() {
        Map<Integer, Esrb> esrbMap = new HashMap<Integer, Esrb>();
        for (Esrb e : esrbDao.getAllAsList()) {
            esrbMap.put(e.getId(), e);
        }
        return esrbMap;
    }

    public Esrb findByName(String name) {
        return esrbDao.findByName(name);
    }

    public Esrb findById(int id) {
        return esrbDao.findById(id);
    }

    public void delete(Esrb esrb) {
        esrbDao.delete(esrb);
    }
}
