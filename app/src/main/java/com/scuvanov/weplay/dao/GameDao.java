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

    @Query("SELECT * FROM Game ORDER BY name DESC")
    LiveData<List<Game>> getAll();

    @Query("SELECT * FROM Game ORDER BY name DESC")
    List<Game> getAllList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Game... game);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertGame(Game game);

    @Delete
    void delete(Game game);

    @Query("DELETE FROM Game")
    void deleteAll();
}
