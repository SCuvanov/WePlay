package com.scuvanov.weplay.repository;

import android.content.Context;

import com.scuvanov.weplay.database.AppDatabase;

public class RepositoryFactory {

    public enum RepositoryType {
        GENRE,
        PLATFORM
    }

    private AppDatabase db;

    private RepositoryFactory() {}

    public RepositoryFactory(Context context) {
        this.db = AppDatabase.getAppDatabase(context);
    }

    public BaseRepository getRepository(RepositoryType repositoryType) {
        if (repositoryType == RepositoryType.GENRE) new GenreRepository(db.genreDao());
        if (repositoryType == RepositoryType.PLATFORM) new PlatformRepository(db.platformDao());
        return null;
    }
}
