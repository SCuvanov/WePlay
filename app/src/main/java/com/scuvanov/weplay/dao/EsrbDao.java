package com.scuvanov.weplay.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.scuvanov.weplay.entity.Esrb;

import java.util.List;

@Dao
public interface EsrbDao {

    @Query("SELECT * FROM Esrb")
    LiveData<List<Esrb>> getAll();

    @Query("SELECT * FROM Esrb WHERE eid IN (:esrbIds)")
    LiveData<List<Esrb>> loadAllByIds(int[] esrbIds);

    @Query("SELECT * FROM Esrb WHERE name LIKE :name LIMIT 1")
    LiveData<Esrb> findByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Esrb... esrbs);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertEsrb(Esrb esrb);

    @Delete
    void delete(Esrb esrb);
}
