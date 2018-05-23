package com.scuvanov.weplay.entity;

import java.util.List;

public class Game {

    int id, created_at, updated_at, rating_count, hypes;
    int[] platforms, genres;
    List<String> genreNames, platformNames;
    String name, slug, url, esrbName;
    double rating;

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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

    public int getHypes() {
        return hypes;
    }

    public void setHypes(int hypes) {
        this.hypes = hypes;
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

    public List<String> getGenreNames() {
        return genreNames;
    }

    public void setGenreNames(List<String> genreNames) {
        this.genreNames = genreNames;
    }

    public List<String> getPlatformNames() {
        return platformNames;
    }

    public void setPlatformNames(List<String> platformNames) {
        this.platformNames = platformNames;
    }

    public String getEsrbName() {
        return esrbName;
    }

    public void setEsrbName(String esrbName) {
        this.esrbName = esrbName;
    }
}
