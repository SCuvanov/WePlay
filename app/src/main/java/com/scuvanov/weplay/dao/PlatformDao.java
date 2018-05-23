package com.scuvanov.weplay.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.scuvanov.weplay.entity.Platform;

import java.util.List;

@Dao
public interface PlatformDao {

    @Query("SELECT * FROM Platform ORDER BY name ASC")
    LiveData<List<Platform>> getAll();

    @Query("SELECT * FROM Platform ORDER BY name ASC")
    List<Platform> getAllAsList();

    @Query("SELECT * FROM Platform WHERE id IN (:platformIds)")
    List<Platform> loadAllByIds(int[] platformIds);

    @Query("SELECT * FROM Platform WHERE name LIKE :name LIMIT 1")
    Platform findByName(String name);

    @Query("SELECT * FROM Platform WHERE id IN (:id) LIMIT 1")
    Platform findById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE) //If there is a conflict, replace the record.
    void insertAll(Platform... platforms);

    @Insert(onConflict = OnConflictStrategy.REPLACE) //If there is a conflict, replace the record.
    void insertPlatform(Platform platform);

    @Delete
    void delete(Platform platform);
}
