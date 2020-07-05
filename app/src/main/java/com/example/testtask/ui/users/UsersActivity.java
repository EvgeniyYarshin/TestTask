package com.example.testtask.ui.users;

import androidx.fragment.app.Fragment;

import com.example.testtask.AppDelegate;
import com.example.testtask.common.SingleFragmentActivity;
import com.example.testtask.data.Storage;

public class UsersActivity extends SingleFragmentActivity implements Storage.StorageOwner {

    @Override
    protected Fragment getFragment() {
        return UsersFragment.newInstance();
    }

    @Override
    public Storage obtainStorage() {
        return ((AppDelegate) getApplicationContext()).getStorage();
    }
}
