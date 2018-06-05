package com.scuvanov.weplay.repository;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.scuvanov.weplay.dao.CompanyDao;
import com.scuvanov.weplay.database.AppDatabase;
import com.scuvanov.weplay.entity.Company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyRepository {

    private CompanyDao companyDao;

    public CompanyRepository() {
        this.companyDao = AppDatabase.getAppDatabase().companyDao();
    }

    public void insertAll(Company... companys) {
        AsyncTask.execute(() -> { //Same as new Runnable()
            companyDao.insertAll(companys);
        });
    }

    public void insertCompany(Company company) {
        AsyncTask.execute(() -> {
            companyDao.insertCompany(company);
        });
    }

    public LiveData<List<Company>> getAll() {
        return companyDao.getAll();
    }

    public Map<Integer, Company> getAllAsMap() {
        Map<Integer, Company> companyMap = new HashMap<Integer, Company>();
        for (Company p : companyDao.getAllAsList()) {
            companyMap.put(p.getId(), p);
        }
        return companyMap;
    }

    public List<String> getAllCompanyNames(int[] companyIds) {
        if (companyIds == null) return null;
        List<String> companyNames = new ArrayList<String>();
        for (Company p : companyDao.loadAllByIds(companyIds)) {
            companyNames.add(p.getName());
        }
        return companyNames;
    }

    public String getCompanyNamesAsString(int[] companyIds) {
        if (companyIds == null) return null;
        String companyNames = "";
        List<Company> companys = companyDao.loadAllByIds(companyIds);
        for (Company p : companys) {
            if (companys.size() == 1)
                companyNames = companyNames.concat(p.getName());
            else
                companyNames = companyNames.concat(p.getName() + ", ");

        }
        return companyNames;
    }

    public Company findByName(String name) {
        return companyDao.findByName(name);
    }

    public Company findById(int id) {
        return companyDao.findById(id);
    }

    public void delete(Company company) {
        companyDao.delete(company);
    }

    public void deleteAndInsertAll(Company... companies) {
        AsyncTask.execute(() -> {
            companyDao.deleteAll();
            companyDao.insertAll(companies);
        });
    }
}
