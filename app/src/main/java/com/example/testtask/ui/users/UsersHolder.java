package com.example.testtask.ui.users;

import androidx.recyclerview.widget.RecyclerView;
import com.example.testtask.data.model.User;
import com.example.testtask.databinding.UserBinding;

public class UsersHolder extends RecyclerView.ViewHolder {

    private UserBinding mUserBinding;

    public UsersHolder(UserBinding binding) {
        super(binding.getRoot());
        mUserBinding = binding;
    }

    public void bind(User item, UsersAdapter.OnItemClickListener onItemClickListener) {
        mUserBinding.setUser(new UserListItemViewModel(item));
        mUserBinding.setOnItemClickListener(onItemClickListener);
        mUserBinding.executePendingBindings();
    }
}