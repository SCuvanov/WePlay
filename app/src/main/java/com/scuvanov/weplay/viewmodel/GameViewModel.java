package com.scuvanov.weplay.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.google.android.gms.common.api.Api;
import com.scuvanov.weplay.application.WePlayApplication;
import com.scuvanov.weplay.entity.Game;
import com.scuvanov.weplay.entity.Genre;
import com.scuvanov.weplay.entity.Platform;
import com.scuvanov.weplay.repository.GameRepository;
import com.scuvanov.weplay.repository.GenreRepository;
import com.scuvanov.weplay.repository.PlatformRepository;
import com.scuvanov.weplay.repository.RepositoryFactory;
import com.scuvanov.weplay.util.APIUtil;

import java.util.ArrayList;
import java.util.List;

public class GameViewModel extends ViewModel {

    private List<Game> gamesList = new ArrayList<Game>();
    private GameRepository gameRepository;

    public GameViewModel() {
        this.gameRepository = RepositoryFactory.getGameRepository();
    }

    public LiveData<List<Game>> getObservableGames() {
        return gameRepository.getAll();
    }

    public void getGames(String title) {
        APIUtil.getGames(title);
    }

    public List<Game> getGamesList() {
        return gamesList;
    }

    public void setGamesList(List<Game> gamesList) {
        this.gamesList.clear();
        this.gamesList.addAll(gamesList);
    }

    public interface GameCallback {
        void onSuccess(List<Game> games);
        void onError(String message);
    }
}
