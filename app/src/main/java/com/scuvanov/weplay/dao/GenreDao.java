package com.scuvanov.weplay.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.scuvanov.weplay.entity.Genre;

import java.util.List;

@Dao
public interface GenreDao {

    @Query("SELECT * FROM genre")
    List<Genre> getAll();

    @Query("SELECT * FROM genre WHERE gid IN (:genreIds)")
    List<Genre> loadAllByIds(int[] genreIds);

    @Query("SELECT * FROM genre WHERE name LIKE :name LIMIT 1")
    Genre findByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE) //If there is a conflict, replace the record.
    void insertAll(Genre... genres);

    @Delete
    void delete(Genre genre);
}
