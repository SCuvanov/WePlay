package com.scuvanov.weplay.repository;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.google.common.primitives.Ints;
import com.scuvanov.weplay.dao.GameDao;
import com.scuvanov.weplay.database.AppDatabase;
import com.scuvanov.weplay.entity.Game;
import com.scuvanov.weplay.util.APIUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameRepository {

    private final GameDao gameDao;
    private final GenreRepository genreRepository;
    private final PlatformRepository platformRepository;
    private final CompanyRepository companyRepository;

    public GameRepository() {
        this.gameDao = AppDatabase.getAppDatabase().gameDao();
        this.genreRepository = RepositoryFactory.getGenreRepository();
        this.platformRepository = RepositoryFactory.getPlatformRepository();
        this.companyRepository = RepositoryFactory.getCompanyRepository();
    }

    //Database Methods
    public void insertAll(Game... games) {
        processGames(false, games);
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
        processGames(true, games);
    }

    private void processGames(Boolean delete, Game... games) {
        AsyncTask.execute(() -> {
            int[] companyIds = new int[]{};
            Set<Integer> companyIdSet = new HashSet<Integer>();
            for (Game g : games) {
                if(g.getDevelopers() == null || g.getDevelopers().length <= 0) continue;
                for(int i : g.getDevelopers()){
                    companyIdSet.add(i);
                }
            }

            companyIds = Ints.toArray(companyIdSet);

            APIUtil.getCompanies(companyIds);

            if (delete) gameDao.deleteAll();

            for (Game g : games) {
                g.setGenreNames(genreRepository.getGenreNamesAsString(g.getGenres()));
                //Set Developer Names
                g.setDeveloperNames(companyRepository.getCompanyNamesAsString(companyIds));
            }
            gameDao.insertAll(games);
        });
    }
}
