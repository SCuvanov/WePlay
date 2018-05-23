package com.scuvanov.weplay.entity;

import java.util.List;

public class GameCollection {

    private int id;
    private List<Game> gameList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }
}
