package com.example.testtask.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.testtask.ui.photos.PhotosViewModel;


public class PhotosViewModelCustomFactory extends ViewModelProvider.NewInstanceFactory {

    private int mId;

    public PhotosViewModelCustomFactory(int id) {
        mId = id;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PhotosViewModel(mId);
    }
}
