package com.example.testtask.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.testtask.data.model.User;
import com.example.testtask.ui.users.UsersAdapter;

public class CustomBindingAdapter {
    @BindingAdapter({"data", "clickHandler"})
    public static void configureRecyclerView(RecyclerView recyclerView, PagedList<User> projects, UsersAdapter.OnItemClickListener listener) {
        UsersAdapter adapter = new UsersAdapter(listener);
        adapter.submitList(projects);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"refreshState", "onRefresh"})
    public static void configureSwipeRefreshLayout(SwipeRefreshLayout layout, boolean isLoading, SwipeRefreshLayout.OnRefreshListener listener) {
        layout.setOnRefreshListener(listener);
        layout.post(() -> layout.setRefreshing(isLoading));
    }
}
