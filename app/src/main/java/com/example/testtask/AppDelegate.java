package com.example.testtask;

import android.app.Application;

import com.example.testtask.data.Storage;

public class AppDelegate extends Application {

    private Storage mStorage;
    @Override
    public void onCreate() {
        super.onCreate();
        mStorage = new Storage();
    }
    public Storage getStorage() {
        return mStorage;
    }
}
