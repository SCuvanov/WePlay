package com.scuvanov.weplay.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.scuvanov.weplay.application.WePlayApplication;
import com.scuvanov.weplay.dao.EsrbDao;
import com.scuvanov.weplay.dao.GenreDao;
import com.scuvanov.weplay.dao.PlatformDao;
import com.scuvanov.weplay.entity.Esrb;
import com.scuvanov.weplay.entity.Genre;
import com.scuvanov.weplay.entity.Platform;

@Database(entities = {Genre.class, Platform.class, Esrb.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    private static final String DB_NAME = "weplay-database";

    public abstract GenreDao genreDao();
    public abstract PlatformDao platformDao();
    public abstract EsrbDao esrbDao();

    public static AppDatabase getAppDatabase() {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(WePlayApplication.getContext(),
                    AppDatabase.class, DB_NAME).build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
