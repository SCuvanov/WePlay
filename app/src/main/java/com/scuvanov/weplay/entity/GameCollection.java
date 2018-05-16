package com.scuvanov.weplay.entity;

import java.util.List;

public class GameCollection {

    private int gcid;
    private List<Game> gameList;

    public int getGcid() {
        return gcid;
    }

    public void setGcid(int gcid) {
        this.gcid = gcid;
    }
}
