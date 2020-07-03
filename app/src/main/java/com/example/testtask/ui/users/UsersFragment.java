package com.example.testtask.ui.users;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testtask.R;
import com.example.testtask.common.RefreshOwner;
import com.example.testtask.common.Refreshable;
import com.example.testtask.data.api.JsonPlaceHolderAPI;
import com.example.testtask.data.model.User;
import com.example.testtask.databinding.UserBinding;
import com.example.testtask.databinding.UsersBinding;
import com.example.testtask.ui.photos.PhotosActivity;
import com.example.testtask.ui.photos.PhotosFragment;
import com.example.testtask.utils.CustomFactory;

import java.util.List;

public class UsersFragment extends Fragment  {

    private UsersViewModel mUsersViewModel;
    private UsersAdapter.OnItemClickListener mOnItemClickListener = username -> {
        /*Intent intent = new Intent(getActivity(), ProfileActivity.class);
        Bundle args = new Bundle();
        args.putString(ProfileFragment.PROFILE_KEY, username);
        intent.putExtra(ProfileActivity.USERNAME_KEY, args);
        startActivity(intent);*/
    };

    public static UsersFragment newInstance() {
        return new UsersFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //CustomFactory factory = new CustomFactory(mOnItemClickListener);
        mUsersViewModel = new UsersViewModel(mOnItemClickListener);
        //mUsersViewModel = ViewModelProviders.of(this, factory).get(UsersViewModel.class);
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
