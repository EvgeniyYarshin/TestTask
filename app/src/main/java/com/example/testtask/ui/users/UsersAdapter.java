package com.example.testtask.ui.users;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testtask.R;
import com.example.testtask.data.model.User;
import com.example.testtask.databinding.UserBinding;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersHolder> {

    @NonNull
    private final List<User> mUsers;
    private final OnItemClickListener mOnItemClickListener;

    public UsersAdapter(@NonNull List<User> users, OnItemClickListener onItemClickListener) {
        mUsers = users;
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        inflater.inflate(R.layout.li_user, parent, false);
        UserBinding binding = UserBinding.inflate(inflater, parent, false);

        return new UsersHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersHolder holder, int position) {
        User user = mUsers.get(position);
        if(user != null)
            holder.bind(user, mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        if(mUsers == null)
            return 0;
        return mUsers.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int id);
    }
}