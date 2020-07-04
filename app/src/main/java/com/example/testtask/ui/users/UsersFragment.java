package com.example.testtask.ui.users;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.testtask.R;
import com.example.testtask.databinding.UsersBinding;
import com.example.testtask.ui.photos.PhotosActivity;
import com.example.testtask.ui.photos.PhotosFragment;
import com.example.testtask.utils.UsersViewModelCustomFactory;

import java.util.Objects;

public class UsersFragment extends Fragment  {

    private UsersViewModel mUsersViewModel;
    private UsersAdapter.OnItemClickListener mOnItemClickListener = id -> {
        Intent intent = new Intent(getActivity(), PhotosActivity.class);
        Bundle args = new Bundle();
        args.putInt(PhotosFragment.PROFILE_KEY, id);
        intent.putExtra(PhotosActivity.USERNAME_KEY, args);
        startActivity(intent);
    };

    public static UsersFragment newInstance() {
        return new UsersFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Objects.requireNonNull(getActivity()).setTitle(R.string.users);
        UsersViewModelCustomFactory factory = new UsersViewModelCustomFactory(mOnItemClickListener);
        mUsersViewModel = ViewModelProviders.of(this, factory).get(UsersViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UsersBinding binding = UsersBinding.inflate(inflater, container, false);
        binding.setVm(mUsersViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }
}
