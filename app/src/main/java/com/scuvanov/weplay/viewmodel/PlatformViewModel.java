package com.scuvanov.weplay.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.scuvanov.weplay.entity.Platform;
import com.scuvanov.weplay.repository.PlatformRepository;
import com.scuvanov.weplay.repository.RepositoryFactory;

import java.util.List;

public class PlatformViewModel extends ViewModel {

    private PlatformRepository platformRepository;

    public PlatformViewModel() {
        this.platformRepository = RepositoryFactory.getPlatformRepository();
    }

    public void insertAll(Platform... platforms) {
        platformRepository.insertAll(platforms);
    }

    public void insertPlatform(Platform platform) {
        platformRepository.insertPlatform(platform);
    }

    public LiveData<List<Platform>> getAll() {
        return platformRepository.getAll();
    }

    public LiveData<Platform> findByName(String name) {
        return platformRepository.findByName(name);
    }

    public void delete(Platform platform) {
        platformRepository.delete(platform);
    }
}
