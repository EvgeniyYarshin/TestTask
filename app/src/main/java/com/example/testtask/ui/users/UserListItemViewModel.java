package com.example.testtask.ui.users;

import com.example.testtask.data.model.User;

public class UserListItemViewModel {
    private int mId;
    private String mName;

    UserListItemViewModel(User user) {
        mId = user.getId();
        mName = user.getName();
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }
}
