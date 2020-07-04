package com.example.testtask.ui.users;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.example.testtask.BuildConfig;
import com.example.testtask.data.api.JsonPlaceHolderAPI;
import com.example.testtask.data.model.User;
import com.example.testtask.ui.AbstractViewModel;
import com.example.testtask.utils.JSONParser;

import org.json.JSONArray;

import java.util.List;

public class UsersViewModel extends AbstractViewModel {
    private UsersAdapter.OnItemClickListener mOnItemClickListener;
    private MutableLiveData<List<User>> mUsers = new MutableLiveData<>();

    public UsersViewModel(UsersAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        updateData();
    }

    public MutableLiveData<List<User>> getUsers() {
        return mUsers;
    }

    @Override
    protected void updateData() {
        mIsLoading.setValue(true);
        new AsyncTask<Void,Void,List<User>>() {
            @Override
            protected List<User> doInBackground(Void... voids) {
                JSONArray array = JsonPlaceHolderAPI.loadData(BuildConfig.API_URL + BuildConfig.API_QUERY_USERS);

                List<User> users = JSONParser.getJSONUsers(array);
                return users;
            }
            @Override
            protected void onPostExecute(List<User> data) {
                mUsers.setValue(data);
                mIsLoading.setValue(false);
            }
        }.execute();
    }

    @Override
    protected void onCleared() {
        //TODO clean up resources
    }

    public UsersAdapter.OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }
}
