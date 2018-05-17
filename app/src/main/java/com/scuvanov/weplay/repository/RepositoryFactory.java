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

    public static GenreRepository getGenreRepository() {
        return new GenreRepository();
    }

    public static PlatformRepository getPlatformRepository(){
        return new PlatformRepository();
    }

    public static EsrbRepository getEsrbRepository(){
        return new EsrbRepository();
    }
}
