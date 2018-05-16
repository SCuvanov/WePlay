package com.scuvanov.weplay.repository;

import android.content.Context;

import com.scuvanov.weplay.database.AppDatabase;

public class RepositoryFactory {

    private AppDatabase db;

    public RepositoryFactory(Context context) {
        this.db = AppDatabase.getAppDatabase(context);
    }


    //TODO: Build some type of factory method;

}
