package com.scuvanov.weplay.repository;

import android.content.Context;

import com.scuvanov.weplay.database.AppDatabase;

public class RepositoryFactory {

    public enum RepositoryType {
        GENRE,
        PLATFORM
    }

    private RepositoryFactory() {}

    public static BaseRepository getRepository(Context context, RepositoryType repositoryType) {
        AppDatabase db = AppDatabase.getAppDatabase(context);

        if (repositoryType == RepositoryType.GENRE) new GenreRepository(db.genreDao());
        if (repositoryType == RepositoryType.PLATFORM) new PlatformRepository(db.platformDao());
        return null;
    }
}
