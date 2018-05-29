package com.scuvanov.weplay.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.scuvanov.weplay.entity.Esrb;

public class EsrbTypeConverter {

    @TypeConverter
    public static Esrb fromString(String esrbJson) {
        if (esrbJson == null) return null;

        Gson gson = new Gson();
        Esrb esrb = gson.fromJson(esrbJson, Esrb.class);
        return esrb;
    }

    @TypeConverter
    public static String toString(Esrb esrb) {
        if (esrb == null) return null;

        Gson gson = new Gson();
        String esrbJson = gson.toJson(esrb);
        return esrbJson;
    }


}
