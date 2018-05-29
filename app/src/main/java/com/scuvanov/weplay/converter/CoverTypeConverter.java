package com.scuvanov.weplay.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.scuvanov.weplay.entity.Cover;

public class CoverTypeConverter {

    @TypeConverter
    public static Cover fromString(String coverJson) {
        if (coverJson == null) return null;

        Gson gson = new Gson();
        Cover cover = gson.fromJson(coverJson, Cover.class);
        return cover;
    }

    @TypeConverter
    public static String toString(Cover cover) {
        if (cover == null) return null;

        Gson gson = new Gson();
        String coverJson = gson.toJson(cover);
        return coverJson;
    }
}
