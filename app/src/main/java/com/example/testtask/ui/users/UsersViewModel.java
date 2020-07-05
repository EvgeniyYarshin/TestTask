package com.example.testtask.ui.users;

import androidx.lifecycle.MutableLiveData;

import com.example.testtask.BuildConfig;
import com.example.testtask.data.Storage;
import com.example.testtask.data.api.JsonPlaceHolderAPI;
import com.example.testtask.data.model.User;
import com.example.testtask.ui.AbstractViewModel;

import java.util.List;

public class UsersViewModel extends AbstractViewModel {
    private UsersAdapter.OnItemClickListener mOnItemClickListener;
    private MutableLiveData<List<User>> mUsers = new MutableLiveData<>();
    private JsonPlaceHolderAPI taskAsync;
    private Storage mStorage;

    public UsersViewModel(Storage storage, UsersAdapter.OnItemClickListener onItemClickListener) {
        mStorage = storage;
        mOnItemClickListener = onItemClickListener;

        updateData();
    }

    public MutableLiveData<List<User>> getUsers() {
        return mUsers;
    }

    @Override
    protected void updateData() {
        mIsErrorVisible.setValue(false);
        mIsLoading.setValue(true);
        taskAsync = new JsonPlaceHolderAPI(output -> {
            if(output == null) {
                mIsErrorVisible.setValue(true);
                mIsLoading.setValue(false);
                return;
            }

            mUsers.setValue(mStorage.getUsers(output));
            mIsLoading.setValue(false);
        });
        taskAsync.execute(BuildConfig.API_URL + BuildConfig.API_QUERY_USERS);
    }

    @Override
    protected void onCleared() {
        taskAsync.cancel(false);
    }

    public UsersAdapter.OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }
}
