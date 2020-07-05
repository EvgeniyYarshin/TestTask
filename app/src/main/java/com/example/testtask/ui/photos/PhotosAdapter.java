package com.example.testtask.ui.photos;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testtask.R;
import com.example.testtask.data.model.Photo;
import com.example.testtask.databinding.PhotoBinding;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosHolder> {
    @NonNull
    private final List<Photo> mPhotos;

    public PhotosAdapter(@NonNull List<Photo> photos) {
        mPhotos = photos;
    }

    @NonNull
    @Override
    public PhotosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        inflater.inflate(R.layout.li_user, parent, false);
        PhotoBinding binding = PhotoBinding.inflate(inflater, parent, false);

        return new PhotosHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosHolder holder, int position) {
        Photo photo = mPhotos.get(position);
        if(photo != null)
            holder.bind(photo);
    }

    @Override
    public int getItemCount() {
        if(mPhotos == null)
            return 0;
        return mPhotos.size();
    }
}
