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

import org.apache.commons.lang3.StringUtils;

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
        LiveData<Genre> genreLiveData = null;
        LiveData<Platform> platformLiveData = null;
        LiveData<Esrb> esrbLiveData = null;

        if(!StringUtils.isBlank(genre)) genreLiveData = genreRepository.findByName(genre);
        if(!StringUtils.isBlank(platform)) platformLiveData = platformRepository.findByName(platform);
        if(!StringUtils.isBlank(esrb)) esrbLiveData = esrbRepository.findByName(esrb);

        Integer genreId = null;
        Integer platformId = null;
        Integer esrbId = null;

        if(genreLiveData != null) genreId = genreLiveData.getValue().getId();
        if(platformLiveData != null) platformId = platformLiveData.getValue().getId();
        if(esrbLiveData != null) esrbId = esrbLiveData.getValue().getId();

        List<Game> tempGames = APIUtil.getGames(WePlayApplication.getContext(), title, genreId, platformId, esrbId, rangeLower, rangeUpper);
        if(tempGames != null && !tempGames.isEmpty())
            gameList = tempGames;
        return gameList;
    }


    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }
}
