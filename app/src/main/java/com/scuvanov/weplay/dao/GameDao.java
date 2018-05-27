package com.scuvanov.weplay.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.scuvanov.weplay.entity.Game;

import java.util.List;

@Dao
public interface GameDao {

    @Query("SELECT * FROM Game ORDER BY name ASC")
    LiveData<List<Game>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Game... game);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertGame(Game game);

    @Delete
    void delete(Game game);
}
