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

    @Query("SELECT * FROM Genre ORDER BY name ASC")
    LiveData<List<Genre>> getAll();

    @Query("SELECT * FROM Genre ORDER BY name ASC")
    List<Genre> getAllAsList();

    @Query("SELECT * FROM Genre WHERE id IN (:genreIds)")
    List<Genre> loadAllByIds(int[] genreIds);

    @Query("SELECT * FROM Genre WHERE name LIKE :name LIMIT 1")
    Genre findByName(String name);

    @Query("SELECT * FROM Genre WHERE id IN (:id) LIMIT 1")
    Genre findById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE) //If there is a conflict, replace the record.
    void insertAll(Genre... genres);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertGenre(Genre genre);

    @Delete
    void delete(Genre genre);
}
