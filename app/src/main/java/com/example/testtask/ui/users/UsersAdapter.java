package com.example.testtask.ui.users;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testtask.R;
import com.example.testtask.data.model.User;
import com.example.testtask.databinding.UserBinding;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends PagedListAdapter<User, UsersHolder> {

    @NonNull
    private final OnItemClickListener mOnItemClickListener;

    public UsersAdapter(OnItemClickListener onItemClickListener) {
        super(CALLBACK);
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.li_user, parent, false);
        UserBinding binding = UserBinding.inflate(inflater, parent, false);

        return new UsersHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersHolder holder, int position) {
        User user = getItem(position);
        if(user != null)
            holder.bind(user, mOnItemClickListener);
    }

    public static final DiffUtil.ItemCallback<User> CALLBACK = new DiffUtil.ItemCallback<User>() {
        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.equals(newItem);
        }
    };

    public interface OnItemClickListener {
        void onItemClick(String username);
    }
}