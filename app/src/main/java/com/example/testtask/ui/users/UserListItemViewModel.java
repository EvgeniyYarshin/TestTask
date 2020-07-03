package com.example.testtask.ui.users;

import android.widget.TextView;

import com.example.testtask.data.model.User;
import com.example.testtask.ui.AbstractViewModel;

public class UserListItemViewModel {
    private String mName;
    private String mUsername;

    public UserListItemViewModel(User user) {
        mName = user.getName();
        mUsername = user.getUsername();
    }

    public String getName() {
        return mName;
    }

    public String getUsername() {
        return mUsername;
    }

}
