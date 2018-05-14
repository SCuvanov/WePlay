package com.scuvanov.weplay.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.content.Context;

import com.scuvanov.weplay.database.AppDatabase;
import com.scuvanov.weplay.entity.Platform;

import java.util.List;

@Dao
public interface PlatformDao {

    @Query("SELECT * FROM platform")
    List<Platform> getAll();

    @Query("SELECT * FROM platform WHERE pid IN (:platformIds)")
    List<Platform> loadAllByIds(int[] platformIds);

    @Query("SELECT * FROM platform WHERE name LIKE :name LIMIT 1")
    Platform findByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
        //If there is a conflict, replace the record.
    void insertAll(Platform... platforms);

    @Delete
    void delete(Platform platform);
}
