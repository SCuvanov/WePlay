package com.scuvanov.weplay.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

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

import java.util.ArrayList;
import java.util.List;

public class GameViewModel extends ViewModel {

    private GenreRepository genreRepository;
    private PlatformRepository platformRepository;
    private EsrbRepository esrbRepository;

    private List<Game> gameList = new ArrayList<Game>();

    public GameViewModel() {
        this.genreRepository = RepositoryFactory.getGenreRepository();
        this.platformRepository = RepositoryFactory.getPlatformRepository();
        this.esrbRepository = RepositoryFactory.getEsrbRepository();
    }

    public List<Game> getGames(String title, String genre, String platform, String esrb, Integer rangeLower, Integer rangeUpper) {
        LiveData<Genre> genreLiveData = genreRepository.findByName(genre);
        LiveData<Platform> platformLiveData = platformRepository.findByName(platform);
        LiveData<Esrb> esrbLiveData = esrbRepository.findByName(esrb);

        //TODO: This may cause null pointer exception
        Integer genreId = genreLiveData.getValue().getId();
        Integer platformId = platformLiveData.getValue().getId();
        Integer esrbId = esrbLiveData.getValue().getId();

        APIUtil.getGames(WePlayApplication.getContext(), title, genreId, platformId, esrbId, rangeLower, rangeUpper);
        return null;
    }


    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }
}
