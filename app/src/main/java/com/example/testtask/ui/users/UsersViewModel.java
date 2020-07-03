package com.example.testtask.ui.users;

import android.util.Log;

import com.example.testtask.ui.AbstractViewModel;

public class UsersViewModel extends AbstractViewModel {
    private UsersAdapter.OnItemClickListener mOnItemClickListener;

    public UsersViewModel(UsersAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        updateProjects();
    }

    @Override
    protected void updateProjects() {
        //mIsErrorVisible.postValue(false);
        //mIsLoading.postValue(true);


        Log.d("KEK", "updateProjects");
    }

    public UsersAdapter.OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }
}
