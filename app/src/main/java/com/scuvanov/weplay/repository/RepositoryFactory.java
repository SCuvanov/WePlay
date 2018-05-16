package com.scuvanov.weplay.repository;

import android.content.Context;

import com.scuvanov.weplay.database.AppDatabase;

public class RepositoryFactory {

    public enum RepositoryType {
        GENRE,
        PLATFORM,
        ESRB
    }

    private RepositoryFactory() {}

    public static GenreRepository getGenreRepository(Context context) {
        AppDatabase db = AppDatabase.getAppDatabase(context);
        return new GenreRepository(db.genreDao());
    }

    public static PlatformRepository getPlatformRepository(Context context){
        AppDatabase db = AppDatabase.getAppDatabase(context);
        return new PlatformRepository(db.platformDao());
    }

    public static EsrbRepository getEsrbRepository(Context context){
        AppDatabase db = AppDatabase.getAppDatabase(context);
        return new EsrbRepository(db.esrbDao());
    }
}
