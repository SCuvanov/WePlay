package com.scuvanov.weplay.repository;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.scuvanov.weplay.dao.EsrbDao;
import com.scuvanov.weplay.database.AppDatabase;
import com.scuvanov.weplay.entity.Esrb;

import java.util.List;

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

    public LiveData<Esrb> findByName(String name) {
        return esrbDao.findByName(name);
    }

    public void delete(Esrb esrb) {
        esrbDao.delete(esrb);
    }
}
