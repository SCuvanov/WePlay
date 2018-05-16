package com.scuvanov.weplay.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.scuvanov.weplay.entity.Genre;

import java.util.List;

@Dao
public interface GenreDao {

    @Query("SELECT * FROM Genre")
    LiveData<List<Genre>> getAll();

    @Query("SELECT * FROM Genre WHERE gid IN (:genreIds)")
    LiveData<List<Genre>> loadAllByIds(int[] genreIds);

    @Query("SELECT * FROM Genre WHERE name LIKE :name LIMIT 1")
    LiveData<Genre> findByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE) //If there is a conflict, replace the record.
    void insertAll(Genre... genres);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertGenre(Genre genre);

    @Delete
    void delete(Genre genre);
}
