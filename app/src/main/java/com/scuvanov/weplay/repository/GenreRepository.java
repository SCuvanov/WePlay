package com.scuvanov.weplay.repository;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.scuvanov.weplay.dao.GenreDao;
import com.scuvanov.weplay.database.AppDatabase;
import com.scuvanov.weplay.entity.Genre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenreRepository extends BaseRepository {

    private final GenreDao genreDao;

    public GenreRepository() {
        this.genreDao = AppDatabase.getAppDatabase().genreDao();
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

    public Map<Integer, Genre> getAllAsMap() {
        Map<Integer, Genre> genreMap = new HashMap<Integer, Genre>();
        for (Genre g : genreDao.getAllAsList()) {
            genreMap.put(g.getId(), g);
        }
        return genreMap;
    }

    public List<String> getAllGenreNames(int[] genreIds) {
        if(genreIds == null) return null;
        List<String> genreNames = new ArrayList<String>();
        for(Genre g: genreDao.loadAllByIds(genreIds)) {
            genreNames.add(g.getName());
        }
        return genreNames;
    }

    public String getGenreNamesAsString(int[] genreIds) {
        if(genreIds == null) return null;
        String genreNames = "";
        List<Genre> genres = genreDao.loadAllByIds(genreIds);
        for(Genre g : genres) {
            if(genres.size() == 1)
                genreNames = genreNames.concat(g.getName());
            else
                genreNames = genreNames.concat(g.getName() + ", ");
        }
        return genreNames;
    }

    public List<Genre> loadAllByIds(int[] genreIds) {
        return genreDao.loadAllByIds(genreIds);
    }

    public Genre findByName(String name) {
        return genreDao.findByName(name);
    }

    public Genre findById(int id) {
        return genreDao.findById(id);
    }

    public void delete(Genre genre) {
        genreDao.delete(genre);
    }
}
