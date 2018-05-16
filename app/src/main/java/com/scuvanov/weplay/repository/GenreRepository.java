package com.scuvanov.weplay.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.scuvanov.weplay.dao.GenreDao;
import com.scuvanov.weplay.database.AppDatabase;
import com.scuvanov.weplay.entity.Genre;

import java.util.List;

public class GenreRepository extends BaseRepository {

    private final GenreDao genreDao;

    public GenreRepository(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    //Database Methods
    public void insertAll(Genre... genres) {
        AsyncTask.execute(() -> { //Same as new Runnable()
            genreDao.insertAll(genres);
        });
    }

    public void insertGenre(Genre genre) {
        AsyncTask.execute(() -> {
            genreDao.insertGenre(genre);
        });
    }

    public LiveData<List<Genre>> getAll() {
        return genreDao.getAll();
    }

    public LiveData<Genre> findByName(String name) {
        return genreDao.findByName(name);
    }

    public void delete(Genre genre) {
        genreDao.delete(genre);
    }
}
