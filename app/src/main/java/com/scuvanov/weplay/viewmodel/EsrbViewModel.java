package com.scuvanov.weplay.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.scuvanov.weplay.entity.Esrb;
import com.scuvanov.weplay.repository.EsrbRepository;

import java.util.List;

public class EsrbViewModel extends ViewModel {

    private EsrbRepository esrbRepository;

    public EsrbViewModel(EsrbRepository esrbRepository) {
        this.esrbRepository = esrbRepository;
    }

    public void insertAll(Esrb... esrbs) {
        esrbRepository.insertAll(esrbs);
    }

    public void insertEsrb(Esrb genre) {
        esrbRepository.insertEsrb(genre);
    }

    public LiveData<List<Esrb>> getAll() {
        return esrbRepository.getAll();
    }

    public LiveData<Esrb> findByName(String name) {
        return esrbRepository.findByName(name);
    }

    public void delete(Esrb genre) {
        esrbRepository.delete(genre);
    }
}
