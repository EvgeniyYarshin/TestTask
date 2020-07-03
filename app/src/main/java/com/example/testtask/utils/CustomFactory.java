package com.example.testtask.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.testtask.ui.users.UsersAdapter;
import com.example.testtask.ui.users.UsersViewModel;

public class CustomFactory extends ViewModelProvider.NewInstanceFactory {

    private UsersAdapter.OnItemClickListener mOnItemClickListener;

    public CustomFactory(UsersAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UsersViewModel(mOnItemClickListener);
    }
}
