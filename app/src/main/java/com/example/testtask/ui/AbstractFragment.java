package com.example.testtask.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.testtask.data.Storage;

public abstract class AbstractFragment extends Fragment {

    protected Storage storage;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Storage.StorageOwner) {
            storage = ((Storage.StorageOwner) context).obtainStorage();
        }
    }
}
