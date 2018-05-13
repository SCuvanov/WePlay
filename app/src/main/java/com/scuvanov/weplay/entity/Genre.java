package com.scuvanov.weplay.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;

import com.scuvanov.weplay.database.AppDatabase;

@Entity(indices = {@Index(value = {"id", "name"}, unique = true)})
public class Genre {

    @PrimaryKey
    private int gid;

    //@ColumnInfo(name = "id") By Default - No need to annotate
    private int id;
    private String name;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
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
    public static Genre addGenre(Context context, Genre genre){
        AppDatabase db = AppDatabase.getAppDatabase(context.getApplicationContext());
        db.genreDao().insertAll(genre);

        return genre;
    }
}
