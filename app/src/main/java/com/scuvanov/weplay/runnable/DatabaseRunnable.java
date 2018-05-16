package com.scuvanov.weplay.runnable;

import com.scuvanov.weplay.entity.Genre;
import com.scuvanov.weplay.entity.Platform;

import java.util.List;

public class DatabaseRunnable implements Runnable {
    private DatabaseCallback callback;

    public DatabaseRunnable(DatabaseCallback callback) {
        this.callback = callback;
    }

    @Override
    public void run() {

    }


    public interface DatabaseCallback {
        List<Genre> onGenreSuccess();
        List<Platform> onPlatformSuccess();
    }



}
