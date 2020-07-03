package com.example.testtask.ui.photos;

import androidx.fragment.app.Fragment;

import com.example.testtask.common.SingleFragmentActivity;

public class PhotosActivity extends SingleFragmentActivity {
    public static final String USERNAME_KEY = "USERNAME_KEY";

    @Override
    protected Fragment getFragment() {
        return null;
        /*if (getIntent() != null) {
            return PhotosFragment.newInstance(getIntent().getBundleExtra(USERNAME_KEY));
        }
        throw new IllegalStateException("getIntent cannot be null");*/
    }
}
