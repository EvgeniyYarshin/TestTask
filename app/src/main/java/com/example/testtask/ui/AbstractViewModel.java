package com.example.testtask.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public abstract class AbstractViewModel extends ViewModel {
    protected MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    protected MutableLiveData<Boolean> mIsErrorVisible = new MutableLiveData<>();
    protected SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = this::updateData;

    abstract protected void updateData();

    public MutableLiveData<Boolean> getIsLoading() {
        return mIsLoading;
    }

    public MutableLiveData<Boolean> getIsErrorVisible() {
        return mIsErrorVisible;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }

}
