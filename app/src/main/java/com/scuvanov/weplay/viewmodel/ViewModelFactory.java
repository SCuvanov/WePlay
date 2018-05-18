package com.scuvanov.weplay.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class ViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GenreViewModel.class))
            return (T) new GenreViewModel();
        else if (modelClass.isAssignableFrom(PlatformViewModel.class))
            return (T) new PlatformViewModel();
        else if (modelClass.isAssignableFrom(EsrbViewModel.class))
            return (T) new EsrbViewModel();
        else if (modelClass.isAssignableFrom(GameViewModel.class))
            return (T) new GameViewModel();
        else {
            throw new IllegalArgumentException("ViewModel Not Found");
        }
    }
}
