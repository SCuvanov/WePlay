package com.scuvanov.weplay.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

public class IntArrayTypeConverter {

    @TypeConverter
    public static int[] fromString(String arrayJson) {
        if (arrayJson == null) return null;

        Gson gson = new Gson();
        int[] array = gson.fromJson(arrayJson, int[].class);
        return array;
    }

    @TypeConverter
    public static String toString(int[] array) {
        if (array == null) return null;

        Gson gson = new Gson();
        String arrayJson = gson.toJson(array);
        return arrayJson;
    }
}
