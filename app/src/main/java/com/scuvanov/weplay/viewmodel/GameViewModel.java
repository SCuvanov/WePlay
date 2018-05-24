package com.scuvanov.weplay.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.scuvanov.weplay.application.WePlayApplication;
import com.scuvanov.weplay.entity.Game;
import com.scuvanov.weplay.repository.GenreRepository;
import com.scuvanov.weplay.repository.PlatformRepository;
import com.scuvanov.weplay.repository.RepositoryFactory;
import com.scuvanov.weplay.util.APIUtil;

import java.util.ArrayList;
import java.util.List;

public class GameViewModel extends ViewModel {

    private List<Game> gamesList = new ArrayList<Game>();

    public GameViewModel() {
    }

    public void getGames() {
        getGames(null, null);
    }

    public void getGames(String title, GameCallback gameCallback) {
        new GetGamesTask(title).execute(gameCallback);
    }

    public List<Game> getGamesList() {
        if (gamesList == null || gamesList.isEmpty()) {
            getGames(null, null);
        }
        return gamesList;
    }

    public void setGamesList(List<Game> gamesList) {
        this.gamesList = gamesList;
    }

    public interface GameCallback {
        void onSuccess(List<Game> games);

        void onError(String message);
    }

    private class GetGamesTask extends AsyncTask<GameCallback, Void, Void> {

        private String title;

        private GenreRepository genreRepository;
        private PlatformRepository platformRepository;
        //private EsrbRepository esrbRepository;

        public GetGamesTask(String title) {
            this.genreRepository = RepositoryFactory.getGenreRepository();
            this.platformRepository = RepositoryFactory.getPlatformRepository();
            //this.esrbRepository = RepositoryFactory.getEsrbRepository();

            this.title = title;
        }

        @Override
        protected Void doInBackground(GameCallback... gameCallbacks) {
            APIUtil.getGames(WePlayApplication.getContext(), title, new GameCallback() {
                @Override
                public void onSuccess(List<Game> games) {
                    if (games == null || !games.isEmpty()) {
                        for (Game game : games) {
                            game.setGenreNames(genreRepository.getAllGenreNames(game.getGenres()));
                            game.setPlatformNames(platformRepository.getAllPlatformNames(game.getPlatforms()));
                        }

                        if (games != null && !games.isEmpty()) {
                            gamesList.clear();
                            gamesList.addAll(games);
                        }

                        if (gameCallbacks != null && gameCallbacks.length > 0) {
                            gameCallbacks[0].onSuccess(gamesList);
                        }
                    }
                }

                @Override
                public void onError(String message) {
                    gameCallbacks[0].onError(message);
                }
            });

            return null;
        }
    }
}
