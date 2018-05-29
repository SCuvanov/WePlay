package com.scuvanov.weplay.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.scuvanov.weplay.entity.Screenshot;

public class ScreenshotTypeConverter {

    @TypeConverter
    public static Screenshot[] fromString(String screenshotsJson) {
        if (screenshotsJson == null) return null;

        Gson gson = new Gson();
        Screenshot[] screenshots = gson.fromJson(screenshotsJson, Screenshot[].class);
        return screenshots;
    }

    @TypeConverter
    public static String toString(Screenshot[] screenshots) {
        if (screenshots == null) return null;

        Gson gson = new Gson();
        String screenshotsJson = gson.toJson(screenshots);
        return screenshotsJson;
    }
}
