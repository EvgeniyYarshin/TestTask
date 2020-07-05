package com.example.testtask.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.testtask.data.Storage;
import com.example.testtask.ui.photos.PhotosViewModel;


public class PhotosViewModelCustomFactory extends ViewModelProvider.NewInstanceFactory {

    private int mId;
    private Storage mStorage;

    public PhotosViewModelCustomFactory(Storage storage, int id) {
        mStorage = storage;
        mId = id;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PhotosViewModel(mStorage, mId);
    }
}
