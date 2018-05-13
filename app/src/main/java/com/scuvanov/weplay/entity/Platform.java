package com.scuvanov.weplay.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;

import com.scuvanov.weplay.database.AppDatabase;

@Entity(indices = {@Index(value = {"id", "name"}, unique = true)})
public class Platform {

    @PrimaryKey
    private int pid;

    //@ColumnInfo(name = "id") By Default, no need to annotate
    private int id;
    private String name;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Database Methods
    public static Platform addPlatform(Context context, Platform platform){
        AppDatabase db = AppDatabase.getAppDatabase(context.getApplicationContext());
        db.platformDao().insertAll(platform);

        return platform;
    }
}
