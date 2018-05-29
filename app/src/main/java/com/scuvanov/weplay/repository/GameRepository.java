package com.scuvanov.weplay.repository;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.scuvanov.weplay.dao.GameDao;
import com.scuvanov.weplay.dao.GenreDao;
import com.scuvanov.weplay.dao.PlatformDao;
import com.scuvanov.weplay.database.AppDatabase;
import com.scuvanov.weplay.entity.Game;

import java.util.List;

public class GameRepository {

    private final GameDao gameDao;
    private final GenreRepository genreRepository;
    private final PlatformRepository platformRepository;

    public GameRepository() {
        this.gameDao = AppDatabase.getAppDatabase().gameDao();
        this.genreRepository = RepositoryFactory.getGenreRepository();
        this.platformRepository = RepositoryFactory.getPlatformRepository();
    }

    //Database Methods
    public void insertAll(Game... games) {
        AsyncTask.execute(() -> { //Same as new Runnable()
            for(Game g : games){
                g.setGenreNames(genreRepository.getGenreNamesAsString(g.getGenres()));
                g.setPlatformNames(platformRepository.getPlatformNamesAsString(g.getPlatforms()));
            }
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

    public void deleteAndInsertAll(Game... games) {
        AsyncTask.execute(() -> {
            gameDao.deleteAll();

            for(Game g : games){
                g.setGenreNames(genreRepository.getGenreNamesAsString(g.getGenres()));
                g.setPlatformNames(platformRepository.getPlatformNamesAsString(g.getPlatforms()));
            }
            gameDao.insertAll(games);
        });
    }
}
