package com.example.testtask.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.testtask.data.model.User;

public abstract class AbstractViewModel extends ViewModel {
    protected MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    protected MutableLiveData<Boolean> mIsErrorVisible = new MutableLiveData<>();
    protected LiveData<PagedList<User>> mUsers = new MutableLiveData<>();
    protected SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = this::updateProjects;

    abstract protected void updateProjects();

    public MutableLiveData<Boolean> getIsLoading() {
        return mIsLoading;
    }

    public MutableLiveData<Boolean> getIsErrorVisible() {
        return mIsErrorVisible;
    }

    public LiveData<PagedList<User>> getUsers() {
        return mUsers;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }

}
