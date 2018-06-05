package com.scuvanov.weplay.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.scuvanov.weplay.entity.Company;

import java.util.List;

@Dao
public interface CompanyDao {

    @Query("SELECT * FROM Company ORDER BY name ASC")
    LiveData<List<Company>> getAll();

    @Query("SELECT * FROM Company ORDER BY name ASC")
    List<Company> getAllAsList();

    @Query("SELECT * FROM Company WHERE id IN (:companyIds)")
    List<Company> loadAllByIds(int[] companyIds);

    @Query("SELECT * FROM Company WHERE name LIKE :name LIMIT 1")
    Company findByName(String name);

    @Query("SELECT * FROM Company WHERE id IN (:id) LIMIT 1")
    Company findById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
        //If there is a conflict, replace the record.
    void insertAll(Company... companys);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
        //If there is a conflict, replace the record.
    void insertCompany(Company company);

    @Delete
    void delete(Company company);

    @Query("DELETE FROM Company")
    void deleteAll();

}
