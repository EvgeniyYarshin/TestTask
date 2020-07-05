package com.example.testtask.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.testtask.data.Storage;
import com.example.testtask.ui.users.UsersAdapter;
import com.example.testtask.ui.users.UsersViewModel;

public class UsersViewModelCustomFactory extends ViewModelProvider.NewInstanceFactory {

    private Storage mStorage;
    private UsersAdapter.OnItemClickListener mOnItemClickListener;

    public UsersViewModelCustomFactory(Storage storage, UsersAdapter.OnItemClickListener onItemClickListener) {
        mStorage = storage;
        mOnItemClickListener = onItemClickListener;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UsersViewModel(mStorage, mOnItemClickListener);
    }
}