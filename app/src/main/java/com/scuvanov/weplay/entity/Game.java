package com.scuvanov.weplay.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.scuvanov.weplay.converter.CoverTypeConverter;
import com.scuvanov.weplay.converter.EsrbTypeConverter;
import com.scuvanov.weplay.converter.IntArrayTypeConverter;
import com.scuvanov.weplay.converter.ScreenshotTypeConverter;

@Entity(indices = {@Index(value = {"id"}, unique = true)})
public class Game {

    @PrimaryKey
    @NonNull
    private int id;
    private int created_at, updated_at, rating_count;
    private String name, url, genreNames, platformNames, developerNames;
    private double rating;

    @TypeConverters(IntArrayTypeConverter.class)
    private int[] platforms, genres, developers;
    @TypeConverters(CoverTypeConverter.class)
    private Cover cover;
    @TypeConverters(EsrbTypeConverter.class)
    private Esrb esrb;
    @TypeConverters(ScreenshotTypeConverter.class)
    private Screenshot[] screenshots;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRating_count() {
        return rating_count;
    }

    public void setRating_count(int rating_count) {
        this.rating_count = rating_count;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int[] getPlatforms() {
        return platforms;
    }

    public void setPlatforms(int[] platforms) {
        this.platforms = platforms;
    }

    public int[] getGenres() {
        return genres;
    }

    public void setGenres(int[] genres) {
        this.genres = genres;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public Esrb getEsrb() {
        return esrb;
    }

    public void setEsrb(Esrb esrb) {
        this.esrb = esrb;
    }

    public Screenshot[] getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(Screenshot[] screenshots) {
        this.screenshots = screenshots;
    }

    public int[] getDevelopers() {
        return developers;
    }

    public void setDevelopers(int[] developers) {
        this.developers = developers;
    }

    public String getGenreNames() {
        return genreNames;
    }

    public void setGenreNames(String genreNames) {
        this.genreNames = genreNames;
    }

    public String getPlatformNames() {
        return platformNames;
    }

    public void setPlatformNames(String platformNames) {
        this.platformNames = platformNames;
    }

    public String getDeveloperNames() {
        return developerNames;
    }

    public void setDeveloperNames(String developerNames) {
        this.developerNames = developerNames;
    }
}
