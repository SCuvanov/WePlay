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

    public void getGames(String title, String genre, String platform, String esrb, Integer rangeLower, Integer rangeUpper, GameCallback gameCallback) {
        new GetGamesTask(title, genre, platform, esrb, rangeLower, rangeUpper).execute(gameCallback);
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
        private GenreRepository genreRepository;
        private PlatformRepository platformRepository;
        private EsrbRepository esrbRepository;

        private String title, genreName, platformName, esrbName;
        private Integer rangeLower, rangeUpper;

        public GetGamesTask(String title, String genreName, String platformName, String esrbName, Integer rangeLower, Integer rangeUpper) {
            this.title = title;
            this.genreName = genreName;
            this.platformName = platformName;
            this.esrbName = esrbName;
            this.rangeLower = rangeLower;
            this.rangeUpper = rangeUpper;

            this.genreRepository = RepositoryFactory.getGenreRepository();
            this.platformRepository = RepositoryFactory.getPlatformRepository();
            this.esrbRepository = RepositoryFactory.getEsrbRepository();
        }


        @Override
        protected Void doInBackground(GameCallback... gameCallbacks) {
            Genre genreData = genreRepository.findByName(genreName);
            Platform platformData = platformRepository.findByName(platformName);
            Esrb esrbData = esrbRepository.findByName(esrbName);

            Integer genreId = null;
            Integer platformId = null;
            Integer esrbId = null;

            if(genreData != null) genreId = genreData.getId();
            if(platformData != null) platformId = platformData.getId();
            if(esrbData != null) esrbId = esrbData.getId();

            APIUtil.getGames(WePlayApplication.getContext(), title, genreId, platformId, esrbId, rangeLower, rangeUpper, games -> {
                gamesList = games;
                Log.e("DO IN BACKGROUND", gamesList.toString());
                gameCallbacks[0].onSuccess(games);
            });

            return null;
        }
    }


}
