package com.scuvanov.weplay.repository;

public class RepositoryFactory {

    //TODO: Make this a singleton, the repositories should be singletons?

    public enum RepositoryType {
        GENRE,
        PLATFORM,
        ESRB
    }

    private RepositoryFactory() {
    }

    public static GenreRepository getGenreRepository() {
        return new GenreRepository();
    }

    public static PlatformRepository getPlatformRepository() {
        return new PlatformRepository();
    }

    public static EsrbRepository getEsrbRepository() {
        return new EsrbRepository();
    }

    public static GameRepository getGameRepository() {
        return new GameRepository();
    }

    public static CompanyRepository getCompanyRepository() {
        return new CompanyRepository();
    }
}
