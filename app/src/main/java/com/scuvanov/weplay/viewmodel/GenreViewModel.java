package com.scuvanov.weplay.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.scuvanov.weplay.entity.Genre;
import com.scuvanov.weplay.repository.GenreRepository;

import java.util.List;

public class GenreViewModel extends ViewModel {

    private GenreRepository genreRepository;

    public GenreViewModel(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public void insertAll(Genre... genres){
        genreRepository.insertAll(genres);
    }

    public void insertGenre(Genre genre){
        genreRepository.insertGenre(genre);
    }

    public LiveData<List<Genre>> getAll(){
        return genreRepository.getAll();
    }

    public LiveData<Genre> findByName(String name) {
        return genreRepository.findByName(name);
    }

    public void delete(Genre genre) {
        genreRepository.delete(genre);
    }
}
