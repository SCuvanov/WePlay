package com.scuvanov.weplay.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.util.Log;

import com.scuvanov.weplay.application.WePlayApplication;
import com.scuvanov.weplay.entity.Esrb;
import com.scuvanov.weplay.entity.Game;
import com.scuvanov.weplay.entity.Genre;
import com.scuvanov.weplay.entity.Platform;
import com.scuvanov.weplay.repository.EsrbRepository;
import com.scuvanov.weplay.repository.GenreRepository;
import com.scuvanov.weplay.repository.PlatformRepository;
import com.scuvanov.weplay.repository.RepositoryFactory;
import com.scuvanov.weplay.util.APIUtil;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;

public class GameViewModel extends ViewModel {

    private List<Game> gamesList = new ArrayList<Game>();

    public GameViewModel() {}

    public void getGames(String title, GameCallback gameCallback) {
        new GetGamesTask(title).execute(gameCallback);
    }

    public List<Game> getGamesList() {
        return gamesList;
    }

    public void setGamesList(List<Game> gamesList) {
        this.gamesList = gamesList;
    }

    public interface GameCallback {
        void onSuccess(List<Game> games);
    }

    private class GetGamesTask extends AsyncTask<GameCallback, Void, Void> {

        private String title;

        public GetGamesTask(String title) {
            this.title = title;
        }

        @Override
        protected Void doInBackground(GameCallback... gameCallbacks) {
            APIUtil.getGames(WePlayApplication.getContext(), title, games -> {
                gamesList = games;
                gameCallbacks[0].onSuccess(games);
            });

            return null;
        }
    }


}
