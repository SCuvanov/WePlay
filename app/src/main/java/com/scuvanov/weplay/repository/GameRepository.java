package com.scuvanov.weplay.repository;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.scuvanov.weplay.dao.GameDao;
import com.scuvanov.weplay.database.AppDatabase;
import com.scuvanov.weplay.entity.Game;

import java.util.List;

public class GameRepository {

    private final GameDao gameDao;

    public GameRepository() {
        this.gameDao = AppDatabase.getAppDatabase().gameDao();
    }

    //Database Methods
    public void insertAll(Game... games) {
        AsyncTask.execute(() -> { //Same as new Runnable()
            gameDao.insertAll(games);
        });
    }

    public void insertGame(Game game) {
        AsyncTask.execute(() -> {
            gameDao.insertGame(game);
        });
    }

    public LiveData<List<Game>> getAll() {
        return gameDao.getAll();
    }

    public void delete(Game game) {
        gameDao.delete(game);
    }
}
