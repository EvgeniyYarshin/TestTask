package com.example.testtask.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.testtask.AppDelegate;
import com.example.testtask.data.Storage;
import com.example.testtask.data.model.Photo;
import com.example.testtask.data.model.User;
import com.example.testtask.ui.photos.PhotosAdapter;
import com.example.testtask.ui.users.UsersAdapter;

import java.util.List;

public class CustomBindingAdapter {
    @BindingAdapter({"imageUrl", "progressBar"})
    public static void loadImage(ImageView imageView, String urlImage, ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(null);
        imageView.setTag(urlImage);
        Storage storage = ((AppDelegate) imageView.getContext().getApplicationContext()).getStorage();
        storage.getImage(urlImage, bitmap -> {
            if (urlImage == imageView.getTag()) {
                imageView.setImageBitmap(bitmap);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @BindingAdapter({"photosData"})
    public static void configureRecyclerView(RecyclerView recyclerView, List<Photo> photos) {
        PhotosAdapter adapter = new PhotosAdapter(photos);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"usersData", "clickHandler"})
    public static void configureRecyclerView(RecyclerView recyclerView, List<User> users, UsersAdapter.OnItemClickListener listener) {
        UsersAdapter adapter = new UsersAdapter(users, listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"refreshState", "onRefresh"})
    public static void configureSwipeRefreshLayout(SwipeRefreshLayout layout, boolean isLoading, SwipeRefreshLayout.OnRefreshListener listener) {
        layout.setOnRefreshListener(listener);
        layout.post(() -> layout.setRefreshing(isLoading));
    }
}
