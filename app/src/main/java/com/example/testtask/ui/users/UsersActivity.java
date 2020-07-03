package com.example.testtask.ui.users;

import androidx.fragment.app.Fragment;

import com.example.testtask.common.SingleFragmentActivity;

public class UsersActivity extends SingleFragmentActivity {

    @Override
    protected Fragment getFragment() {
        return UsersFragment.newInstance();
    }
}
