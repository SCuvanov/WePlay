package com.scuvanov.weplay.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.scuvanov.weplay.application.WePlayApplication;
import com.scuvanov.weplay.entity.Game;
import com.scuvanov.weplay.entity.Genre;
import com.scuvanov.weplay.entity.Platform;
import com.scuvanov.weplay.repository.GenreRepository;
import com.scuvanov.weplay.repository.PlatformRepository;
import com.scuvanov.weplay.repository.RepositoryFactory;
import com.scuvanov.weplay.util.APIUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GameViewModel extends ViewModel {

    private List<Game> gamesList = new ArrayList<Game>();

    private GenreRepository genreRepository;
    private PlatformRepository platformRepository;
    //private EsrbRepository esrbRepository;

    public GameViewModel() {
        this.genreRepository = RepositoryFactory.getGenreRepository();
        this.platformRepository = RepositoryFactory.getPlatformRepository();
        //this.esrbRepository = RepositoryFactory.getEsrbRepository();
    }

    public LiveData<List<Genre>> getObservableGenres(){
        return genreRepository.getAll();
    }

    public LiveData<List<Platform>> getObservablePlatforms(){
        return platformRepository.getAll();
    }

    public void getGames(String title, GameCallback gameCallback) {
        APIUtil.getGames(WePlayApplication.getContext(), title, new GameCallback() {
            @Override
            public void onSuccess(List<Game> games) {
                if(games == null || games.isEmpty()) return;

                gamesList.clear();
                gamesList.addAll(games);
                gameCallback.onSuccess(gamesList);
            }

            @Override
            public void onError(String message) {
                gameCallback.onError(message);
            }
        });
    }

    public List<Game> getGamesList() {
        return gamesList;
    }

    public void setGamesList(List<Game> gamesList) {
        this.gamesList = gamesList;
    }

    public interface GameCallback {
        void onSuccess(List<Game> games);

        void onError(String message);
    }
}
