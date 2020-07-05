package com.example.testtask.ui.photos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.testtask.R;
import com.example.testtask.databinding.PhotosBinding;
import com.example.testtask.ui.AbstractFragment;
import com.example.testtask.utils.PhotosViewModelCustomFactory;

import java.util.Objects;

public class PhotosFragment extends AbstractFragment {
    public static final String PROFILE_KEY = "PROFILE_KEY";
    private PhotosViewModel mPhotosViewModel;

    static PhotosFragment newInstance(Bundle args) {
        PhotosFragment fragment = new PhotosFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Objects.requireNonNull(getActivity()).setTitle(R.string.photos);
        if (getArguments() != null) {
            int mId = getArguments().getInt(PROFILE_KEY);
            PhotosViewModelCustomFactory factory = new PhotosViewModelCustomFactory(storage, mId);
            mPhotosViewModel = ViewModelProviders.of(this, factory).get(PhotosViewModel.class);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PhotosBinding binding = PhotosBinding.inflate(inflater, container, false);
        binding.setVm(mPhotosViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }
}
